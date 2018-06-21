package com.senpure.base.model;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class Sequence implements Serializable {
    private static final long serialVersionUID = 945199211L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    //标识
    private String type;
    //前缀
    private String prefix;
    //后缀
    private String suffix;
    //当前序列号
    private Integer sequence;
    //格式化的长度
    private Integer digit;
    //每次增长
    private Integer span;

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
    public Sequence setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * get 标识
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * set 标识
     *
     * @return
     */
    public Sequence setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * get 前缀
     *
     * @return
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * set 前缀
     *
     * @return
     */
    public Sequence setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * get 后缀
     *
     * @return
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * set 后缀
     *
     * @return
     */
    public Sequence setSuffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    /**
     * get 当前序列号
     *
     * @return
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * set 当前序列号
     *
     * @return
     */
    public Sequence setSequence(Integer sequence) {
        this.sequence = sequence;
        return this;
    }

    /**
     * get 格式化的长度
     *
     * @return
     */
    public Integer getDigit() {
        return digit;
    }

    /**
     * set 格式化的长度
     *
     * @return
     */
    public Sequence setDigit(Integer digit) {
        this.digit = digit;
        return this;
    }

    /**
     * get 每次增长
     *
     * @return
     */
    public Integer getSpan() {
        return span;
    }

    /**
     * set 每次增长
     *
     * @return
     */
    public Sequence setSpan(Integer span) {
        this.span = span;
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
    public Sequence setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "Sequence{"
                + "id=" + id
                + ",version=" + version
                + ",type=" + type
                + ",prefix=" + prefix
                + ",suffix=" + suffix
                + ",sequence=" + sequence
                + ",digit=" + digit
                + ",span=" + span
                + "}";
    }

}