package com.apps.quantitymeasurement.uc15.dto;

// DTO = Data Transfer Object
// This class is used to carry data
// between Controller and Service

public class QuantityDTO {

    // stores numeric value
    // example: 1
    private double value;

    // stores unit name
    // example: "FEET"
    private String unit;

    // stores measurement category
    // example: "length"
    private String measurementType;

    // constructor
    // called when object is created
    public QuantityDTO(
            double value,
            String unit,
            String measurementType) {

        System.out.println(
                "DTO : QuantityDTO object created"
        );

        // storing incoming data
        // into object variables

        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    // returns value
    public double getValue() {
        return value;
    }

    // returns unit
    public String getUnit() {
        return unit;
    }

    // returns measurement type
    public String getMeasurementType() {
        return measurementType;
    }

    @Override
    public String toString() {

        return value + " " + unit + " (" + measurementType + ")";
    }
}