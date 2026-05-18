package com.app.quantitymeasurement.repository;

// database repository test
import com.quantity.measurement.entity.QuantityMeasurementEntity;
import com.quantity.measurement.repository.QuantityMeasurementDatabaseRepository;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementDatabaseRepositoryTest {

    @Test
    void testSave() {

        QuantityMeasurementDatabaseRepository repository =
                new QuantityMeasurementDatabaseRepository();

        // entity

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        "COMPARE",
                        1,
                        12,
                        true
                );

        // save into database

        repository.save(entity);

        System.out.println(
                "DATABASE TEST PASSED"
        );
    }
}