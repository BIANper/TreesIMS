package com.yuyu.tree.vo;

import com.github.pagehelper.IPage;
import lombok.Data;

@Data
public class PageVo implements IPage {

    private Integer pageNum = 1;

    private Integer pageSize = 20;

    private String orderBy = null;

    public PageVo(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
