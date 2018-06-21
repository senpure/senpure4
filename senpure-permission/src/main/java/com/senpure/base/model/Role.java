package com.senpure.base.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 1021021432L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private String name;
    private Date createDate;
    private Long createTime;
    private String description;
    //外键,modelName:Container,tableName:senpure_container
    private Integer containerId;

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
    public Role setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }


    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }


    public Role setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }


    public Role setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getDescription() {
        return description;
    }


    public Role setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * get 外键,modelName:Container,tableName:senpure_container
     *
     * @return
     */
    public Integer getContainerId() {
        return containerId;
    }

    /**
     * set 外键,modelName:Container,tableName:senpure_container
     *
     * @return
     */
    public Role setContainerId(Integer containerId) {
        this.containerId = containerId;
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
    public Role setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "Role{"
                + "id=" + id
                + ",version=" + version
                + ",name=" + name
                + ",createDate=" + createDate
                + ",createTime=" + createTime
                + ",description=" + description
                + ",containerId=" + containerId
                + "}";
    }

}