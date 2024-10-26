document.querySelector('#editUserButton').addEventListener('click', async (event) => {
    event.preventDefault();
    const form = document.querySelector('.user-form');
    const userId = form.querySelector('#user-id').value;
    const formData = new FormData(form);

    try {
        const response = await fetch(`/api/v1/admin/users/${userId}`, { // URL для обновления пользователя
            method: 'PUT',
            body: JSON.stringify(Object.fromEntries(formData)),
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Error updating user');
        }

        const result = await response.text();
        alert(result);
        await fetchAndUpdateUsersTable();

    } catch (error) {
        console.error('Error:', error);
        alert('Failed to update user. Please try again later!');
    }
});