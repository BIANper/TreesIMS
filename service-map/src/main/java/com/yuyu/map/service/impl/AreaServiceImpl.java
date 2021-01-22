package com.yuyu.map.service.impl;

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
}
