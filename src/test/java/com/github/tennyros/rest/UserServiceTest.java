//package ru.kata.spring.boot;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import ru.kata.spring.boot.exceptions.RoleNotFoundException;
//import ru.kata.spring.boot.models.Role;
//import ru.kata.spring.boot.models.User;
//import ru.kata.spring.boot.repositories.UserRepository;
//import ru.kata.spring.boot.services.RoleService;
//import ru.kata.spring.boot.services.impl.UserServiceImpl;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//
//    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @BeforeEach
//    public void setUp() {
//       MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetUserByEmail_UserExists() {
//        Long userId = 1L;
//        String firstName = "firstName";
//        String lastName = "lastName";
//        String password = "password";
//        int age = 20;
//        String email = "admin@mail.com";
//
//        User user = new User();
//        user.setId(userId);
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setPassword(password);
//        user.setAge(age);
//        user.setEmail(email);
//        log.info("User: {}", user);
//        System.out.println(user);
//        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
//
//        Optional<User> foundUser = userService.getUserByEmail(email);
//
//        assertTrue(foundUser.isPresent());
//        assertEquals(email, foundUser.get().getUsername());
//    }
//}
