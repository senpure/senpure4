package com.senpure.demo.entity;

import com.senpure.base.annotation.Explain;
import com.senpure.base.entity.LongAndVersionEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by 罗中正 on 2018/1/18 0018.
 */
@Entity
@Table(name = "demo_notice")
@Explain("公告模型")
public class Notice extends LongAndVersionEntity {

    @Explain("公共内容")
    private String msg;
    @Explain("发出时间")
    private Date sendDate;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
}
