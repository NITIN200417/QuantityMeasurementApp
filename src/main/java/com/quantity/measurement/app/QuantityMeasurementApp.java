package com.quantity.measurement.app;

// main application class

import com.quantity.measurement.controller.QuantityMeasurementController;
import com.quantity.measurement.repository.IQuantityMeasurementRepository;
import com.quantity.measurement.repository.QuantityMeasurementDatabaseRepository;
import com.quantity.measurement.service.IQuantityMeasurementService;
import com.quantity.measurement.service.QuantityMeasurementServiceImpl;


public class QuantityMeasurementApp {

    // JVM starts from main()

    public static void main(String[] args) {

        System.out.println(
                "APP STARTED"
        );

        // STEP 1
        // create repository object

        IQuantityMeasurementRepository repository =
                new QuantityMeasurementDatabaseRepository();

        // STEP 2
        // inject repository into service

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(
                        repository
                );

        // STEP 3
        // inject service into controller

        QuantityMeasurementController controller =
                new QuantityMeasurementController(
                        service
                );

        // STEP 4
        // call controller method

        controller.compareExample();

        System.out.println(
                "APP FINISHED"
        );
    }
}