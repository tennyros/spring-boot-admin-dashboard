package com.github.tennyros.rest.dtos;

import com.github.tennyros.rest.models.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "User response dto")
public class UserResponseDto {

    @Schema(description = "User's ID")
    private Long id;

    @Schema(description = "User's first name")
    @NotBlank(message = "Username must be defined!")
    @Size(min = 3, max = 18, message = "Username must be from 3 to 18 symbols long!")
    private String firstName;

    @Schema(description = "User's last name")
    @NotBlank(message = "Username must be defined!")
    @Size(min = 3, max = 18, message = "Username must be from 3 to 18 symbols long!")
    private String lastName;

    @Schema(description = "User's email")
    @NotBlank(message = "Email must be defined!")
    @Email(message = "Email must be valid!")
    private String email;

    @Schema(description = "User's age")
    @NotNull(message = "Age must be defined!")
    @Min(value = 14, message = "Age must not be less than 14!")
    @Max(value = 125, message = "Age must not be more than 125!")
    private Integer age;

    @Schema(description = "User's roles")
    @NotEmpty(message = "Role must be selected!")
    private Set<Role> roles;

    @Schema(description = "User's admin status")
    private boolean admin;
}
