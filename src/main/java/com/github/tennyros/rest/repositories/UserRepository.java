package com.github.tennyros.rest.repositories;

import com.github.tennyros.rest.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "roles")
    Optional<User> findByEmail(String email);
    @EntityGraph(attributePaths = "roles")
    @Query("SELECT u FROM User u")
    List<User> findAllUsersWithRole();
}
