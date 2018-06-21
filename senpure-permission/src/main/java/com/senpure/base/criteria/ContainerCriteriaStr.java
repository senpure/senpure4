package com.senpure.base.criteria;

import com.senpure.base.criterion.CriteriaStr;
import com.senpure.base.struct.PatternDate;
import com.senpure.base.validator.DynamicDate;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class ContainerCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 611392665L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    private String name;
    //table [senpure_container][column = name] order
    private String nameOrder;
    private String description;
    private String level;
    private String relation;
    //table [senpure_container][column = create_time] order
    private String createTimeOrder;
    private String createDate;
    @DynamicDate
    private PatternDate createDateValid = new PatternDate();
    //所属容器层次结构,主要是为了查询,多有用like查询 -1-2-3-12-
    private String containerStructure;
    //父容器ID(外键,modelName:Container,tableName:senpure_container)
    private String parentId;
    //table [senpure_container][column = parent_id] order
    private String parentIdOrder;

    public ContainerCriteria toContainerCriteria() {
        ContainerCriteria criteria = new ContainerCriteria();
        criteria.setUsePage(Boolean.valueOf(getUsePage()));
        criteria.setPage(Integer.valueOf(getPage()));
        criteria.setPageSize(Integer.valueOf(getPageSize()));
        criteria.setStartDate(getStartDateValid().getDate());
        criteria.setEndDate(getEndDateValid().getDate());
        //主键
        if (id != null) {
            criteria.setId(Integer.valueOf(id));
        }
        //乐观锁，版本控制
        if (version != null) {
            criteria.setVersion(Integer.valueOf(version));
        }
        if (name != null) {
            criteria.setName(name);
        }
        //table [senpure_container][column = name] order
        if (nameOrder != null) {
            criteria.setNameOrder(nameOrder);
        }
        if (description != null) {
            criteria.setDescription(description);
        }
        if (level != null) {
            criteria.setLevel(Integer.valueOf(level));
        }
        if (relation != null) {
            criteria.setRelation(Long.valueOf(relation));
        }
        //table [senpure_container][column = create_time] order
        if (createTimeOrder != null) {
            criteria.setCreateTimeOrder(createTimeOrder);
        }
        if (createDate != null) {
            criteria.setCreateDate(createDateValid.getDate());
            if (createDateValid.getDate() != null) {
                criteria.setCreateTime(createDateValid.getDate().getTime());
            }
        }
        //所属容器层次结构,主要是为了查询,多有用like查询 -1-2-3-12-
        if (containerStructure != null) {
            criteria.setContainerStructure(containerStructure);
        }
        //父容器ID(外键,modelName:Container,tableName:senpure_container)
        if (parentId != null) {
            criteria.setParentId(Integer.valueOf(parentId));
        }
        //table [senpure_container][column = parent_id] order
        if (parentIdOrder != null) {
            criteria.setParentIdOrder(parentIdOrder);
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
        sb.append("ContainerCriteriaStr{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (name != null) {
            sb.append("name=").append(name).append(",");
        }
        if (description != null) {
            sb.append("description=").append(description).append(",");
        }
        if (level != null) {
            sb.append("level=").append(level).append(",");
        }
        if (relation != null) {
            sb.append("relation=").append(relation).append(",");
        }
        if (createDate != null) {
            sb.append("createDate=").append(createDate).append(",");
        }
        if (containerStructure != null) {
            sb.append("containerStructure=").append(containerStructure).append(",");
        }
        if (parentId != null) {
            sb.append("parentId=").append(parentId).append(",");
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
        if (parentIdOrder != null) {
            sb.append("parentIdOrder=").append(parentIdOrder).append(",");
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
    public ContainerCriteriaStr setId(String id) {
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
    public ContainerCriteriaStr setVersion(String version) {
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
     * get table [senpure_container][column = name] order
     *
     * @return
     */
    public String getNameOrder() {
        return nameOrder;
    }


    public ContainerCriteriaStr setName(String name) {
        if (name != null && name.trim().length() == 0) {
            return this;
        }
        this.name = name;
        return this;
    }

    /**
     * set table [senpure_container][column = name] order DESC||ASC
     *
     * @return
     */
    public ContainerCriteriaStr setNameOrder(String nameOrder) {
        if (nameOrder != null && nameOrder.trim().length() == 0) {
            return this;
        }
        this.nameOrder = nameOrder;
        return this;
    }


    public String getDescription() {
        return description;
    }


    public ContainerCriteriaStr setDescription(String description) {
        if (description != null && description.trim().length() == 0) {
            return this;
        }
        this.description = description;
        return this;
    }


    public String getLevel() {
        return level;
    }


    public ContainerCriteriaStr setLevel(String level) {
        if (level != null && level.trim().length() == 0) {
            return this;
        }
        this.level = level;
        return this;
    }


    public String getRelation() {
        return relation;
    }


    public ContainerCriteriaStr setRelation(String relation) {
        if (relation != null && relation.trim().length() == 0) {
            return this;
        }
        this.relation = relation;
        return this;
    }


    /**
     * get table [senpure_container][column = create_time] order
     *
     * @return
     */
    public String getCreateTimeOrder() {
        return createTimeOrder;
    }

    /**
     * set table [senpure_container][column = create_time] order DESC||ASC
     *
     * @return
     */
    public ContainerCriteriaStr setCreateTimeOrder(String createTimeOrder) {
        if (createTimeOrder != null && createTimeOrder.trim().length() == 0) {
            return this;
        }
        this.createTimeOrder = createTimeOrder;
        return this;
    }


    public String getCreateDate() {
        return createDate;
    }


    public ContainerCriteriaStr setCreateDate(String createDate) {
        if (createDate != null && createDate.trim().length() == 0) {
            return this;
        }
        this.createDate = createDate;
        return this;
    }


    /**
     * get 所属容器层次结构,主要是为了查询,多有用like查询 -1-2-3-12-
     *
     * @return
     */
    public String getContainerStructure() {
        return containerStructure;
    }

    /**
     * set 所属容器层次结构,主要是为了查询,多有用like查询 -1-2-3-12-
     *
     * @return
     */
    public ContainerCriteriaStr setContainerStructure(String containerStructure) {
        if (containerStructure != null && containerStructure.trim().length() == 0) {
            return this;
        }
        this.containerStructure = containerStructure;
        return this;
    }


    /**
     * get 父容器ID(外键,modelName:Container,tableName:senpure_container)
     *
     * @return
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * get table [senpure_container][column = parent_id] order
     *
     * @return
     */
    public String getParentIdOrder() {
        return parentIdOrder;
    }

    /**
     * set 父容器ID(外键,modelName:Container,tableName:senpure_container)
     *
     * @return
     */
    public ContainerCriteriaStr setParentId(String parentId) {
        if (parentId != null && parentId.trim().length() == 0) {
            return this;
        }
        this.parentId = parentId;
        return this;
    }

    /**
     * set table [senpure_container][column = parent_id] order DESC||ASC
     *
     * @return
     */
    public ContainerCriteriaStr setParentIdOrder(String parentIdOrder) {
        if (parentIdOrder != null && parentIdOrder.trim().length() == 0) {
            return this;
        }
        this.parentIdOrder = parentIdOrder;
        return this;
    }


}