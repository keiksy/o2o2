package com.example.service;

import com.example.Exception.ProductCategoryOperationException;
import com.example.Exception.ProductOperationException;
import com.example.dto.ImageHolder;
import com.example.dto.ProductExecution;
import com.example.entity.Product;

import java.io.InputStream;
import java.util.List;

public interface ProductService {
    ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) throws ProductOperationException;

    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

    Product getProductById(long productId);

    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException;
}
