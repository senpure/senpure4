package com.senpure.base.criteria;

import com.senpure.base.criterion.Criteria;
import org.hibernate.validator.constraints.Length;

/**
 * Created by DZ on 2016-07-25 16:55
 */
    public class LoginCriteria extends Criteria {

    @Length(max=24,min=5,message="{username.length.error}")
    private String account ;
    @Length(min=5,max=24,message="{password.length.error}")
    private String password ;
    private String loginIP;
    private boolean remember=true;
    private String loginType;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

}
