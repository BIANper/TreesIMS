package com.yuyu.tree.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuyu.tree.dao.TimsLogMapper;
import com.yuyu.tree.po.TimsLog;
import com.yuyu.tree.service.LogService;
import com.yuyu.tree.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogServiceImpl implements LogService {

    @Autowired
    private TimsLogMapper logMapper;

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

}
