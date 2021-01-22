package com.yuyu.tree.service;

import com.github.pagehelper.PageInfo;
import com.yuyu.tree.po.Record;
import com.yuyu.tree.vo.PageVo;

public interface RecordService {

    PageInfo<Record> getRecordPage(PageVo pageVo, Long treeId);

}
