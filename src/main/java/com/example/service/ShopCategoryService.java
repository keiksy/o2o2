package com.example.service;

import com.example.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
    String SCLISTKEY = "shopcategorylist";

    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
