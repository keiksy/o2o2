package com.example.dto;

import com.example.entity.Product;
import com.example.enums.ProductStateEnum;

import java.util.List;

public class ProductExecution {
    private int state;
    private String stateinfo;
    private int count;
    private Product product;
    private List<Product> productList;

    public ProductExecution() {}

    public ProductExecution(ProductStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateinfo = stateEnum.getStateInfo();
    }

    public ProductExecution(ProductStateEnum stateEnum, Product product) {
        this.state = stateEnum.getState();
        this.stateinfo = stateEnum.getStateInfo();
        this.product = product;
    }

    public ProductExecution(ProductStateEnum stateEnum, List<Product> productList) {
        this.state = stateEnum.getState();
        this.stateinfo = stateEnum.getStateInfo();
        this.productList = productList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateinfo() {
        return stateinfo;
    }

    public void setStateinfo(String stateinfo) {
        this.stateinfo = stateinfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
