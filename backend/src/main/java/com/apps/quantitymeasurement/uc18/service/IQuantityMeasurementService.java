package com.apps.quantitymeasurement.uc18.service;

import com.apps.quantitymeasurement.uc18.entity.QuantityDTO;

public interface IQuantityMeasurementService {

    boolean compare(QuantityDTO q1, QuantityDTO q2);

    double add(QuantityDTO q1, QuantityDTO q2);

    double subtract(QuantityDTO q1, QuantityDTO q2);

    double multiply(QuantityDTO q1, QuantityDTO q2);

    double divide(QuantityDTO q1, QuantityDTO q2);

    // NEW
    double convert(QuantityDTO q1, QuantityDTO q2);
}