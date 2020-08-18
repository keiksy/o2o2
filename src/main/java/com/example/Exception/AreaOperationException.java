package com.example.Exception;

public class AreaOperationException extends RuntimeException {
    private static final long serialVersionUID = -197398729L;

    public AreaOperationException(String msg) {
        super(msg);
    }
}
