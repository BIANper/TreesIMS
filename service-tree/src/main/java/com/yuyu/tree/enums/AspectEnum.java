package com.yuyu.tree.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AspectEnum {

    FLAT(0, "平地"),
    EAST(1,"东"),
    WSET(2,"西"),
    SOUTH(3,"南"),
    NORTH(4,"北"),
    EASTSOUTH(5,"东南"),
    EASTNORTH(6,"东北"),
    WESTSOUTH(7,"西南"),
    WESTNORTH(8,"西北");

    private final int code;
    private final String text;

}
