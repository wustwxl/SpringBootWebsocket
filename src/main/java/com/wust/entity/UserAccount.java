package com.wust.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : F7687967
 * Date: 2017/10/16
 * Time: 上午 08:09
 * Description:
 */

public class UserAccount implements Serializable {

    /**
     * @Description: 将对象进行序列化, 可实现在Redis中存储该对象
     */
    private static final long serialVersionUID = -1L;

    private Long id;

    private String tel;

    private String pass;

    private String nickname;

    private String device;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yy-MM-dd hh:mm:ss")
    private Date createTime;

    private Boolean delflag;

    public UserAccount(String tel, String pass, String nickname) {
        this.tel = tel;
        this.pass = pass;
        this.nickname = nickname;
    }

    public UserAccount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDelflag() {
        return delflag;
    }

    public void setDelflag(Boolean delflag) {
        this.delflag = delflag;
    }
}