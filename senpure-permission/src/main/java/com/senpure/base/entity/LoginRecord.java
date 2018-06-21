package com.senpure.base.entity;

import com.senpure.base.PermissionConstant;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by DZ on 2016-07-02 14:04
 */

@Table(name = PermissionConstant.DATA_BASE_PREFIX+ "_LOGIN_RECORD")
public class LoginRecord extends LongAndVersionEntity{
    private static final long serialVersionUID = -2351846848214229447L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;
    private Long loginTime;
    private Date loginDate;
    private Long logoutTime;
    private Date logoutDate;

    private String loginType;

    private String logoutType;
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Long getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Long logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Date getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(Date logoutDate) {
        this.logoutDate = logoutDate;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLogoutType() {
        return logoutType;
    }

    public void setLogoutType(String logoutType) {
        this.logoutType = logoutType;
    }
}
