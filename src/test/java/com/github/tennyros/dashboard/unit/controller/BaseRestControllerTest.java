package com.github.tennyros.dashboard.unit.controller;

import com.github.tennyros.dashboard.configs.SuccessUserHandler;
import com.github.tennyros.dashboard.dtos.UserResponseDto;
import com.github.tennyros.dashboard.exceptions.UserNotFoundException;
import com.github.tennyros.dashboard.http.rest.BaseRestController;
import com.github.tennyros.dashboard.mappers.UserMapper;
import com.github.tennyros.dashboard.models.User;
import com.github.tennyros.dashboard.services.AppUserDetailsService;
import com.github.tennyros.dashboard.services.RegistrationService;
import com.github.tennyros.dashboard.services.UserService;
import com.github.tennyros.dashboard.utils.UserValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BaseRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class BaseRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private SuccessUserHandler successUserHandler;

    @MockBean
    private AppUserDetailsService appUserDetailsService;

    @MockBean
    private UserValidator userValidator;

    @MockBean
    private RegistrationService registrationService;

    @Test
    void getUsers_shouldReturnOkWithBody() throws Exception {
        User user = new User();
        UserResponseDto dto = new UserResponseDto();

        given(userService.getAllUsers()).willReturn(List.of(user));
        given(userMapper.toResponseDto(user)).willReturn(dto);

        mockMvc.perform(get("/api/v1/admin/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void getUsers_shouldReturnNoContentIfEmpty() throws Exception {
        given(userService.getAllUsers()).willReturn(List.of());

        mockMvc.perform(get("/api/v1/admin/users"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getUser_shouldReturnOk() throws Exception {
        User user = new User();
        UserResponseDto dto = new UserResponseDto();

        given(userService.getUserById(anyLong())).willReturn(Optional.of(user));
        given(userMapper.toResponseDto(user)).willReturn(dto);

        mockMvc.perform(get("/api/v1/admin/users/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void getUser_shouldThrowException() throws Exception {
        given(userService.getUserById(anyLong())).willThrow(UserNotFoundException.class);

        mockMvc.perform(get("/api/v1/admin/users/{id}", 1L))
                .andExpect(status().isNotFound());
    }

}
