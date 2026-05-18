package com.quantity.measurement.entity;

public class QuantityDTO {

    private double value;
    private String unit;
    private String type;

    // constructor

    public QuantityDTO(
            double value,
            String unit,
            String type) {

        this.value = value;
        this.unit = unit;
        this.type = type;
    }

    // getters

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getType() {
        return type;
    }
}
