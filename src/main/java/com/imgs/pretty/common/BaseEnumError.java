package com.imgs.pretty.common;

import lombok.Getter;

@Getter
public enum BaseEnumError {

    LOGIN_ERROR(201,"用户名或密码错误"),
    PARAM_ERROR(202,"参数错误")
    ;


    private int code;

    private String msg;

    BaseEnumError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
