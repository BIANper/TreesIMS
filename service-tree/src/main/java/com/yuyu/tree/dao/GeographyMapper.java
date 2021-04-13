package com.yuyu.tree.dao;

import com.yuyu.tree.po.Geography;
import org.springframework.stereotype.Repository;

@Repository
public interface GeographyMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByTreeIds(Long[] id);

    int insert(Geography record);

    int insertSelective(Geography record);

    Geography selectByPrimaryKey(Long id);

    Geography selectByTreeId(Long treeId);

    long updateByTreeIdSelective(Geography record);

    int updateByPrimaryKeyWithBLOBs(Geography record);

    int updateByPrimaryKey(Geography record);
}