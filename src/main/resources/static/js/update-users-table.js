function fetchAndUpdateUserTable() {
    fetch('/api/v1/admin/users')
        .then(response => response.json())
        .then(users => {
            updateUserTable(users);
        })
        .catch(error => {
            console.error('Error fetching users:', error);
        });
}

function updateUserTable(users) {
    const tbody = document.querySelector('#users-table-body');
    tbody.innerHTML = '';

    users.forEach(user => {
        const row = document.createElement('tr');
        row.innerHTML = `<td>${user.id}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.age}</td>
                        <td>${user.roles.map(role => role.roleName).join(' ').replace(/ROLE_/g, '')}</td>
                        <td>
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#userEditModal"
                                    data-bs-id="${user.id}" data-bs-firstname="${user.firstName}"
                                    data-bs-lastname="${user.lastName}" data-bs-age="${user.age}"
                                    data-bs-email="${user.email}">Edit</button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger delete-button" data-user-id="${user.id}">Delete</button>
                        </td>`;
        tbody.appendChild(row);
    });

    document.querySelectorAll('.delete-button').forEach(button => {
        button.addEventListener('click', function() {
            const userId = this.getAttribute('data-user-id');
            console.log('userId=' + userId);
            deleteUser(userId);
        });
    });
}