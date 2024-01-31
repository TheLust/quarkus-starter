package com.learn.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entity, String field, String value) {
        super(entity + " with " + field + " '" + value + "' " + "not found");
    }
}
