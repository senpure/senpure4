package com.senpure.base.criteria;

import com.senpure.base.criterion.CriteriaStr;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class SystemValueCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 748356364L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    private String type;
    //table [senpure_system_value][column = type] order
    private String typeOrder;
    private String key;
    //table [senpure_system_value][column = system_key] order
    private String keyOrder;
    private String value;
    private String description;

    public SystemValueCriteria toSystemValueCriteria() {
        SystemValueCriteria criteria = new SystemValueCriteria();
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
        if (type != null) {
            criteria.setType(type);
        }
        //table [senpure_system_value][column = type] order
        if (typeOrder != null) {
            criteria.setTypeOrder(typeOrder);
        }
        if (key != null) {
            criteria.setKey(key);
        }
        //table [senpure_system_value][column = system_key] order
        if (keyOrder != null) {
            criteria.setKeyOrder(keyOrder);
        }
        if (value != null) {
            criteria.setValue(value);
        }
        if (description != null) {
            criteria.setDescription(description);
        }
        return criteria;
    }


    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("SystemValueCriteriaStr{");
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

    @Override
    protected void afterStr(StringBuilder sb) {
        if (typeOrder != null) {
            sb.append("typeOrder=").append(typeOrder).append(",");
        }
        if (keyOrder != null) {
            sb.append("keyOrder=").append(keyOrder).append(",");
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
    public SystemValueCriteriaStr setId(String id) {
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
    public SystemValueCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
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


    public SystemValueCriteriaStr setType(String type) {
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
    public SystemValueCriteriaStr setTypeOrder(String typeOrder) {
        if (typeOrder != null && typeOrder.trim().length() == 0) {
            return this;
        }
        this.typeOrder = typeOrder;
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


    public SystemValueCriteriaStr setKey(String key) {
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
    public SystemValueCriteriaStr setKeyOrder(String keyOrder) {
        if (keyOrder != null && keyOrder.trim().length() == 0) {
            return this;
        }
        this.keyOrder = keyOrder;
        return this;
    }


    public String getValue() {
        return value;
    }


    public SystemValueCriteriaStr setValue(String value) {
        if (value != null && value.trim().length() == 0) {
            return this;
        }
        this.value = value;
        return this;
    }


    public String getDescription() {
        return description;
    }


    public SystemValueCriteriaStr setDescription(String description) {
        if (description != null && description.trim().length() == 0) {
            return this;
        }
        this.description = description;
        return this;
    }


}