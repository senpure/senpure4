package com.senpure.base.model;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = 534669811L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    //字符串唯一标识
    private String name;
    //权限名
    private String readableName;
    //是否从数据库更新过
    private Boolean databaseUpdate;
    //NORMAL 正常 ，OWNER 检查所有者，IGNORE 可以忽略(正常放行)
    private String type;
    //'1,2' type为OWNER 配合verifyName使用
    private String offset;
    //'containerResource',roleResource' type为OWNER 配合offset使用
    private String verifyName;
    private String description;
    //排序
    private Integer sort;

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
    public Permission setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * get 字符串唯一标识
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set 字符串唯一标识
     *
     * @return
     */
    public Permission setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * get 权限名
     *
     * @return
     */
    public String getReadableName() {
        return readableName;
    }

    /**
     * set 权限名
     *
     * @return
     */
    public Permission setReadableName(String readableName) {
        this.readableName = readableName;
        return this;
    }

    /**
     * get 是否从数据库更新过
     *
     * @return
     */
    public Boolean getDatabaseUpdate() {
        return databaseUpdate;
    }

    /**
     * set 是否从数据库更新过
     *
     * @return
     */
    public Permission setDatabaseUpdate(Boolean databaseUpdate) {
        this.databaseUpdate = databaseUpdate;
        return this;
    }

    /**
     * get NORMAL 正常 ，OWNER 检查所有者，IGNORE 可以忽略(正常放行)
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * set NORMAL 正常 ，OWNER 检查所有者，IGNORE 可以忽略(正常放行)
     *
     * @return
     */
    public Permission setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * get '1,2' type为OWNER 配合verifyName使用
     *
     * @return
     */
    public String getOffset() {
        return offset;
    }

    /**
     * set '1,2' type为OWNER 配合verifyName使用
     *
     * @return
     */
    public Permission setOffset(String offset) {
        this.offset = offset;
        return this;
    }

    /**
     * get 'containerResource',roleResource' type为OWNER 配合offset使用
     *
     * @return
     */
    public String getVerifyName() {
        return verifyName;
    }

    /**
     * set 'containerResource',roleResource' type为OWNER 配合offset使用
     *
     * @return
     */
    public Permission setVerifyName(String verifyName) {
        this.verifyName = verifyName;
        return this;
    }

    public String getDescription() {
        return description;
    }


    public Permission setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * get 排序
     *
     * @return
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * set 排序
     *
     * @return
     */
    public Permission setSort(Integer sort) {
        this.sort = sort;
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
    public Permission setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "Permission{"
                + "id=" + id
                + ",version=" + version
                + ",name=" + name
                + ",readableName=" + readableName
                + ",databaseUpdate=" + databaseUpdate
                + ",type=" + type
                + ",offset=" + offset
                + ",verifyName=" + verifyName
                + ",description=" + description
                + ",sort=" + sort
                + "}";
    }

}