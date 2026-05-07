package com.apps.quantitymeasurement.uc15.service;

import com.apps.quantitymeasurement.uc15.dto.QuantityDTO;

// interface = contract
// service must implement these methods

public interface IQuantityMeasurementService {

    // compare two quantities
    boolean compare(
            QuantityDTO q1,
            QuantityDTO q2
    );
}