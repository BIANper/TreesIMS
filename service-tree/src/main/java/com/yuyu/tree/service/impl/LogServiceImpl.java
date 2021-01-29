package com.yuyu.tree.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuyu.tree.dao.TimsLogMapper;
import com.yuyu.tree.dao.TreeMapper;
import com.yuyu.tree.po.TimsLog;
import com.yuyu.tree.po.Tree;
import com.yuyu.tree.service.LogService;
import com.yuyu.tree.vo.PageVo;
import com.yuyu.tree.vo.TimsLogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LogServiceImpl implements LogService {

    @Autowired
    private TimsLogMapper logMapper;

    @Autowired
    private TreeMapper treeMapper;

    @Override
    public void saveLog(TimsLog timsLog) {
        logMapper.insert(timsLog);
    }

    @Override
    public PageInfo<TimsLog> getLogPage(PageVo pageVo, Long treeId) {
        Page<TimsLog> page = PageHelper.startPage(pageVo);
        PageInfo<TimsLog> pageInfo = page.toPageInfo();
        List<TimsLog> logList = logMapper.selectByTreeId(treeId);
        pageInfo.setList(logList);
        return pageInfo;
    }

    @Override
    public PageInfo<TimsLogVo> getRecentPage(PageVo pageVo) {
        Page<TimsLogVo> page = PageHelper.startPage(pageVo);
        PageInfo<TimsLogVo> pageInfo = page.toPageInfo();
        List<TimsLog> logList = logMapper.selectAll();
        List<TimsLogVo> logVoList = logList.stream().map(log -> {
            TimsLogVo logVo = new TimsLogVo();
            BeanUtils.copyProperties(log, logVo);
            Tree tree = treeMapper.selectByPrimaryKey(log.getTreeId());
            BeanUtils.copyProperties(tree, logVo);
            return logVo;
        }).collect(Collectors.toList());
        pageInfo.setList(logVoList);
        return pageInfo;
    }

}
