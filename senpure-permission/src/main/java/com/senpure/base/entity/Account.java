package com.senpure.base.entity;


import com.senpure.base.PermissionConstant;
import com.senpure.base.annotation.Explain;

import javax.persistence.*;
import java.util.Date;

/**
 * 只记录账号信息相关信息<br>
 * Created by DZ on 2016-06-27 10:47
 */
@Entity
@Table(name = PermissionConstant.DATA_BASE_PREFIX+"_ACCOUNT")
public class Account   extends LongAndVersionEntity{
    private static final long serialVersionUID = -3733641324319450846L;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="containerId",nullable = false)
    private Container container;
    @Column(length = 24,nullable = false,unique = true)
    private String account;
    @Column(length = 24,nullable = false)
    private String password;
    @Column
    private String name ;
    @Column(nullable = false)
    private Long createTime;
    @Column(nullable = false)
    private Date createDate;

    /**
     * 当前ip255.255.255.255
     */
    @Explain("当前ip")
    @Column(nullable = true, length = 64)
    private String ip;
    // 数字IP，只存一个最接近真实IP的数据
    @Column(nullable = true)
    @Explain("数字IP，只存一个最接近真实IP的数据")
    private Long ipNumber;
    /**
     * 当前来源，火狐，360，手机等
     * */
    @Explain("当前来源，火狐，360，手机等")
    private String source;

    @Explain("账号禁止登录的说明")
    private String banStr;
    private Long banExpiryTime;
    private Date banExpiryDate;
    @Explain("本次登录时间")
    private Long loginTime;
    @Explain("本次登录时间")
    private Date loginDate;
    private String loginType ;
    @Column(nullable = true, length = 32)
    private Boolean online;
    @Column(nullable = false, length = 32)
    private String accountState ;
    @Column(nullable = true, length = 32)
    private String client;
    @Column(nullable = true, length = 12)
    private String clientVersion;
    private Integer clientVersionNumber;
    private Long lastLoginTime;
    private  Date lastLoginDate;
    private Long lastLogoutTime;
    private  Date lastLogoutDate;
    @Column(nullable = true, length = 32)
    private String lastLoginType;
    @Column(nullable = true, length = 32)
    private String lastLogoutType ;
    @Column(nullable = true, length = 64)
    private String lastLoginIp;
    private Long lastLoginIpNumber;
    private String lastLoginSource;


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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getIpNumber() {
        return ipNumber;
    }

    public void setIpNumber(Long ipNumber) {
        this.ipNumber = ipNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getAccountState() {
        return accountState;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public Integer getClientVersionNumber() {
        return clientVersionNumber;
    }

    public void setClientVersionNumber(Integer clientVersionNumber) {
        this.clientVersionNumber = clientVersionNumber;
    }

    public String getLastLogoutType() {
        return lastLogoutType;
    }

    public void setLastLogoutType(String lastLogoutType) {
        this.lastLogoutType = lastLogoutType;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getLastLogoutTime() {
        return lastLogoutTime;
    }

    public void setLastLogoutTime(Long lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastLoginSource() {
        return lastLoginSource;
    }

    public void setLastLoginSource(String lastLoginSource) {
        this.lastLoginSource = lastLoginSource;
    }

    public String getLastLoginType() {
        return lastLoginType;
    }

    public void setLastLoginType(String lastLoginType) {
        this.lastLoginType = lastLoginType;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastLogoutDate() {
        return lastLogoutDate;
    }

    public void setLastLogoutDate(Date lastLogoutDate) {
        this.lastLogoutDate = lastLogoutDate;
    }

    public Long getLastLoginIpNumber() {
        return lastLoginIpNumber;
    }

    public void setLastLoginIpNumber(Long lastLoginIpNumber) {
        this.lastLoginIpNumber = lastLoginIpNumber;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public String getBanStr() {
        return banStr;
    }

    public void setBanStr(String banStr) {
        this.banStr = banStr;
    }

    public Long getBanExpiryTime() {
        return banExpiryTime;
    }

    public void setBanExpiryTime(Long banExpiryTime) {
        this.banExpiryTime = banExpiryTime;
    }

    public Date getBanExpiryDate() {
        return banExpiryDate;
    }

    public void setBanExpiryDate(Date banExpiryDate) {
        this.banExpiryDate = banExpiryDate;
    }
}
