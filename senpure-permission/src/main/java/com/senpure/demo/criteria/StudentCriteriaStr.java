package com.senpure.demo.criteria;

import com.senpure.base.criterion.CriteriaStr;
import com.senpure.base.struct.PatternDate;
import com.senpure.base.validator.DynamicDate;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
public class StudentCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 2004864803L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    private String name;
    //table [demo_student][column = name] order
    private String nameOrder;
    //自己取得外号，如齐天大圣，狗蛋
    private String nick;
    //table [demo_student][column = nick] order
    private String nickOrder;
    //创建时间
    private String createDate;
    @DynamicDate
    private PatternDate createDateValid = new PatternDate();
    //table [demo_student][column = create_time] order
    private String createTimeOrder;
    private String phoneNumber;
    private String age;
    //学号
    private String num;
    private String good;
    //所属班级(外键,modelName:Clazz,tableName:demo_class)
    private String clazzId;
    //table [demo_student][column = clazz_id] order
    private String clazzIdOrder;

    public StudentCriteria toStudentCriteria() {
        StudentCriteria criteria = new StudentCriteria();
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
        if (name != null) {
            criteria.setName(name);
        }
        //table [demo_student][column = name] order
        if (nameOrder != null) {
            criteria.setNameOrder(nameOrder);
        }
        //自己取得外号，如齐天大圣，狗蛋
        if (nick != null) {
            criteria.setNick(nick);
        }
        //table [demo_student][column = nick] order
        if (nickOrder != null) {
            criteria.setNickOrder(nickOrder);
        }
        //创建时间
        if (createDate != null) {
            criteria.setCreateDate(createDateValid.getDate());
            if (createDateValid.getDate() != null) {
                criteria.setCreateTime(createDateValid.getDate().getTime());
            }
        }
        //table [demo_student][column = create_time] order
        if (createTimeOrder != null) {
            criteria.setCreateTimeOrder(createTimeOrder);
        }
        if (phoneNumber != null) {
            criteria.setPhoneNumber(phoneNumber);
        }
        if (age != null) {
            criteria.setAge(Integer.valueOf(age));
        }
        //学号
        if (num != null) {
            criteria.setNum(Integer.valueOf(num));
        }
        if (good != null) {
            criteria.setGood(Boolean.valueOf(good));
        }
        //所属班级(外键,modelName:Clazz,tableName:demo_class)
        if (clazzId != null) {
            criteria.setClazzId(Long.valueOf(clazzId));
        }
        //table [demo_student][column = clazz_id] order
        if (clazzIdOrder != null) {
            criteria.setClazzIdOrder(clazzIdOrder);
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
        sb.append("StudentCriteriaStr{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (name != null) {
            sb.append("name=").append(name).append(",");
        }
        if (nick != null) {
            sb.append("nick=").append(nick).append(",");
        }
        if (createDate != null) {
            sb.append("createDate=").append(createDate).append(",");
        }
        if (phoneNumber != null) {
            sb.append("phoneNumber=").append(phoneNumber).append(",");
        }
        if (age != null) {
            sb.append("age=").append(age).append(",");
        }
        if (num != null) {
            sb.append("num=").append(num).append(",");
        }
        if (good != null) {
            sb.append("good=").append(good).append(",");
        }
        if (clazzId != null) {
            sb.append("clazzId=").append(clazzId).append(",");
        }
    }

    @Override
    protected void afterStr(StringBuilder sb) {
        if (nameOrder != null) {
            sb.append("nameOrder=").append(nameOrder).append(",");
        }
        if (nickOrder != null) {
            sb.append("nickOrder=").append(nickOrder).append(",");
        }
        if (createTimeOrder != null) {
            sb.append("createTimeOrder=").append(createTimeOrder).append(",");
        }
        if (clazzIdOrder != null) {
            sb.append("clazzIdOrder=").append(clazzIdOrder).append(",");
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
    public StudentCriteriaStr setId(String id) {
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
    public StudentCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
        return this;
    }


    public String getName() {
        return name;
    }

    /**
     * get table [demo_student][column = name] order
     *
     * @return
     */
    public String getNameOrder() {
        return nameOrder;
    }


    public StudentCriteriaStr setName(String name) {
        if (name != null && name.trim().length() == 0) {
            return this;
        }
        this.name = name;
        return this;
    }

    /**
     * set table [demo_student][column = name] order DESC||ASC
     *
     * @return
     */
    public StudentCriteriaStr setNameOrder(String nameOrder) {
        if (nameOrder != null && nameOrder.trim().length() == 0) {
            this.nameOrder = null;
            return this;
        }
        this.nameOrder = nameOrder;
        return this;
    }


    /**
     * get 自己取得外号，如齐天大圣，狗蛋
     *
     * @return
     */
    public String getNick() {
        return nick;
    }

    /**
     * get table [demo_student][column = nick] order
     *
     * @return
     */
    public String getNickOrder() {
        return nickOrder;
    }

    /**
     * set 自己取得外号，如齐天大圣，狗蛋
     *
     * @return
     */
    public StudentCriteriaStr setNick(String nick) {
        if (nick != null && nick.trim().length() == 0) {
            return this;
        }
        this.nick = nick;
        return this;
    }

    /**
     * set table [demo_student][column = nick] order DESC||ASC
     *
     * @return
     */
    public StudentCriteriaStr setNickOrder(String nickOrder) {
        if (nickOrder != null && nickOrder.trim().length() == 0) {
            this.nickOrder = null;
            return this;
        }
        this.nickOrder = nickOrder;
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
    public StudentCriteriaStr setCreateDate(String createDate) {
        if (createDate != null && createDate.trim().length() == 0) {
            return this;
        }
        this.createDate = createDate;
        return this;
    }


    /**
     * get table [demo_student][column = create_time] order
     *
     * @return
     */
    public String getCreateTimeOrder() {
        return createTimeOrder;
    }

    /**
     * set table [demo_student][column = create_time] order DESC||ASC
     *
     * @return
     */
    public StudentCriteriaStr setCreateTimeOrder(String createTimeOrder) {
        if (createTimeOrder != null && createTimeOrder.trim().length() == 0) {
            this.createTimeOrder = null;
            return this;
        }
        this.createTimeOrder = createTimeOrder;
        return this;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public StudentCriteriaStr setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.trim().length() == 0) {
            return this;
        }
        this.phoneNumber = phoneNumber;
        return this;
    }


    public String getAge() {
        return age;
    }


    public StudentCriteriaStr setAge(String age) {
        if (age != null && age.trim().length() == 0) {
            return this;
        }
        this.age = age;
        return this;
    }


    /**
     * get 学号
     *
     * @return
     */
    public String getNum() {
        return num;
    }

    /**
     * set 学号
     *
     * @return
     */
    public StudentCriteriaStr setNum(String num) {
        if (num != null && num.trim().length() == 0) {
            return this;
        }
        this.num = num;
        return this;
    }


    public String getGood() {
        return good;
    }


    public StudentCriteriaStr setGood(String good) {
        if (good != null && good.trim().length() == 0) {
            return this;
        }
        this.good = good;
        return this;
    }


    /**
     * get 所属班级(外键,modelName:Clazz,tableName:demo_class)
     *
     * @return
     */
    public String getClazzId() {
        return clazzId;
    }

    /**
     * get table [demo_student][column = clazz_id] order
     *
     * @return
     */
    public String getClazzIdOrder() {
        return clazzIdOrder;
    }

    /**
     * set 所属班级(外键,modelName:Clazz,tableName:demo_class)
     *
     * @return
     */
    public StudentCriteriaStr setClazzId(String clazzId) {
        if (clazzId != null && clazzId.trim().length() == 0) {
            return this;
        }
        this.clazzId = clazzId;
        return this;
    }

    /**
     * set table [demo_student][column = clazz_id] order DESC||ASC
     *
     * @return
     */
    public StudentCriteriaStr setClazzIdOrder(String clazzIdOrder) {
        if (clazzIdOrder != null && clazzIdOrder.trim().length() == 0) {
            this.clazzIdOrder = null;
            return this;
        }
        this.clazzIdOrder = clazzIdOrder;
        return this;
    }


}