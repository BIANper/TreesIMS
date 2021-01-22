package com.yuyu.common.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 2682057009523132647L;

    private Long id;

    private String name;

    private String nameZh;

}
