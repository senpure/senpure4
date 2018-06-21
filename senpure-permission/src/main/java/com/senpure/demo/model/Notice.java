package com.senpure.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告模型
 * 
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
public class Notice implements Serializable {
    private static final long serialVersionUID = 339330519L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    //公共内容
    private String msg;
    //发出时间
    private Date sendDate;

    /**
     * get 主键
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * set 主键
     *
     * @return
     */
    public Notice setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * get 公共内容
     *
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * set 公共内容
     *
     * @return
     */
    public Notice setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * get 发出时间
     *
     * @return
     */
    public Date getSendDate() {
        return sendDate;
    }

    /**
     * set 发出时间
     *
     * @return
     */
    public Notice setSendDate(Date sendDate) {
        this.sendDate = sendDate;
        return this;
    }

    /**
     * get 乐观锁，版本控制
     *
     * @return
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * set 乐观锁，版本控制
     *
     * @return
     */
    public Notice setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "Notice{"
                + "id=" + id
                + ",version=" + version
                + ",msg=" + msg
                + ",sendDate=" + sendDate
                + "}";
    }

}