function populateEditUserForm(event) {
    const button = event.relatedTarget;
    const userId = button.getAttribute('data-bs-id');
    const userFirstName = button.getAttribute('data-bs-firstname');
    const userLastName = button.getAttribute('data-bs-lastname');
    const userAge = button.getAttribute('data-bs-age');
    const userEmail = button.getAttribute('data-bs-email');

    const modalIdInput = document.querySelector('#user-id');
    const hiddenIdInput = document.querySelector('#id');
    const modalFirstNameInput = document.querySelector('.user-firstname');
    const modalLastNameInput = document.querySelector('.user-lastname');
    const modalAgeInput = document.querySelector('.user-age');
    const modalEmailInput = document.querySelector('.user-email');

    modalIdInput.value = userId;
    hiddenIdInput.value = userId;
    modalFirstNameInput.value = userFirstName;
    modalLastNameInput.value = userLastName;
    modalAgeInput.value = userAge;
    modalEmailInput.value = userEmail;
}

const userEditModal = document.querySelector('#userEditModal');
userEditModal.addEventListener('show.bs.modal', populateEditUserForm)

userEditModal.addEventListener('hidden.bs.modal', function () {
    const form = userEditModal.querySelector('.edit-user-form');
    clearErrors(form);
    form.reset();
});

document.querySelector('.edit-user-button').addEventListener('click', async function () {
    const form = document.querySelector('.edit-user-form');
    clearErrors(form);
    await handleFormSubmission(form, editUser);
});

document.querySelector('.edit-user-button').addEventListener('click', async function () {
    const form = document.querySelector('.edit-user-form');
    clearErrors(form);
    await handleFormSubmission(form, editUser);
});

async function editUser(form) {
    const userId = form.querySelector('#user-id').value;
    const formData = {
        id: userId,
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
        const response = await fetch(`/api/v1/admin/users/${userId}`, {
            method: 'PUT',
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

        const updatedUser = await response.json();
        console.log('User updated successfully:', updatedUser);
        await fetchAndUpdateUsersTable();

        const userEditModal = document.querySelector('#userEditModal');

        userEditModal.classList.remove('show');
        document.querySelector('.modal-backdrop').remove();

        form.querySelector('.user-password').value = '';
        form.querySelector('.user-passwordConfirm').value = '';

        clearErrors(form);
    } catch (error) {
        console.error('Error updating user:', error);
    }
}