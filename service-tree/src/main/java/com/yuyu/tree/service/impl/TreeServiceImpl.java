package com.yuyu.tree.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuyu.common.utils.R;
import com.yuyu.tree.dao.CareMapper;
import com.yuyu.tree.dao.GeographyMapper;
import com.yuyu.tree.dao.RecordMapper;
import com.yuyu.tree.dao.TreeMapper;
import com.yuyu.tree.feign.MapFeignService;
import com.yuyu.tree.po.Care;
import com.yuyu.tree.po.Geography;
import com.yuyu.tree.po.Record;
import com.yuyu.tree.po.Tree;
import com.yuyu.tree.service.TreeService;
import com.yuyu.tree.vo.PageVo;
import com.yuyu.tree.vo.TreeBaseVo;
import com.yuyu.tree.vo.TreeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
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
    private RecordMapper recordMapper;
    @Autowired
    private MapFeignService mapFeignService;

    @Override
    public PageInfo<TreeBaseVo> getTreeBasePage(PageVo pageVo) {
        Page<TreeBaseVo> page = PageHelper.startPage(pageVo);
        List<Tree> treeList = treeMapper.selectAll();
        PageInfo<TreeBaseVo> pageInfo = page.toPageInfo();
        List<TreeBaseVo> treeBaseVoList = treeList.stream().map(tree -> {
            TreeBaseVo baseVo = new TreeBaseVo();
            BeanUtils.copyProperties(tree, baseVo);
            Geography geography = geographyMapper.selectByTreeId(tree.getId());
            BeanUtils.copyProperties(geography,baseVo);
            R area = mapFeignService.getArea(geography.getAreaId());
            baseVo.setArea(area.get("data"));
            return baseVo;
        }).collect(Collectors.toList());
        pageInfo.setList(treeBaseVoList);
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
        return treeVo;
    }

    @Override
    public void saveTree(TreeVo treeVo, String username) {
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
        Record record = new Record();
        record.setType(true);
        record.setTreeId(tree.getId());
        record.setCreator(username);
        record.setCreateTime(new Timestamp(new Date().getTime()));
        recordMapper.insert(record);
    }

    @Override
    public void updateTreeBase(TreeVo treeVo, String username) {
        Tree tree = new Tree();
        BeanUtils.copyProperties(treeVo,tree);
        Geography geography = new Geography();
        BeanUtils.copyProperties(treeVo, geography);
        geography.setTreeId(tree.getId());
        geographyMapper.updateByPrimaryKeySelective(geography);
        Record record = new Record();
        record.setType(false);
        record.setTreeId(tree.getId());
        record.setCreator(username);
        record.setCreateTime(new Timestamp(new Date().getTime()));
        recordMapper.insert(record);
    }

    @Override
    public void deleteTree(Long[] treeIds) {
        treeMapper.deleteByPrimaryKeys(treeIds);
        geographyMapper.deleteByTreeIds(treeIds);
        careMapper.deleteByTreeIds(treeIds);
        recordMapper.deleteByTreeIds(treeIds);
    }

}
