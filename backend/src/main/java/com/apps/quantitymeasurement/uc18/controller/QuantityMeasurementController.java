package com.apps.quantitymeasurement.uc18.controller;

import com.apps.quantitymeasurement.uc18.entity.QuantityOperationRequest;
import com.apps.quantitymeasurement.uc18.service.IQuantityMeasurementService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/quantity")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    // =====================================================
    // CONVERT
    // =====================================================

    @PostMapping("/convert")
    public double convertExample(
            @RequestBody QuantityOperationRequest request
    ) {
        System.out.println("CONTROLLER : convert() running");
        return service.convert(request.getQ1(), request.getQ2());
    }

    // =====================================================
    // COMPARE
    // =====================================================

    @PostMapping("/compare")
    public boolean compareExample(
            @RequestBody QuantityOperationRequest request
    ) {
        System.out.println("CONTROLLER : compare() running");
        return service.compare(request.getQ1(), request.getQ2());
    }

    // =====================================================
    // ADDITION
    // =====================================================

    @PostMapping("/add")
    public double addExample(
            @RequestBody QuantityOperationRequest request
    ) {
        System.out.println("CONTROLLER : add() running");
        return service.add(request.getQ1(), request.getQ2());
    }

    // =====================================================
    // SUBTRACTION
    // =====================================================

    @PostMapping("/subtract")
    public double subtractExample(
            @RequestBody QuantityOperationRequest request
    ) {
        System.out.println("CONTROLLER : subtract() running");
        return service.subtract(request.getQ1(), request.getQ2());
    }

    // =====================================================
    // MULTIPLICATION
    // =====================================================

    @PostMapping("/multiply")
    public double multiplyExample(
            @RequestBody QuantityOperationRequest request
    ) {
        System.out.println("CONTROLLER : multiply() running");
        return service.multiply(request.getQ1(), request.getQ2());
    }

    // =====================================================
    // DIVISION
    // =====================================================

    @PostMapping("/divide")
    public double divideExample(
            @RequestBody QuantityOperationRequest request
    ) {
        System.out.println("CONTROLLER : divide() running");
        return service.divide(request.getQ1(), request.getQ2());
    }
}