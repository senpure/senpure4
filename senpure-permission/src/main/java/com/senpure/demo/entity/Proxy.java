package com.senpure.demo.entity;

import com.senpure.base.annotation.Explain;
import com.senpure.base.entity.LongAndVersionEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by 罗中正 on 2018/3/22 0022.
 */
@Entity
@Table(name = "demo_proxy")
@Explain("代理关系表")
public class Proxy extends LongAndVersionEntity {

    @Explain("上级id")
    private  Long parent;
    @Explain("上级id")
    private Long  child;
    @Explain("间隔级数")
    private Integer level;
    @Explain("绑定时间")
    private Long  bindTime;
    @Explain("绑定时间")
    private Date bindDate;

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long getChild() {
        return child;
    }

    public void setChild(Long child) {
        this.child = child;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getBindTime() {
        return bindTime;
    }

    public void setBindTime(Long bindTime) {
        this.bindTime = bindTime;
    }

    public Date getBindDate() {
        return bindDate;
    }

    public void setBindDate(Date bindDate) {
        this.bindDate = bindDate;
    }
}
