package com.senpure.base.model;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = 1493076390L;

    //主键
    private Integer id;
    //乐观锁，版本控制
    private Integer version;
    private Integer parentId;
    private String text;
    private String icon;
    private String uri;
    private String config;
    private Integer sort;
    private Boolean databaseUpdate;
    //不登录也有的菜单
    private Boolean directView;
    private String i18nKey;
    private String description;

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
    public Menu setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getParentId() {
        return parentId;
    }


    public Menu setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getText() {
        return text;
    }


    public Menu setText(String text) {
        this.text = text;
        return this;
    }

    public String getIcon() {
        return icon;
    }


    public Menu setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getUri() {
        return uri;
    }


    public Menu setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getConfig() {
        return config;
    }


    public Menu setConfig(String config) {
        this.config = config;
        return this;
    }

    public Integer getSort() {
        return sort;
    }


    public Menu setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Boolean getDatabaseUpdate() {
        return databaseUpdate;
    }


    public Menu setDatabaseUpdate(Boolean databaseUpdate) {
        this.databaseUpdate = databaseUpdate;
        return this;
    }

    /**
     * get 不登录也有的菜单
     *
     * @return
     */
    public Boolean getDirectView() {
        return directView;
    }

    /**
     * set 不登录也有的菜单
     *
     * @return
     */
    public Menu setDirectView(Boolean directView) {
        this.directView = directView;
        return this;
    }

    public String getI18nKey() {
        return i18nKey;
    }


    public Menu setI18nKey(String i18nKey) {
        this.i18nKey = i18nKey;
        return this;
    }

    public String getDescription() {
        return description;
    }


    public Menu setDescription(String description) {
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
    public Menu setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "Menu{"
                + "id=" + id
                + ",version=" + version
                + ",parentId=" + parentId
                + ",text=" + text
                + ",icon=" + icon
                + ",uri=" + uri
                + ",config=" + config
                + ",sort=" + sort
                + ",databaseUpdate=" + databaseUpdate
                + ",directView=" + directView
                + ",i18nKey=" + i18nKey
                + ",description=" + description
                + "}";
    }

}