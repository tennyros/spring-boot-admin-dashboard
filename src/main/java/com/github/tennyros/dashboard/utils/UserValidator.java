package com.github.tennyros.dashboard.utils;

import com.github.tennyros.dashboard.dtos.UserRequestDto;
import com.github.tennyros.dashboard.models.User;
import com.github.tennyros.dashboard.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRequestDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRequestDto userRequestDto = (UserRequestDto) target;

        Optional<User> userByEmail = userService.getUserByEmail(userRequestDto.getEmail());
        if (userByEmail.isPresent() && !userByEmail.get().getId().equals(userRequestDto.getId())) {
            errors.rejectValue("email", "", "User with such email is already exists!");
        }

        if (!userRequestDto.getPassword().equals(userRequestDto.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "error.userDto",
                    "Passwords do not match!");
        }
    }
}
