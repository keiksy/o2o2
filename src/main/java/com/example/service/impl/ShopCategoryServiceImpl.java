package com.example.service.impl;

import com.example.Exception.ShopCategoryOperationException;
import com.example.cache.JedisUtil;
import com.example.dao.ShopCategoryDao;
import com.example.entity.ShopCategory;
import com.example.service.ShopCategoryService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        String key = SCLISTKEY;
        ObjectMapper mapper = new ObjectMapper();
        List<ShopCategory> shopCategoryList = null;
        if (shopCategoryCondition == null) {
            key = key + "_allfirstlevel";
        } else if (shopCategoryCondition.getParent() != null &&
                shopCategoryCondition.getParent().getShopCategoryId() != null) {
            key = key + "_parent" + shopCategoryCondition.getParent().getShopCategoryId();
        } else if (shopCategoryCondition != null) {
            key = key + "_allsecondlevel";
        }

        if (!jedisKeys.exists(key)) {
            String jsonString = null;
            shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
            try {
                jsonString = mapper.writeValueAsString(shopCategoryList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new ShopCategoryOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
            try {
                shopCategoryList = mapper.readValue(jsonString, javaType);
            } catch (JsonParseException e) {
                e.printStackTrace();
                throw new ShopCategoryOperationException(e.getMessage());
            } catch (JsonMappingException e) {
                e.printStackTrace();
                throw new ShopCategoryOperationException(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                throw new ShopCategoryOperationException(e.getMessage());
            }
        }
        return shopCategoryList;
    }
}
