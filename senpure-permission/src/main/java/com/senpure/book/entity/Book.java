package com.senpure.book.entity;

import com.senpure.base.annotation.Explain;
import com.senpure.base.entity.LongAndVersionEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by 罗中正 on 2018/1/16 0016.
 */
@Entity
@Table(name = "demo_book")
public class Book extends LongAndVersionEntity {
    @Explain("书名")
    private String name;
    @Explain("价格")
    private Double price;
    @Explain("发行时间")
    private Long releaseTime;
    @Explain("发行时间")
    private Date releaseDate;
    @Explain("作者")
    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
