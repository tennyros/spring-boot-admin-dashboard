document.querySelectorAll('.password-confirm-input').forEach(function(passwordConfirmElement) {
    passwordConfirmElement.addEventListener('input', function() {
        const form = this.closest('form');
        const password = form.querySelector('.password-input').value;
        const confirmPassword = this.value;
        const errorMessage = form.querySelector('.password-error');

        if (confirmPassword !== password) {
            errorMessage.style.display = 'block';
            errorMessage.textContent = 'Passwords do not match!';
        } else {
            errorMessage.style.display = 'none';
        }
    });
});