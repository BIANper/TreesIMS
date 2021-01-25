package com.yuyu.tree.dao;

import com.yuyu.tree.po.TimsLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimsLogMapper {

    void insert(TimsLog timsLog);

    List<TimsLog> selectByTreeId(Long treeId);

}
