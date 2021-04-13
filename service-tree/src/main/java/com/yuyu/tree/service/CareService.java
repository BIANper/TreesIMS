package com.yuyu.tree.service;

import com.github.pagehelper.PageInfo;
import com.yuyu.tree.po.Care;
import com.yuyu.tree.vo.CareVo;
import com.yuyu.tree.vo.PageVo;

import java.util.List;
import java.util.Map;


public interface CareService {

    PageInfo<CareVo> getWarnPage(PageVo pageVo);

    void updateCare(Care care);

    Map<String, List<Map<String, String>>> getChartData();

    void addCare(Care care, Long treeId);
}
