package com.example.service;

import com.example.Exception.ShopOperationException;
import com.example.dto.ImageHolder;
import com.example.dto.ShopExecution;
import com.example.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
    Shop getByShopId(long shopId);
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

}
