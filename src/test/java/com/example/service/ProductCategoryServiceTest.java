package com.example.service;

import com.example.BaseTest;
import com.example.dto.ProductCategoryExecution;
import com.example.dto.ProductExecution;
import com.example.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductCategoryServiceTest extends BaseTest {
    @Autowired
    private ProductCategoryService service;

    @Test
    public void testDeleteProductCategory() {
        Long pcid = 6L;
        Long shopId = 28L;
        ProductCategoryExecution pce = service.deleteProductCategory(pcid, shopId);
        System.out.println(pce.getStateInfo());
    }
}
