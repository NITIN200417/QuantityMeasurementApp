package com.quantity.measurement.exception;

public class DatabaseException
        extends RuntimeException {

    public DatabaseException(
            String message) {

        super(message);
    }
}