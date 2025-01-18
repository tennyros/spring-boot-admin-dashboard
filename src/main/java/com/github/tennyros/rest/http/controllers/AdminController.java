package com.github.tennyros.rest.http.controllers;

import com.github.tennyros.rest.models.User;
import com.github.tennyros.rest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.github.tennyros.rest.dtos.UserRequestDto;
import com.github.tennyros.rest.services.RoleService;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping(value = "/admin")
    public String adminFullInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("userRequestDto", new UserRequestDto());
        model.addAttribute("roles", roleService.getAllRoles());
        return "/main";
    }
}