package com.quantity.measurement.quantity;

public class Quantity<U> {

    private double value;
    private U unit;

    // constructor

    public Quantity(
            double value,
            U unit) {

        this.value = value;
        this.unit = unit;
    }

    // getter

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }
}