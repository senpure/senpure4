package com.senpure.base.entity;

import com.senpure.base.PermissionConstant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by DZ on 2016-06-28 13:58
 */
@Entity
@Table(name = PermissionConstant.DATA_BASE_PREFIX+ "_SYSTEM_VALUE")
public class SystemValue extends LongAndVersionEntity{
    private static final long serialVersionUID = 1068563978298321852L;
    @Column(length = 32,nullable = false)
    private String type;
    @Column(name = "systemKey",length = 32,nullable = false)
    private String key;
    @Column(name = "systemValue",length = 128,nullable = false)
    private String value;
    private String description;
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
