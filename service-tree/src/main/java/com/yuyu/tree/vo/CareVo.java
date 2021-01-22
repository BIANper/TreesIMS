package com.yuyu.tree.vo;

import com.yuyu.tree.enums.ClassesEnum;
import com.yuyu.tree.po.Care;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CareVo extends Care {

    private String nameZh;

    private String identifier;

    private ClassesEnum classes;

}
