package com.github.tennyros.dashboard;

import com.github.tennyros.dashboard.models.User;
import com.github.tennyros.dashboard.repositories.UserRepository;
import com.github.tennyros.dashboard.services.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testGetUserByEmail_UserExists() {

        String email = "admin@mail.com";

        User user = User.builder()
                .id(1L)
                .firstName("firstName")
                .lastName("lastName")
                .password("password")
                .age(20)
                .email(email)
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserByEmail(email);

        assertTrue(foundUser.isPresent());
        assertEquals(email, foundUser.get().getUsername());
    }
}
