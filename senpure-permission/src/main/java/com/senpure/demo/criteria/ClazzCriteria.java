package com.senpure.demo.criteria;

import com.senpure.demo.model.Clazz;
import com.senpure.base.criterion.Criteria;

import java.util.Date;
import java.io.Serializable;

/**
 * 班级信息
 *
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
public class ClazzCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 1416951444L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    //年级，如三年级
    private Integer age;
    //几班，如二班
    private Integer num;
    //创建时间
    private Date createDate;
    //创建时间，时间戳
    private Long createTime;
    //table [demo_class][column = create_time] order
    private String createTimeOrder;

    public static Clazz toClazz(ClazzCriteria criteria, Clazz clazz) {
        clazz.setId(criteria.getId());
        clazz.setAge(criteria.getAge());
        clazz.setNum(criteria.getNum());
        clazz.setCreateDate(criteria.getCreateDate());
        clazz.setCreateTime(criteria.getCreateTime());
        clazz.setVersion(criteria.getVersion());
        return clazz;
    }

    public Clazz toClazz() {
        Clazz clazz = new Clazz();
        return toClazz(this, clazz);
    }

    /**
     * 将ClazzCriteria 的有效值(不为空),赋值给 Clazz
     *
     * @return Clazz
     */
    public Clazz effective(Clazz clazz) {
        if (getId() != null) {
            clazz.setId(getId());
        }
        if (getAge() != null) {
            clazz.setAge(getAge());
        }
        if (getNum() != null) {
            clazz.setNum(getNum());
        }
        if (getCreateDate() != null) {
            clazz.setCreateDate(getCreateDate());
        }
        if (getCreateTime() != null) {
            clazz.setCreateTime(getCreateTime());
        }
        if (getVersion() != null) {
            clazz.setVersion(getVersion());
        }
        return clazz;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("ClazzCriteria{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (age != null) {
            sb.append("age=").append(age).append(",");
        }
        if (num != null) {
            sb.append("num=").append(num).append(",");
        }
        if (createDate != null) {
            sb.append("createDate=").append(createDate).append(",");
        }
        if (createTime != null) {
            sb.append("createTime=").append(createTime).append(",");
        }
    }

    /**
     * get 主键
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * set 主键
     *
     * @return
     */
    public ClazzCriteria setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * get 年级，如三年级
     *
     * @return
     */
    public Integer getAge() {
        return age;
    }

    /**
     * set 年级，如三年级
     *
     * @return
     */
    public ClazzCriteria setAge(Integer age) {
        this.age = age;
        return this;
    }


    /**
     * get 几班，如二班
     *
     * @return
     */
    public Integer getNum() {
        return num;
    }

    /**
     * set 几班，如二班
     *
     * @return
     */
    public ClazzCriteria setNum(Integer num) {
        this.num = num;
        return this;
    }


    /**
     * get 创建时间
     *
     * @return
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * set 创建时间
     *
     * @return
     */
    public ClazzCriteria setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }


    /**
     * get 创建时间，时间戳
     *
     * @return
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * get table [demo_class][column = create_time] order
     *
     * @return
     */
    public String getCreateTimeOrder() {
        return createTimeOrder;
    }

    /**
     * set 创建时间，时间戳
     *
     * @return
     */
    public ClazzCriteria setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * set table [demo_class][column = create_time] order DESC||ASC
     *
     * @return
     */
    public ClazzCriteria setCreateTimeOrder(String createTimeOrder) {
        this.createTimeOrder = createTimeOrder;
        putSort("create_time", createTimeOrder);
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
    public ClazzCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}