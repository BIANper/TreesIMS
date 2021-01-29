package com.yuyu.tree.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TimsLog implements Serializable {

    private static final long serialVersionUID = 1553103715908695332L;

    private Long id;
    private Long treeId;
    private String creator;
    private Integer action;
    private String params;
    private Date createTime;
}
