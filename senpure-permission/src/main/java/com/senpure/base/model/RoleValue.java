package com.senpure.base.model;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class RoleValue implements Serializable {
    private static final long serialVersionUID = 1099570973L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private String key;
    private String value;
    private String description;
    //外键,modelName:Role,tableName:senpure_role
    private Long roleId;

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
    public RoleValue setId(Long id) {
        this.id = id;
        return this;
    }

    public String getKey() {
        return key;
    }


    public RoleValue setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }


    public RoleValue setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDescription() {
        return description;
    }


    public RoleValue setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * get 外键,modelName:Role,tableName:senpure_role
     *
     * @return
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * set 外键,modelName:Role,tableName:senpure_role
     *
     * @return
     */
    public RoleValue setRoleId(Long roleId) {
        this.roleId = roleId;
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
    public RoleValue setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "RoleValue{"
                + "id=" + id
                + ",version=" + version
                + ",key=" + key
                + ",value=" + value
                + ",description=" + description
                + ",roleId=" + roleId
                + "}";
    }

}