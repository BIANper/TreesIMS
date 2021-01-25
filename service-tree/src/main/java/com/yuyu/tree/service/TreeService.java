package com.yuyu.tree.service;

import com.github.pagehelper.PageInfo;
import com.yuyu.tree.vo.PageVo;
import com.yuyu.tree.vo.TreeBaseVo;
import com.yuyu.tree.vo.TreeVo;

public interface TreeService {

    PageInfo<TreeBaseVo> getTreeBasePage(PageVo pageVo);

    TreeVo getTreeVo(Long treeId);

    Long saveTree(TreeVo treeVo, String username);

    void updateTreeBase(TreeVo treeVo, String username);

    void deleteTree(Long[] treeIds);


}
