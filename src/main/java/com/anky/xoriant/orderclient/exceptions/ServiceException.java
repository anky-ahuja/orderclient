package com.anky.xoriant.orderclient.exceptions;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

    private final String message;
    private final int statusCode;

    public ServiceException(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
