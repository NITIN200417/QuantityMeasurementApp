package com.apps.quantitymeasurement.uc15.units;

public enum WeightUnit implements IMeasurable{

    MILLIGRAM(0.001),
    GRAM(1.0),
    KILOGRAM(1000.0),
    POUND(453.592),
    TONNE(1_000_000.0);

    private final double factor;

    // constructor for enum constants
    WeightUnit(double factor) {

        System.out.println(
                "UNIT : LengthUnit constant created"
        );

        this.factor = factor;
    }

    // converts current value -> gram
    @Override
    public double convertToBaseUnit(double value) {

        System.out.println(
                "UNIT : convertToBaseUnit() running"
        );

        return value * factor;
    }

    // converts gram -> current unit
    @Override
    public double convertFromBaseUnit(
            double baseValue) {

        System.out.println(
                "UNIT : convertFromBaseUnit() running"
        );

        return baseValue / factor;
    }
}
