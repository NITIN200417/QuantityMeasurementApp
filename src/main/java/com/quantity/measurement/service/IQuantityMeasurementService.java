package com.quantity.measurement.service;

import com.quantity.measurement.entity.QuantityDTO;

public interface IQuantityMeasurementService {

    boolean compare(
            QuantityDTO q1,
            QuantityDTO q2
    );
}
