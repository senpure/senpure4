package com.senpure.base.criteria;

import com.senpure.base.criterion.CriteriaStr;
import com.senpure.base.struct.PatternDate;
import com.senpure.base.validator.DynamicDate;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class RoleCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 1021021432L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    private String name;
    //table [senpure_role][column = name] order
    private String nameOrder;
    private String createDate;
    @DynamicDate
    private PatternDate createDateValid = new PatternDate();
    //table [senpure_role][column = create_time] order
    private String createTimeOrder;
    private String description;
    //外键,modelName:Container,tableName:senpure_container
    private String containerId;
    //table [senpure_role][column = container_id] order
    private String containerIdOrder;

    public RoleCriteria toRoleCriteria() {
        RoleCriteria criteria = new RoleCriteria();
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
        if (name != null) {
            criteria.setName(name);
        }
        //table [senpure_role][column = name] order
        if (nameOrder != null) {
            criteria.setNameOrder(nameOrder);
        }
        if (createDate != null) {
            criteria.setCreateDate(createDateValid.getDate());
            if (createDateValid.getDate() != null) {
                criteria.setCreateTime(createDateValid.getDate().getTime());
            }
        }
        //table [senpure_role][column = create_time] order
        if (createTimeOrder != null) {
            criteria.setCreateTimeOrder(createTimeOrder);
        }
        if (description != null) {
            criteria.setDescription(description);
        }
        //外键,modelName:Container,tableName:senpure_container
        if (containerId != null) {
            criteria.setContainerId(Integer.valueOf(containerId));
        }
        //table [senpure_role][column = container_id] order
        if (containerIdOrder != null) {
            criteria.setContainerIdOrder(containerIdOrder);
        }
        return criteria;
    }

    @Override
    public void setDatePattern(String datePattern) {
        super.setDatePattern(datePattern);
        createDateValid.setPattern(datePattern);
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("RoleCriteriaStr{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (name != null) {
            sb.append("name=").append(name).append(",");
        }
        if (createDate != null) {
            sb.append("createDate=").append(createDate).append(",");
        }
        if (description != null) {
            sb.append("description=").append(description).append(",");
        }
        if (containerId != null) {
            sb.append("containerId=").append(containerId).append(",");
        }
    }

    @Override
    protected void afterStr(StringBuilder sb) {
        if (nameOrder != null) {
            sb.append("nameOrder=").append(nameOrder).append(",");
        }
        if (createTimeOrder != null) {
            sb.append("createTimeOrder=").append(createTimeOrder).append(",");
        }
        if (containerIdOrder != null) {
            sb.append("containerIdOrder=").append(containerIdOrder).append(",");
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
    public RoleCriteriaStr setId(String id) {
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
    public RoleCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
        return this;
    }


    public String getName() {
        return name;
    }

    /**
     * get table [senpure_role][column = name] order
     *
     * @return
     */
    public String getNameOrder() {
        return nameOrder;
    }


    public RoleCriteriaStr setName(String name) {
        if (name != null && name.trim().length() == 0) {
            return this;
        }
        this.name = name;
        return this;
    }

    /**
     * set table [senpure_role][column = name] order DESC||ASC
     *
     * @return
     */
    public RoleCriteriaStr setNameOrder(String nameOrder) {
        if (nameOrder != null && nameOrder.trim().length() == 0) {
            return this;
        }
        this.nameOrder = nameOrder;
        return this;
    }


    public String getCreateDate() {
        return createDate;
    }


    public RoleCriteriaStr setCreateDate(String createDate) {
        if (createDate != null && createDate.trim().length() == 0) {
            return this;
        }
        this.createDate = createDate;
        return this;
    }


    /**
     * get table [senpure_role][column = create_time] order
     *
     * @return
     */
    public String getCreateTimeOrder() {
        return createTimeOrder;
    }

    /**
     * set table [senpure_role][column = create_time] order DESC||ASC
     *
     * @return
     */
    public RoleCriteriaStr setCreateTimeOrder(String createTimeOrder) {
        if (createTimeOrder != null && createTimeOrder.trim().length() == 0) {
            return this;
        }
        this.createTimeOrder = createTimeOrder;
        return this;
    }


    public String getDescription() {
        return description;
    }


    public RoleCriteriaStr setDescription(String description) {
        if (description != null && description.trim().length() == 0) {
            return this;
        }
        this.description = description;
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
     * get table [senpure_role][column = container_id] order
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
    public RoleCriteriaStr setContainerId(String containerId) {
        if (containerId != null && containerId.trim().length() == 0) {
            return this;
        }
        this.containerId = containerId;
        return this;
    }

    /**
     * set table [senpure_role][column = container_id] order DESC||ASC
     *
     * @return
     */
    public RoleCriteriaStr setContainerIdOrder(String containerIdOrder) {
        if (containerIdOrder != null && containerIdOrder.trim().length() == 0) {
            return this;
        }
        this.containerIdOrder = containerIdOrder;
        return this;
    }


}