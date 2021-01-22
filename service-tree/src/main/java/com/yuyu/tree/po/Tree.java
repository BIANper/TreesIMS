package com.yuyu.tree.po;

import com.yuyu.tree.enums.ClassesEnum;
import com.yuyu.tree.enums.OwnershipEnum;
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
    private ClassesEnum classes;

    /**
     * 分布 0散生 1群状
     */
    private Boolean distribution;

    /**
     * 中文名
     */
    private String nameZh;

    /**
     * 拉丁名
     */
    private String nameLa;

    /**
     * 科属
     */
    private Long genusId;

    /**
     * 估测树龄
     */
    private Short ageEstim;

    /**
     * 真实树龄
     */
    private Short agerReal;

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
    private OwnershipEnum ownership;

}
