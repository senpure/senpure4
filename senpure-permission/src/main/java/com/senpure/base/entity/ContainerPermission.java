package com.senpure.base.entity;


import com.senpure.base.PermissionConstant;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = PermissionConstant.DATA_BASE_PREFIX + "_CONTAINER_PERMISSION")
public class ContainerPermission extends LongAndVersionEntity {

    /**
     *
     */
    private static final long serialVersionUID = -5080867717001924566L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "containerId")
    private Container container;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permissionId")
    private Permission permission;
    private Long expiryTime;
    private Date expiryDate;


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

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }


}
