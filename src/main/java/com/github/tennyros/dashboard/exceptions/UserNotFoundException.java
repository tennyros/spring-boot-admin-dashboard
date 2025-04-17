package com.github.tennyros.dashboard.exceptions;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super("User not found!");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}