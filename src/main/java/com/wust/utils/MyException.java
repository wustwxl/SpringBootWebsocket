package com.wust.utils;

/**
 * Created by IntelliJ IDEA.
 * @author : F7687967
 * Date: 2017/10/16
 * Time: 下午 05:00
 * Description: 异常处理类
 */

public class MyException extends RuntimeException {
//spring 对于 RuntimeException 异常才会进行事务回滚。

    /**
     * @Description: 构造方法
    */
    public MyException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
