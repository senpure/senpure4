package com.senpure.base.criteria;

import com.senpure.base.model.RoleValue;
import com.senpure.base.criterion.Criteria;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class RoleValueCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 1099570973L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private String key;
    //table [senpure_role_value][column = role_key] order
    private String keyOrder;
    private String value;
    private String description;
    //外键,modelName:Role,tableName:senpure_role
    private Long roleId;
    //table [senpure_role_value][column = role_id] order
    private String roleIdOrder;

    public static RoleValue toRoleValue(RoleValueCriteria criteria, RoleValue roleValue) {
        roleValue.setId(criteria.getId());
        roleValue.setKey(criteria.getKey());
        roleValue.setValue(criteria.getValue());
        roleValue.setDescription(criteria.getDescription());
        roleValue.setRoleId(criteria.getRoleId());
        roleValue.setVersion(criteria.getVersion());
        return roleValue;
    }

    public RoleValue toRoleValue() {
        RoleValue roleValue = new RoleValue();
        return toRoleValue(this, roleValue);
    }

    /**
     * 将RoleValueCriteria 的有效值(不为空),赋值给 RoleValue
     *
     * @return RoleValue
     */
    public RoleValue effective(RoleValue roleValue) {
        if (getId() != null) {
            roleValue.setId(getId());
        }
        if (getKey() != null) {
            roleValue.setKey(getKey());
        }
        if (getValue() != null) {
            roleValue.setValue(getValue());
        }
        if (getDescription() != null) {
            roleValue.setDescription(getDescription());
        }
        if (getRoleId() != null) {
            roleValue.setRoleId(getRoleId());
        }
        if (getVersion() != null) {
            roleValue.setVersion(getVersion());
        }
        return roleValue;
    }

    @Override
    public boolean isHasDate() {
        return false;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("RoleValueCriteria{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (key != null) {
            sb.append("key=").append(key).append(",");
        }
        if (value != null) {
            sb.append("value=").append(value).append(",");
        }
        if (description != null) {
            sb.append("description=").append(description).append(",");
        }
        if (roleId != null) {
            sb.append("roleId=").append(roleId).append(",");
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
    public RoleValueCriteria setId(Long id) {
        this.id = id;
        return this;
    }

    public String getKey() {
        return key;
    }

    /**
     * get table [senpure_role_value][column = role_key] order
     *
     * @return
     */
    public String getKeyOrder() {
        return keyOrder;
    }


    public RoleValueCriteria setKey(String key) {
        if (key != null && key.trim().length() == 0) {
            return this;
        }
        this.key = key;
        return this;
    }

    /**
     * set table [senpure_role_value][column = role_key] order DESC||ASC
     *
     * @return
     */
    public RoleValueCriteria setKeyOrder(String keyOrder) {
        this.keyOrder = keyOrder;
        putSort("role_key", keyOrder);
        return this;
    }


    public String getValue() {
        return value;
    }


    public RoleValueCriteria setValue(String value) {
        if (value != null && value.trim().length() == 0) {
            return this;
        }
        this.value = value;
        return this;
    }


    public String getDescription() {
        return description;
    }


    public RoleValueCriteria setDescription(String description) {
        if (description != null && description.trim().length() == 0) {
            return this;
        }
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
     * get table [senpure_role_value][column = role_id] order
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
    public RoleValueCriteria setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    /**
     * set table [senpure_role_value][column = role_id] order DESC||ASC
     *
     * @return
     */
    public RoleValueCriteria setRoleIdOrder(String roleIdOrder) {
        this.roleIdOrder = roleIdOrder;
        putSort("role_id", roleIdOrder);
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
    public RoleValueCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}