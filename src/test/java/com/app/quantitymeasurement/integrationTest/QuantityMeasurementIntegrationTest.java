package com.app.quantitymeasurement.integrationTest;

import com.quantity.measurement.controller.QuantityMeasurementController;
import com.quantity.measurement.repository.IQuantityMeasurementRepository;
import com.quantity.measurement.repository.QuantityMeasurementDatabaseRepository;
import com.quantity.measurement.service.IQuantityMeasurementService;
import com.quantity.measurement.service.QuantityMeasurementServiceImpl;
import org.junit.jupiter.api.Test;
// integration test

public class QuantityMeasurementIntegrationTest {

    @Test
    void testCompleteFlow() {

        System.out.println(
                "INTEGRATION TEST STARTED"
        );

        // repository

        IQuantityMeasurementRepository repository =
                new QuantityMeasurementDatabaseRepository();

        // service

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(
                        repository
                );

        // controller

        QuantityMeasurementController controller =
                new QuantityMeasurementController(
                        service
                );

        // complete flow

        controller.compareExample();

        System.out.println(
                "INTEGRATION TEST PASSED"
        );
    }
}