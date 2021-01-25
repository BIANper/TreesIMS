package com.yuyu.tree.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuyu.tree.dao.CareMapper;
import com.yuyu.tree.dao.TreeMapper;
import com.yuyu.tree.po.Care;
import com.yuyu.tree.po.Tree;
import com.yuyu.tree.service.CareService;
import com.yuyu.tree.vo.CareVo;
import com.yuyu.tree.vo.PageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CareServiceImpl implements CareService {

    @Autowired
    private CareMapper careMapper;

    @Autowired
    private TreeMapper treeMapper;

    @Override
    public PageInfo<CareVo> getWarnPage(PageVo pageVo) {
        Page<CareVo> page = PageHelper.startPage(pageVo);
        PageInfo<CareVo> pageInfo = page.toPageInfo();
        List<Care> carelist = careMapper.selectByGrowthStatus();
        List<CareVo> careVoList = carelist.stream().map(care -> {
            CareVo careVo = new CareVo();
            BeanUtils.copyProperties(care, careVo);
            Tree tree = treeMapper.selectByPrimaryKey(care.getTreeId());
            BeanUtils.copyProperties(tree, careVo);
            return careVo;
        }).collect(Collectors.toList());
        pageInfo.setList(careVoList);
        return pageInfo;
    }

    @Override
    public void updateCare(Care care) {
        careMapper.updateByPrimaryKeySelective(care);
    }
}
