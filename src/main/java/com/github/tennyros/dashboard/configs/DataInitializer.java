package com.github.tennyros.dashboard.configs;

import com.github.tennyros.dashboard.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.github.tennyros.dashboard.exceptions.RoleNotFoundException;
import com.github.tennyros.dashboard.models.Role;
import com.github.tennyros.dashboard.models.User;
import com.github.tennyros.dashboard.services.RoleService;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    @Value("${admin.first_name}")
    private String adminFirstName;

    @Value("${admin.last_name}")
    private String adminLastName;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.age}")
    private Integer adminAge;

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        createRoles("ROLE_USER");
        createRoles("ROLE_ADMIN");
        createAdmin(adminFirstName, adminLastName, adminPassword, adminEmail, adminAge);
    }

    private void createRoles(String roleName) {
        if (roleService.getRoleByName(roleName).isEmpty()) {
            roleService.addRole(new Role(roleName));
        }
    }

    private void createAdmin(String firstName, String lastName, String password, String email, Integer age) {
        if (userService.getUserByEmail(email).isEmpty()) {
            User admin = User.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .password(passwordEncoder.encode(password))
                    .email(email)
                    .age(age)
                    .roles(Set.of(roleService.getRoleByName("ROLE_ADMIN").orElseThrow(RoleNotFoundException::new),
                            roleService.getRoleByName("ROLE_USER").orElseThrow(RoleNotFoundException::new)))
                    .admin(true)
                    .build();
            userService.addUser(admin);
        }
    }
}
