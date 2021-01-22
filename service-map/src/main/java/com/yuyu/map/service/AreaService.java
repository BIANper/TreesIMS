package com.yuyu.map.service;

import com.yuyu.map.po.Area;

import java.util.List;

public interface AreaService {

    List<Area> getAllProvince();

    List<Area> getAllChild(Integer pid);

    Area getArea(Integer areaId);

}
