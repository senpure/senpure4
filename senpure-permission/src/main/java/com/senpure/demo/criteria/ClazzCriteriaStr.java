package com.senpure.demo.criteria;

import com.senpure.base.criterion.CriteriaStr;
import com.senpure.base.struct.PatternDate;
import com.senpure.base.validator.DynamicDate;

import java.io.Serializable;

/**
 * 班级信息
 *
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
public class ClazzCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 1416951444L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    //年级，如三年级
    private String age;
    //几班，如二班
    private String num;
    //创建时间
    private String createDate;
    @DynamicDate
    private PatternDate createDateValid = new PatternDate();
    //table [demo_class][column = create_time] order
    private String createTimeOrder;

    public ClazzCriteria toClazzCriteria() {
        ClazzCriteria criteria = new ClazzCriteria();
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
        //年级，如三年级
        if (age != null) {
            criteria.setAge(Integer.valueOf(age));
        }
        //几班，如二班
        if (num != null) {
            criteria.setNum(Integer.valueOf(num));
        }
        //创建时间
        if (createDate != null) {
            criteria.setCreateDate(createDateValid.getDate());
            if (createDateValid.getDate() != null) {
                criteria.setCreateTime(createDateValid.getDate().getTime());
            }
        }
        //table [demo_class][column = create_time] order
        if (createTimeOrder != null) {
            criteria.setCreateTimeOrder(createTimeOrder);
        }
        return criteria;
    }

    @Override
    public void setDatePattern(String datePattern) {
        super.setDatePattern(datePattern);
        createDateValid.setPattern(datePattern);
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("ClazzCriteriaStr{");
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
    }

    @Override
    protected void afterStr(StringBuilder sb) {
        if (createTimeOrder != null) {
            sb.append("createTimeOrder=").append(createTimeOrder).append(",");
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
    public ClazzCriteriaStr setId(String id) {
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
    public ClazzCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
        return this;
    }


    /**
     * get 年级，如三年级
     *
     * @return
     */
    public String getAge() {
        return age;
    }

    /**
     * set 年级，如三年级
     *
     * @return
     */
    public ClazzCriteriaStr setAge(String age) {
        if (age != null && age.trim().length() == 0) {
            return this;
        }
        this.age = age;
        return this;
    }


    /**
     * get 几班，如二班
     *
     * @return
     */
    public String getNum() {
        return num;
    }

    /**
     * set 几班，如二班
     *
     * @return
     */
    public ClazzCriteriaStr setNum(String num) {
        if (num != null && num.trim().length() == 0) {
            return this;
        }
        this.num = num;
        return this;
    }


    /**
     * get 创建时间
     *
     * @return
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * set 创建时间
     *
     * @return
     */
    public ClazzCriteriaStr setCreateDate(String createDate) {
        if (createDate != null && createDate.trim().length() == 0) {
            return this;
        }
        this.createDate = createDate;
        return this;
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
     * set table [demo_class][column = create_time] order DESC||ASC
     *
     * @return
     */
    public ClazzCriteriaStr setCreateTimeOrder(String createTimeOrder) {
        if (createTimeOrder != null && createTimeOrder.trim().length() == 0) {
            this.createTimeOrder = null;
            return this;
        }
        this.createTimeOrder = createTimeOrder;
        return this;
    }


}