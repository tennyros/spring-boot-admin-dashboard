async function handleFormSubmission(form, submitAction) {
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    await submitAction(form);
}

function handleValidationErrors(errors, form) {
    clearErrors(form);

    for (let field in errors.fieldErrors) {
        const errorDiv = form.querySelector(`#${field}-error`);
        if (errorDiv) {
            errorDiv.style.display = 'block';
            errorDiv.textContent = errors.fieldErrors[field];
        }
    }
}

function getSelectedRoles(form) {
    const selectedOptions = form.querySelectorAll('.roles-select option:checked');
    return Array.from(selectedOptions).map(option => {
        const roleShortName = option.textContent;
        return roleShortName === 'ADMIN' ? { roleName: 'ROLE_ADMIN' } : { roleName: 'ROLE_USER' };
    });
}

function clearForm(form) {
    form.reset();
    const fields = form.querySelectorAll('input, textarea');
    fields.forEach(field => field.value = '');
}

function clearErrors(form) {
    form.querySelectorAll('.alert.alert-danger').forEach(errorDiv => {
        errorDiv.style.display = 'none';
        errorDiv.textContent = '';
    })
}