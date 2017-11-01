package com.wust.utils;

/**
 * Created by IntelliJ IDEA.
 * @author : F7687967
 * Date: 2017/10/16
 * Time: 上午 08:51
 * Description: 统一返回类型
 */
public class MessageVo {
    private String code;
    private String info;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
