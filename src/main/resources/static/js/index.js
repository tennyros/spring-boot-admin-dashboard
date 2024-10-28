import {addUser} from './add-user';
import {deleteUser} from "./delete-user";
import './password-check';
import {editUser, populateEditUserForm} from './update-user';
import {clearErrors, handleFormSubmission} from './utils';

document.querySelector('.add-user-button').addEventListener("click", async function() {
    const form = document.querySelector('.add-user-form');
    clearErrors(form);
    await handleFormSubmission(form, (form) => addUser(form));
});

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

document.querySelectorAll('.delete-button').forEach(button => {
    button.addEventListener('click', async function() {
        const userId = this.getAttribute('data-user-id');
        await deleteUser(userId);
    });
});