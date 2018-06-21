package com.senpure.base.model;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class SystemValue implements Serializable {
    private static final long serialVersionUID = 748356364L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private String type;
    private String key;
    private String value;
    private String description;

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
    public SystemValue setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }


    public SystemValue setType(String type) {
        this.type = type;
        return this;
    }

    public String getKey() {
        return key;
    }


    public SystemValue setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }


    public SystemValue setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDescription() {
        return description;
    }


    public SystemValue setDescription(String description) {
        this.description = description;
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
    public SystemValue setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "SystemValue{"
                + "id=" + id
                + ",version=" + version
                + ",type=" + type
                + ",key=" + key
                + ",value=" + value
                + ",description=" + description
                + "}";
    }

}