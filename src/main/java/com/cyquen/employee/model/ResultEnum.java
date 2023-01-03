package com.cyquen.employee.model;

public enum ResultEnum {

    SUCCESS(200),

    FAIL(400);
    public int code;

    ResultEnum(int code) {
        this.code = code;
    }
}
