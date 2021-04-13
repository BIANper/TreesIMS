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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        PageHelper.clearPage();
        return pageInfo;
    }

    @Override
    public void updateCare(Care care) {
        careMapper.updateByPrimaryKeySelective(care);
    }

    @Override
    public Map<String, List<Map<String, String>>> getChartData() {
        HashMap<String, List<Map<String, String>>> map = new HashMap<>();
        List<Map<String, String>> statusTotal = careMapper.selectStatusTotal();
        statusTotal.forEach( statusMap -> {
            switch (statusMap.get("status")) {
                case "0":
                    statusMap.put("status","正常株");
                    break;
                case "1":
                    statusMap.put("status","衰弱株");
                    break;
                case "2":
                    statusMap.put("status","濒危株");
                    break;
            }
        });
        List<Map<String, String>> envTotal = careMapper.selectEnvTotal();
        envTotal.forEach( envMap -> {
            switch (envMap.get("env")) {
                case "0":
                    envMap.put("env","良好");
                    break;
                case "1":
                    envMap.put("env","中等");
                    break;
                case "2":
                    envMap.put("env","差");
                    break;
            }
        });
        map.put("status",statusTotal);
        map.put("env",envTotal);
        return map;
    }

    @Override
    public void addCare(Care care, Long treeId) {
        care.setTreeId(treeId);
        careMapper.insert(care);
    }
}
