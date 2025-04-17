package com.github.tennyros.dashboard.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, updatable = false, unique = true)
    private Long id;

    @NotBlank(message = "Role must be defined!")
    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return getRoleName();
    }

    @Override
    public String toString() {
        return roleName;
    }
}
