package com.yuyu.tree.service;

import com.github.pagehelper.PageInfo;
import com.yuyu.tree.po.Care;
import com.yuyu.tree.vo.CareVo;
import com.yuyu.tree.vo.PageVo;
import com.yuyu.tree.vo.TreeVo;

public interface CareService {

    PageInfo<CareVo> getWarnPage(PageVo pageVo);

    void updateCare(Care care);

}
