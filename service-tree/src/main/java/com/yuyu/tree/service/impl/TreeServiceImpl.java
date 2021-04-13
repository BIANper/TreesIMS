package com.yuyu.tree.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuyu.common.annotation.SysLog;
import com.yuyu.common.enums.ActEnum;
import com.yuyu.common.utils.R;
import com.yuyu.tree.dao.CareMapper;
import com.yuyu.tree.dao.GeographyMapper;
import com.yuyu.tree.dao.TreeMapper;
import com.yuyu.tree.feign.MapFeignService;
import com.yuyu.tree.po.Care;
import com.yuyu.tree.po.Geography;
import com.yuyu.tree.po.Tree;
import com.yuyu.tree.service.TreeService;
import com.yuyu.tree.vo.PageVo;
import com.yuyu.tree.vo.TreeBaseVo;
import com.yuyu.tree.vo.TreeVo;
import com.yuyu.tree.vo.TreesSearchVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeMapper treeMapper;
    @Autowired
    private GeographyMapper geographyMapper;
    @Autowired
    private CareMapper careMapper;
    @Autowired
    private MapFeignService mapFeignService;

    @Override
    public PageInfo<TreeBaseVo> getTreeBasePage(TreesSearchVo searchVo) {
        Page<TreeBaseVo> page = PageHelper.startPage(searchVo);
        List<Tree> treeList = treeMapper.selectByCond(searchVo);
        PageInfo<TreeBaseVo> pageInfo = page.toPageInfo();
        List<TreeBaseVo> treeBaseVoList = treeList.stream().map(tree -> {
            TreeBaseVo baseVo = new TreeBaseVo();
            BeanUtils.copyProperties(tree, baseVo);
            Geography geography = geographyMapper.selectByTreeId(tree.getId());
            BeanUtils.copyProperties(geography,baseVo);
            R area = mapFeignService.getArea(geography.getAreaId());
            baseVo.setArea((String) area.get("data"));
            baseVo.setId(tree.getId());
            return baseVo;
        }).collect(Collectors.toList());
        pageInfo.setList(treeBaseVoList);
        PageHelper.clearPage();
        return pageInfo;
    }

    @Override
    public TreeVo getTreeVo(Long treeId) {
        Tree tree = treeMapper.selectByPrimaryKey(treeId);
        TreeVo treeVo = new TreeVo();
        BeanUtils.copyProperties(tree, treeVo);
        Geography geography = geographyMapper.selectByTreeId(treeId);
        BeanUtils.copyProperties(geography,treeVo);
        Care care = careMapper.selectByTreeId(treeId);
        BeanUtils.copyProperties(care,treeVo);
        R area = mapFeignService.getArea(geography.getAreaId());
        treeVo.setArea((String) area.get("data"));
        treeVo.setId(tree.getId());
        return treeVo;
    }

    @Override
    @SysLog(ActEnum.CREATE)
    @Transactional
    public Long saveTree(TreeVo treeVo, String username) {
        Tree tree = new Tree();
        BeanUtils.copyProperties(treeVo,tree);
        treeMapper.insert(tree);
        Geography geography = new Geography();
        BeanUtils.copyProperties(treeVo, geography);
        geography.setTreeId(tree.getId());
        geographyMapper.insert(geography);
        Care care = new Care();
        BeanUtils.copyProperties(treeVo, care);
        care.setTreeId(tree.getId());
        careMapper.insert(care);
        return tree.getId();
    }

    @Override
    @SysLog(ActEnum.UPDATA)
    @Transactional
    public Long updateTreeBase(TreeVo treeVo, String username) {
        Tree tree = new Tree();
        BeanUtils.copyProperties(treeVo,tree);
        treeMapper.updateByPrimaryKeySelective(tree);
        Geography geography = new Geography();
        BeanUtils.copyProperties(treeVo, geography);
        geography.setTreeId(tree.getId());
        return geographyMapper.updateByTreeIdSelective(geography);
    }

    @Override
    @SysLog(ActEnum.DELETE)
    @Transactional
    public Long deleteTree(Long[] treeIds) {
        geographyMapper.deleteByTreeIds(treeIds);
        careMapper.deleteByTreeIds(treeIds);
        return treeMapper.deleteByPrimaryKeys(treeIds);
    }

}
