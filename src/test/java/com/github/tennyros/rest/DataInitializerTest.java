package com.github.tennyros.rest;

import com.github.tennyros.rest.configs.DataInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.github.tennyros.rest.repositories.RoleRepository;
import com.github.tennyros.rest.repositories.UserRepository;

import static org.mockito.Mockito.when;

class DataInitializerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private DataInitializer dataInitializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRun_createsRolesAndAdminOnce() throws Exception {
        when(roleRepository.findByRoleName("ADMIN")).thenReturn(null);
        when(roleRepository.findByRoleName("USER")).thenReturn(null);
    }
}
