package com.senpure.base.criteria;

import com.senpure.base.model.Container;
import com.senpure.base.criterion.Criteria;

import java.io.Serializable;
import java.util.Date;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class ContainerCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 611392665L;

    //主键
    private Integer id;
    //乐观锁，版本控制
    private Integer version;
    private String name;
    //table [senpure_container][column = name] order
    private String nameOrder;
    private String description;
    private Integer level;
    private Long relation;
    private Long createTime;
    //table [senpure_container][column = create_time] order
    private String createTimeOrder;
    private Date createDate;
    //所属容器层次结构,主要是为了查询,多有用like查询 -1-2-3-12-
    private String containerStructure;
    //父容器ID(外键,modelName:Container,tableName:senpure_container)
    private Integer parentId;
    //table [senpure_container][column = parent_id] order
    private String parentIdOrder;

    public static Container toContainer(ContainerCriteria criteria, Container container) {
        container.setId(criteria.getId());
        container.setName(criteria.getName());
        container.setDescription(criteria.getDescription());
        container.setLevel(criteria.getLevel());
        container.setRelation(criteria.getRelation());
        container.setCreateTime(criteria.getCreateTime());
        container.setCreateDate(criteria.getCreateDate());
        container.setContainerStructure(criteria.getContainerStructure());
        container.setParentId(criteria.getParentId());
        container.setVersion(criteria.getVersion());
        return container;
    }

    public Container toContainer() {
        Container container = new Container();
        return toContainer(this, container);
    }

    /**
     * 将ContainerCriteria 的有效值(不为空),赋值给 Container
     *
     * @return Container
     */
    public Container effective(Container container) {
        if (getId() != null) {
            container.setId(getId());
        }
        if (getName() != null) {
            container.setName(getName());
        }
        if (getDescription() != null) {
            container.setDescription(getDescription());
        }
        if (getLevel() != null) {
            container.setLevel(getLevel());
        }
        if (getRelation() != null) {
            container.setRelation(getRelation());
        }
        if (getCreateTime() != null) {
            container.setCreateTime(getCreateTime());
        }
        if (getCreateDate() != null) {
            container.setCreateDate(getCreateDate());
        }
        if (getContainerStructure() != null) {
            container.setContainerStructure(getContainerStructure());
        }
        if (getParentId() != null) {
            container.setParentId(getParentId());
        }
        if (getVersion() != null) {
            container.setVersion(getVersion());
        }
        return container;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("ContainerCriteria{");
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
        if (createTime != null) {
            sb.append("createTime=").append(createTime).append(",");
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

    /**
     * get 主键
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * set 主键
     *
     * @return
     */
    public ContainerCriteria setId(Integer id) {
        this.id = id;
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


    public ContainerCriteria setName(String name) {
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
    public ContainerCriteria setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
        putSort("name", nameOrder);
        return this;
    }


    public String getDescription() {
        return description;
    }


    public ContainerCriteria setDescription(String description) {
        if (description != null && description.trim().length() == 0) {
            return this;
        }
        this.description = description;
        return this;
    }


    public Integer getLevel() {
        return level;
    }


    public ContainerCriteria setLevel(Integer level) {
        this.level = level;
        return this;
    }


    public Long getRelation() {
        return relation;
    }


    public ContainerCriteria setRelation(Long relation) {
        this.relation = relation;
        return this;
    }


    public Long getCreateTime() {
        return createTime;
    }

    /**
     * get table [senpure_container][column = create_time] order
     *
     * @return
     */
    public String getCreateTimeOrder() {
        return createTimeOrder;
    }


    public ContainerCriteria setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * set table [senpure_container][column = create_time] order DESC||ASC
     *
     * @return
     */
    public ContainerCriteria setCreateTimeOrder(String createTimeOrder) {
        this.createTimeOrder = createTimeOrder;
        putSort("create_time", createTimeOrder);
        return this;
    }


    public Date getCreateDate() {
        return createDate;
    }


    public ContainerCriteria setCreateDate(Date createDate) {
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
    public ContainerCriteria setContainerStructure(String containerStructure) {
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
    public Integer getParentId() {
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
    public ContainerCriteria setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    /**
     * set table [senpure_container][column = parent_id] order DESC||ASC
     *
     * @return
     */
    public ContainerCriteria setParentIdOrder(String parentIdOrder) {
        this.parentIdOrder = parentIdOrder;
        putSort("parent_id", parentIdOrder);
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
    public ContainerCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}