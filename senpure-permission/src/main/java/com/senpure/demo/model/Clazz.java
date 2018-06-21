package com.senpure.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 班级信息
 * 
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
public class Clazz implements Serializable {
    private static final long serialVersionUID = 1416951444L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    //年级，如三年级
    private Integer age;
    //几班，如二班
    private Integer num;
    //创建时间
    private Date createDate;
    //创建时间，时间戳
    private Long createTime;

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
    public Clazz setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * get 年级，如三年级
     *
     * @return
     */
    public Integer getAge() {
        return age;
    }

    /**
     * set 年级，如三年级
     *
     * @return
     */
    public Clazz setAge(Integer age) {
        this.age = age;
        return this;
    }

    /**
     * get 几班，如二班
     *
     * @return
     */
    public Integer getNum() {
        return num;
    }

    /**
     * set 几班，如二班
     *
     * @return
     */
    public Clazz setNum(Integer num) {
        this.num = num;
        return this;
    }

    /**
     * get 创建时间
     *
     * @return
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * set 创建时间
     *
     * @return
     */
    public Clazz setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    /**
     * get 创建时间，时间戳
     *
     * @return
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * set 创建时间，时间戳
     *
     * @return
     */
    public Clazz setCreateTime(Long createTime) {
        this.createTime = createTime;
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
    public Clazz setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "Clazz{"
                + "id=" + id
                + ",version=" + version
                + ",age=" + age
                + ",num=" + num
                + ",createDate=" + createDate
                + ",createTime=" + createTime
                + "}";
    }

}