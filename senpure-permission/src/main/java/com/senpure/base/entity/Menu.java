package com.senpure.base.entity;

import com.senpure.base.PermissionConstant;
import com.senpure.base.annotation.Explain;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by 罗中正 on 2017/6/15.
 */
@Entity
@Table(name = PermissionConstant.DATA_BASE_PREFIX+ "_MENU")
public class Menu  extends VersionEntity implements Serializable{
    private static final long serialVersionUID = -7140237383870155753L;
    @Id
    @GenericGenerator(name = "hibernate", strategy = "assigned")
    private Integer id;
    private Integer parentId;
    private String text;
    private String icon;
    private String uri;
    private String config;
    private Integer sort;
    private Boolean databaseUpdate = false;
    @Explain("不登录也有的菜单")
    private Boolean directView=false;
    private String i18nKey;
    private String description;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public Boolean getDatabaseUpdate() {
        return databaseUpdate;
    }

    public void setDatabaseUpdate(Boolean databaseUpdate) {
        this.databaseUpdate = databaseUpdate;
    }

    public Boolean getDirectView() {
        return directView;
    }

    public void setDirectView(Boolean directView) {
        this.directView = directView;
    }

    public String getI18nKey() {
        return i18nKey;
    }

    public void setI18nKey(String i18nKey) {
        this.i18nKey = i18nKey;
    }
}
