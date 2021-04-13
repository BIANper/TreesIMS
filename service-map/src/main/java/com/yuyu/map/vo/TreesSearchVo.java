package com.yuyu.map.vo;

import com.github.pagehelper.IPage;
import lombok.Data;

@Data
public class TreesSearchVo implements IPage {

    private Integer pageNum = 1;

    private Integer pageSize = 20;

    private String orderBy = null;

    private String identifier;

    private String name;

    private Integer classes;

}
