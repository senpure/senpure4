package com.senpure.demo.criteria;

import com.senpure.demo.model.Student;
import com.senpure.base.criterion.Criteria;

import java.util.Date;
import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
public class StudentCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 2004864803L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private String name;
    //table [demo_student][column = name] order
    private String nameOrder;
    //自己取得外号，如齐天大圣，狗蛋
    private String nick;
    //table [demo_student][column = nick] order
    private String nickOrder;
    //创建时间
    private Date createDate;
    //创建时间，时间戳
    private Long createTime;
    //table [demo_student][column = create_time] order
    private String createTimeOrder;
    private String phoneNumber;
    private Integer age;
    //学号
    private Integer num;
    private Boolean good;
    //所属班级(外键,modelName:Clazz,tableName:demo_class)
    private Long clazzId;
    //table [demo_student][column = clazz_id] order
    private String clazzIdOrder;

    public static Student toStudent(StudentCriteria criteria, Student student) {
        student.setId(criteria.getId());
        student.setName(criteria.getName());
        student.setNick(criteria.getNick());
        student.setCreateDate(criteria.getCreateDate());
        student.setCreateTime(criteria.getCreateTime());
        student.setPhoneNumber(criteria.getPhoneNumber());
        student.setAge(criteria.getAge());
        student.setNum(criteria.getNum());
        student.setGood(criteria.getGood());
        student.setClazzId(criteria.getClazzId());
        student.setVersion(criteria.getVersion());
        return student;
    }

    public Student toStudent() {
        Student student = new Student();
        return toStudent(this, student);
    }

    /**
     * 将StudentCriteria 的有效值(不为空),赋值给 Student
     *
     * @return Student
     */
    public Student effective(Student student) {
        if (getId() != null) {
            student.setId(getId());
        }
        if (getName() != null) {
            student.setName(getName());
        }
        if (getNick() != null) {
            student.setNick(getNick());
        }
        if (getCreateDate() != null) {
            student.setCreateDate(getCreateDate());
        }
        if (getCreateTime() != null) {
            student.setCreateTime(getCreateTime());
        }
        if (getPhoneNumber() != null) {
            student.setPhoneNumber(getPhoneNumber());
        }
        if (getAge() != null) {
            student.setAge(getAge());
        }
        if (getNum() != null) {
            student.setNum(getNum());
        }
        if (getGood() != null) {
            student.setGood(getGood());
        }
        if (getClazzId() != null) {
            student.setClazzId(getClazzId());
        }
        if (getVersion() != null) {
            student.setVersion(getVersion());
        }
        return student;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("StudentCriteria{");
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
        if (createTime != null) {
            sb.append("createTime=").append(createTime).append(",");
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
    public StudentCriteria setId(Long id) {
        this.id = id;
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


    public StudentCriteria setName(String name) {
        if (name != null && name.trim().length() == 0) {
            this.name = null;
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
    public StudentCriteria setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
        putSort("name", nameOrder);
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
    public StudentCriteria setNick(String nick) {
        if (nick != null && nick.trim().length() == 0) {
            this.nick = null;
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
    public StudentCriteria setNickOrder(String nickOrder) {
        this.nickOrder = nickOrder;
        putSort("nick", nickOrder);
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
    public StudentCriteria setCreateDate(Date createDate) {
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
     * get table [demo_student][column = create_time] order
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
    public StudentCriteria setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * set table [demo_student][column = create_time] order DESC||ASC
     *
     * @return
     */
    public StudentCriteria setCreateTimeOrder(String createTimeOrder) {
        this.createTimeOrder = createTimeOrder;
        putSort("create_time", createTimeOrder);
        return this;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public StudentCriteria setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.trim().length() == 0) {
            this.phoneNumber = null;
            return this;
        }
        this.phoneNumber = phoneNumber;
        return this;
    }


    public Integer getAge() {
        return age;
    }


    public StudentCriteria setAge(Integer age) {
        this.age = age;
        return this;
    }


    /**
     * get 学号
     *
     * @return
     */
    public Integer getNum() {
        return num;
    }

    /**
     * set 学号
     *
     * @return
     */
    public StudentCriteria setNum(Integer num) {
        this.num = num;
        return this;
    }


    public Boolean getGood() {
        return good;
    }


    public StudentCriteria setGood(Boolean good) {
        this.good = good;
        return this;
    }


    /**
     * get 所属班级(外键,modelName:Clazz,tableName:demo_class)
     *
     * @return
     */
    public Long getClazzId() {
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
    public StudentCriteria setClazzId(Long clazzId) {
        this.clazzId = clazzId;
        return this;
    }

    /**
     * set table [demo_student][column = clazz_id] order DESC||ASC
     *
     * @return
     */
    public StudentCriteria setClazzIdOrder(String clazzIdOrder) {
        this.clazzIdOrder = clazzIdOrder;
        putSort("clazz_id", clazzIdOrder);
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
    public StudentCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}