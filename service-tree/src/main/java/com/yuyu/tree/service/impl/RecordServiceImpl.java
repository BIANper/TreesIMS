package com.yuyu.tree.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuyu.tree.dao.RecordMapper;
import com.yuyu.tree.po.Record;
import com.yuyu.tree.service.RecordService;
import com.yuyu.tree.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public PageInfo<Record> getRecordPage(PageVo pageVo, Long treeId) {
        Page<Record> page = PageHelper.startPage(pageVo);
        PageInfo<Record> pageInfo = page.toPageInfo();
        List<Record> recordList = recordMapper.selectByTreeId(treeId);
        pageInfo.setList(recordList);
        return pageInfo;
    }
}
