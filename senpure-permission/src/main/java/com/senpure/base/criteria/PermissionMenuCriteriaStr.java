package com.senpure.base.criteria;

import com.senpure.base.criterion.CriteriaStr;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class PermissionMenuCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 343182696L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    private String menuId;
    //table [senpure_permission_menu][column = menu_id] order
    private String menuIdOrder;
    private String permissionName;
    //table [senpure_permission_menu][column = permission_name] order
    private String permissionNameOrder;
    private String dataBaseUpdate;

    public PermissionMenuCriteria toPermissionMenuCriteria() {
        PermissionMenuCriteria criteria = new PermissionMenuCriteria();
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
        if (menuId != null) {
            criteria.setMenuId(Integer.valueOf(menuId));
        }
        //table [senpure_permission_menu][column = menu_id] order
        if (menuIdOrder != null) {
            criteria.setMenuIdOrder(menuIdOrder);
        }
        if (permissionName != null) {
            criteria.setPermissionName(permissionName);
        }
        //table [senpure_permission_menu][column = permission_name] order
        if (permissionNameOrder != null) {
            criteria.setPermissionNameOrder(permissionNameOrder);
        }
        if (dataBaseUpdate != null) {
            criteria.setDataBaseUpdate(Boolean.valueOf(dataBaseUpdate));
        }
        return criteria;
    }


    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("PermissionMenuCriteriaStr{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (menuId != null) {
            sb.append("menuId=").append(menuId).append(",");
        }
        if (permissionName != null) {
            sb.append("permissionName=").append(permissionName).append(",");
        }
        if (dataBaseUpdate != null) {
            sb.append("dataBaseUpdate=").append(dataBaseUpdate).append(",");
        }
    }

    @Override
    protected void afterStr(StringBuilder sb) {
        if (menuIdOrder != null) {
            sb.append("menuIdOrder=").append(menuIdOrder).append(",");
        }
        if (permissionNameOrder != null) {
            sb.append("permissionNameOrder=").append(permissionNameOrder).append(",");
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
    public PermissionMenuCriteriaStr setId(String id) {
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
    public PermissionMenuCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
        return this;
    }


    public String getMenuId() {
        return menuId;
    }

    /**
     * get table [senpure_permission_menu][column = menu_id] order
     *
     * @return
     */
    public String getMenuIdOrder() {
        return menuIdOrder;
    }


    public PermissionMenuCriteriaStr setMenuId(String menuId) {
        if (menuId != null && menuId.trim().length() == 0) {
            return this;
        }
        this.menuId = menuId;
        return this;
    }

    /**
     * set table [senpure_permission_menu][column = menu_id] order DESC||ASC
     *
     * @return
     */
    public PermissionMenuCriteriaStr setMenuIdOrder(String menuIdOrder) {
        if (menuIdOrder != null && menuIdOrder.trim().length() == 0) {
            return this;
        }
        this.menuIdOrder = menuIdOrder;
        return this;
    }


    public String getPermissionName() {
        return permissionName;
    }

    /**
     * get table [senpure_permission_menu][column = permission_name] order
     *
     * @return
     */
    public String getPermissionNameOrder() {
        return permissionNameOrder;
    }


    public PermissionMenuCriteriaStr setPermissionName(String permissionName) {
        if (permissionName != null && permissionName.trim().length() == 0) {
            return this;
        }
        this.permissionName = permissionName;
        return this;
    }

    /**
     * set table [senpure_permission_menu][column = permission_name] order DESC||ASC
     *
     * @return
     */
    public PermissionMenuCriteriaStr setPermissionNameOrder(String permissionNameOrder) {
        if (permissionNameOrder != null && permissionNameOrder.trim().length() == 0) {
            return this;
        }
        this.permissionNameOrder = permissionNameOrder;
        return this;
    }


    public String getDataBaseUpdate() {
        return dataBaseUpdate;
    }


    public PermissionMenuCriteriaStr setDataBaseUpdate(String dataBaseUpdate) {
        if (dataBaseUpdate != null && dataBaseUpdate.trim().length() == 0) {
            return this;
        }
        this.dataBaseUpdate = dataBaseUpdate;
        return this;
    }


}