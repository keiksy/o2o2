package com.example.Exception;

public class ShopCategoryOperationException extends RuntimeException {
    public static final long serialVersionUID = 187392399L;

    public ShopCategoryOperationException(String msg) {
        super(msg);
    }
}
