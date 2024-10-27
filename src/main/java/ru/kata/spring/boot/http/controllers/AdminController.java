package ru.kata.spring.boot.http.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot.dtos.UserRequestDto;
import ru.kata.spring.boot.models.User;
import ru.kata.spring.boot.services.RoleService;
import ru.kata.spring.boot.services.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
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
        return "/admin/main";
    }
}