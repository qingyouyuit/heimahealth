package com.itheima.health.common;

import lombok.Data;

@Data
public class R {
    private boolean flag;
    private String message;
    private Object data;

    public static R success(String msg, Object data) {
        R r = new R();
        r.flag = true;
        r.message = msg;
        r.data = data;
        return r;
    }

    public static R success(String msg) {
        R r = new R();
        r.flag = true;
        r.message = msg;
        return r;
    }

    public static R error(String msg) {
        R r = new R();
        r.flag = false;
        r.message = msg;
        return r;
    }
}
