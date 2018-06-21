package com.senpure.base.entity;


import com.senpure.base.PermissionConstant;
import com.senpure.base.annotation.Explain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = PermissionConstant.DATA_BASE_PREFIX + "_PERMISSION")
public class Permission extends LongAndVersionEntity {

    private static final long serialVersionUID = -7137920267258203370L;

    @Column(nullable = false, length = 128)
    @Explain("字符串唯一标识")
    private String name;
    @Column(nullable = false, length = 128)
    @Explain("权限名")
    private String readableName;
    @Explain("是否从数据库更新过")
    private Boolean databaseUpdate = false;

    /**
     * NORMAL 正常 ，OWNER 检查所有者，IGNORE 可以忽略(正常放行)
     */
    @Column(nullable = false, length = 12)
    @Explain("NORMAL 正常 ，OWNER 检查所有者，IGNORE 可以忽略(正常放行)")
    private String type;
    @Explain("'1,2' type为OWNER 配合verifyName使用")
    private String offset;
    @Explain("'containerResource',roleResource' type为OWNER 配合offset使用")
    private String verifyName;
    @Column( length = 1000)
    private String description;
    @Explain("排序")
    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public String getReadableName() {
        return readableName;
    }

    public void setReadableName(String readableName) {
        this.readableName = readableName;
    }

    public Boolean getDatabaseUpdate() {
        return databaseUpdate;
    }

    public void setDatabaseUpdate(Boolean databaseUpdate) {
        this.databaseUpdate = databaseUpdate;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getVerifyName() {
        return verifyName;
    }

    public void setVerifyName(String verifyName) {
        this.verifyName = verifyName;
    }


}
