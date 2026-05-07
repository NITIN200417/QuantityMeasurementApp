package com.apps.quantitymeasurement.uc15.dto;

public class QuantityOperationDTO {

    private final QuantityDTO quantity1;
    private final QuantityDTO quantity2;
    private final String operation;

    public QuantityOperationDTO(QuantityDTO quantity1, QuantityDTO quantity2,
                                String operation){

        this.quantity1 = quantity1;
        this.quantity2 = quantity2;
        this.operation = operation;
    }

    public QuantityDTO getQuantity1(){
        return quantity1;
    }

    public QuantityDTO getQuantity2(){
        return quantity2;
    }

    public String getOperation(){
        return operation;
    }

}
