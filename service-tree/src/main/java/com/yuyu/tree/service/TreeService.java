package com.yuyu.tree.service;

import com.github.pagehelper.PageInfo;
import com.yuyu.tree.vo.PageVo;
import com.yuyu.tree.vo.TreeBaseVo;
import com.yuyu.tree.vo.TreeVo;
import com.yuyu.tree.vo.TreesSearchVo;

public interface TreeService {

    PageInfo<TreeBaseVo> getTreeBasePage(TreesSearchVo searchVo);

    TreeVo getTreeVo(Long treeId);

    Long saveTree(TreeVo treeVo, String username);

    Long updateTreeBase(TreeVo treeVo, String username);

    Long deleteTree(Long[] treeIds);


}
