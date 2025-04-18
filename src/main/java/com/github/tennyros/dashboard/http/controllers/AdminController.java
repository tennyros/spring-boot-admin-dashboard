package com.github.tennyros.dashboard.http.controllers;

import com.github.tennyros.dashboard.models.User;
import com.github.tennyros.dashboard.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.github.tennyros.dashboard.dtos.UserRequestDto;
import com.github.tennyros.dashboard.services.RoleService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping(value = "admin")
    public String adminFullInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("userRequestDto", new UserRequestDto());
        model.addAttribute("roles", roleService.getAllRoles());
        return "main";
    }
}