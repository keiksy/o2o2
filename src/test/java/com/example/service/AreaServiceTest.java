package com.example.service;

import com.example.BaseTest;
import com.example.dto.ShopExecution;
import com.example.entity.Area;
import com.example.entity.Shop;
import com.example.entity.ShopCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;
    @Autowired
    private CacheService cacheService;

    @Test
    public void testQueryArea() {
        cacheService.removeFromCache(areaService.AREALISTKEY);
        List<Area> areaList = areaService.getAreaList();
        System.out.println(areaList.size());
    }
}
