package com.senpure.base.criteria;

import com.senpure.base.criterion.CriteriaStr;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class PermissionCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 534669811L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    //字符串唯一标识
    private String name;
    //table [senpure_permission][column = name] order
    private String nameOrder;
    //权限名
    private String readableName;
    //是否从数据库更新过
    private String databaseUpdate;
    //NORMAL 正常 ，OWNER 检查所有者，IGNORE 可以忽略(正常放行)
    private String type;
    //table [senpure_permission][column = type] order
    private String typeOrder;
    //'1,2' type为OWNER 配合verifyName使用
    private String offset;
    //'containerResource',roleResource' type为OWNER 配合offset使用
    private String verifyName;
    //table [senpure_permission][column = verify_name] order
    private String verifyNameOrder;
    private String description;
    //排序
    private String sort;

    public PermissionCriteria toPermissionCriteria() {
        PermissionCriteria criteria = new PermissionCriteria();
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
        //字符串唯一标识
        if (name != null) {
            criteria.setName(name);
        }
        //table [senpure_permission][column = name] order
        if (nameOrder != null) {
            criteria.setNameOrder(nameOrder);
        }
        //权限名
        if (readableName != null) {
            criteria.setReadableName(readableName);
        }
        //是否从数据库更新过
        if (databaseUpdate != null) {
            criteria.setDatabaseUpdate(Boolean.valueOf(databaseUpdate));
        }
        //NORMAL 正常 ，OWNER 检查所有者，IGNORE 可以忽略(正常放行)
        if (type != null) {
            criteria.setType(type);
        }
        //table [senpure_permission][column = type] order
        if (typeOrder != null) {
            criteria.setTypeOrder(typeOrder);
        }
        //'1,2' type为OWNER 配合verifyName使用
        if (offset != null) {
            criteria.setOffset(offset);
        }
        //'containerResource',roleResource' type为OWNER 配合offset使用
        if (verifyName != null) {
            criteria.setVerifyName(verifyName);
        }
        //table [senpure_permission][column = verify_name] order
        if (verifyNameOrder != null) {
            criteria.setVerifyNameOrder(verifyNameOrder);
        }
        if (description != null) {
            criteria.setDescription(description);
        }
        //排序
        if (sort != null) {
            criteria.setSort(Integer.valueOf(sort));
        }
        return criteria;
    }


    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("PermissionCriteriaStr{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (name != null) {
            sb.append("name=").append(name).append(",");
        }
        if (readableName != null) {
            sb.append("readableName=").append(readableName).append(",");
        }
        if (databaseUpdate != null) {
            sb.append("databaseUpdate=").append(databaseUpdate).append(",");
        }
        if (type != null) {
            sb.append("type=").append(type).append(",");
        }
        if (offset != null) {
            sb.append("offset=").append(offset).append(",");
        }
        if (verifyName != null) {
            sb.append("verifyName=").append(verifyName).append(",");
        }
        if (description != null) {
            sb.append("description=").append(description).append(",");
        }
        if (sort != null) {
            sb.append("sort=").append(sort).append(",");
        }
    }

    @Override
    protected void afterStr(StringBuilder sb) {
        if (nameOrder != null) {
            sb.append("nameOrder=").append(nameOrder).append(",");
        }
        if (typeOrder != null) {
            sb.append("typeOrder=").append(typeOrder).append(",");
        }
        if (verifyNameOrder != null) {
            sb.append("verifyNameOrder=").append(verifyNameOrder).append(",");
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
    public PermissionCriteriaStr setId(String id) {
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
    public PermissionCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
        return this;
    }


    /**
     * get 字符串唯一标识
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * get table [senpure_permission][column = name] order
     *
     * @return
     */
    public String getNameOrder() {
        return nameOrder;
    }

    /**
     * set 字符串唯一标识
     *
     * @return
     */
    public PermissionCriteriaStr setName(String name) {
        if (name != null && name.trim().length() == 0) {
            return this;
        }
        this.name = name;
        return this;
    }

    /**
     * set table [senpure_permission][column = name] order DESC||ASC
     *
     * @return
     */
    public PermissionCriteriaStr setNameOrder(String nameOrder) {
        if (nameOrder != null && nameOrder.trim().length() == 0) {
            return this;
        }
        this.nameOrder = nameOrder;
        return this;
    }


    /**
     * get 权限名
     *
     * @return
     */
    public String getReadableName() {
        return readableName;
    }

    /**
     * set 权限名
     *
     * @return
     */
    public PermissionCriteriaStr setReadableName(String readableName) {
        if (readableName != null && readableName.trim().length() == 0) {
            return this;
        }
        this.readableName = readableName;
        return this;
    }


    /**
     * get 是否从数据库更新过
     *
     * @return
     */
    public String getDatabaseUpdate() {
        return databaseUpdate;
    }

    /**
     * set 是否从数据库更新过
     *
     * @return
     */
    public PermissionCriteriaStr setDatabaseUpdate(String databaseUpdate) {
        if (databaseUpdate != null && databaseUpdate.trim().length() == 0) {
            return this;
        }
        this.databaseUpdate = databaseUpdate;
        return this;
    }


    /**
     * get NORMAL 正常 ，OWNER 检查所有者，IGNORE 可以忽略(正常放行)
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * get table [senpure_permission][column = type] order
     *
     * @return
     */
    public String getTypeOrder() {
        return typeOrder;
    }

    /**
     * set NORMAL 正常 ，OWNER 检查所有者，IGNORE 可以忽略(正常放行)
     *
     * @return
     */
    public PermissionCriteriaStr setType(String type) {
        if (type != null && type.trim().length() == 0) {
            return this;
        }
        this.type = type;
        return this;
    }

    /**
     * set table [senpure_permission][column = type] order DESC||ASC
     *
     * @return
     */
    public PermissionCriteriaStr setTypeOrder(String typeOrder) {
        if (typeOrder != null && typeOrder.trim().length() == 0) {
            return this;
        }
        this.typeOrder = typeOrder;
        return this;
    }


    /**
     * get '1,2' type为OWNER 配合verifyName使用
     *
     * @return
     */
    public String getOffset() {
        return offset;
    }

    /**
     * set '1,2' type为OWNER 配合verifyName使用
     *
     * @return
     */
    public PermissionCriteriaStr setOffset(String offset) {
        if (offset != null && offset.trim().length() == 0) {
            return this;
        }
        this.offset = offset;
        return this;
    }


    /**
     * get 'containerResource',roleResource' type为OWNER 配合offset使用
     *
     * @return
     */
    public String getVerifyName() {
        return verifyName;
    }

    /**
     * get table [senpure_permission][column = verify_name] order
     *
     * @return
     */
    public String getVerifyNameOrder() {
        return verifyNameOrder;
    }

    /**
     * set 'containerResource',roleResource' type为OWNER 配合offset使用
     *
     * @return
     */
    public PermissionCriteriaStr setVerifyName(String verifyName) {
        if (verifyName != null && verifyName.trim().length() == 0) {
            return this;
        }
        this.verifyName = verifyName;
        return this;
    }

    /**
     * set table [senpure_permission][column = verify_name] order DESC||ASC
     *
     * @return
     */
    public PermissionCriteriaStr setVerifyNameOrder(String verifyNameOrder) {
        if (verifyNameOrder != null && verifyNameOrder.trim().length() == 0) {
            return this;
        }
        this.verifyNameOrder = verifyNameOrder;
        return this;
    }


    public String getDescription() {
        return description;
    }


    public PermissionCriteriaStr setDescription(String description) {
        if (description != null && description.trim().length() == 0) {
            return this;
        }
        this.description = description;
        return this;
    }


    /**
     * get 排序
     *
     * @return
     */
    public String getSort() {
        return sort;
    }

    /**
     * set 排序
     *
     * @return
     */
    public PermissionCriteriaStr setSort(String sort) {
        if (sort != null && sort.trim().length() == 0) {
            return this;
        }
        this.sort = sort;
        return this;
    }


}