package com.example.dao;

import com.example.BaseTest;
import com.example.entity.ProductCategory;
import com.example.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao dao;

    @Test
    public void testProductCategoryDao() {
        List<ProductCategory> list = dao.queryProductCategoryList(0L);
        assertEquals(list.size(), 0);
    }

    @Test
    public void testBatchInsertProductCategory() {
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryName("C1");
        pc.setPriority(1);
        pc.setCreateTime(new Date());
        pc.setShopId(40L);
        ProductCategory pc2 = new ProductCategory();
        pc2.setProductCategoryName("C2");
        pc2.setPriority(2);
        pc2.setCreateTime(new Date());
        pc2.setShopId(40L);
        ProductCategory pc3 = new ProductCategory();
        pc3.setProductCategoryName("C3");
        pc3.setPriority(3);
        pc3.setCreateTime(new Date());
        pc3.setShopId(40L);
        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(pc);
        productCategoryList.add(pc2);
        productCategoryList.add(pc3);
        int effectedNum = dao.batchInsertProductCategory(productCategoryList);
        assertEquals(3, effectedNum);
    }

    @Test
    public void testDeleteProductCategory() {
        long shopId = 1;
        List<ProductCategory> productCategoryList = dao.queryProductCategoryList(shopId);
        for(ProductCategory pc : productCategoryList) {
            if (pc.getProductCategoryName().equals("1")) {
                int effectNum = dao.deleteProductCategory(pc.getProductCategoryId(), shopId);
                assertEquals(0, effectNum);
            }
        }
    }
}
