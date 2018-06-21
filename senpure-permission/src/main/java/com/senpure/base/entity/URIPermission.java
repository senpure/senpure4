package com.senpure.base.entity;


import com.senpure.base.PermissionConstant;
import com.senpure.base.annotation.Explain;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/2/7.
 */
@Entity
@Table(name = PermissionConstant.DATA_BASE_PREFIX + "_URI_PERMISSION")
public class URIPermission extends LongAndVersionEntity{

    @Column
    private String uriAndMethod;
    @Explain("是否从数据库更新过")
    private Boolean databaseUpdate = false;

   // @JoinColumn(name = "permissionName" ,referencedColumnName = "name")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "permissionId")
    private Permission permission;

    public String getUriAndMethod() {
        return uriAndMethod;
    }

    public void setUriAndMethod(String uriAndMethod) {
        this.uriAndMethod = uriAndMethod;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Boolean getDatabaseUpdate() {
        return databaseUpdate;
    }

    public void setDatabaseUpdate(Boolean databaseUpdate) {
        this.databaseUpdate = databaseUpdate;
    }
}
