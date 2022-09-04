package com.anky.xoriant.orderclient.exceptions;

import lombok.Data;

@Data
public class ClientDataException extends RuntimeException{

    private String message;

    public ClientDataException(String message) {
        this.message = message;
    }
}
