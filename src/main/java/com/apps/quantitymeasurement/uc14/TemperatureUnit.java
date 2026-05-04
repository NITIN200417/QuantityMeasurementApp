package com.apps.quantitymeasurement.uc14;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS(
            "Celsius",
            value -> value,                     // C → C
            value -> value                      // C → C
    ),

    FAHRENHEIT(
            "Fahrenheit",
            value -> (value - 32) * 5 / 9,      // F → C
            value -> (value * 9 / 5) + 32       // C → F
    );




    private final String unitName;
    private final Function<Double, Double> toBase;
    private final Function<Double, Double> fromBase;

    // Constructor
    TemperatureUnit(String unitName,
                    Function<Double, Double> toBase,
                    Function<Double, Double> fromBase) {

        this.unitName = unitName;
        this.toBase = toBase;
        this.fromBase = fromBase;
    }

    @Override
    public String getUnitName() {
        return unitName;
    }

    @Override
    public double getConversionFactor() {
        return 1.0; // not applicable for temperature
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toBase.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return fromBase.apply(baseValue);
    }

    public double convertTo(double value, TemperatureUnit targetUnit) {
        double baseValue = this.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    @Override
    public boolean supportsArithmetic() {
        return false; // temperature doesn't support multiply/divide
    }

    @Override
    public void validateOperationSupport(String operation) {
        if (operation.equalsIgnoreCase("MULTIPLY") ||
                operation.equalsIgnoreCase("DIVIDE")) {

            throw new UnsupportedOperationException(
                    this.name() + " does not support " + operation + " operation"
            );
        }
    }

    @Override
    public String toString() {
        return unitName;
    }
}