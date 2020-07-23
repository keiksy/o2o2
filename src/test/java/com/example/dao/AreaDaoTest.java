package com.example.dao;

import com.example.BaseTest;
import com.example.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea() {
        List<Area> areas = areaDao.queryArea();
        assertEquals(1+1,2);
    }
}
