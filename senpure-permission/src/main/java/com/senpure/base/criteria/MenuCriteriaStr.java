package com.senpure.base.criteria;

import com.senpure.base.criterion.CriteriaStr;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class MenuCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 1493076390L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    private String parentId;
    //table [senpure_menu][column = parent_id] order
    private String parentIdOrder;
    private String text;
    private String icon;
    private String uri;
    private String config;
    private String sort;
    private String databaseUpdate;
    //不登录也有的菜单
    private String directView;
    private String i18nKey;
    //table [senpure_menu][column = i18n_key] order
    private String i18nKeyOrder;
    private String description;

    public MenuCriteria toMenuCriteria() {
        MenuCriteria criteria = new MenuCriteria();
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
        if (parentId != null) {
            criteria.setParentId(Integer.valueOf(parentId));
        }
        //table [senpure_menu][column = parent_id] order
        if (parentIdOrder != null) {
            criteria.setParentIdOrder(parentIdOrder);
        }
        if (text != null) {
            criteria.setText(text);
        }
        if (icon != null) {
            criteria.setIcon(icon);
        }
        if (uri != null) {
            criteria.setUri(uri);
        }
        if (config != null) {
            criteria.setConfig(config);
        }
        if (sort != null) {
            criteria.setSort(Integer.valueOf(sort));
        }
        if (databaseUpdate != null) {
            criteria.setDatabaseUpdate(Boolean.valueOf(databaseUpdate));
        }
        //不登录也有的菜单
        if (directView != null) {
            criteria.setDirectView(Boolean.valueOf(directView));
        }
        if (i18nKey != null) {
            criteria.setI18nKey(i18nKey);
        }
        //table [senpure_menu][column = i18n_key] order
        if (i18nKeyOrder != null) {
            criteria.setI18nKeyOrder(i18nKeyOrder);
        }
        if (description != null) {
            criteria.setDescription(description);
        }
        return criteria;
    }


    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("MenuCriteriaStr{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (parentId != null) {
            sb.append("parentId=").append(parentId).append(",");
        }
        if (text != null) {
            sb.append("text=").append(text).append(",");
        }
        if (icon != null) {
            sb.append("icon=").append(icon).append(",");
        }
        if (uri != null) {
            sb.append("uri=").append(uri).append(",");
        }
        if (config != null) {
            sb.append("config=").append(config).append(",");
        }
        if (sort != null) {
            sb.append("sort=").append(sort).append(",");
        }
        if (databaseUpdate != null) {
            sb.append("databaseUpdate=").append(databaseUpdate).append(",");
        }
        if (directView != null) {
            sb.append("directView=").append(directView).append(",");
        }
        if (i18nKey != null) {
            sb.append("i18nKey=").append(i18nKey).append(",");
        }
        if (description != null) {
            sb.append("description=").append(description).append(",");
        }
    }

    @Override
    protected void afterStr(StringBuilder sb) {
        if (parentIdOrder != null) {
            sb.append("parentIdOrder=").append(parentIdOrder).append(",");
        }
        if (i18nKeyOrder != null) {
            sb.append("i18nKeyOrder=").append(i18nKeyOrder).append(",");
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
    public MenuCriteriaStr setId(String id) {
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
    public MenuCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
        return this;
    }


    public String getParentId() {
        return parentId;
    }

    /**
     * get table [senpure_menu][column = parent_id] order
     *
     * @return
     */
    public String getParentIdOrder() {
        return parentIdOrder;
    }


    public MenuCriteriaStr setParentId(String parentId) {
        if (parentId != null && parentId.trim().length() == 0) {
            return this;
        }
        this.parentId = parentId;
        return this;
    }

    /**
     * set table [senpure_menu][column = parent_id] order DESC||ASC
     *
     * @return
     */
    public MenuCriteriaStr setParentIdOrder(String parentIdOrder) {
        if (parentIdOrder != null && parentIdOrder.trim().length() == 0) {
            return this;
        }
        this.parentIdOrder = parentIdOrder;
        return this;
    }


    public String getText() {
        return text;
    }


    public MenuCriteriaStr setText(String text) {
        if (text != null && text.trim().length() == 0) {
            return this;
        }
        this.text = text;
        return this;
    }


    public String getIcon() {
        return icon;
    }


    public MenuCriteriaStr setIcon(String icon) {
        if (icon != null && icon.trim().length() == 0) {
            return this;
        }
        this.icon = icon;
        return this;
    }


    public String getUri() {
        return uri;
    }


    public MenuCriteriaStr setUri(String uri) {
        if (uri != null && uri.trim().length() == 0) {
            return this;
        }
        this.uri = uri;
        return this;
    }


    public String getConfig() {
        return config;
    }


    public MenuCriteriaStr setConfig(String config) {
        if (config != null && config.trim().length() == 0) {
            return this;
        }
        this.config = config;
        return this;
    }


    public String getSort() {
        return sort;
    }


    public MenuCriteriaStr setSort(String sort) {
        if (sort != null && sort.trim().length() == 0) {
            return this;
        }
        this.sort = sort;
        return this;
    }


    public String getDatabaseUpdate() {
        return databaseUpdate;
    }


    public MenuCriteriaStr setDatabaseUpdate(String databaseUpdate) {
        if (databaseUpdate != null && databaseUpdate.trim().length() == 0) {
            return this;
        }
        this.databaseUpdate = databaseUpdate;
        return this;
    }


    /**
     * get 不登录也有的菜单
     *
     * @return
     */
    public String getDirectView() {
        return directView;
    }

    /**
     * set 不登录也有的菜单
     *
     * @return
     */
    public MenuCriteriaStr setDirectView(String directView) {
        if (directView != null && directView.trim().length() == 0) {
            return this;
        }
        this.directView = directView;
        return this;
    }


    public String getI18nKey() {
        return i18nKey;
    }

    /**
     * get table [senpure_menu][column = i18n_key] order
     *
     * @return
     */
    public String getI18nKeyOrder() {
        return i18nKeyOrder;
    }


    public MenuCriteriaStr setI18nKey(String i18nKey) {
        if (i18nKey != null && i18nKey.trim().length() == 0) {
            return this;
        }
        this.i18nKey = i18nKey;
        return this;
    }

    /**
     * set table [senpure_menu][column = i18n_key] order DESC||ASC
     *
     * @return
     */
    public MenuCriteriaStr setI18nKeyOrder(String i18nKeyOrder) {
        if (i18nKeyOrder != null && i18nKeyOrder.trim().length() == 0) {
            return this;
        }
        this.i18nKeyOrder = i18nKeyOrder;
        return this;
    }


    public String getDescription() {
        return description;
    }


    public MenuCriteriaStr setDescription(String description) {
        if (description != null && description.trim().length() == 0) {
            return this;
        }
        this.description = description;
        return this;
    }


}