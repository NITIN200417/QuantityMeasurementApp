package com.quantitymeasurement.service;

import com.quantitymeasurement.entity.QuantityDTO;

public interface IQuantityMeasurementService {

    boolean compare(
            QuantityDTO q1,
            QuantityDTO q2
    );
}