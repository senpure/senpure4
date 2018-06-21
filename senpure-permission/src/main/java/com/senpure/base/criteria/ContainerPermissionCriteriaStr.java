package com.senpure.base.criteria;

import com.senpure.base.criterion.CriteriaStr;
import com.senpure.base.struct.PatternDate;
import com.senpure.base.validator.DynamicDate;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:20
 */
public class ContainerPermissionCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 1444113447L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    //table [senpure_container_permission][column = expiry_time] order
    private String expiryTimeOrder;
    private String expiryDate;
    @DynamicDate
    private PatternDate expiryDateValid = new PatternDate();
    //外键,modelName:Container,tableName:senpure_container
    private String containerId;
    //table [senpure_container_permission][column = container_id] order
    private String containerIdOrder;
    //外键,modelName:Permission,tableName:senpure_permission
    private String permissionId;
    //table [senpure_container_permission][column = permission_id] order
    private String permissionIdOrder;

    public ContainerPermissionCriteria toContainerPermissionCriteria() {
        ContainerPermissionCriteria criteria = new ContainerPermissionCriteria();
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
        //table [senpure_container_permission][column = expiry_time] order
        if (expiryTimeOrder != null) {
            criteria.setExpiryTimeOrder(expiryTimeOrder);
        }
        if (expiryDate != null) {
            criteria.setExpiryDate(expiryDateValid.getDate());
            if (expiryDateValid.getDate() != null) {
                criteria.setExpiryTime(expiryDateValid.getDate().getTime());
            }
        }
        //外键,modelName:Container,tableName:senpure_container
        if (containerId != null) {
            criteria.setContainerId(Integer.valueOf(containerId));
        }
        //table [senpure_container_permission][column = container_id] order
        if (containerIdOrder != null) {
            criteria.setContainerIdOrder(containerIdOrder);
        }
        //外键,modelName:Permission,tableName:senpure_permission
        if (permissionId != null) {
            criteria.setPermissionId(Long.valueOf(permissionId));
        }
        //table [senpure_container_permission][column = permission_id] order
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
        sb.append("ContainerPermissionCriteriaStr{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
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

    @Override
    protected void afterStr(StringBuilder sb) {
        if (expiryTimeOrder != null) {
            sb.append("expiryTimeOrder=").append(expiryTimeOrder).append(",");
        }
        if (containerIdOrder != null) {
            sb.append("containerIdOrder=").append(containerIdOrder).append(",");
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
    public ContainerPermissionCriteriaStr setId(String id) {
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
    public ContainerPermissionCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
        return this;
    }


    /**
     * get table [senpure_container_permission][column = expiry_time] order
     *
     * @return
     */
    public String getExpiryTimeOrder() {
        return expiryTimeOrder;
    }

    /**
     * set table [senpure_container_permission][column = expiry_time] order DESC||ASC
     *
     * @return
     */
    public ContainerPermissionCriteriaStr setExpiryTimeOrder(String expiryTimeOrder) {
        if (expiryTimeOrder != null && expiryTimeOrder.trim().length() == 0) {
            return this;
        }
        this.expiryTimeOrder = expiryTimeOrder;
        return this;
    }


    public String getExpiryDate() {
        return expiryDate;
    }


    public ContainerPermissionCriteriaStr setExpiryDate(String expiryDate) {
        if (expiryDate != null && expiryDate.trim().length() == 0) {
            return this;
        }
        this.expiryDate = expiryDate;
        return this;
    }


    /**
     * get 外键,modelName:Container,tableName:senpure_container
     *
     * @return
     */
    public String getContainerId() {
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
    public ContainerPermissionCriteriaStr setContainerId(String containerId) {
        if (containerId != null && containerId.trim().length() == 0) {
            return this;
        }
        this.containerId = containerId;
        return this;
    }

    /**
     * set table [senpure_container_permission][column = container_id] order DESC||ASC
     *
     * @return
     */
    public ContainerPermissionCriteriaStr setContainerIdOrder(String containerIdOrder) {
        if (containerIdOrder != null && containerIdOrder.trim().length() == 0) {
            return this;
        }
        this.containerIdOrder = containerIdOrder;
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
    public ContainerPermissionCriteriaStr setPermissionId(String permissionId) {
        if (permissionId != null && permissionId.trim().length() == 0) {
            return this;
        }
        this.permissionId = permissionId;
        return this;
    }

    /**
     * set table [senpure_container_permission][column = permission_id] order DESC||ASC
     *
     * @return
     */
    public ContainerPermissionCriteriaStr setPermissionIdOrder(String permissionIdOrder) {
        if (permissionIdOrder != null && permissionIdOrder.trim().length() == 0) {
            return this;
        }
        this.permissionIdOrder = permissionIdOrder;
        return this;
    }


}