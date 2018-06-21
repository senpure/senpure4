package com.senpure.base.entity;


import com.senpure.base.PermissionConstant;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = PermissionConstant.DATA_BASE_PREFIX + "_ROLE_PERMISSION")
public class RolePermission extends LongAndVersionEntity {
    /**
     *
     */
    private static final long serialVersionUID = 2404844436780625172L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId")
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permissionId")
    private Permission permission;
    private Long expiryTime;
    private Date expiryDate;

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

}
