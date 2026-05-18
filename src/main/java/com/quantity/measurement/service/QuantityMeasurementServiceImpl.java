package com.quantity.measurement.service;

// service layer

import com.quantity.measurement.entity.QuantityDTO;
import com.quantity.measurement.entity.QuantityMeasurementEntity;
import com.quantity.measurement.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    // service uses repository

    private final
    IQuantityMeasurementRepository repository;

    // constructor injection

    public QuantityMeasurementServiceImpl(
            IQuantityMeasurementRepository repository) {

        System.out.println(
                "SERVICE : object created"
        );

        this.repository = repository;
    }

    // business logic

    @Override
    public boolean compare(
            QuantityDTO q1,
            QuantityDTO q2) {

        System.out.println(
                "SERVICE : compare() running"
        );

        // business logic

        boolean result =
                q1.getValue() * 12
                        == q2.getValue();

        // create entity

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        "COMPARE",
                        q1.getValue(),
                        q2.getValue(),
                        result
                );

        // save into repository

        repository.save(entity);

        return result;
    }
}