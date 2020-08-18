package com.example.service.impl;

import com.example.Exception.HeadLineOperationException;
import com.example.cache.JedisUtil;
import com.example.dao.HeadLineDao;
import com.example.entity.HeadLine;
import com.example.service.HeadLineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        String key = HLLLISTKEY;
        List<HeadLine> headLines = null;
        ObjectMapper mapper = new ObjectMapper();
        if (headLineCondition != null && headLineCondition.getEnableStatus() != null) {
            key = key + "_" + headLineCondition.getEnableStatus();
        }
        if (!jedisKeys.exists(key)) {
            headLines = headLineDao.queryHeadLine(headLineCondition);
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(headLines);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new HeadLineOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
            try {
                headLines = mapper.readValue(jsonString, javaType);
            } catch (IOException e) {
                e.printStackTrace();
                throw new HeadLineOperationException(e.getMessage());
            }
        }
        return headLines;
    }
}
