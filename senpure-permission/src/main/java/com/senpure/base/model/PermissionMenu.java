package com.senpure.base.model;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class PermissionMenu implements Serializable {
    private static final long serialVersionUID = 343182696L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private Integer menuId;
    private String permissionName;
    private Boolean dataBaseUpdate;

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
    public PermissionMenu setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getMenuId() {
        return menuId;
    }


    public PermissionMenu setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    public String getPermissionName() {
        return permissionName;
    }


    public PermissionMenu setPermissionName(String permissionName) {
        this.permissionName = permissionName;
        return this;
    }

    public Boolean getDataBaseUpdate() {
        return dataBaseUpdate;
    }


    public PermissionMenu setDataBaseUpdate(Boolean dataBaseUpdate) {
        this.dataBaseUpdate = dataBaseUpdate;
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
    public PermissionMenu setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "PermissionMenu{"
                + "id=" + id
                + ",version=" + version
                + ",menuId=" + menuId
                + ",permissionName=" + permissionName
                + ",dataBaseUpdate=" + dataBaseUpdate
                + "}";
    }

}