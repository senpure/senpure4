package com.senpure.demo.criteria;

import com.senpure.demo.model.Notice;
import com.senpure.base.criterion.Criteria;

import java.util.Date;
import java.io.Serializable;

/**
 * 公告模型
 *
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
public class NoticeCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 339330519L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    //公共内容
    private String msg;
    //发出时间
    private Date sendDate;
    //table [demo_notice][column = send_date] order
    private String sendDateOrder;

    public static Notice toNotice(NoticeCriteria criteria, Notice notice) {
        notice.setId(criteria.getId());
        notice.setMsg(criteria.getMsg());
        notice.setSendDate(criteria.getSendDate());
        notice.setVersion(criteria.getVersion());
        return notice;
    }

    public Notice toNotice() {
        Notice notice = new Notice();
        return toNotice(this, notice);
    }

    /**
     * 将NoticeCriteria 的有效值(不为空),赋值给 Notice
     *
     * @return Notice
     */
    public Notice effective(Notice notice) {
        if (getId() != null) {
            notice.setId(getId());
        }
        if (getMsg() != null) {
            notice.setMsg(getMsg());
        }
        if (getSendDate() != null) {
            notice.setSendDate(getSendDate());
        }
        if (getVersion() != null) {
            notice.setVersion(getVersion());
        }
        return notice;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("NoticeCriteria{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (msg != null) {
            sb.append("msg=").append(msg).append(",");
        }
        if (sendDate != null) {
            sb.append("sendDate=").append(sendDate).append(",");
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
    public NoticeCriteria setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * get 公共内容
     *
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * set 公共内容
     *
     * @return
     */
    public NoticeCriteria setMsg(String msg) {
        if (msg != null && msg.trim().length() == 0) {
            this.msg = null;
            return this;
        }
        this.msg = msg;
        return this;
    }


    /**
     * get 发出时间
     *
     * @return
     */
    public Date getSendDate() {
        return sendDate;
    }

    /**
     * get table [demo_notice][column = send_date] order
     *
     * @return
     */
    public String getSendDateOrder() {
        return sendDateOrder;
    }

    /**
     * set 发出时间
     *
     * @return
     */
    public NoticeCriteria setSendDate(Date sendDate) {
        this.sendDate = sendDate;
        return this;
    }

    /**
     * set table [demo_notice][column = send_date] order DESC||ASC
     *
     * @return
     */
    public NoticeCriteria setSendDateOrder(String sendDateOrder) {
        this.sendDateOrder = sendDateOrder;
        putSort("send_date", sendDateOrder);
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
    public NoticeCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}