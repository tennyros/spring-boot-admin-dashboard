package com.github.tennyros.rest.services;

import com.github.tennyros.rest.models.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleService {

    void addRole(Role role);
    Optional<Role> getRoleByName(String roleName);
    Set<Role> getAllRoles();
}
