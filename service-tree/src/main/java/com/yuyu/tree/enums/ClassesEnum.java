package com.yuyu.tree.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClassesEnum {

    NOTABLETREE(0, "名木"),
    ONELEVEL(1, "一级古树"),
    TWOLEVEL(2, "二级古树"),
    THREELEVEL(3, "三级古树");

    private final int code;
    private final String text;

}
