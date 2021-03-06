package com.elepy.exceptions;

public class ElepyException extends ElepyErrorMessage {
    public ElepyException(String message) {
        this(message, 400);
    }

    public ElepyException(String message, int status) {
        super(message, status);
    }
}
