package com.senpure.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 2004864803L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private String name;
    //自己取得外号，如齐天大圣，狗蛋
    private String nick;
    //创建时间
    private Date createDate;
    //创建时间，时间戳
    private Long createTime;
    private String phoneNumber;
    private Integer age;
    //学号
    private Integer num;
    private Boolean good;
    //所属班级(外键,modelName:Clazz,tableName:demo_class)
    private Long clazzId;

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
    public Student setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }


    public Student setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * get 自己取得外号，如齐天大圣，狗蛋
     *
     * @return
     */
    public String getNick() {
        return nick;
    }

    /**
     * set 自己取得外号，如齐天大圣，狗蛋
     *
     * @return
     */
    public Student setNick(String nick) {
        this.nick = nick;
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
    public Student setCreateDate(Date createDate) {
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
    public Student setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public Student setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Integer getAge() {
        return age;
    }


    public Student setAge(Integer age) {
        this.age = age;
        return this;
    }

    /**
     * get 学号
     *
     * @return
     */
    public Integer getNum() {
        return num;
    }

    /**
     * set 学号
     *
     * @return
     */
    public Student setNum(Integer num) {
        this.num = num;
        return this;
    }

    public Boolean getGood() {
        return good;
    }


    public Student setGood(Boolean good) {
        this.good = good;
        return this;
    }

    /**
     * get 所属班级(外键,modelName:Clazz,tableName:demo_class)
     *
     * @return
     */
    public Long getClazzId() {
        return clazzId;
    }

    /**
     * set 所属班级(外键,modelName:Clazz,tableName:demo_class)
     *
     * @return
     */
    public Student setClazzId(Long clazzId) {
        this.clazzId = clazzId;
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
    public Student setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "Student{"
                + "id=" + id
                + ",version=" + version
                + ",name=" + name
                + ",nick=" + nick
                + ",createDate=" + createDate
                + ",createTime=" + createTime
                + ",phoneNumber=" + phoneNumber
                + ",age=" + age
                + ",num=" + num
                + ",good=" + good
                + ",clazzId=" + clazzId
                + "}";
    }

}