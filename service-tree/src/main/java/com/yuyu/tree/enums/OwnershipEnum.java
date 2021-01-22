package com.yuyu.tree.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OwnershipEnum {

    NATION(0, "国有"),
    COLLECTIVE(1, "集体"),
    PERSON(2, "个人"),
    OTHER(3, "其他");

    private final int code;
    private final String text;
}
