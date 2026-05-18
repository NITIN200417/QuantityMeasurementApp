package com.app.quantitymeasurement.contoller;


import com.quantity.measurement.controller.QuantityMeasurementController;
import com.quantity.measurement.repository.IQuantityMeasurementRepository;
import com.quantity.measurement.repository.QuantityMeasurementCacheRepository;
import com.quantity.measurement.service.IQuantityMeasurementService;
import com.quantity.measurement.service.QuantityMeasurementServiceImpl;
import org.junit.jupiter.api.Test;

// controller test class

public class QuantityMeasurementControllerTest {

    @Test
    void testCompareExample() {

        System.out.println(
                "CONTROLLER TEST STARTED"
        );

        // create repository

        IQuantityMeasurementRepository repository =
                new QuantityMeasurementCacheRepository();

        // create service

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(
                        repository
                );

        // create controller

        QuantityMeasurementController controller =
                new QuantityMeasurementController(
                        service
                );

        // call method

        controller.compareExample();

        System.out.println(
                "CONTROLLER TEST FINISHED"
        );
    }
}