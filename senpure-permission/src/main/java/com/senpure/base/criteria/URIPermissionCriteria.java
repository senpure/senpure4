package com.senpure.base.criteria;

import com.senpure.base.model.URIPermission;
import com.senpure.base.criterion.Criteria;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class URIPermissionCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 1046435846L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private String uriAndMethod;
    //table [senpure_uri_permission][column = uri_and_method] order
    private String uriAndMethodOrder;
    //是否从数据库更新过
    private Boolean databaseUpdate;
    //外键,modelName:Permission,tableName:senpure_permission
    private Long permissionId;
    //table [senpure_uri_permission][column = permission_id] order
    private String permissionIdOrder;

    public static URIPermission toURIPermission(URIPermissionCriteria criteria, URIPermission uriPermission) {
        uriPermission.setId(criteria.getId());
        uriPermission.setUriAndMethod(criteria.getUriAndMethod());
        uriPermission.setDatabaseUpdate(criteria.getDatabaseUpdate());
        uriPermission.setPermissionId(criteria.getPermissionId());
        uriPermission.setVersion(criteria.getVersion());
        return uriPermission;
    }

    public URIPermission toURIPermission() {
        URIPermission uriPermission = new URIPermission();
        return toURIPermission(this, uriPermission);
    }

    /**
     * 将URIPermissionCriteria 的有效值(不为空),赋值给 URIPermission
     *
     * @return URIPermission
     */
    public URIPermission effective(URIPermission uriPermission) {
        if (getId() != null) {
            uriPermission.setId(getId());
        }
        if (getUriAndMethod() != null) {
            uriPermission.setUriAndMethod(getUriAndMethod());
        }
        if (getDatabaseUpdate() != null) {
            uriPermission.setDatabaseUpdate(getDatabaseUpdate());
        }
        if (getPermissionId() != null) {
            uriPermission.setPermissionId(getPermissionId());
        }
        if (getVersion() != null) {
            uriPermission.setVersion(getVersion());
        }
        return uriPermission;
    }

    @Override
    public boolean isHasDate() {
        return false;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("URIPermissionCriteria{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (uriAndMethod != null) {
            sb.append("uriAndMethod=").append(uriAndMethod).append(",");
        }
        if (databaseUpdate != null) {
            sb.append("databaseUpdate=").append(databaseUpdate).append(",");
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
    public URIPermissionCriteria setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUriAndMethod() {
        return uriAndMethod;
    }

    /**
     * get table [senpure_uri_permission][column = uri_and_method] order
     *
     * @return
     */
    public String getUriAndMethodOrder() {
        return uriAndMethodOrder;
    }


    public URIPermissionCriteria setUriAndMethod(String uriAndMethod) {
        if (uriAndMethod != null && uriAndMethod.trim().length() == 0) {
            return this;
        }
        this.uriAndMethod = uriAndMethod;
        return this;
    }

    /**
     * set table [senpure_uri_permission][column = uri_and_method] order DESC||ASC
     *
     * @return
     */
    public URIPermissionCriteria setUriAndMethodOrder(String uriAndMethodOrder) {
        this.uriAndMethodOrder = uriAndMethodOrder;
        putSort("uri_and_method", uriAndMethodOrder);
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
    public URIPermissionCriteria setDatabaseUpdate(Boolean databaseUpdate) {
        this.databaseUpdate = databaseUpdate;
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
     * get table [senpure_uri_permission][column = permission_id] order
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
    public URIPermissionCriteria setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
        return this;
    }

    /**
     * set table [senpure_uri_permission][column = permission_id] order DESC||ASC
     *
     * @return
     */
    public URIPermissionCriteria setPermissionIdOrder(String permissionIdOrder) {
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
    public URIPermissionCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}