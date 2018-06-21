package com.senpure.demo.entity;

import com.senpure.base.annotation.Explain;
import com.senpure.base.entity.LongAndVersionEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by 罗中正 on 2017/10/20.
 */

@Entity
@Table(name = "DEMO_STUDENT")
public class Student extends LongAndVersionEntity {
    private static final long serialVersionUID = -729955412857513533L;

    private String name;
    @Explain("自己取得外号，如齐天大圣，狗蛋")
    private String nick;
    @Explain("所属班级")
    @ManyToOne
    @JoinColumn(name = "clazzId")
    private  Clazz clazz;
    @Explain("创建时间")
    private Date createDate;
    @Explain("创建时间，时间戳")
    private Long createTime;
    private String phoneNumber;
    private Integer age;
    @Explain("学号")
    private Integer num;
    private Boolean good;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Boolean getGood() {
        return good;
    }

    public void setGood(Boolean good) {
        this.good = good;
    }
}
