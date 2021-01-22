package com.yuyu.tree.vo;

import com.github.pagehelper.IPage;
import lombok.Data;

@Data
public class PageVo implements IPage {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String orderBy = null;

}
