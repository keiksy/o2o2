package com.example.dao;

import com.example.BaseTest;
import com.example.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testQueryHeadLine() {
        List<HeadLine> headLines = headLineDao.queryHeadLine(new HeadLine());
        assertEquals(headLines.size(), 4);
    }
}
