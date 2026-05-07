package com.apps.quantitymeasurement.uc15.model;

import com.apps.quantitymeasurement.uc15.units.IMeasurable;

// generic class
// U can be:
// LengthUnit
// TemperatureUnit
// VolumeUnit

public class QuantityModel<U extends IMeasurable> {

    // stores numeric value
    // example: 1
    private final double value;

    // stores actual unit object
    // example: LengthUnit.FEET
    private final U unit;

    // constructor
    public QuantityModel(
            double value,
            U unit) {

        System.out.println(
                "MODEL : QuantityModel object created"
        );

        // validation
        // prevents invalid quantity

        if(unit == null ||
                !Double.isFinite(value)) {

            throw new IllegalArgumentException(
                    "Invalid Quantity"
            );
        }

        // storing values inside object

        this.value = value;
        this.unit = unit;
    }

    // returns numeric value
    public double getValue() {
        return value;
    }

    // returns actual unit object
    public U getUnit() {
        return unit;
    }

    @Override
    public String toString() {

        return value + " " + unit;
    }
}