package com.quantitymeasurement.entity;

public class QuantityDTO {

    private double value;

    private String unit;

    private String type;

    // default constructor

    public QuantityDTO() {
    }

    // parameterized constructor

    public QuantityDTO(
            double value,
            String unit,
            String type
    ) {

        this.value = value;

        this.unit = unit;

        this.type = type;
    }

    // getters setters

    public double getValue() {
        return value;
    }

    public void setValue(
            double value
    ) {

        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(
            String unit
    ) {

        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(
            String type
    ) {

        this.type = type;
    }
}