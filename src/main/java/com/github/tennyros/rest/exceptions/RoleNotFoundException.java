package com.github.tennyros.rest.exceptions;

public class RoleNotFoundException extends BaseException {

    public RoleNotFoundException() {
        super("Role not found!");
    }
}
