package com.yuyu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActEnum {

    UPDATA(0),
    CREATE(1),
    DELETE(2);

    private final int code;

}
