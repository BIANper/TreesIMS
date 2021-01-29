package com.yuyu.tree.dao;

import com.yuyu.tree.po.Care;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CareMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByTreeIds(Long[] id);

    int insert(Care record);

    int insertSelective(Care record);

    Care selectByPrimaryKey(Long id);

    Care selectByTreeId(Long treeId);

    List<Care> selectByGrowthStatus();

    int updateByPrimaryKeySelective(Care record);

    int updateByPrimaryKey(Care record);

    List<Map<String, String>> selectStatusTotal();

    List<Map<String, String>> selectEnvTotal();
}