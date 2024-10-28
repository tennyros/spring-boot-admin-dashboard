import {fetchAndUpdateUsersTable} from "./update-users-table";

export async function deleteUser(userId) {
    if (!confirm('Are you sure you want to delete this user?')) {
        return;
    }
    try {
        const response = await fetch(`/api/v1/admin/users/${userId}`, {
            method: 'DELETE'
        });
        if (!response.ok) {
            throw new Error('Failed to delete user!');
        }
        if (response.status === 204) {
            // alert('User deleted successfully!');
            await fetchAndUpdateUsersTable();
        } else {
            const errorData = await response.json();
            console.error('Server responded with error:', errorData);
        }
    } catch(error) {
        console.error('Error deleting user:', error);
        alert('Failed to delete user!');
    }
}