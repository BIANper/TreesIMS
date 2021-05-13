package com.yuyu.map.dao;

import com.yuyu.map.po.GIS;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GisMapper {

    List<GIS> getAll();

    List<GIS> getByTreeIds(List<Integer> treeIds);
}
