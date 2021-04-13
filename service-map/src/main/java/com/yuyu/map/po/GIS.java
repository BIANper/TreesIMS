package com.yuyu.map.po;

import lombok.Data;

@Data
public class GIS {

    private Long id;

    private Long treeId;

    /**
     * 经纬度
     */
    private String gis;
}
