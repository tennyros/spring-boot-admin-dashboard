package com.github.tennyros.rest.services;

import com.github.tennyros.rest.dtos.UserRequestDto;
import com.github.tennyros.rest.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    Optional<User> getUserByEmail(String email);

    void mapAndUpdateRoles(UserRequestDto userRequestDto, User userForUpdate);
    void mapRolesForNewUser(UserRequestDto userRequestDto, User newUser);
}
