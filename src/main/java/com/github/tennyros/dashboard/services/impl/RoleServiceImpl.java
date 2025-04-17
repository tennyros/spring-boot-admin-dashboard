package com.github.tennyros.dashboard.services.impl;

import com.github.tennyros.dashboard.models.Role;
import com.github.tennyros.dashboard.repositories.RoleRepository;
import com.github.tennyros.dashboard.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getAllRoles() {
        return roleRepository.getAllRoles();
    }
}
