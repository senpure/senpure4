package com.senpure.base.criteria;

import com.senpure.base.model.RolePermission;
import com.senpure.base.criterion.Criteria;

import java.io.Serializable;
import java.util.Date;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class RolePermissionCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 589858148L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private Long expiryTime;
    //table [senpure_role_permission][column = expiry_time] order
    private String expiryTimeOrder;
    private Date expiryDate;
    //外键,modelName:Role,tableName:senpure_role
    private Long roleId;
    //table [senpure_role_permission][column = role_id] order
    private String roleIdOrder;
    //外键,modelName:Permission,tableName:senpure_permission
    private Long permissionId;
    //table [senpure_role_permission][column = permission_id] order
    private String permissionIdOrder;

    public static RolePermission toRolePermission(RolePermissionCriteria criteria, RolePermission rolePermission) {
        rolePermission.setId(criteria.getId());
        rolePermission.setExpiryTime(criteria.getExpiryTime());
        rolePermission.setExpiryDate(criteria.getExpiryDate());
        rolePermission.setRoleId(criteria.getRoleId());
        rolePermission.setPermissionId(criteria.getPermissionId());
        rolePermission.setVersion(criteria.getVersion());
        return rolePermission;
    }

    public RolePermission toRolePermission() {
        RolePermission rolePermission = new RolePermission();
        return toRolePermission(this, rolePermission);
    }

    /**
     * 将RolePermissionCriteria 的有效值(不为空),赋值给 RolePermission
     *
     * @return RolePermission
     */
    public RolePermission effective(RolePermission rolePermission) {
        if (getId() != null) {
            rolePermission.setId(getId());
        }
        if (getExpiryTime() != null) {
            rolePermission.setExpiryTime(getExpiryTime());
        }
        if (getExpiryDate() != null) {
            rolePermission.setExpiryDate(getExpiryDate());
        }
        if (getRoleId() != null) {
            rolePermission.setRoleId(getRoleId());
        }
        if (getPermissionId() != null) {
            rolePermission.setPermissionId(getPermissionId());
        }
        if (getVersion() != null) {
            rolePermission.setVersion(getVersion());
        }
        return rolePermission;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("RolePermissionCriteria{");
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
        if (roleId != null) {
            sb.append("roleId=").append(roleId).append(",");
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
    public RolePermissionCriteria setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    /**
     * get table [senpure_role_permission][column = expiry_time] order
     *
     * @return
     */
    public String getExpiryTimeOrder() {
        return expiryTimeOrder;
    }


    public RolePermissionCriteria setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
        return this;
    }

    /**
     * set table [senpure_role_permission][column = expiry_time] order DESC||ASC
     *
     * @return
     */
    public RolePermissionCriteria setExpiryTimeOrder(String expiryTimeOrder) {
        this.expiryTimeOrder = expiryTimeOrder;
        putSort("expiry_time", expiryTimeOrder);
        return this;
    }


    public Date getExpiryDate() {
        return expiryDate;
    }


    public RolePermissionCriteria setExpiryDate(Date expiryDate) {
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
     * get table [senpure_role_permission][column = role_id] order
     *
     * @return
     */
    public String getRoleIdOrder() {
        return roleIdOrder;
    }

    /**
     * set 外键,modelName:Role,tableName:senpure_role
     *
     * @return
     */
    public RolePermissionCriteria setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    /**
     * set table [senpure_role_permission][column = role_id] order DESC||ASC
     *
     * @return
     */
    public RolePermissionCriteria setRoleIdOrder(String roleIdOrder) {
        this.roleIdOrder = roleIdOrder;
        putSort("role_id", roleIdOrder);
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
     * get table [senpure_role_permission][column = permission_id] order
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
    public RolePermissionCriteria setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
        return this;
    }

    /**
     * set table [senpure_role_permission][column = permission_id] order DESC||ASC
     *
     * @return
     */
    public RolePermissionCriteria setPermissionIdOrder(String permissionIdOrder) {
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
    public RolePermissionCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}