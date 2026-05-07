package com.apps.quantitymeasurement.uc15.dto;

public class ResponseDTO {

    private final boolean success;
    private final String message;
    private final Object data;

    public ResponseDTO(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }

    public Object getData(){
        return data;
    }

    @Override
    public String toString(){
        if(success){
            return "SUCCESS : " + data;
        }
        return "ERROR : " + message;
    }
}
