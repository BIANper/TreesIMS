package com.yuyu.tree.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnvEnum {

    WELL(0, "好"),
    MEDIUM(1, "中"),
    BAD(2, "差");

    private final int code;
    private final String text;
}
