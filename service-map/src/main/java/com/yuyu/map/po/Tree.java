package com.yuyu.map.po;

import lombok.Data;

/**
 * 树木基础信息
 */
@Data
public class Tree {

    private Long id;

    /**
     * 编号
     */
    private String identifier;

    /**
     * 级别 0名木 1一级 2二级 3三级
     */
    private Byte classes;

    /**
     * 分布 0散生 1群状
     */
    private Byte distribution;

    /**
     * 中文名
     */
    private String nameZh;

    /**
     * 拉丁名
     */
    private String nameLa;

    /**
     * 科
     */
    private String family;

    /**
     * 属
     */
    private String genus;

    /**
     * 估测树龄
     */
    private Short ageEstim;

    /**
     * 真实树龄
     */
    private Short ageReal;

    /**
     * 树高m
     */
    private Float height;

    /**
     * 东西冠幅m
     */
    private Short widthEw;

    /**
     * 南北冠幅m
     */
    private Short widthSn;

    /**
     * 胸围cm
     */
    private Short bust;

    /**
     * 权属 0国有 1集体 2个人 3其他
     */
    private Byte ownership;

}
