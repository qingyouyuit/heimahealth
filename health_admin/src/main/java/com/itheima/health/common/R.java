package com.itheima.health.common;

import lombok.Data;

@Data
public class R {
    private boolean flag;
    private String message;
    private Object data;

    public static R success(Object data){
        R r = new R();
        r.flag = true;
        r.message = "OK";
        r.data = data;
        return r;
    }

    //TODO 自行添加其他操作成功或失败的静态方法
}
