package com.yuyu.tree.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PositionEnum {

    FLAT(0, "平地"),
    RIDGE(1,"脊部"),
    TOP(2,"上部"),
    CENTER(3,"中部"),
    BOTTOM(4,"下部"),
    VALLEY(5,"山谷");

    private final int code;
    private final String text;

}
