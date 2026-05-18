package com.app.quantitymeasurement.repository;

// repository test
import com.quantity.measurement.entity.QuantityMeasurementEntity;
import com.quantity.measurement.repository.QuantityMeasurementCacheRepository;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementCacheRepositoryTest {

    @Test
    void testSave() {

        QuantityMeasurementCacheRepository repository =
                new QuantityMeasurementCacheRepository();

        // entity object

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        "COMPARE",
                        1,
                        12,
                        true
                );

        // save

        repository.save(entity);

        System.out.println(
                "CACHE REPOSITORY TEST PASSED"
        );
    }
}