package com.example.service.impl;

import com.example.Exception.ShopOperationException;
import com.example.dao.ShopDao;
import com.example.dto.ImageHolder;
import com.example.dto.ShopExecution;
import com.example.entity.Shop;
import com.example.enums.ShopStateEnum;
import com.example.service.ShopService;
import com.example.util.ImageUtil;
import com.example.util.PageCalculator;
import com.example.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList != null) {
            se.setShopList(shopList);
            se.setCount(count);
        } else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        if (shop==null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum<=0) {
                throw new ShopOperationException("店铺创建失败");
            } else {
                if (thumbnail.getImage()!=null) {
                    try {
                        addShopImg(shop, thumbnail.getImage(), thumbnail.getImageName());
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:"+e.getMessage());
                    }
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum<=0) {
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }

            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        if (shop==null || shop.getShopId()==null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            //判断是否需要更新图片
            try {
                if (thumbnail.getImage() != null && !thumbnail.getImageName().equals("")) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, thumbnail.getImage(), thumbnail.getImageName());
                }
                //更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOperationException("modifyShop error:" + e.getMessage());
            }
        }
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(new ImageHolder(fileName, shopImgInputStream), dest);
        shop.setShopImg(shopImgAddr);
    }
}
