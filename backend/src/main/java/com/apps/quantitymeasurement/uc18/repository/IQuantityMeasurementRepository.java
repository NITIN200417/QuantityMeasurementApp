package com.apps.quantitymeasurement.uc18.repository;

import com.apps.quantitymeasurement.uc18.entity.QuantityMeasurementEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface IQuantityMeasurementRepository

        extends JpaRepository<
        QuantityMeasurementEntity,
        Long> {

}
