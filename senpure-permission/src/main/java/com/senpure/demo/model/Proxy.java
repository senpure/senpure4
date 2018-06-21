package com.senpure.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 代理关系表
 * 
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
public class Proxy implements Serializable {
    private static final long serialVersionUID = 315239481L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    //上级id
    private Long parent;
    //上级id
    private Long child;
    //间隔级数
    private Integer level;
    //绑定时间
    private Long bindTime;
    //绑定时间
    private Date bindDate;

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
    public Proxy setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * get 上级id
     *
     * @return
     */
    public Long getParent() {
        return parent;
    }

    /**
     * set 上级id
     *
     * @return
     */
    public Proxy setParent(Long parent) {
        this.parent = parent;
        return this;
    }

    /**
     * get 上级id
     *
     * @return
     */
    public Long getChild() {
        return child;
    }

    /**
     * set 上级id
     *
     * @return
     */
    public Proxy setChild(Long child) {
        this.child = child;
        return this;
    }

    /**
     * get 间隔级数
     *
     * @return
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * set 间隔级数
     *
     * @return
     */
    public Proxy setLevel(Integer level) {
        this.level = level;
        return this;
    }

    /**
     * get 绑定时间
     *
     * @return
     */
    public Long getBindTime() {
        return bindTime;
    }

    /**
     * set 绑定时间
     *
     * @return
     */
    public Proxy setBindTime(Long bindTime) {
        this.bindTime = bindTime;
        return this;
    }

    /**
     * get 绑定时间
     *
     * @return
     */
    public Date getBindDate() {
        return bindDate;
    }

    /**
     * set 绑定时间
     *
     * @return
     */
    public Proxy setBindDate(Date bindDate) {
        this.bindDate = bindDate;
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
    public Proxy setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "Proxy{"
                + "id=" + id
                + ",version=" + version
                + ",parent=" + parent
                + ",child=" + child
                + ",level=" + level
                + ",bindTime=" + bindTime
                + ",bindDate=" + bindDate
                + "}";
    }

}