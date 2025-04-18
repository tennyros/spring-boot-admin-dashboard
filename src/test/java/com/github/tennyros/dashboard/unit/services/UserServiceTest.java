package com.github.tennyros.dashboard.unit.services;

import com.github.tennyros.dashboard.models.Role;
import com.github.tennyros.dashboard.models.User;
import com.github.tennyros.dashboard.repositories.UserRepository;
import com.github.tennyros.dashboard.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void addUser_shouldSetAdminRole_whenUserHasAdminRole() {
        User user = new User();
        Role role = new Role("ROLE_ADMIN");

        user.setRoles(Collections.singleton(role));

        userService.addUser(user);

        assertTrue(user.isAdmin());
        verify(userRepository).save(user);
    }

    @Test
    void addUser_shouldSaveUser_whenNoAdminRole() {
        User user = new User();
        Role role = new Role("ROLE_USER");

        user.setRoles(Collections.singleton(role));

        userService.addUser(user);

        assertFalse(user.isAdmin());
        verify(userRepository).save(user);
    }

    @Test
    void updateUser_shouldEncodePassword_whenSaveUser() {
        User user = User.builder()
                .roles(Collections.singleton(new Role("ROLE_USER")))
                .build();

        String encodedPassword = "encodedPassword";

        when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);

        userService.updateUser(user);

        assertEquals(encodedPassword, user.getPassword());
        verify(userRepository).save(user);
    }

    @Test
    void deleteUser_shouldDeleteUser_whenIdIsNot1() {
        Long userId = 2L;

        userService.deleteUser(userId);

        verify(userRepository).deleteById(userId);
    }

    @Test
    void deleteUser_shouldThrowException_whenIdIs1() {
        Long superAdminId = 1L;

        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class,
                () -> userService.deleteUser(superAdminId));

        assertEquals("You can not delete super administrator!", exception.getMessage());
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserById(userId);

        assertTrue(foundUser.isPresent());
        assertEquals(userId, foundUser.get().getId());
    }

    @Test
    void getAllUsers_shouldReturnAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAllUsersWithRole()).thenReturn(users);

        List<User> allUsers = userService.getAllUsers();

        assertNotNull(allUsers);
        assertEquals(2, allUsers.size());
    }

    @Test
    void getUserByEmail_shouldReturnUser_whenUserExists() {
        String email = "admin@mail.com";

        User user = User.builder()
                .email(email)
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserByEmail(email);

        assertTrue(foundUser.isPresent());
        assertEquals(email, foundUser.get().getUsername());
    }
}
