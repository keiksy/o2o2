package com.example.dao;

import com.example.BaseTest;
import com.example.entity.Product;
import com.example.entity.ProductCategory;
import com.example.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductImgDao productImgDao;
    @Autowired
    private ProductDao dao;

    @Test
    public void testAInsertProduct() throws Exception {
        Shop shop1 = new Shop();
        shop1.setShopId(1L);
        ProductCategory pc1 = new ProductCategory();
        pc1.setProductCategoryId(1L);
        Product product1 = new Product();
        product1.setProductName("test1");
        product1.setProductDesc("test desc1");
        product1.setImgAddr("test addr1");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setLastEditTime(new Date());
        product1.setShop(shop1);
        product1.setProductCategory(pc1);
        Product product2 = new Product();
        product2.setProductName("test2");
        product2.setProductDesc("test desc2");
        product2.setImgAddr("test addr2");
        product2.setPriority(2);
        product2.setEnableStatus(0);
        product2.setCreateTime(new Date());
        product2.setLastEditTime(new Date());
        product2.setShop(shop1);
        product2.setProductCategory(pc1);
        int effectedNum = dao.insertProduct(product1);
        assertEquals(1, effectedNum);
        effectedNum = dao.insertProduct(product2);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQueryProductList() {
        Product condition = new Product();
        Shop shop = new Shop();
        shop.setShopId(28L);
        condition.setShop(shop);
        List<Product> productList = dao.queryProductList(condition, 0, 999);
        assertEquals(7, productList.size());
    }

    @Test
    public void testQueryProductById() {
        long id = 4L;
        Product product = dao.queryProductById(id);
        System.out.println(product.getProductName());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(29L);
        product.setProductId(1L);
        product.setProductName("dadadajiba");
        product.setShop(shop);
        dao.updateProduct(product);
    }

    @Test
    public void testUpdatePCID() {
        Long pcid = 1L;
        dao.updateProductCategoryToNull(pcid);
    }
}
