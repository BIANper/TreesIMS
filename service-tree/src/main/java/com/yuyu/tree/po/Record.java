package com.yuyu.tree.po;

import lombok.Data;
import java.util.Date;

/**
 * 操作记录信息
 */
@Data
public class Record {

    private Long id;

    private Long treeId;

    /**
     * 类型 0添加 1更新
     */
    private Boolean type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 操作
     */
    private String action;

}
