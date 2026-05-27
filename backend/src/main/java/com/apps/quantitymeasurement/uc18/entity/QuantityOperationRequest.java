package com.apps.quantitymeasurement.uc18.entity;

public class QuantityOperationRequest {

    private QuantityDTO q1;

    private QuantityDTO q2;

    public QuantityOperationRequest() {
    }

    public QuantityDTO getQ1() {
        return q1;
    }

    public void setQ1(QuantityDTO q1) {
        this.q1 = q1;
    }

    public QuantityDTO getQ2() {
        return q2;
    }

    public void setQ2(QuantityDTO q2) {
        this.q2 = q2;
    }
}