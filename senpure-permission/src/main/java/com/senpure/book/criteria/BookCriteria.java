package com.senpure.book.criteria;

import com.senpure.book.model.Book;
import com.senpure.base.criterion.Criteria;

import java.io.Serializable;
import java.util.Date;

/**
 * @author senpure-generator
 * @version 2018-1-25 15:15:45
 */
public class BookCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 1770406487L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    //书名
    private String name;
    //table [demo_book][column = name] order
    private String nameOrder;
    //价格
    private Double price;
    //发行时间
    private Long releaseTime;
    //table [demo_book][column = release_time] order
    private String releaseTimeOrder;
    //发行时间
    private Date releaseDate;
    //作者(外键,modelName:Author,tableName:demo_author)
    private Long authorId;
    //table [demo_book][column = author_id] order
    private String authorIdOrder;

    public static Book toBook(BookCriteria criteria, Book book) {
        book.setId(criteria.getId());
        book.setName(criteria.getName());
        book.setPrice(criteria.getPrice());
        book.setReleaseTime(criteria.getReleaseTime());
        book.setReleaseDate(criteria.getReleaseDate());
        book.setAuthorId(criteria.getAuthorId());
        book.setVersion(criteria.getVersion());
        return book;
    }

    public Book toBook() {
        Book book = new Book();
        return toBook(this, book);
    }

    /**
     * 将BookCriteria 的有效值(不为空),赋值给 Book
     *
     * @return Book
     */
    public Book effective(Book book) {
        if (getId() != null) {
            book.setId(getId());
        }
        if (getName() != null) {
            book.setName(getName());
        }
        if (getPrice() != null) {
            book.setPrice(getPrice());
        }
        if (getReleaseTime() != null) {
            book.setReleaseTime(getReleaseTime());
        }
        if (getReleaseDate() != null) {
            book.setReleaseDate(getReleaseDate());
        }
        if (getAuthorId() != null) {
            book.setAuthorId(getAuthorId());
        }
        if (getVersion() != null) {
            book.setVersion(getVersion());
        }
        return book;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("BookCriteria{");
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
        if (releaseTime != null) {
            sb.append("releaseTime=").append(releaseTime).append(",");
        }
        if (releaseDate != null) {
            sb.append("releaseDate=").append(releaseDate).append(",");
        }
        if (authorId != null) {
            sb.append("authorId=").append(authorId).append(",");
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
    public BookCriteria setId(Long id) {
        this.id = id;
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
    public BookCriteria setName(String name) {
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
    public BookCriteria setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
        putSort("name", nameOrder);
        return this;
    }


    /**
     * get 价格
     *
     * @return
     */
    public Double getPrice() {
        return price;
    }

    /**
     * set 价格
     *
     * @return
     */
    public BookCriteria setPrice(Double price) {
        this.price = price;
        return this;
    }


    /**
     * get 发行时间
     *
     * @return
     */
    public Long getReleaseTime() {
        return releaseTime;
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
     * set 发行时间
     *
     * @return
     */
    public BookCriteria setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
        return this;
    }

    /**
     * set table [demo_book][column = release_time] order DESC||ASC
     *
     * @return
     */
    public BookCriteria setReleaseTimeOrder(String releaseTimeOrder) {
        this.releaseTimeOrder = releaseTimeOrder;
        putSort("release_time", releaseTimeOrder);
        return this;
    }


    /**
     * get 发行时间
     *
     * @return
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * set 发行时间
     *
     * @return
     */
    public BookCriteria setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }


    /**
     * get 作者(外键,modelName:Author,tableName:demo_author)
     *
     * @return
     */
    public Long getAuthorId() {
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
    public BookCriteria setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    /**
     * set table [demo_book][column = author_id] order DESC||ASC
     *
     * @return
     */
    public BookCriteria setAuthorIdOrder(String authorIdOrder) {
        this.authorIdOrder = authorIdOrder;
        putSort("author_id", authorIdOrder);
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
    public BookCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}