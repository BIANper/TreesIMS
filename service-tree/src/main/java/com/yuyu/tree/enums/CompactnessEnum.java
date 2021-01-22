package com.yuyu.tree.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CompactnessEnum {

    VERYCOMPACT(0, "极紧密"),
    COMPACTN(1, "紧密"),
    MEDIUM(2, "中等"),
    BITLOOSE(5,"较疏松"),
    LOOSE(4,"疏松");

    private final int code;
    private final String text;
}
