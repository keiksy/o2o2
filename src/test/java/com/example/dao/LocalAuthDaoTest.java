package com.example.dao;

import com.example.BaseTest;
import com.example.entity.LocalAuth;
import com.example.entity.PersonInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class LocalAuthDaoTest extends BaseTest {
    @Autowired
    private LocalAuthDao localAuthDao;

    @Test
    public void testQueryLocalByUserNameandPwd() {
        LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd("testbind",
                "s05bse6q2qlb9qblls96s592y55y556s");
        assertEquals((long)localAuth.getLocalAuthId(), 13);
    }

    @Test
    public void testQueryLocalByUserId() {
        LocalAuth localAuth = localAuthDao.queryLocalByUserId(1);
        assertEquals((long)localAuth.getLocalAuthId(), 13);
    }

}
