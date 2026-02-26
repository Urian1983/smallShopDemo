package com.example.eCommerceDemo.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("User already exists");
    }
}
