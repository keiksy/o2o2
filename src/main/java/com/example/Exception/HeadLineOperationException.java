package com.example.Exception;

public class HeadLineOperationException extends RuntimeException {
    public static final long serialVersionUID = 29849829L;

    public HeadLineOperationException(String msg) {
        super(msg);
    }
}
