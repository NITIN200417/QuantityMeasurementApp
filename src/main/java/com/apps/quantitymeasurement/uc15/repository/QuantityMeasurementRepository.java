package com.apps.quantitymeasurement.uc15.repository;

import com.apps.quantitymeasurement.uc15.entity.QuantityMeasurementEntity;

// repository implementation

public class QuantityMeasurementRepository
        implements IQuantityMeasurementRepository {

    // save method implementation

    @Override
    public void save(
            QuantityMeasurementEntity entity) {

        System.out.println(
                "REPOSITORY : save() running"
        );

        // currently just printing
        // later we can store in DB/file/list

        System.out.println(
                "Saved : " + entity
        );
    }
}