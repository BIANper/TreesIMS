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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @ApiOperation(httpMethod = "POST",value = "新增养护数据")
    @PostMapping("/")
    public R addCare(Care care, Long treeId) {
        careService.addCare(care, treeId);
        return R.ok();
    }

    @ApiOperation(httpMethod = "PUT",value = "获取图表数据")
    @GetMapping("/chart")
    public R getChartData() {
        Map<String, List<Map<String, String>>> data = careService.getChartData();
        return R.ok().put("data",data);
    }


}
