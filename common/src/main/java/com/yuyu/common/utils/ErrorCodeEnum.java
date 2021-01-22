package com.yuyu.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    UNKNOWN_EXCEPTION(10000,"未知错误"),
    VALID_EXCEPTION(10001,"参数格式错误"),
    ACCOUNT_EXCEPTION(40001,"账号不存在"),
    PWD_EXCEPTION(40002,"密码错误");

    private final int code;
    private final String message;

}
