package com.apps.quantitymeasurement.uc15.service;

import com.apps.quantitymeasurement.uc15.dto.QuantityDTO;
import com.apps.quantitymeasurement.uc15.model.QuantityModel;
import com.apps.quantitymeasurement.uc15.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.uc15.units.*;

// service implementation
// contains actual business logic

public class QuantityMeasurementService
        implements IQuantityMeasurementService {


    private final
    IQuantityMeasurementRepository repository;

    // constructor

    public QuantityMeasurementService(
            IQuantityMeasurementRepository repository) {

        System.out.println(
                "SERVICE : object created"
        );

        this.repository = repository;
    }

    // helper method
    // converts String -> actual unit object

    private IMeasurable resolve(
            String type,
            String unit) {

        System.out.println(
                "SERVICE : resolve() running"
        );

        // checking measurement category

        return switch (type.toLowerCase()) {

            // converts "FEET"
            // into LengthUnit.FEET

            case "length" ->
                    LengthUnit.valueOf(unit);

            // converts "CELSIUS"
            // into TemperatureUnit.CELSIUS

            case "temperature" ->
                    TemperatureUnit.valueOf(unit);

            default ->
                    throw new RuntimeException(
                            "Invalid Measurement Type"
                    );
        };
    }

    // compare logic
    @Override
    public boolean compare(
            QuantityDTO q1,
            QuantityDTO q2) {

        System.out.println(
                "SERVICE : compare() running"
        );

        // STEP 1
        // converting String -> enum object

        IMeasurable unit1 =
                resolve(
                        q1.getMeasurementType(),
                        q1.getUnit()
                );

        IMeasurable unit2 =
                resolve(
                        q2.getMeasurementType(),
                        q2.getUnit()
                );

        // STEP 2
        // creating business objects

        QuantityModel<IMeasurable> quantity1 =
                new QuantityModel<>(
                        q1.getValue(),
                        unit1
                );

        QuantityModel<IMeasurable> quantity2 =
                new QuantityModel<>(
                        q2.getValue(),
                        unit2
                );

        // checking same category

        if(!quantity1.getUnit()
                .getClass()
                .equals(
                        quantity2.getUnit()
                                .getClass()
                )) {

            throw new RuntimeException(
                    "Cross category comparison not allowed"
            );
        }

        // STEP 3
        // converting both values into base units

        double base1 =
                quantity1.getUnit()
                        .convertToBaseUnit(
                                quantity1.getValue()
                        );

        double base2 =
                quantity2.getUnit()
                        .convertToBaseUnit(
                                quantity2.getValue()
                        );

        // STEP 4
        // comparing base values

        return Math.abs(base1 - base2)
                < 0.0001;
    }
}