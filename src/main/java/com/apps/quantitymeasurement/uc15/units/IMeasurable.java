package com.apps.quantitymeasurement.uc15.units;

// interface = contract
// all units must implement these methods

public interface IMeasurable {

    // converts value into base unit
    // example:
    // FEET -> meter
    double convertToBaseUnit(double value);

    // converts base unit into current unit
    // example:
    // meter -> FEET
    double convertFromBaseUnit(double baseValue);

    // checks arithmetic support
    // temperature arithmetic is not allowed
    default boolean supportsArithmetic() {
        return true;
    }
}