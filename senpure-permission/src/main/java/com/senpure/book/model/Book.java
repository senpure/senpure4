package com.senpure.book.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author senpure-generator
 * @version 2018-1-25 15:15:45
 */
public class Book implements Serializable {
    private static final long serialVersionUID = 1770406487L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    //书名
    private String name;
    //价格
    private Double price;
    //发行时间
    private Long releaseTime;
    //发行时间
    private Date releaseDate;
    //作者(外键,modelName:Author,tableName:demo_author)
    private Long authorId;

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
    public Book setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * get 书名
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set 书名
     *
     * @return
     */
    public Book setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * get 价格
     *
     * @return
     */
    public Double getPrice() {
        return price;
    }

    /**
     * set 价格
     *
     * @return
     */
    public Book setPrice(Double price) {
        this.price = price;
        return this;
    }

    /**
     * get 发行时间
     *
     * @return
     */
    public Long getReleaseTime() {
        return releaseTime;
    }

    /**
     * set 发行时间
     *
     * @return
     */
    public Book setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
        return this;
    }

    /**
     * get 发行时间
     *
     * @return
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * set 发行时间
     *
     * @return
     */
    public Book setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    /**
     * get 作者(外键,modelName:Author,tableName:demo_author)
     *
     * @return
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * set 作者(外键,modelName:Author,tableName:demo_author)
     *
     * @return
     */
    public Book setAuthorId(Long authorId) {
        this.authorId = authorId;
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
    public Book setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "Book{"
                + "id=" + id
                + ",version=" + version
                + ",name=" + name
                + ",price=" + price
                + ",releaseTime=" + releaseTime
                + ",releaseDate=" + releaseDate
                + ",authorId=" + authorId
                + "}";
    }

}