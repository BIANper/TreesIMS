package com.yuyu.map.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuyu.map.dao.AreaMapper;
import com.yuyu.map.po.Area;
import com.yuyu.map.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> getAllProvince() {
        List<Area> allProvince = areaMapper.getAllProvince();
        return allProvince;
    }

    @Override
    public List<Area> getAllChild(Integer pid) {
        List<Area> childList = areaMapper.getByPid(pid);
        return childList;
    }

    @Override
    public Area getArea(Integer areaId) {
        Area area = areaMapper.getById(areaId);
        return area;
    }

    @Override
    public JSONArray getAreaCenter(Integer areaId) {
        String center = areaMapper.getCenterById(areaId);
        String sb = new StringBuffer(center.substring(6)).reverse().substring(1);
        String[] s = new StringBuffer(sb).reverse().toString().split(" ");
        JSONArray array = new JSONArray();
        array.set(0, s[0]);
        array.set(1, s[1]);
        return array;
    }
}
