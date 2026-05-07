package com.apps.quantitymeasurement.uc15.units;

import java.util.function.Function;

// enum because temperature units are fixed constants

public enum TemperatureUnit
        implements IMeasurable {

    // CELSIUS unit

    CELSIUS(

            // convertToBaseUnit()
            // Celsius base unit is itself

            value -> value,

            // convertFromBaseUnit()
            // base unit to Celsius

            value -> value
    ),

    // FAHRENHEIT unit

    FAHRENHEIT(

            // Fahrenheit -> Celsius

            value -> (value - 32) * 5 / 9,

            // Celsius -> Fahrenheit

            value -> (value * 9 / 5) + 32
    );

    // function object for conversion to base unit

    private final
    Function<Double, Double> toBase;

    // function object for conversion from base unit

    private final
    Function<Double, Double> fromBase;

    // constructor

    TemperatureUnit(
            Function<Double, Double> toBase,
            Function<Double, Double> fromBase) {

        System.out.println(
                "UNIT : TemperatureUnit constant created"
        );

        this.toBase = toBase;
        this.fromBase = fromBase;
    }

    // converts value -> base unit
    // base unit = Celsius

    @Override
    public double convertToBaseUnit(
            double value) {

        System.out.println(
                "TEMPERATURE : convertToBaseUnit() running"
        );

        return toBase.apply(value);
    }

    // converts base unit -> current unit

    @Override
    public double convertFromBaseUnit(
            double baseValue) {

        System.out.println(
                "TEMPERATURE : convertFromBaseUnit() running"
        );

        return fromBase.apply(baseValue);
    }

    // temperature arithmetic not supported

    @Override
    public boolean supportsArithmetic() {

        System.out.println(
                "TEMPERATURE : supportsArithmetic() running"
        );

        return false;
    }
}