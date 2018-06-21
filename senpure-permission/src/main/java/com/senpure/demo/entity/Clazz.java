package com.senpure.demo.entity;

import com.senpure.base.annotation.Explain;
import com.senpure.base.entity.LongAndVersionEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by 罗中正 on 2017/10/20.
 */
@Explain("班级信息")
@Entity
@Table(name = "DEMO_CLASS")
public class Clazz extends LongAndVersionEntity {

    @Explain("年级，如三年级")
    private Integer age;
    @Explain("几班，如二班")
    private Integer num;
    @Explain("创建时间")
    private Date createDate;
    @Explain("创建时间，时间戳")
    private Long createTime;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
