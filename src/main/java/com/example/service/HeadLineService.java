package com.example.service;

import com.example.entity.HeadLine;

import java.io.IOException;
import java.util.List;

public interface HeadLineService {
    String HLLLISTKEY = "headlinelist";
    List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
