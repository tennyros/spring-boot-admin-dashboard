document.querySelectorAll('.delete-button').forEach(button => {
    button.addEventListener('click', function() {
        const userId = this.getAttribute('data-user-id');
        deleteUser(userId);
    });
});

function deleteUser(userId) {
    if (!confirm('Are you sure you want to delete this user?')) {
        return;
    }

    fetch(`/api/v1/admin/users/${userId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to delete user');
            }
            if (response.status === 204) {
                alert('User deleted successfully!')
                fetchAndUpdateUserTable();
            } else {
                return response.json()
            }
        })
        .catch(error => {
            console.error('Error deleting user:', error);
            alert('Failed to delete user. Please try again later!');
        });
}