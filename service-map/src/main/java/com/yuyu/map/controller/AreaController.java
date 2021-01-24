package com.yuyu.map.controller;

import com.yuyu.common.utils.R;
import com.yuyu.map.po.Area;
import com.yuyu.map.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags={"省市区数据接口"})
@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @ApiOperation(httpMethod = "GET",value = "获取所有的省")
    @GetMapping("/province")
    public R getAllProvince() {
        List<Area> allProvince = areaService.getAllProvince();
        return R.ok().put("data",allProvince);
    }

    @ApiOperation(httpMethod = "GET",value = "获取管辖的市/区")
    @GetMapping("/child/{pid}")
    public R getChild(@PathVariable("pid") Integer pid) {
        List<Area> allChild = areaService.getAllChild(pid);
        return R.ok().put("data",allChild);
    }

    @ApiOperation(httpMethod = "GET",value = "获取指定的省/市/区")
    @GetMapping("/{id}")
    public R getArea(@PathVariable("id") Integer id) {
        Area area = areaService.getArea(id);
        return R.ok().put("data",area.getName());
    }

}
