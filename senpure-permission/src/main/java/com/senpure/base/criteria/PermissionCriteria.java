package com.senpure.base.criteria;

import com.senpure.base.model.Permission;
import com.senpure.base.criterion.Criteria;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class PermissionCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 534669811L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    //字符串唯一标识
    private String name;
    //table [senpure_permission][column = name] order
    private String nameOrder;
    //权限名
    private String readableName;
    //是否从数据库更新过
    private Boolean databaseUpdate;
    //NORMAL 正常 ，OWNER 检查所有者，IGNORE 可以忽略(正常放行)
    private String type;
    //table [senpure_permission][column = type] order
    private String typeOrder;
    //'1,2' type为OWNER 配合verifyName使用
    private String offset;
    //'containerResource',roleResource' type为OWNER 配合offset使用
    private String verifyName;
    //table [senpure_permission][column = verify_name] order
    private String verifyNameOrder;
    private String description;
    //排序
    private Integer sort;

    public static Permission toPermission(PermissionCriteria criteria, Permission permission) {
        permission.setId(criteria.getId());
        permission.setName(criteria.getName());
        permission.setReadableName(criteria.getReadableName());
        permission.setDatabaseUpdate(criteria.getDatabaseUpdate());
        permission.setType(criteria.getType());
        permission.setOffset(criteria.getOffset());
        permission.setVerifyName(criteria.getVerifyName());
        permission.setDescription(criteria.getDescription());
        permission.setSort(criteria.getSort());
        permission.setVersion(criteria.getVersion());
        return permission;
    }

    public Permission toPermission() {
        Permission permission = new Permission();
        return toPermission(this, permission);
    }

    /**
     * 将PermissionCriteria 的有效值(不为空),赋值给 Permission
     *
     * @return Permission
     */
    public Permission effective(Permission permission) {
        if (getId() != null) {
            permission.setId(getId());
        }
        if (getName() != null) {
            permission.setName(getName());
        }
        if (getReadableName() != null) {
            permission.setReadableName(getReadableName());
        }
        if (getDatabaseUpdate() != null) {
            permission.setDatabaseUpdate(getDatabaseUpdate());
        }
        if (getType() != null) {
            permission.setType(getType());
        }
        if (getOffset() != null) {
            permission.setOffset(getOffset());
        }
        if (getVerifyName() != null) {
            permission.setVerifyName(getVerifyName());
        }
        if (getDescription() != null) {
            permission.setDescription(getDescription());
        }
        if (getSort() != null) {
            permission.setSort(getSort());
        }
        if (getVersion() != null) {
            permission.setVersion(getVersion());
        }
        return permission;
    }

    @Override
    public boolean isHasDate() {
        return false;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("PermissionCriteria{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (name != null) {
            sb.append("name=").append(name).append(",");
        }
        if (readableName != null) {
            sb.append("readableName=").append(readableName).append(",");
        }
        if (databaseUpdate != null) {
            sb.append("databaseUpdate=").append(databaseUpdate).append(",");
        }
        if (type != null) {
            sb.append("type=").append(type).append(",");
        }
        if (offset != null) {
            sb.append("offset=").append(offset).append(",");
        }
        if (verifyName != null) {
            sb.append("verifyName=").append(verifyName).append(",");
        }
        if (description != null) {
            sb.append("description=").append(description).append(",");
        }
        if (sort != null) {
            sb.append("sort=").append(sort).append(",");
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
    public PermissionCriteria setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * get 字符串唯一标识
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * get table [senpure_permission][column = name] order
     *
     * @return
     */
    public String getNameOrder() {
        return nameOrder;
    }

    /**
     * set 字符串唯一标识
     *
     * @return
     */
    public PermissionCriteria setName(String name) {
        if (name != null && name.trim().length() == 0) {
            return this;
        }
        this.name = name;
        return this;
    }

    /**
     * set table [senpure_permission][column = name] order DESC||ASC
     *
     * @return
     */
    public PermissionCriteria setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
        putSort("name", nameOrder);
        return this;
    }


    /**
     * get 权限名
     *
     * @return
     */
    public String getReadableName() {
        return readableName;
    }

    /**
     * set 权限名
     *
     * @return
     */
    public PermissionCriteria setReadableName(String readableName) {
        if (readableName != null && readableName.trim().length() == 0) {
            return this;
        }
        this.readableName = readableName;
        return this;
    }


    /**
     * get 是否从数据库更新过
     *
     * @return
     */
    public Boolean getDatabaseUpdate() {
        return databaseUpdate;
    }

    /**
     * set 是否从数据库更新过
     *
     * @return
     */
    public PermissionCriteria setDatabaseUpdate(Boolean databaseUpdate) {
        this.databaseUpdate = databaseUpdate;
        return this;
    }


    /**
     * get NORMAL 正常 ，OWNER 检查所有者，IGNORE 可以忽略(正常放行)
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * get table [senpure_permission][column = type] order
     *
     * @return
     */
    public String getTypeOrder() {
        return typeOrder;
    }

    /**
     * set NORMAL 正常 ，OWNER 检查所有者，IGNORE 可以忽略(正常放行)
     *
     * @return
     */
    public PermissionCriteria setType(String type) {
        if (type != null && type.trim().length() == 0) {
            return this;
        }
        this.type = type;
        return this;
    }

    /**
     * set table [senpure_permission][column = type] order DESC||ASC
     *
     * @return
     */
    public PermissionCriteria setTypeOrder(String typeOrder) {
        this.typeOrder = typeOrder;
        putSort("type", typeOrder);
        return this;
    }


    /**
     * get '1,2' type为OWNER 配合verifyName使用
     *
     * @return
     */
    public String getOffset() {
        return offset;
    }

    /**
     * set '1,2' type为OWNER 配合verifyName使用
     *
     * @return
     */
    public PermissionCriteria setOffset(String offset) {
        if (offset != null && offset.trim().length() == 0) {
            return this;
        }
        this.offset = offset;
        return this;
    }


    /**
     * get 'containerResource',roleResource' type为OWNER 配合offset使用
     *
     * @return
     */
    public String getVerifyName() {
        return verifyName;
    }

    /**
     * get table [senpure_permission][column = verify_name] order
     *
     * @return
     */
    public String getVerifyNameOrder() {
        return verifyNameOrder;
    }

    /**
     * set 'containerResource',roleResource' type为OWNER 配合offset使用
     *
     * @return
     */
    public PermissionCriteria setVerifyName(String verifyName) {
        if (verifyName != null && verifyName.trim().length() == 0) {
            return this;
        }
        this.verifyName = verifyName;
        return this;
    }

    /**
     * set table [senpure_permission][column = verify_name] order DESC||ASC
     *
     * @return
     */
    public PermissionCriteria setVerifyNameOrder(String verifyNameOrder) {
        this.verifyNameOrder = verifyNameOrder;
        putSort("verify_name", verifyNameOrder);
        return this;
    }


    public String getDescription() {
        return description;
    }


    public PermissionCriteria setDescription(String description) {
        if (description != null && description.trim().length() == 0) {
            return this;
        }
        this.description = description;
        return this;
    }


    /**
     * get 排序
     *
     * @return
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * set 排序
     *
     * @return
     */
    public PermissionCriteria setSort(Integer sort) {
        this.sort = sort;
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
    public PermissionCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}