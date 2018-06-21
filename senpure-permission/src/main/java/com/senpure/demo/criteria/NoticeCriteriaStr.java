package com.senpure.demo.criteria;

import com.senpure.base.criterion.CriteriaStr;
import com.senpure.base.struct.PatternDate;
import com.senpure.base.validator.DynamicDate;

import java.io.Serializable;

/**
 * 公告模型
 *
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
public class NoticeCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 339330519L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    //公共内容
    private String msg;
    //发出时间
    private String sendDate;
    @DynamicDate
    private PatternDate sendDateValid = new PatternDate();
    //table [demo_notice][column = send_date] order
    private String sendDateOrder;

    public NoticeCriteria toNoticeCriteria() {
        NoticeCriteria criteria = new NoticeCriteria();
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
        //公共内容
        if (msg != null) {
            criteria.setMsg(msg);
        }
        //发出时间
        if (sendDate != null) {
            criteria.setSendDate(sendDateValid.getDate());
        }
        //table [demo_notice][column = send_date] order
        if (sendDateOrder != null) {
            criteria.setSendDateOrder(sendDateOrder);
        }
        return criteria;
    }

    @Override
    public void setDatePattern(String datePattern) {
        super.setDatePattern(datePattern);
        sendDateValid.setPattern(datePattern);
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("NoticeCriteriaStr{");
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

    @Override
    protected void afterStr(StringBuilder sb) {
        if (sendDateOrder != null) {
            sb.append("sendDateOrder=").append(sendDateOrder).append(",");
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
    public NoticeCriteriaStr setId(String id) {
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
    public NoticeCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
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
    public NoticeCriteriaStr setMsg(String msg) {
        if (msg != null && msg.trim().length() == 0) {
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
    public String getSendDate() {
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
    public NoticeCriteriaStr setSendDate(String sendDate) {
        if (sendDate != null && sendDate.trim().length() == 0) {
            return this;
        }
        this.sendDate = sendDate;
        return this;
    }

    /**
     * set table [demo_notice][column = send_date] order DESC||ASC
     *
     * @return
     */
    public NoticeCriteriaStr setSendDateOrder(String sendDateOrder) {
        if (sendDateOrder != null && sendDateOrder.trim().length() == 0) {
            this.sendDateOrder = null;
            return this;
        }
        this.sendDateOrder = sendDateOrder;
        return this;
    }


}