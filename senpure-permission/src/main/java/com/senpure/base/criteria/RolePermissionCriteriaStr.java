package com.senpure.base.criteria;

import com.senpure.base.criterion.CriteriaStr;
import com.senpure.base.struct.PatternDate;
import com.senpure.base.validator.DynamicDate;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class RolePermissionCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 589858148L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    //table [senpure_role_permission][column = expiry_time] order
    private String expiryTimeOrder;
    private String expiryDate;
    @DynamicDate
    private PatternDate expiryDateValid = new PatternDate();
    //外键,modelName:Role,tableName:senpure_role
    private String roleId;
    //table [senpure_role_permission][column = role_id] order
    private String roleIdOrder;
    //外键,modelName:Permission,tableName:senpure_permission
    private String permissionId;
    //table [senpure_role_permission][column = permission_id] order
    private String permissionIdOrder;

    public RolePermissionCriteria toRolePermissionCriteria() {
        RolePermissionCriteria criteria = new RolePermissionCriteria();
        criteria.setUsePage(Boolean.valueOf(getUsePage()));
        criteria.setPage(Integer.valueOf(getPage()));
        criteria.setPageSize(Integer.valueOf(getPageSize()));
        criteria.setStartDate(getStartDateValid().getDate());
        criteria.setEndDate(getEndDateValid().getDate());
        //主键
        if (id != null) {
            criteria.setId(Long.valueOf(id));
        }
        //乐观锁，版本控制
        if (version != null) {
            criteria.setVersion(Integer.valueOf(version));
        }
        //table [senpure_role_permission][column = expiry_time] order
        if (expiryTimeOrder != null) {
            criteria.setExpiryTimeOrder(expiryTimeOrder);
        }
        if (expiryDate != null) {
            criteria.setExpiryDate(expiryDateValid.getDate());
            if (expiryDateValid.getDate() != null) {
                criteria.setExpiryTime(expiryDateValid.getDate().getTime());
            }
        }
        //外键,modelName:Role,tableName:senpure_role
        if (roleId != null) {
            criteria.setRoleId(Long.valueOf(roleId));
        }
        //table [senpure_role_permission][column = role_id] order
        if (roleIdOrder != null) {
            criteria.setRoleIdOrder(roleIdOrder);
        }
        //外键,modelName:Permission,tableName:senpure_permission
        if (permissionId != null) {
            criteria.setPermissionId(Long.valueOf(permissionId));
        }
        //table [senpure_role_permission][column = permission_id] order
        if (permissionIdOrder != null) {
            criteria.setPermissionIdOrder(permissionIdOrder);
        }
        return criteria;
    }

    @Override
    public void setDatePattern(String datePattern) {
        super.setDatePattern(datePattern);
        expiryDateValid.setPattern(datePattern);
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("RolePermissionCriteriaStr{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
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

    @Override
    protected void afterStr(StringBuilder sb) {
        if (expiryTimeOrder != null) {
            sb.append("expiryTimeOrder=").append(expiryTimeOrder).append(",");
        }
        if (roleIdOrder != null) {
            sb.append("roleIdOrder=").append(roleIdOrder).append(",");
        }
        if (permissionIdOrder != null) {
            sb.append("permissionIdOrder=").append(permissionIdOrder).append(",");
        }
        super.afterStr(sb);
    }


    /**
     * get 主键
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * set 主键
     *
     * @return
     */
    public RolePermissionCriteriaStr setId(String id) {
        if (id != null && id.trim().length() == 0) {
            return this;
        }
        this.id = id;
        return this;
    }


    /**
     * get 乐观锁，版本控制
     *
     * @return
     */
    public String getVersion() {
        return version;
    }

    /**
     * set 乐观锁，版本控制
     *
     * @return
     */
    public RolePermissionCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
        return this;
    }


    /**
     * get table [senpure_role_permission][column = expiry_time] order
     *
     * @return
     */
    public String getExpiryTimeOrder() {
        return expiryTimeOrder;
    }

    /**
     * set table [senpure_role_permission][column = expiry_time] order DESC||ASC
     *
     * @return
     */
    public RolePermissionCriteriaStr setExpiryTimeOrder(String expiryTimeOrder) {
        if (expiryTimeOrder != null && expiryTimeOrder.trim().length() == 0) {
            return this;
        }
        this.expiryTimeOrder = expiryTimeOrder;
        return this;
    }


    public String getExpiryDate() {
        return expiryDate;
    }


    public RolePermissionCriteriaStr setExpiryDate(String expiryDate) {
        if (expiryDate != null && expiryDate.trim().length() == 0) {
            return this;
        }
        this.expiryDate = expiryDate;
        return this;
    }


    /**
     * get 外键,modelName:Role,tableName:senpure_role
     *
     * @return
     */
    public String getRoleId() {
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
    public RolePermissionCriteriaStr setRoleId(String roleId) {
        if (roleId != null && roleId.trim().length() == 0) {
            return this;
        }
        this.roleId = roleId;
        return this;
    }

    /**
     * set table [senpure_role_permission][column = role_id] order DESC||ASC
     *
     * @return
     */
    public RolePermissionCriteriaStr setRoleIdOrder(String roleIdOrder) {
        if (roleIdOrder != null && roleIdOrder.trim().length() == 0) {
            return this;
        }
        this.roleIdOrder = roleIdOrder;
        return this;
    }


    /**
     * get 外键,modelName:Permission,tableName:senpure_permission
     *
     * @return
     */
    public String getPermissionId() {
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
    public RolePermissionCriteriaStr setPermissionId(String permissionId) {
        if (permissionId != null && permissionId.trim().length() == 0) {
            return this;
        }
        this.permissionId = permissionId;
        return this;
    }

    /**
     * set table [senpure_role_permission][column = permission_id] order DESC||ASC
     *
     * @return
     */
    public RolePermissionCriteriaStr setPermissionIdOrder(String permissionIdOrder) {
        if (permissionIdOrder != null && permissionIdOrder.trim().length() == 0) {
            return this;
        }
        this.permissionIdOrder = permissionIdOrder;
        return this;
    }


}