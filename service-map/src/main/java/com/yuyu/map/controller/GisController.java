package com.yuyu.map.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuyu.common.utils.R;
import com.yuyu.map.po.Area;
import com.yuyu.map.po.GIS;
import com.yuyu.map.service.GisService;
import com.yuyu.map.vo.TreesSearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags={"GIS数据接口"})
@RestController
@RequestMapping("/gis")
public class GisController {

    @Autowired
    private GisService gisService;

    @ApiOperation(httpMethod = "GET",value = "获取所有")
    @GetMapping("/")
    public R getAll() {
        List<JSONObject> all = gisService.getAll();
        return R.ok().put("data",all);
    }

    @ApiOperation(httpMethod = "GET",value = "获取指定GIS")
    @GetMapping("/search")
    public R getGisList(TreesSearchVo searchVo) {
        List<JSONObject> all = gisService.getGisList(searchVo);
        return R.ok().put("data",all);
    }

}
