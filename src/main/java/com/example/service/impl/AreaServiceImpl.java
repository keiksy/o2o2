package com.example.service.impl;

import com.example.Exception.AreaOperationException;
import com.example.cache.JedisUtil;
import com.example.dao.AreaDao;
import com.example.entity.Area;
import com.example.service.AreaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Override
    @Transactional
    public List<Area> getAreaList() {
        String key = AREALISTKEY;
        List<Area> areaList = null;
        ObjectMapper mapper = new ObjectMapper();
        if (!jedisKeys.exists(key)) {
            areaList = areaDao.queryArea();
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(areaList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new AreaOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
            try {
                areaList = mapper.readValue(jsonString, javaType);
            } catch (IOException e) {
                throw new AreaOperationException(e.getMessage());
            }
        }
        return areaList;
    }
}
