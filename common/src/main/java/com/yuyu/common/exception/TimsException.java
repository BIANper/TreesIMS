package com.yuyu.common.exception;

import lombok.Getter;

@Getter
public class TimsException extends Exception {

    private static final long serialVersionUID = -5694262226361784590L;

    private Integer code;
    private String msg;

    public TimsException(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public TimsException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public TimsException(Integer code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }

}
