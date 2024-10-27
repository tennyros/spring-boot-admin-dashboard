document.querySelector('.add-user-button').addEventListener("click", async function() {
    const form = document.querySelector('.add-user-form');
    clearErrors(form);
    await handleFormSubmission(form, (form) => addUser(form));
});

async function addUser(form) {
    const formData = {
        firstName: form.querySelector('.user-firstname').value,
        lastName: form.querySelector('.user-lastname').value,
        age: form.querySelector('.user-age').value,
        email: form.querySelector('.user-email').value,
        password: form.querySelector('.user-password').value,
        passwordConfirm: form.querySelector('.user-passwordConfirm').value,
        roles: getSelectedRoles(form)
    };

    if (formData.roles.length === 0) {
        form.querySelector('#roles-error').style.display = 'block';
        return;
    } else {
        form.querySelector('#roles-error').style.display = 'none';
    }

    try {
        const response = await fetch('/api/v1/admin/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });
        if (!response.ok) {
            const errors = await response.json();
            handleValidationErrors(errors, form);
            form.querySelector('.user-password').value = '';
            form.querySelector('.user-passwordConfirm').value = '';
            throw new Error('Validation or server error!');
        }

        form.querySelector('.user-password').value = '';
        form.querySelector('.user-passwordConfirm').value = '';

        const data = await response.json();
        clearForm(form);
        clearErrors(form);
        console.log('User creation success:', data);
        // alert('User created successfully!');
        await fetchAndUpdateUsersTable();
        document.querySelector('#admin-view-tab').click();
    } catch (error) {
        console.error('Error creating user:', error);
    }
}