package com.yuyu.tree.service;

import com.github.pagehelper.PageInfo;
import com.yuyu.tree.po.TimsLog;
import com.yuyu.tree.vo.PageVo;

public interface LogService {
    void saveLog(TimsLog timsLog);

    PageInfo<TimsLog> getLogPage(PageVo pageVo, Long treeId);
}
