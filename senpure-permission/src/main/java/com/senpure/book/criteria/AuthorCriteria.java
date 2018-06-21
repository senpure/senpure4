package com.senpure.book.criteria;

import com.senpure.book.model.Author;
import com.senpure.base.criterion.Criteria;

import java.io.Serializable;
import java.util.Date;

/**
 * @author senpure-generator
 * @version 2018-1-25 15:15:45
 */
public class AuthorCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 1247575657L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    //联系方式
    private String phone;
    //姓名
    private String name;
    //table [demo_author][column = name] order
    private String nameOrder;
    //入行时间
    private Date joinWriters;
    //table [demo_author][column = join_writers] order
    private String joinWritersOrder;
    //是否党员
    private Boolean party;

    public static Author toAuthor(AuthorCriteria criteria, Author author) {
        author.setId(criteria.getId());
        author.setPhone(criteria.getPhone());
        author.setName(criteria.getName());
        author.setJoinWriters(criteria.getJoinWriters());
        author.setParty(criteria.getParty());
        author.setVersion(criteria.getVersion());
        return author;
    }

    public Author toAuthor() {
        Author author = new Author();
        return toAuthor(this, author);
    }

    /**
     * 将AuthorCriteria 的有效值(不为空),赋值给 Author
     *
     * @return Author
     */
    public Author effective(Author author) {
        if (getId() != null) {
            author.setId(getId());
        }
        if (getPhone() != null) {
            author.setPhone(getPhone());
        }
        if (getName() != null) {
            author.setName(getName());
        }
        if (getJoinWriters() != null) {
            author.setJoinWriters(getJoinWriters());
        }
        if (getParty() != null) {
            author.setParty(getParty());
        }
        if (getVersion() != null) {
            author.setVersion(getVersion());
        }
        return author;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("AuthorCriteria{");
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
    public AuthorCriteria setId(Long id) {
        this.id = id;
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
    public AuthorCriteria setPhone(String phone) {
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
    public AuthorCriteria setName(String name) {
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
    public AuthorCriteria setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
        putSort("name", nameOrder);
        return this;
    }


    /**
     * get 入行时间
     *
     * @return
     */
    public Date getJoinWriters() {
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
    public AuthorCriteria setJoinWriters(Date joinWriters) {
        this.joinWriters = joinWriters;
        return this;
    }

    /**
     * set table [demo_author][column = join_writers] order DESC||ASC
     *
     * @return
     */
    public AuthorCriteria setJoinWritersOrder(String joinWritersOrder) {
        this.joinWritersOrder = joinWritersOrder;
        putSort("join_writers", joinWritersOrder);
        return this;
    }


    /**
     * get 是否党员
     *
     * @return
     */
    public Boolean getParty() {
        return party;
    }

    /**
     * set 是否党员
     *
     * @return
     */
    public AuthorCriteria setParty(Boolean party) {
        this.party = party;
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
    public AuthorCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}