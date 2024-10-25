document.querySelector('#addUserButton').addEventListener("click", function() {
    const form = document.querySelector('#user-form');
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    addUser();
});

function addUser() {
    const formData = {
        firstName: document.querySelector('.user-firstname').value,
        lastName: document.querySelector('.user-lastname').value,
        age: document.querySelector('.user-age').value,
        email: document.querySelector('.user-email').value,
        password: document.querySelector('.user-password').value,
        passwordConfirm: document.querySelector('.user-passwordConfirm').value,
        roles: getSelectedRoles()
    };

    if (formData.roles.length === 0) {
        document.querySelector('#roles-error').style.display = 'block';
        return;
    } else {
        document.querySelector('#roles-error').style.display = 'none';
    }

    fetch('/api/v1/admin/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(errors => {
                    handleValidationErrors(errors);
                    throw new Error('Validation or server error!')
                });
            }
            return response.json();
        })
        .then(data => {
            console.log('User creation success:', data);
            // alert('User created successfully!');
            clearForm();
            fetchAndUpdateUserTable();
            document.querySelector('#admin-view-tab').click();
        })
        .catch(error => {
            console.error('Error creating user:', error)
        });
}

function handleValidationErrors(errors) {
    clearErrors();

    for (let field in errors.fieldErrors) {
        const errorDiv = document.querySelector(`#${field}-error`);
        if (errorDiv) {
            errorDiv.style.display = 'block';
            errorDiv.textContent = errors.fieldErrors[field];
        }
    }
}

function getSelectedRoles() {
    const selectedOptions = document.querySelectorAll('.roles-select option:checked');
    return Array.from(selectedOptions).map(option => {
        const roleShortName = option.textContent;
        return roleShortName === 'ADMIN' ? { roleName: 'ROLE_ADMIN' } : { roleName: 'ROLE_USER' };
    });
}

function clearForm() {
    document.querySelector('#user-form').reset();
}

function clearErrors() {
    document.querySelectorAll('.error-message').forEach(errorDiv => {
        errorDiv.style.display = 'none';
        errorDiv.textContent = '';
    })
}