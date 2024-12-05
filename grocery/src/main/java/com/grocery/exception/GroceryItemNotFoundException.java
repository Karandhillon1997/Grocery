package com.grocery.exception;

public class GroceryItemNotFoundException extends RuntimeException {
    public GroceryItemNotFoundException(String message) {
        super(message);
    }
}
