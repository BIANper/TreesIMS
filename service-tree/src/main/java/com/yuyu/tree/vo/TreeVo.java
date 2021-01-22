package com.yuyu.tree.vo;

import com.yuyu.tree.enums.*;
import com.yuyu.tree.po.Tree;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TreeVo extends Tree {

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 地区
     */
    private Long areaId;

    /**
     * 地址
     */
    private String address;

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

    /**
     * 生长势 0正常株 1衰弱株 2濒危株 3死亡株
     */
    private StatusEnum growthStatus;

    /**
     * 生长环境 0好 1中 2差
     */
    private EnvEnum growthEnv;

    /**
     * 保护
     */
    private Integer protection;

    /**
     * 复壮
     */
    private Integer rejuvenate;

    /**
     * 补充
     */
    private String description;

}
