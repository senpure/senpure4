package com.senpure.base.entity;


import com.senpure.base.PermissionConstant;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = PermissionConstant.DATA_BASE_PREFIX + "_ROLE")
public class Role extends LongAndVersionEntity{
    private static final long serialVersionUID = -7767418310648447445L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "containerId")
    private Container container;
    @Column(nullable = false, length = 32)
    private String name;
    private Date createDate;
    private Long createTime;

    private String description;

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

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }



    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}
