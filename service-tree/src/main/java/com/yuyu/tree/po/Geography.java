package com.yuyu.tree.po;

import com.yuyu.tree.enums.AspectEnum;
import com.yuyu.tree.enums.CompactnessEnum;
import com.yuyu.tree.enums.PositionEnum;
import com.yuyu.tree.enums.SlopeEnum;
import lombok.Data;

/**
 * 立地条件信息
 */
@Data
public class Geography {

    private Long id;

    private Long treeId;

    /**
     * 地区
     */
    private Integer areaId;

    /**
     * 地址
     */
    private String address;

    /**
     * 经纬度
     */
    private byte[] gis;

    /**
     * 海拔
     */
    private Short altitude;

    /**
     * 坡向 0平地 1东 2西 3南 4北 5东南 6东北 7西南 8西北
     */
    private AspectEnum aspect;

    /**
     * 坡度 0平坡 1缓坡 2斜坡 3陡坡 4急坡 5险坡
     */
    private SlopeEnum slope;

    /**
     * 坡位 0平地 1脊部 2上部 3中部 4下部 5山谷
     */
    private PositionEnum position;

    /**
     * 土壤
     */
    private String soil;

    /**
     * 紧密度 0极紧密 1紧密 2中等 3较疏松 4疏松
     */
    private CompactnessEnum compactness;

}
