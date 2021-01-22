package com.yuyu.tree.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    NORMAL(0, "正常株"),
    WEAK(1, "衰弱株"),
    DANGER(2, "濒危株"),
    DEATH(3, "死亡株");

    private final int code;
    private final String text;
}
