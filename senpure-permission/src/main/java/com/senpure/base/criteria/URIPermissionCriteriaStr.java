package com.senpure.base.criteria;

import com.senpure.base.criterion.CriteriaStr;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class URIPermissionCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 1046435846L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    private String uriAndMethod;
    //table [senpure_uri_permission][column = uri_and_method] order
    private String uriAndMethodOrder;
    //是否从数据库更新过
    private String databaseUpdate;
    //外键,modelName:Permission,tableName:senpure_permission
    private String permissionId;
    //table [senpure_uri_permission][column = permission_id] order
    private String permissionIdOrder;

    public URIPermissionCriteria toURIPermissionCriteria() {
        URIPermissionCriteria criteria = new URIPermissionCriteria();
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
        if (uriAndMethod != null) {
            criteria.setUriAndMethod(uriAndMethod);
        }
        //table [senpure_uri_permission][column = uri_and_method] order
        if (uriAndMethodOrder != null) {
            criteria.setUriAndMethodOrder(uriAndMethodOrder);
        }
        //是否从数据库更新过
        if (databaseUpdate != null) {
            criteria.setDatabaseUpdate(Boolean.valueOf(databaseUpdate));
        }
        //外键,modelName:Permission,tableName:senpure_permission
        if (permissionId != null) {
            criteria.setPermissionId(Long.valueOf(permissionId));
        }
        //table [senpure_uri_permission][column = permission_id] order
        if (permissionIdOrder != null) {
            criteria.setPermissionIdOrder(permissionIdOrder);
        }
        return criteria;
    }


    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("URIPermissionCriteriaStr{");
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

    @Override
    protected void afterStr(StringBuilder sb) {
        if (uriAndMethodOrder != null) {
            sb.append("uriAndMethodOrder=").append(uriAndMethodOrder).append(",");
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
    public URIPermissionCriteriaStr setId(String id) {
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
    public URIPermissionCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
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


    public URIPermissionCriteriaStr setUriAndMethod(String uriAndMethod) {
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
    public URIPermissionCriteriaStr setUriAndMethodOrder(String uriAndMethodOrder) {
        if (uriAndMethodOrder != null && uriAndMethodOrder.trim().length() == 0) {
            return this;
        }
        this.uriAndMethodOrder = uriAndMethodOrder;
        return this;
    }


    /**
     * get 是否从数据库更新过
     *
     * @return
     */
    public String getDatabaseUpdate() {
        return databaseUpdate;
    }

    /**
     * set 是否从数据库更新过
     *
     * @return
     */
    public URIPermissionCriteriaStr setDatabaseUpdate(String databaseUpdate) {
        if (databaseUpdate != null && databaseUpdate.trim().length() == 0) {
            return this;
        }
        this.databaseUpdate = databaseUpdate;
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
    public URIPermissionCriteriaStr setPermissionId(String permissionId) {
        if (permissionId != null && permissionId.trim().length() == 0) {
            return this;
        }
        this.permissionId = permissionId;
        return this;
    }

    /**
     * set table [senpure_uri_permission][column = permission_id] order DESC||ASC
     *
     * @return
     */
    public URIPermissionCriteriaStr setPermissionIdOrder(String permissionIdOrder) {
        if (permissionIdOrder != null && permissionIdOrder.trim().length() == 0) {
            return this;
        }
        this.permissionIdOrder = permissionIdOrder;
        return this;
    }


}