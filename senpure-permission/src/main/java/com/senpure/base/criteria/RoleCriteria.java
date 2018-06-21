package com.senpure.base.criteria;

import com.senpure.base.model.Role;
import com.senpure.base.criterion.Criteria;

import java.io.Serializable;
import java.util.Date;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class RoleCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 1021021432L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private String name;
    //table [senpure_role][column = name] order
    private String nameOrder;
    private Date createDate;
    private Long createTime;
    //table [senpure_role][column = create_time] order
    private String createTimeOrder;
    private String description;
    //外键,modelName:Container,tableName:senpure_container
    private Integer containerId;
    //table [senpure_role][column = container_id] order
    private String containerIdOrder;

    public static Role toRole(RoleCriteria criteria, Role role) {
        role.setId(criteria.getId());
        role.setName(criteria.getName());
        role.setCreateDate(criteria.getCreateDate());
        role.setCreateTime(criteria.getCreateTime());
        role.setDescription(criteria.getDescription());
        role.setContainerId(criteria.getContainerId());
        role.setVersion(criteria.getVersion());
        return role;
    }

    public Role toRole() {
        Role role = new Role();
        return toRole(this, role);
    }

    /**
     * 将RoleCriteria 的有效值(不为空),赋值给 Role
     *
     * @return Role
     */
    public Role effective(Role role) {
        if (getId() != null) {
            role.setId(getId());
        }
        if (getName() != null) {
            role.setName(getName());
        }
        if (getCreateDate() != null) {
            role.setCreateDate(getCreateDate());
        }
        if (getCreateTime() != null) {
            role.setCreateTime(getCreateTime());
        }
        if (getDescription() != null) {
            role.setDescription(getDescription());
        }
        if (getContainerId() != null) {
            role.setContainerId(getContainerId());
        }
        if (getVersion() != null) {
            role.setVersion(getVersion());
        }
        return role;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("RoleCriteria{");
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
        if (createTime != null) {
            sb.append("createTime=").append(createTime).append(",");
        }
        if (description != null) {
            sb.append("description=").append(description).append(",");
        }
        if (containerId != null) {
            sb.append("containerId=").append(containerId).append(",");
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
    public RoleCriteria setId(Long id) {
        this.id = id;
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


    public RoleCriteria setName(String name) {
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
    public RoleCriteria setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
        putSort("name", nameOrder);
        return this;
    }


    public Date getCreateDate() {
        return createDate;
    }


    public RoleCriteria setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }


    public Long getCreateTime() {
        return createTime;
    }

    /**
     * get table [senpure_role][column = create_time] order
     *
     * @return
     */
    public String getCreateTimeOrder() {
        return createTimeOrder;
    }


    public RoleCriteria setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * set table [senpure_role][column = create_time] order DESC||ASC
     *
     * @return
     */
    public RoleCriteria setCreateTimeOrder(String createTimeOrder) {
        this.createTimeOrder = createTimeOrder;
        putSort("create_time", createTimeOrder);
        return this;
    }


    public String getDescription() {
        return description;
    }


    public RoleCriteria setDescription(String description) {
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
    public Integer getContainerId() {
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
    public RoleCriteria setContainerId(Integer containerId) {
        this.containerId = containerId;
        return this;
    }

    /**
     * set table [senpure_role][column = container_id] order DESC||ASC
     *
     * @return
     */
    public RoleCriteria setContainerIdOrder(String containerIdOrder) {
        this.containerIdOrder = containerIdOrder;
        putSort("container_id", containerIdOrder);
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
    public RoleCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}