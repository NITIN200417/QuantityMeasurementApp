package com.quantitymeasurement.service;

import com.quantitymeasurement.entity.QuantityDTO;

import com.quantitymeasurement.entity.QuantityMeasurementEntity;

import com.quantitymeasurement.repository.IQuantityMeasurementRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service

public class QuantityMeasurementServiceImpl

        implements IQuantityMeasurementService {

    @Autowired

    private IQuantityMeasurementRepository repository;

    @Override
    public boolean compare(
            QuantityDTO q1,
            QuantityDTO q2
    ) {

        System.out.println(
                "SERVICE : compare() running"
        );

        // business logic

        boolean result =
                q1.getValue() * 12
                        == q2.getValue();

        // entity

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(

                        "COMPARE",

                        q1.getValue(),

                        q2.getValue(),

                        result
                );

        // save into database

        repository.save(entity);

        return result;
    }
}