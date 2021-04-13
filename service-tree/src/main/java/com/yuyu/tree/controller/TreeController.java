package com.yuyu.tree.controller;

import com.github.pagehelper.PageInfo;
import com.yuyu.common.utils.R;
import com.yuyu.tree.service.TreeService;
import com.yuyu.tree.vo.TreeBaseVo;
import com.yuyu.tree.vo.TreeVo;
import com.yuyu.tree.vo.TreesSearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Api(tags={"树木数据接口"})
@RestController
public class TreeController {

    @Autowired
    private TreeService treeService;

    @ApiOperation(httpMethod = "GET",value = "获取树木基本数据页")
    @GetMapping("/list/base")
    public R getTreeBaseList(TreesSearchVo searchVo) {
        PageInfo<TreeBaseVo> treeList = treeService.getTreeBasePage(searchVo);
        return R.ok().put("data",treeList);
    }

    @ApiOperation(httpMethod = "GET",value = "获取指定树木全部数据")
    @GetMapping("/{id}")
    public R getTree(@PathVariable("id") Long treeId) {
        TreeVo treeVo = treeService.getTreeVo(treeId);
        return R.ok().put("data",treeVo);
    }

    @ApiOperation(httpMethod = "POST",value = "新增树木数据")
    @PostMapping("/")
    public R addTree(TreeVo treeVo,
                     HttpSession session) {
        String username = getUsername(session);
        treeService.saveTree(treeVo, username);
        return R.ok();
    }

    @ApiOperation(httpMethod = "PUT",value = "更新树木数据")
    @PutMapping("/")
    public R updateTree(TreeVo treeVo,
                        HttpSession session) {
        String username = getUsername(session);
        treeService.updateTreeBase(treeVo,username);
        return R.ok();
    }

    @ApiOperation(httpMethod = "DELETE",value = "删除树木数据")
    @DeleteMapping("/")
    public R deleteTree(Long[] treeIds) {
        treeService.deleteTree(treeIds);
        return R.ok();
    }

    private String getUsername(HttpSession session) {
        SecurityContextImpl securityContext =
                (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
        return securityContext.getAuthentication().getName();
    }

}
