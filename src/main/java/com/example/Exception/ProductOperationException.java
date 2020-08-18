package com.example.Exception;

public class ProductOperationException extends RuntimeException{
    public ProductOperationException(String msg) {
        super(msg);
    }
}
