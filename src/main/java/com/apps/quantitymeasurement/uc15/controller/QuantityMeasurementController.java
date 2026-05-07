package com.apps.quantitymeasurement.uc15.controller;

import com.apps.quantitymeasurement.uc15.dto.QuantityDTO;
import com.apps.quantitymeasurement.uc15.service.IQuantityMeasurementService;

// controller layer

public class QuantityMeasurementController {

    // controller uses service layer

    private final
    IQuantityMeasurementService service;

    // constructor injection
    // service object received from app layer

    public QuantityMeasurementController(
            IQuantityMeasurementService service) {

        System.out.println(
                "CONTROLLER : object created"
        );

        this.service = service;
    }

    // controller method

    public void compareExample() {

        System.out.println(
                "CONTROLLER : compareExample() running"
        );

        // STEP 1
        // controller creates DTO objects

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

        // STEP 2
        // controller sends DTO to service

        boolean result =
                service.compare(q1, q2);

        // STEP 3
        // controller receives result

        System.out.println(
                "CONTROLLER RESULT : " + result
        );
    }
}