package com.apps.quantitymeasurement.uc14;

@FunctionalInterface
interface SupportsArithmetic{
    boolean isSupported();
}

public interface IMeasurable {

    SupportsArithmetic supportsArithmetic = () -> true;

    //methods are mandatory convrsion methods for all units
    public String getUnitName();
    public double getConversionFactor();
    public double convertToBaseUnit(double value);
    public double convertFromBaseUnit(double baseValue);

    default boolean supportsArithmetic(){
        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(String operation){};
}
