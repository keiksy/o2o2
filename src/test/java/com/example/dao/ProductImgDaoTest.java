package com.example.dao;

import com.example.BaseTest;
import com.example.entity.ProductImg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductImgDaoTest extends BaseTest {
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testABatchInsertProductImg() throws Exception {
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("pic1");
        productImg1.setImgDesc("pic1 desc");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(1L);
        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("pic2");
        productImg2.setImgDesc("pic2 desc");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(1L);
        List<ProductImg> productImgList = new ArrayList<>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(2, effectedNum);
    }

    @Test
    public void testDeleteProductImgByProductId() {
        long id = 18L;
        int eff = productImgDao.deleteProductImgByProductId(id);
        assertEquals(eff, 1);
    }

    @Test
    public void testQueryProductImgList() {
        long id = 10L;
        List<ProductImg> list = productImgDao.queryProductImgList(id);
        assertEquals(3, list.size());
    }
}
