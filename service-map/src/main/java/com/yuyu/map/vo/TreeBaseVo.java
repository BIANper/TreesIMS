package com.yuyu.map.vo;

import com.yuyu.map.po.Tree;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TreeBaseVo extends Tree {



    /**
     * 地区
     */
    private String area;

    /**
     * 地址
     */
    private String address;

}
