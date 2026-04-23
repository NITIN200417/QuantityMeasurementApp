package com.apps.quantitymeasurement.uc10;

public enum LengthUnit implements IMeasurable {

    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETER(0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {

        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {

        return conversionFactor;
    }

    // Convert current unit → base (INCHES)
    public double convertToBaseUnit(double value) {

        return value * conversionFactor;
    }

    // Convert base (INCHES) → target unit
    public double convertFromBaseUnit(double baseValue) {

        return baseValue / conversionFactor;
    }
}