package com.example.eCommerceDemo.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("Not Found");
    }
}

