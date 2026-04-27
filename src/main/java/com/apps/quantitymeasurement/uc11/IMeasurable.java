package com.apps.quantitymeasurement.uc11;

public interface IMeasurable {

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    //String getUnitName();
}
