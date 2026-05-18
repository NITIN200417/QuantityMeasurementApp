package com.quantity.measurement.controller;

import com.quantity.measurement.entity.QuantityDTO;
import com.quantity.measurement.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    // controller uses service layer

    private final
    IQuantityMeasurementService service;

    // constructor injection

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
        // create DTO objects

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
        // send DTO to service

        boolean result =
                service.compare(q1, q2);

        // STEP 3
        // receive result

        System.out.println(
                "CONTROLLER RESULT : "
                        + result
        );
    }

}
