package com.steelbox.app.productcatalog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {

    private String message;
    private String stackTrace;
    private int code;

    public ErrorMessage(String message, String stackTrace, int code) {
        this.message = message;
        this.stackTrace = stackTrace;
        this.code = code;
    }

    public ErrorMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
