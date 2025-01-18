package com.github.tennyros.rest.http.rest;

import com.github.tennyros.rest.mappers.UserMapper;
import com.github.tennyros.rest.models.User;
import com.github.tennyros.rest.services.RegistrationService;
import com.github.tennyros.rest.services.UserService;
import com.github.tennyros.rest.utils.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.github.tennyros.rest.dtos.OnCreate;
import com.github.tennyros.rest.dtos.OnUpdate;
import com.github.tennyros.rest.dtos.UserRequestDto;
import com.github.tennyros.rest.dtos.UserResponseDto;
import com.github.tennyros.rest.exceptions.UserIdMismatchException;
import com.github.tennyros.rest.exceptions.UserNotFoundException;
import com.github.tennyros.rest.exceptions.UserValidationException;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BaseRestController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserValidator userValidator;
    private final RegistrationService registrationService;

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UserResponseDto> userResponseDTOs = users.stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userResponseDTOs);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getUser(@PathVariable("id") Long id) {
        return userMapper.toResponseDto(userService.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found!")));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@Valid @Validated(OnCreate.class) @RequestBody
                                         UserRequestDto userRequestDto, BindingResult result) {

        userValidator.validate(userRequestDto, result);
        if (result.hasErrors()) {
            validationErrorMessageInit(result);
        }
        User userEntity = userMapper.requestToEntity(userRequestDto);
        userService.mapRolesForNewUser(userRequestDto, userEntity);
        registrationService.register(userEntity);
        return userMapper.toResponseDto(userEntity);
    }

    @PutMapping( "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto updateUser(@Validated(OnUpdate.class) @PathVariable("id") Long id,
                                      @Valid @RequestBody UserRequestDto userRequestDto, BindingResult result) {

        User userForUpdate = userService.getUserById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found!"));
        userValidator.validate(userRequestDto, result);
        if (result.hasErrors()) {
            validationErrorMessageInit(result);
        }
        if (!id.equals(userRequestDto.getId())) {
            throw new UserIdMismatchException();
        }
        userService.mapAndUpdateRoles(userRequestDto, userForUpdate);
        userMapper.updateUserFromDto(userRequestDto, userForUpdate);
        userService.updateUser(userForUpdate);
        return userMapper.toResponseDto(userForUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {
        if (userService.getUserById(id).isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found!");
        }
        userService.deleteUser(id);
    }

    private static void validationErrorMessageInit(BindingResult result) {
        Map<String, String> fieldErrors = result.getFieldErrors().stream()
                .filter(fieldError -> fieldError.getDefaultMessage() != null)
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (message1, message2) -> message1 + "; " + message2
                ));
        throw new UserValidationException(fieldErrors);
    }
}
