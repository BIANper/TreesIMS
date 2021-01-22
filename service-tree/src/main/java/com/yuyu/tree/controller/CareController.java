package com.yuyu.tree.controller;

import com.github.pagehelper.PageInfo;
import com.yuyu.common.utils.R;
import com.yuyu.tree.po.Care;
import com.yuyu.tree.service.CareService;
import com.yuyu.tree.vo.CareVo;
import com.yuyu.tree.vo.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"树木养护数据接口"})
@RestController
@RequestMapping("/care")
public class CareController {

    @Autowired
    private CareService careService;

    @ApiOperation(httpMethod = "GET",value = "获取预警数据页")
    @GetMapping("/warn")
    public R getWarnList(PageVo pageVo) {
        PageInfo<CareVo> page = careService.getWarnPage(pageVo);
        return R.ok().put("data",page);
    }

    @ApiOperation(httpMethod = "PUT",value = "更新养护数据")
    @PutMapping("/")
    public R updateCare(Care care) {
        careService.updateCare(care);
        return R.ok();
    }


}
