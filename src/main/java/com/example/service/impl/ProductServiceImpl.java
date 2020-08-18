package com.example.service.impl;

import com.example.Exception.ProductOperationException;
import com.example.dao.ProductDao;
import com.example.dao.ProductImgDao;
import com.example.dto.ImageHolder;
import com.example.dto.ProductExecution;
import com.example.entity.Product;
import com.example.entity.ProductImg;
import com.example.enums.ProductStateEnum;
import com.example.service.ProductService;
import com.example.util.ImageUtil;
import com.example.util.PageCalculator;
import com.example.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao dao;
    @Autowired
    private ProductImgDao productImgDao;

    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imageHolderList) throws ProductOperationException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(1);
            if (thumbnail != null) {
                addThumbnail(product, thumbnail);
            }
            try {
                int effectedNum = dao.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品失败：" + e.toString());
            }
            if (imageHolderList != null && imageHolderList.size() > 0) {
                addProductImgList(product, imageHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }


    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = dao.queryProductList(productCondition, rowIndex, pageSize);
        int count = dao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }

    @Override
    public Product getProductById(long productId) {
        return dao.queryProductById(productId);
    }

    @Override
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setLastEditTime(new Date());
            if (thumbnail != null) {
                Product tempProduct = dao.queryProductById(product.getProductId());
                if (tempProduct.getImgAddr() != null) {
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(product, thumbnail);
            }
            if (productImgHolderList != null && productImgHolderList.size() > 0) {
                deleteProductImgList(product.getProductId());
                addProductImgList(product, productImgHolderList);
            }
            try {
                int effectedNum = dao.updateProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new ProductOperationException("更新商品信息失败："+e.toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    private void addThumbnail(Product product, ImageHolder thumbnail) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(thumbnailAddr);
    }

    private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        for (ImageHolder productImgHolder : productImgHolderList) {
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }

        if (productImgList.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum < 0) {
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情图片失败" + e.toString());
            }
        }
    }

    private void deleteProductImgList(long productId) {
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        for (ProductImg productImg : productImgList) {
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        productImgDao.deleteProductImgByProductId(productId);
    }
}
