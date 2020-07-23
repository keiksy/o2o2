package com.example.service;

import com.example.Exception.ProductCategoryOperationException;
import com.example.dao.ProductCategoryDao;
import com.example.dto.ProductCategoryExecution;
import com.example.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getProductCategoryList(Long productCategoryId);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
        throws ProductCategoryOperationException;

    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
        throws ProductCategoryOperationException;
}
