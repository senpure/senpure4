package com.senpure.base.criteria;

import com.senpure.base.model.SystemValue;
import com.senpure.base.criterion.Criteria;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class SystemValueCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 748356364L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private String type;
    //table [senpure_system_value][column = type] order
    private String typeOrder;
    private String key;
    //table [senpure_system_value][column = system_key] order
    private String keyOrder;
    private String value;
    private String description;

    public static SystemValue toSystemValue(SystemValueCriteria criteria, SystemValue systemValue) {
        systemValue.setId(criteria.getId());
        systemValue.setType(criteria.getType());
        systemValue.setKey(criteria.getKey());
        systemValue.setValue(criteria.getValue());
        systemValue.setDescription(criteria.getDescription());
        systemValue.setVersion(criteria.getVersion());
        return systemValue;
    }

    public SystemValue toSystemValue() {
        SystemValue systemValue = new SystemValue();
        return toSystemValue(this, systemValue);
    }

    /**
     * 将SystemValueCriteria 的有效值(不为空),赋值给 SystemValue
     *
     * @return SystemValue
     */
    public SystemValue effective(SystemValue systemValue) {
        if (getId() != null) {
            systemValue.setId(getId());
        }
        if (getType() != null) {
            systemValue.setType(getType());
        }
        if (getKey() != null) {
            systemValue.setKey(getKey());
        }
        if (getValue() != null) {
            systemValue.setValue(getValue());
        }
        if (getDescription() != null) {
            systemValue.setDescription(getDescription());
        }
        if (getVersion() != null) {
            systemValue.setVersion(getVersion());
        }
        return systemValue;
    }

    @Override
    public boolean isHasDate() {
        return false;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("SystemValueCriteria{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (type != null) {
            sb.append("type=").append(type).append(",");
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
    public SystemValueCriteria setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    /**
     * get table [senpure_system_value][column = type] order
     *
     * @return
     */
    public String getTypeOrder() {
        return typeOrder;
    }


    public SystemValueCriteria setType(String type) {
        if (type != null && type.trim().length() == 0) {
            return this;
        }
        this.type = type;
        return this;
    }

    /**
     * set table [senpure_system_value][column = type] order DESC||ASC
     *
     * @return
     */
    public SystemValueCriteria setTypeOrder(String typeOrder) {
        this.typeOrder = typeOrder;
        putSort("type", typeOrder);
        return this;
    }


    public String getKey() {
        return key;
    }

    /**
     * get table [senpure_system_value][column = system_key] order
     *
     * @return
     */
    public String getKeyOrder() {
        return keyOrder;
    }


    public SystemValueCriteria setKey(String key) {
        if (key != null && key.trim().length() == 0) {
            return this;
        }
        this.key = key;
        return this;
    }

    /**
     * set table [senpure_system_value][column = system_key] order DESC||ASC
     *
     * @return
     */
    public SystemValueCriteria setKeyOrder(String keyOrder) {
        this.keyOrder = keyOrder;
        putSort("system_key", keyOrder);
        return this;
    }


    public String getValue() {
        return value;
    }


    public SystemValueCriteria setValue(String value) {
        if (value != null && value.trim().length() == 0) {
            return this;
        }
        this.value = value;
        return this;
    }


    public String getDescription() {
        return description;
    }


    public SystemValueCriteria setDescription(String description) {
        if (description != null && description.trim().length() == 0) {
            return this;
        }
        this.description = description;
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
    public SystemValueCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}