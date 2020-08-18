package com.example.service;

import com.example.entity.Area;

import java.util.List;

public interface AreaService {
    String AREALISTKEY = "arealist";

    List<Area> getAreaList();
}
