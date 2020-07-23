package com.example.Exception;

import com.example.dto.ProductCategoryExecution;

public class ProductCategoryOperationException extends RuntimeException {

    private static final long serialVersionUID = 127174187628973L;

    public ProductCategoryOperationException(String msg) {
        super(msg);
    }
}
