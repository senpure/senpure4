package com.senpure.book.entity;

import com.senpure.base.annotation.Explain;
import com.senpure.base.entity.LongAndVersionEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by 罗中正 on 2018/1/16 0016.
 */
@Entity
@Table(name = "demo_author")
public class Author  extends LongAndVersionEntity {

    @Explain("联系方式")
    private  String phone;
    @Explain("姓名")
    private  String name;
    @Explain("入行时间")
    private Date joinWriters;
    @Explain("是否党员")
    private Boolean party;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getJoinWriters() {
        return joinWriters;
    }

    public void setJoinWriters(Date joinWriters) {
        this.joinWriters = joinWriters;
    }

    public Boolean getParty() {
        return party;
    }

    public void setParty(Boolean party) {
        this.party = party;
    }
}
