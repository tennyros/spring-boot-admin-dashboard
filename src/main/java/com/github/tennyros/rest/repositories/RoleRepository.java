package com.github.tennyros.rest.repositories;

import com.github.tennyros.rest.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
    @Query("SELECT r FROM Role r")
    Set<Role> getAllRoles();
}
