package com.senpure.base.entity;

import com.senpure.base.PermissionConstant;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 罗中正 on 2017/6/16.
 */
@Entity
@Table(name = PermissionConstant.DATA_BASE_PREFIX+ "_PERMISSION_MENU")
public class PermissionMenu extends LongAndVersionEntity{
    private static final long serialVersionUID = -6746933581483135892L;
    private Integer menuId;
    private String permissionName;

    private Boolean dataBaseUpdate=false;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer  menuId) {
        this.menuId = menuId;
    }


    public Boolean getDataBaseUpdate() {
        return dataBaseUpdate;
    }

    public void setDataBaseUpdate(Boolean dataBaseUpdate) {
        this.dataBaseUpdate = dataBaseUpdate;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
