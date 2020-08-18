package com.example.dao;

import com.example.BaseTest;
import com.example.entity.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    public void testQueryShopList() {
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        List<Shop> shops = shopDao.queryShopList(shopCondition, 0, 1);
        assertEquals(shops.size(), 1);
    }

    @Test
    public void testQueryByShopId() {
        long shopId = 39;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println(shop.getArea().getAreaName());
    }

    @Test
    public void testInsertShop() {
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
        shop.setShopName("test shop");
        shop.setShopDesc("test desc");
        shop.setShopAddr("test addr");
        shop.setPhone("test phone");
        shop.setShopImg("test img");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("censoring");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    public void updateDao() {
        Shop shop = new Shop();
        shop.setShopId(39L);
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory category = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(5);
        category.setShopCategoryId(38L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(category);
        shop.setShopName("test xxxxxxxxxxxxx");
        shop.setShopDesc("test desc");
        shop.setShopAddr("test addr");
        shop.setPhone("test phone");
        shop.setShopImg("test img");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("censoring");
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testParentShop() {
        ShopCategory parent = new ShopCategory();
        parent.setShopCategoryId(12L);
        ShopCategory son = new ShopCategory();
        son.setParent(parent);
        Shop shop = new Shop();
        shop.setShopCategory(son);
        List<Shop> shops = shopDao.queryShopList(shop, 0, 999);
        int a = 3;
    }
}
