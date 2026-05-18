package com.quantity.measurement.repository;

// repository contract

import com.quantity.measurement.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository {

    // save entity

    void save(
            QuantityMeasurementEntity entity
    );
}
