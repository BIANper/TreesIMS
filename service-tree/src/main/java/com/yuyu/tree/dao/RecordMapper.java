package com.yuyu.tree.dao;

import com.yuyu.tree.po.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByTreeIds(Long[] id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Long id);

    List<Record> selectByTreeId(Long treeId);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);
}