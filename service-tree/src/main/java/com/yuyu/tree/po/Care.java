package com.yuyu.tree.po;

import lombok.Data;

/**
 * 树木养护信息
 */
@Data
public class Care {

    private Long id;

    private Long treeId;

    /**
     * 生长势 0正常株 1衰弱株 2濒危株 3死亡株
     */
    private Byte growthStatus;

    /**
     * 生长环境 0好 1中 2差
     */
    private Byte growthEnv;

    /**
     * 保护
     */
    private String protection;

    /**
     * 复壮
     */
    private String rejuvenate;

    /**
     * 补充
     */
    private String description;

}
