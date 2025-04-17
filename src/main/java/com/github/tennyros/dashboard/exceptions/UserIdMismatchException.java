package com.github.tennyros.dashboard.exceptions;

public class UserIdMismatchException extends BaseException {

    public UserIdMismatchException() {
        super("User ID in the request does not match the ID in the body!");
    }
}
