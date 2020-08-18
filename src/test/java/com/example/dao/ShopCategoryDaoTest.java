package com.example.dao;

import com.example.BaseTest;
import com.example.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Test
    public void testShopCategoryDao() {
        List<ShopCategory> categories = shopCategoryDao.queryShopCategory(new ShopCategory());
        System.out.println(categories.size());
    }
}
