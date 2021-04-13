package com.yuyu.tree.dao;

import com.yuyu.tree.po.Tree;
import com.yuyu.tree.vo.TreesSearchVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreeMapper {

    int deleteByPrimaryKey(Long id);

    long deleteByPrimaryKeys(Long[] id);

    int insert(Tree record);

    int insertSelective(Tree record);

    Tree selectByPrimaryKey(Long id);

    List<Tree> selectAll();

    List<Tree> selectByCond(TreesSearchVo searchVo);

    int updateByPrimaryKeySelective(Tree record);

    int updateByPrimaryKey(Tree record);
}