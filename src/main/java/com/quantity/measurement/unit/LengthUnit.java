package com.quantity.measurement.unit;

public enum LengthUnit implements IMeasurable{

    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    // conversion factor to base unit
    // base unit = inches

    private final double factor;

    // constructor for enum constants
    LengthUnit(double factor) {

        System.out.println(
                "UNIT : LengthUnit constant created"
        );

        this.factor = factor;
    }

    // converts current value -> inches
    @Override
    public double convertToBaseUnit(double value) {

        System.out.println(
                "UNIT : convertToBaseUnit() running"
        );

        return value * factor;
    }

    // converts inches -> current unit
    @Override
    public double convertFromBaseUnit(
            double baseValue) {

        System.out.println(
                "UNIT : convertFromBaseUnit() running"
        );

        return baseValue / factor;
    }

}
