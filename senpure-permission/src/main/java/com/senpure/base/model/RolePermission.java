package com.senpure.base.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 589858148L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private Long expiryTime;
    private Date expiryDate;
    //外键,modelName:Role,tableName:senpure_role
    private Long roleId;
    //外键,modelName:Permission,tableName:senpure_permission
    private Long permissionId;

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
    public RolePermission setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }


    public RolePermission setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
        return this;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }


    public RolePermission setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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
    public RolePermission setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    /**
     * get 外键,modelName:Permission,tableName:senpure_permission
     *
     * @return
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * set 外键,modelName:Permission,tableName:senpure_permission
     *
     * @return
     */
    public RolePermission setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
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
    public RolePermission setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "RolePermission{"
                + "id=" + id
                + ",version=" + version
                + ",expiryTime=" + expiryTime
                + ",expiryDate=" + expiryDate
                + ",roleId=" + roleId
                + ",permissionId=" + permissionId
                + "}";
    }

}