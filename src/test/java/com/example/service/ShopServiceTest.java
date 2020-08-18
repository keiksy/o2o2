package com.example.service;

import com.example.BaseTest;
import com.example.Exception.ShopOperationException;
import com.example.dto.ImageHolder;
import com.example.dto.ShopExecution;
import com.example.entity.Area;
import com.example.entity.PersonInfo;
import com.example.entity.Shop;
import com.example.entity.ShopCategory;
import com.example.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testGetShopList() {
        Shop shopCondition = new Shop();
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(38L);
        shopCondition.setShopCategory(sc);
        ShopExecution se = shopService.getShopList(shopCondition, 1, 2);
        System.out.println(se.getShopList().size());
    }

    @Test
    public void testModifyShop() throws ShopOperationException, FileNotFoundException {
        Shop shop = shopService.getByShopId(41);
        ;
    }

    @Test
    public void testAddShop() throws IOException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory category = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(5);
        category.setShopCategoryId(38L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(category);
        shop.setShopName("test zzzzz3");
        shop.setShopDesc("test desczz3");
        shop.setShopAddr("test addrzzz3");
        shop.setPhone("test phonezz3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("censoring");
        File shopImg = new File("/Users/cai/desktop/csu.jpeg");
        ShopExecution se = shopService.addShop(shop, new ImageHolder(shopImg.getName(), new FileInputStream(shopImg)));
        assertEquals(se.getState(), ShopStateEnum.CHECK.getState());
    }
}
