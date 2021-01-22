package com.yuyu.map.dao;

import com.yuyu.map.po.Area;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaMapper {

    List<Area> getAllProvince();

    List<Area> getByPid(Integer pid);

    Area getById(Integer areaId);

}
