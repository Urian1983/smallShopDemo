package com.example.eCommerceDemo.exceptions;

public class UserNameNotFoundException extends RuntimeException{
    public UserNameNotFoundException() {
        super("Username not found");
    }
}
