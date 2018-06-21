package com.senpure.base.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class Container implements Serializable {
    private static final long serialVersionUID = 611392665L;

    //主键
    private Integer id;
    //乐观锁，版本控制
    private Integer version;
    private String name;
    private String description;
    private Integer level;
    private Long relation;
    private Long createTime;
    private Date createDate;
    //所属容器层次结构,主要是为了查询,多有用like查询 -1-2-3-12-
    private String containerStructure;
    //父容器ID(外键,modelName:Container,tableName:senpure_container)
    private Integer parentId;

    /**
     * get 主键
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * set 主键
     *
     * @return
     */
    public Container setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }


    public Container setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }


    public Container setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getLevel() {
        return level;
    }


    public Container setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Long getRelation() {
        return relation;
    }


    public Container setRelation(Long relation) {
        this.relation = relation;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }


    public Container setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }


    public Container setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    /**
     * get 所属容器层次结构,主要是为了查询,多有用like查询 -1-2-3-12-
     *
     * @return
     */
    public String getContainerStructure() {
        return containerStructure;
    }

    /**
     * set 所属容器层次结构,主要是为了查询,多有用like查询 -1-2-3-12-
     *
     * @return
     */
    public Container setContainerStructure(String containerStructure) {
        this.containerStructure = containerStructure;
        return this;
    }

    /**
     * get 父容器ID(外键,modelName:Container,tableName:senpure_container)
     *
     * @return
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * set 父容器ID(外键,modelName:Container,tableName:senpure_container)
     *
     * @return
     */
    public Container setParentId(Integer parentId) {
        this.parentId = parentId;
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
    public Container setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "Container{"
                + "id=" + id
                + ",version=" + version
                + ",name=" + name
                + ",description=" + description
                + ",level=" + level
                + ",relation=" + relation
                + ",createTime=" + createTime
                + ",createDate=" + createDate
                + ",containerStructure=" + containerStructure
                + ",parentId=" + parentId
                + "}";
    }

}