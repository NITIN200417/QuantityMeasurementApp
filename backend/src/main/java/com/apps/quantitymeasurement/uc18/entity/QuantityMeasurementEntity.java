package com.apps.quantitymeasurement.uc18.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name = "measurement")

public class QuantityMeasurementEntity {

    @Id

    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )

    private Long id;

    private String operation;

    private double value1;

    private double value2;

    private boolean result;

    // default constructor

    public QuantityMeasurementEntity() {
    }

    // parameterized constructor

    public QuantityMeasurementEntity(

            String operation,

            double value1,

            double value2,

            boolean result
    ) {

        this.operation = operation;

        this.value1 = value1;

        this.value2 = value2;

        this.result = result;
    }

    // getters setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(
            String operation
    ) {

        this.operation = operation;
    }

    public double getValue1() {
        return value1;
    }

    public void setValue1(
            double value1
    ) {

        this.value1 = value1;
    }

    public double getValue2() {
        return value2;
    }

    public void setValue2(
            double value2
    ) {

        this.value2 = value2;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(
            boolean result
    ) {

        this.result = result;
    }
}
