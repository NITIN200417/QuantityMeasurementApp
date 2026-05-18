package com.app.quantitymeasurement.service;

import com.quantity.measurement.entity.QuantityDTO;
import com.quantity.measurement.repository.IQuantityMeasurementRepository;
import com.quantity.measurement.repository.QuantityMeasurementCacheRepository;
import com.quantity.measurement.service.IQuantityMeasurementService;
import com.quantity.measurement.service.QuantityMeasurementServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
// service layer test

public class QuantityMeasurementServiceImplTest {

    @Test
    void testCompare() {

        // repository

        IQuantityMeasurementRepository repository =
                new QuantityMeasurementCacheRepository();

        // service

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(
                        repository
                );

        // test data

        QuantityDTO q1 =
                new QuantityDTO(
                        1,
                        "FEET",
                        "length"
                );

        QuantityDTO q2 =
                new QuantityDTO(
                        12,
                        "INCHES",
                        "length"
                );

        // call service

        boolean result =
                service.compare(q1, q2);

        // assertion

        Assertions.assertTrue(result);

        System.out.println(
                "SERVICE TEST PASSED"
        );
    }
}
