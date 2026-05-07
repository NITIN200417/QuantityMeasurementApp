package com.apps.quantitymeasurement.uc15.app;

import com.apps.quantitymeasurement.uc15.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.uc15.repository.QuantityMeasurementRepository;
import com.apps.quantitymeasurement.uc15.service.QuantityMeasurementService;

// main class
// application starts from here

public class QuantityMeasurementApp {

    // JVM starts execution from main()

    public static void main(String[] args) {

        System.out.println(
                "APP : started"
        );

        // STEP 1
        // create repository object

        QuantityMeasurementRepository repository =
                new QuantityMeasurementRepository();

        // STEP 2
        // create service object
        // repository injected into service

        QuantityMeasurementService service =
                new QuantityMeasurementService(
                        repository
                );

        // STEP 3
        // create controller object
        // service injected into controller

        QuantityMeasurementController controller =
                new QuantityMeasurementController(
                        service
                );

        // STEP 4
        // start controller method

        controller.compareExample();

        System.out.println(
                "APP : ended"
        );
    }
}