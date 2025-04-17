package com.github.tennyros.dashboard;

import com.github.tennyros.dashboard.configs.DataInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.github.tennyros.dashboard.repositories.RoleRepository;
import com.github.tennyros.dashboard.repositories.UserRepository;

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
