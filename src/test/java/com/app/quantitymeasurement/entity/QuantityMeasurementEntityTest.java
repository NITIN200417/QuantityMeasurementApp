package com.app.quantitymeasurement.entity;

// entity test
import com.quantity.measurement.entity.QuantityMeasurementEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementEntityTest {

    @Test
    void testEntityCreation() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        "COMPARE",
                        1,
                        12,
                        true
                );

        // assertions

        Assertions.assertEquals(
                "COMPARE",
                entity.getOperation()
        );

        Assertions.assertEquals(
                true,
                entity.getResult()
        );

        System.out.println(
                "ENTITY TEST PASSED"
        );
    }
}