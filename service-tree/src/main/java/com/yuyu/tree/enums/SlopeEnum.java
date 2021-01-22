package com.yuyu.tree.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SlopeEnum {

    FLAT(0, "平地"),
    GENTLE(1,"缓坡"),
    NORMAL(2,"斜坡"),
    STEEP(3,"陡坡"),
    CLIFF(4,"急坡"),
    DANGER(5,"危坡");

    private final int code;
    private final String text;

}
