package com.senpure.book.criteria;

import com.senpure.base.criterion.CriteriaStr;
import com.senpure.base.struct.PatternDate;
import com.senpure.base.validator.DynamicDate;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 15:15:45
 */
public class BookCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 1770406487L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    //书名
    private String name;
    //table [demo_book][column = name] order
    private String nameOrder;
    //价格
    private String price;
    //table [demo_book][column = release_time] order
    private String releaseTimeOrder;
    //发行时间
    private String releaseDate;
    @DynamicDate
    private PatternDate releaseDateValid = new PatternDate();
    //作者(外键,modelName:Author,tableName:demo_author)
    private String authorId;
    //table [demo_book][column = author_id] order
    private String authorIdOrder;

    public BookCriteria toBookCriteria() {
        BookCriteria criteria = new BookCriteria();
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
        //书名
        if (name != null) {
            criteria.setName(name);
        }
        //table [demo_book][column = name] order
        if (nameOrder != null) {
            criteria.setNameOrder(nameOrder);
        }
        //价格
        if (price != null) {
            criteria.setPrice(Double.valueOf(price));
        }
        //table [demo_book][column = release_time] order
        if (releaseTimeOrder != null) {
            criteria.setReleaseTimeOrder(releaseTimeOrder);
        }
        //发行时间
        if (releaseDate != null) {
            criteria.setReleaseDate(releaseDateValid.getDate());
            if (releaseDateValid.getDate() != null) {
                criteria.setReleaseTime(releaseDateValid.getDate().getTime());
            }
        }
        //作者(外键,modelName:Author,tableName:demo_author)
        if (authorId != null) {
            criteria.setAuthorId(Long.valueOf(authorId));
        }
        //table [demo_book][column = author_id] order
        if (authorIdOrder != null) {
            criteria.setAuthorIdOrder(authorIdOrder);
        }
        return criteria;
    }

    @Override
    public void setDatePattern(String datePattern) {
        super.setDatePattern(datePattern);
        releaseDateValid.setPattern(datePattern);
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("BookCriteriaStr{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (name != null) {
            sb.append("name=").append(name).append(",");
        }
        if (price != null) {
            sb.append("price=").append(price).append(",");
        }
        if (releaseDate != null) {
            sb.append("releaseDate=").append(releaseDate).append(",");
        }
        if (authorId != null) {
            sb.append("authorId=").append(authorId).append(",");
        }
    }

    @Override
    protected void afterStr(StringBuilder sb) {
        if (nameOrder != null) {
            sb.append("nameOrder=").append(nameOrder).append(",");
        }
        if (releaseTimeOrder != null) {
            sb.append("releaseTimeOrder=").append(releaseTimeOrder).append(",");
        }
        if (authorIdOrder != null) {
            sb.append("authorIdOrder=").append(authorIdOrder).append(",");
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
    public BookCriteriaStr setId(String id) {
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
    public BookCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
        return this;
    }


    /**
     * get 书名
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * get table [demo_book][column = name] order
     *
     * @return
     */
    public String getNameOrder() {
        return nameOrder;
    }

    /**
     * set 书名
     *
     * @return
     */
    public BookCriteriaStr setName(String name) {
        if (name != null && name.trim().length() == 0) {
            return this;
        }
        this.name = name;
        return this;
    }

    /**
     * set table [demo_book][column = name] order DESC||ASC
     *
     * @return
     */
    public BookCriteriaStr setNameOrder(String nameOrder) {
        if (nameOrder != null && nameOrder.trim().length() == 0) {
            return this;
        }
        this.nameOrder = nameOrder;
        return this;
    }


    /**
     * get 价格
     *
     * @return
     */
    public String getPrice() {
        return price;
    }

    /**
     * set 价格
     *
     * @return
     */
    public BookCriteriaStr setPrice(String price) {
        if (price != null && price.trim().length() == 0) {
            return this;
        }
        this.price = price;
        return this;
    }


    /**
     * get table [demo_book][column = release_time] order
     *
     * @return
     */
    public String getReleaseTimeOrder() {
        return releaseTimeOrder;
    }

    /**
     * set table [demo_book][column = release_time] order DESC||ASC
     *
     * @return
     */
    public BookCriteriaStr setReleaseTimeOrder(String releaseTimeOrder) {
        if (releaseTimeOrder != null && releaseTimeOrder.trim().length() == 0) {
            return this;
        }
        this.releaseTimeOrder = releaseTimeOrder;
        return this;
    }


    /**
     * get 发行时间
     *
     * @return
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * set 发行时间
     *
     * @return
     */
    public BookCriteriaStr setReleaseDate(String releaseDate) {
        if (releaseDate != null && releaseDate.trim().length() == 0) {
            return this;
        }
        this.releaseDate = releaseDate;
        return this;
    }


    /**
     * get 作者(外键,modelName:Author,tableName:demo_author)
     *
     * @return
     */
    public String getAuthorId() {
        return authorId;
    }

    /**
     * get table [demo_book][column = author_id] order
     *
     * @return
     */
    public String getAuthorIdOrder() {
        return authorIdOrder;
    }

    /**
     * set 作者(外键,modelName:Author,tableName:demo_author)
     *
     * @return
     */
    public BookCriteriaStr setAuthorId(String authorId) {
        if (authorId != null && authorId.trim().length() == 0) {
            return this;
        }
        this.authorId = authorId;
        return this;
    }

    /**
     * set table [demo_book][column = author_id] order DESC||ASC
     *
     * @return
     */
    public BookCriteriaStr setAuthorIdOrder(String authorIdOrder) {
        if (authorIdOrder != null && authorIdOrder.trim().length() == 0) {
            return this;
        }
        this.authorIdOrder = authorIdOrder;
        return this;
    }


}