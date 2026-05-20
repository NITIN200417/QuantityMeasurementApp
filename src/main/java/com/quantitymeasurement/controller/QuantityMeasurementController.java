package com.quantitymeasurement.controller;

import com.quantitymeasurement.entity.QuantityDTO;

import com.quantitymeasurement.service.IQuantityMeasurementService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/v1/quantity")

public class QuantityMeasurementController {

    @Autowired

    private IQuantityMeasurementService service;

    @GetMapping("/compare")

    public boolean compareExample() {

        System.out.println(
                "CONTROLLER : compare() running"
        );

        // DTOs

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

        // service call

        return service.compare(q1, q2);
    }


}