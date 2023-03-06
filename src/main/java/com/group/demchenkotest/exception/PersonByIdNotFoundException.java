package com.group.demchenkotest.exception;

public class PersonByIdNotFoundException extends RuntimeException {
    public PersonByIdNotFoundException(String message) {
        super(message);
    }
}
