package com.github.tennyros.dashboard.services;

import com.github.tennyros.dashboard.models.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleService {

    void addRole(Role role);
    Optional<Role> getRoleByName(String roleName);
    Set<Role> getAllRoles();
}
