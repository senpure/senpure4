package com.senpure.book.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author senpure-generator
 * @version 2018-1-25 15:15:45
 */
public class Author implements Serializable {
    private static final long serialVersionUID = 1247575657L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    //联系方式
    private String phone;
    //姓名
    private String name;
    //入行时间
    private Date joinWriters;
    //是否党员
    private Boolean party;

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
    public Author setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * get 联系方式
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * set 联系方式
     *
     * @return
     */
    public Author setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * get 姓名
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set 姓名
     *
     * @return
     */
    public Author setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * get 入行时间
     *
     * @return
     */
    public Date getJoinWriters() {
        return joinWriters;
    }

    /**
     * set 入行时间
     *
     * @return
     */
    public Author setJoinWriters(Date joinWriters) {
        this.joinWriters = joinWriters;
        return this;
    }

    /**
     * get 是否党员
     *
     * @return
     */
    public Boolean getParty() {
        return party;
    }

    /**
     * set 是否党员
     *
     * @return
     */
    public Author setParty(Boolean party) {
        this.party = party;
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
    public Author setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "Author{"
                + "id=" + id
                + ",version=" + version
                + ",phone=" + phone
                + ",name=" + name
                + ",joinWriters=" + joinWriters
                + ",party=" + party
                + "}";
    }

}