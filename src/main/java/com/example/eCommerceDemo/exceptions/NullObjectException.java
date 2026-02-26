package com.example.eCommerceDemo.exceptions;

public class NullObjectException extends RuntimeException {
    public NullObjectException() {
        super("No object found");
    }
}
