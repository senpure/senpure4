package com.senpure.book.criteria;

import com.senpure.base.criterion.CriteriaStr;
import com.senpure.base.struct.PatternDate;
import com.senpure.base.validator.DynamicDate;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 15:15:45
 */
public class AuthorCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 1247575657L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    //联系方式
    private String phone;
    //姓名
    private String name;
    //table [demo_author][column = name] order
    private String nameOrder;
    //入行时间
    private String joinWriters;
    @DynamicDate
    private PatternDate joinWritersValid = new PatternDate();
    //table [demo_author][column = join_writers] order
    private String joinWritersOrder;
    //是否党员
    private String party;

    public AuthorCriteria toAuthorCriteria() {
        AuthorCriteria criteria = new AuthorCriteria();
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
        //联系方式
        if (phone != null) {
            criteria.setPhone(phone);
        }
        //姓名
        if (name != null) {
            criteria.setName(name);
        }
        //table [demo_author][column = name] order
        if (nameOrder != null) {
            criteria.setNameOrder(nameOrder);
        }
        //入行时间
        if (joinWriters != null) {
            criteria.setJoinWriters(joinWritersValid.getDate());
        }
        //table [demo_author][column = join_writers] order
        if (joinWritersOrder != null) {
            criteria.setJoinWritersOrder(joinWritersOrder);
        }
        //是否党员
        if (party != null) {
            criteria.setParty(Boolean.valueOf(party));
        }
        return criteria;
    }

    @Override
    public void setDatePattern(String datePattern) {
        super.setDatePattern(datePattern);
        joinWritersValid.setPattern(datePattern);
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("AuthorCriteriaStr{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (phone != null) {
            sb.append("phone=").append(phone).append(",");
        }
        if (name != null) {
            sb.append("name=").append(name).append(",");
        }
        if (joinWriters != null) {
            sb.append("joinWriters=").append(joinWriters).append(",");
        }
        if (party != null) {
            sb.append("party=").append(party).append(",");
        }
    }

    @Override
    protected void afterStr(StringBuilder sb) {
        if (nameOrder != null) {
            sb.append("nameOrder=").append(nameOrder).append(",");
        }
        if (joinWritersOrder != null) {
            sb.append("joinWritersOrder=").append(joinWritersOrder).append(",");
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
    public AuthorCriteriaStr setId(String id) {
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
    public AuthorCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
        return this;
    }


    /**
     * get 联系方式
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * set 联系方式
     *
     * @return
     */
    public AuthorCriteriaStr setPhone(String phone) {
        if (phone != null && phone.trim().length() == 0) {
            return this;
        }
        this.phone = phone;
        return this;
    }


    /**
     * get 姓名
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * get table [demo_author][column = name] order
     *
     * @return
     */
    public String getNameOrder() {
        return nameOrder;
    }

    /**
     * set 姓名
     *
     * @return
     */
    public AuthorCriteriaStr setName(String name) {
        if (name != null && name.trim().length() == 0) {
            return this;
        }
        this.name = name;
        return this;
    }

    /**
     * set table [demo_author][column = name] order DESC||ASC
     *
     * @return
     */
    public AuthorCriteriaStr setNameOrder(String nameOrder) {
        if (nameOrder != null && nameOrder.trim().length() == 0) {
            return this;
        }
        this.nameOrder = nameOrder;
        return this;
    }


    /**
     * get 入行时间
     *
     * @return
     */
    public String getJoinWriters() {
        return joinWriters;
    }

    /**
     * get table [demo_author][column = join_writers] order
     *
     * @return
     */
    public String getJoinWritersOrder() {
        return joinWritersOrder;
    }

    /**
     * set 入行时间
     *
     * @return
     */
    public AuthorCriteriaStr setJoinWriters(String joinWriters) {
        if (joinWriters != null && joinWriters.trim().length() == 0) {
            return this;
        }
        this.joinWriters = joinWriters;
        return this;
    }

    /**
     * set table [demo_author][column = join_writers] order DESC||ASC
     *
     * @return
     */
    public AuthorCriteriaStr setJoinWritersOrder(String joinWritersOrder) {
        if (joinWritersOrder != null && joinWritersOrder.trim().length() == 0) {
            return this;
        }
        this.joinWritersOrder = joinWritersOrder;
        return this;
    }


    /**
     * get 是否党员
     *
     * @return
     */
    public String getParty() {
        return party;
    }

    /**
     * set 是否党员
     *
     * @return
     */
    public AuthorCriteriaStr setParty(String party) {
        if (party != null && party.trim().length() == 0) {
            return this;
        }
        this.party = party;
        return this;
    }


}