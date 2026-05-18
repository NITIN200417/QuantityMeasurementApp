package com.quantity.measurement.repository;

// cache repository

import com.quantity.measurement.entity.QuantityMeasurementEntity;

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    @Override
    public void save(
            QuantityMeasurementEntity entity) {

        System.out.println(
                "CACHE REPOSITORY : saved"
        );
    }
}