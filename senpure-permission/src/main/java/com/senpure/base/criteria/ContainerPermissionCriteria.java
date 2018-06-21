package com.senpure.base.criteria;

import com.senpure.base.model.ContainerPermission;
import com.senpure.base.criterion.Criteria;

import java.io.Serializable;
import java.util.Date;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:20
 */
public class ContainerPermissionCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 1444113447L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private Long expiryTime;
    //table [senpure_container_permission][column = expiry_time] order
    private String expiryTimeOrder;
    private Date expiryDate;
    //外键,modelName:Container,tableName:senpure_container
    private Integer containerId;
    //table [senpure_container_permission][column = container_id] order
    private String containerIdOrder;
    //外键,modelName:Permission,tableName:senpure_permission
    private Long permissionId;
    //table [senpure_container_permission][column = permission_id] order
    private String permissionIdOrder;

    public static ContainerPermission toContainerPermission(ContainerPermissionCriteria criteria, ContainerPermission containerPermission) {
        containerPermission.setId(criteria.getId());
        containerPermission.setExpiryTime(criteria.getExpiryTime());
        containerPermission.setExpiryDate(criteria.getExpiryDate());
        containerPermission.setContainerId(criteria.getContainerId());
        containerPermission.setPermissionId(criteria.getPermissionId());
        containerPermission.setVersion(criteria.getVersion());
        return containerPermission;
    }

    public ContainerPermission toContainerPermission() {
        ContainerPermission containerPermission = new ContainerPermission();
        return toContainerPermission(this, containerPermission);
    }

    /**
     * 将ContainerPermissionCriteria 的有效值(不为空),赋值给 ContainerPermission
     *
     * @return ContainerPermission
     */
    public ContainerPermission effective(ContainerPermission containerPermission) {
        if (getId() != null) {
            containerPermission.setId(getId());
        }
        if (getExpiryTime() != null) {
            containerPermission.setExpiryTime(getExpiryTime());
        }
        if (getExpiryDate() != null) {
            containerPermission.setExpiryDate(getExpiryDate());
        }
        if (getContainerId() != null) {
            containerPermission.setContainerId(getContainerId());
        }
        if (getPermissionId() != null) {
            containerPermission.setPermissionId(getPermissionId());
        }
        if (getVersion() != null) {
            containerPermission.setVersion(getVersion());
        }
        return containerPermission;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("ContainerPermissionCriteria{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (expiryTime != null) {
            sb.append("expiryTime=").append(expiryTime).append(",");
        }
        if (expiryDate != null) {
            sb.append("expiryDate=").append(expiryDate).append(",");
        }
        if (containerId != null) {
            sb.append("containerId=").append(containerId).append(",");
        }
        if (permissionId != null) {
            sb.append("permissionId=").append(permissionId).append(",");
        }
    }

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
    public ContainerPermissionCriteria setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    /**
     * get table [senpure_container_permission][column = expiry_time] order
     *
     * @return
     */
    public String getExpiryTimeOrder() {
        return expiryTimeOrder;
    }


    public ContainerPermissionCriteria setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
        return this;
    }

    /**
     * set table [senpure_container_permission][column = expiry_time] order DESC||ASC
     *
     * @return
     */
    public ContainerPermissionCriteria setExpiryTimeOrder(String expiryTimeOrder) {
        this.expiryTimeOrder = expiryTimeOrder;
        putSort("expiry_time", expiryTimeOrder);
        return this;
    }


    public Date getExpiryDate() {
        return expiryDate;
    }


    public ContainerPermissionCriteria setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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
     * get table [senpure_container_permission][column = container_id] order
     *
     * @return
     */
    public String getContainerIdOrder() {
        return containerIdOrder;
    }

    /**
     * set 外键,modelName:Container,tableName:senpure_container
     *
     * @return
     */
    public ContainerPermissionCriteria setContainerId(Integer containerId) {
        this.containerId = containerId;
        return this;
    }

    /**
     * set table [senpure_container_permission][column = container_id] order DESC||ASC
     *
     * @return
     */
    public ContainerPermissionCriteria setContainerIdOrder(String containerIdOrder) {
        this.containerIdOrder = containerIdOrder;
        putSort("container_id", containerIdOrder);
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
     * get table [senpure_container_permission][column = permission_id] order
     *
     * @return
     */
    public String getPermissionIdOrder() {
        return permissionIdOrder;
    }

    /**
     * set 外键,modelName:Permission,tableName:senpure_permission
     *
     * @return
     */
    public ContainerPermissionCriteria setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
        return this;
    }

    /**
     * set table [senpure_container_permission][column = permission_id] order DESC||ASC
     *
     * @return
     */
    public ContainerPermissionCriteria setPermissionIdOrder(String permissionIdOrder) {
        this.permissionIdOrder = permissionIdOrder;
        putSort("permission_id", permissionIdOrder);
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
    public ContainerPermissionCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}