package com.apps.quantitymeasurement.uc15.exception;

// custom exception class

public class InvalidMeasurementException
        extends RuntimeException {

    // constructor

    public InvalidMeasurementException(
            String message) {

        // sends message to parent class

        super(message);

        System.out.println(
                "EXCEPTION : " + message
        );
    }
}