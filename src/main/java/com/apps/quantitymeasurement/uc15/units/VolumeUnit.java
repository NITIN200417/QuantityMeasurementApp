package com.apps.quantitymeasurement.uc15.units;

public enum VolumeUnit implements IMeasurable{

    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double factor;

    // constructor for enum constants
    VolumeUnit(double factor) {

        System.out.println(
                "UNIT : LengthUnit constant created"
        );

        this.factor = factor;
    }

    // converts current value -> Liters
    @Override
    public double convertToBaseUnit(double value) {

        System.out.println(
                "UNIT : convertToBaseUnit() running"
        );

        return value * factor;
    }

    // converts Liters -> current unit
    @Override
    public double convertFromBaseUnit(
            double baseValue) {

        System.out.println(
                "UNIT : convertFromBaseUnit() running"
        );

        return baseValue / factor;
    }
}
