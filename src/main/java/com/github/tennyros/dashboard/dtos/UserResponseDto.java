package com.github.tennyros.dashboard.dtos;

import com.github.tennyros.dashboard.models.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "User response DTO")
public class UserResponseDto {

    @Schema(description = "User's ID")
    private Long id;

    @Schema(description = "User's first name")
    private String firstName;

    @Schema(description = "User's last name")
    private String lastName;

    @Schema(description = "User's email")
    private String email;

    @Schema(description = "User's age")
    private Integer age;

    @Schema(description = "User's roles")
    private Set<Role> roles;

    @Schema(description = "User's admin status")
    private boolean admin;
}
