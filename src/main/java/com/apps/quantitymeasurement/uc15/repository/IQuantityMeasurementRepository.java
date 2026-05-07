package com.apps.quantitymeasurement.uc15.repository;

import com.apps.quantitymeasurement.uc15.entity.QuantityMeasurementEntity;

// interface = contract
// repository must implement save()

public interface IQuantityMeasurementRepository {

    // stores operation result
    void save(
            QuantityMeasurementEntity entity
    );
}