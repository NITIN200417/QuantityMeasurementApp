package com.apps.quantitymeasurement.uc15.entity;

// entity = object to store/save

public class QuantityMeasurementEntity {

    // stores operation name
    // example: COMPARE
    private final String operation;

    // stores operation result
    // example: true
    private final String result;

    // constructor
    public QuantityMeasurementEntity(
            String operation,
            String result) {

        System.out.println(
                "ENTITY : object created"
        );

        this.operation = operation;
        this.result = result;
    }

    @Override
    public String toString() {

        return operation +
                " -> " +
                result;
    }
}