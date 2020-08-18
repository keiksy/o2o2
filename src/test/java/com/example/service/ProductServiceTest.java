package com.example.service;

import com.example.BaseTest;
import com.example.Exception.ShopOperationException;
import com.example.dto.ImageHolder;
import com.example.dto.ProductExecution;
import com.example.entity.Product;
import com.example.entity.ProductCategory;
import com.example.entity.Shop;
import com.example.enums.ProductStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest extends BaseTest {
    @Autowired
    ProductService productService;

    @Test
    public void testAddProduct() throws ShopOperationException, FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(1L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品1");
        product.setPriority(20);
        product.setCreateTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        File thumbnailFile = new File("C:\\Users\\cai\\Desktop\\pic.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
        File productImg1 = new File("C:\\Users\\cai\\Desktop\\pic.jpg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("C:\\Users\\cai\\Desktop\\pic.jpg");
        InputStream is2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
        productImgList.add(new ImageHolder(productImg1.getName(), is1));
        productImgList.add(new ImageHolder(productImg2.getName(), is2));
        // 添加商品并验证
        ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
    }

    @Test
    public void testGetProductList() {
        Product condition = new Product();
        Shop shop = new Shop();
        shop.setShopId(28L);
        condition.setShop(shop);
        ProductExecution pe = productService.getProductList(condition, 1, 999);
        assertEquals(pe.getCount(), 7);
    }

    @Test
    public void testModifyProduct() throws ShopOperationException, FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(29L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(3L);
        product.setProductId(1L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("正式的商品");
        product.setProductDesc("正式的商品");
        File thumbnailFile = new File("C:\\Users\\cai\\Desktop\\wx.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
        File productImg1 = new File("C:\\Users\\cai\\Desktop\\wx.jpg");
        InputStream is1 = new FileInputStream(productImg1);
        List<ImageHolder> productImgList = new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(), is1));
        ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
        assertEquals(pe.getState(), ProductStateEnum.SUCCESS.getState());
    }

}
