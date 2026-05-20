package com.quantitymeasurement.repository;

import com.quantitymeasurement.entity.QuantityMeasurementEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface IQuantityMeasurementRepository

        extends JpaRepository<
        QuantityMeasurementEntity,
        Long> {

}