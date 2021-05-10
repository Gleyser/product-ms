package com.gleyser.productms.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse {

    private final int status_code;
    private final String message;
    
    public int getStatus_code() {
        return status_code;
    }

    public String getMessage() {
        return message;
    }
}
