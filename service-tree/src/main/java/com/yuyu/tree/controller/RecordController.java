package com.yuyu.tree.controller;

import com.github.pagehelper.PageInfo;
import com.yuyu.common.utils.R;
import com.yuyu.tree.po.Care;
import com.yuyu.tree.po.Record;
import com.yuyu.tree.service.CareService;
import com.yuyu.tree.service.RecordService;
import com.yuyu.tree.vo.CareVo;
import com.yuyu.tree.vo.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"树木操作记录接口"})
@RestController
public class RecordController {

    @Autowired
    private RecordService recordService;

    @ApiOperation(httpMethod = "GET",value = "获取指定的记录页")
    @GetMapping("/record/{id}")
    public R getRecordList(PageVo pageVo,
                           @PathVariable("id") Long treeId) {
        PageInfo<Record> page = recordService.getRecordPage(pageVo,treeId);
        return R.ok().put("data",page);
    }

}
