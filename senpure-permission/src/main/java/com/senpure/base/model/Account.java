package com.senpure.base.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 1379989838L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private String account;
    private String password;
    private String name;
    private Long createTime;
    private Date createDate;
    //当前ip
    private String ip;
    //数字IP，只存一个最接近真实IP的数据
    private Long ipNumber;
    //当前来源，火狐，360，手机等
    private String source;
    //账号禁止登录的说明
    private String banStr;
    private Long banExpiryTime;
    private Date banExpiryDate;
    //本次登录时间
    private Long loginTime;
    //本次登录时间
    private Date loginDate;
    private String loginType;
    private Boolean online;
    private String accountState;
    private String client;
    private String clientVersion;
    private Integer clientVersionNumber;
    private Long lastLoginTime;
    private Date lastLoginDate;
    private Long lastLogoutTime;
    private Date lastLogoutDate;
    private String lastLoginType;
    private String lastLogoutType;
    private String lastLoginIp;
    private Long lastLoginIpNumber;
    private String lastLoginSource;
    //外键,modelName:Container,tableName:senpure_container
    private Integer containerId;

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
    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAccount() {
        return account;
    }


    public Account setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getPassword() {
        return password;
    }


    public Account setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }


    public Account setName(String name) {
        this.name = name;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }


    public Account setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }


    public Account setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    /**
     * get 当前ip
     *
     * @return
     */
    public String getIp() {
        return ip;
    }

    /**
     * set 当前ip
     *
     * @return
     */
    public Account setIp(String ip) {
        this.ip = ip;
        return this;
    }

    /**
     * get 数字IP，只存一个最接近真实IP的数据
     *
     * @return
     */
    public Long getIpNumber() {
        return ipNumber;
    }

    /**
     * set 数字IP，只存一个最接近真实IP的数据
     *
     * @return
     */
    public Account setIpNumber(Long ipNumber) {
        this.ipNumber = ipNumber;
        return this;
    }

    /**
     * get 当前来源，火狐，360，手机等
     *
     * @return
     */
    public String getSource() {
        return source;
    }

    /**
     * set 当前来源，火狐，360，手机等
     *
     * @return
     */
    public Account setSource(String source) {
        this.source = source;
        return this;
    }

    /**
     * get 账号禁止登录的说明
     *
     * @return
     */
    public String getBanStr() {
        return banStr;
    }

    /**
     * set 账号禁止登录的说明
     *
     * @return
     */
    public Account setBanStr(String banStr) {
        this.banStr = banStr;
        return this;
    }

    public Long getBanExpiryTime() {
        return banExpiryTime;
    }


    public Account setBanExpiryTime(Long banExpiryTime) {
        this.banExpiryTime = banExpiryTime;
        return this;
    }

    public Date getBanExpiryDate() {
        return banExpiryDate;
    }


    public Account setBanExpiryDate(Date banExpiryDate) {
        this.banExpiryDate = banExpiryDate;
        return this;
    }

    /**
     * get 本次登录时间
     *
     * @return
     */
    public Long getLoginTime() {
        return loginTime;
    }

    /**
     * set 本次登录时间
     *
     * @return
     */
    public Account setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
        return this;
    }

    /**
     * get 本次登录时间
     *
     * @return
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * set 本次登录时间
     *
     * @return
     */
    public Account setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
        return this;
    }

    public String getLoginType() {
        return loginType;
    }


    public Account setLoginType(String loginType) {
        this.loginType = loginType;
        return this;
    }

    public Boolean getOnline() {
        return online;
    }


    public Account setOnline(Boolean online) {
        this.online = online;
        return this;
    }

    public String getAccountState() {
        return accountState;
    }


    public Account setAccountState(String accountState) {
        this.accountState = accountState;
        return this;
    }

    public String getClient() {
        return client;
    }


    public Account setClient(String client) {
        this.client = client;
        return this;
    }

    public String getClientVersion() {
        return clientVersion;
    }


    public Account setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
        return this;
    }

    public Integer getClientVersionNumber() {
        return clientVersionNumber;
    }


    public Account setClientVersionNumber(Integer clientVersionNumber) {
        this.clientVersionNumber = clientVersionNumber;
        return this;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }


    public Account setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
        return this;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }


    public Account setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public Long getLastLogoutTime() {
        return lastLogoutTime;
    }


    public Account setLastLogoutTime(Long lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
        return this;
    }

    public Date getLastLogoutDate() {
        return lastLogoutDate;
    }


    public Account setLastLogoutDate(Date lastLogoutDate) {
        this.lastLogoutDate = lastLogoutDate;
        return this;
    }

    public String getLastLoginType() {
        return lastLoginType;
    }


    public Account setLastLoginType(String lastLoginType) {
        this.lastLoginType = lastLoginType;
        return this;
    }

    public String getLastLogoutType() {
        return lastLogoutType;
    }


    public Account setLastLogoutType(String lastLogoutType) {
        this.lastLogoutType = lastLogoutType;
        return this;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }


    public Account setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
        return this;
    }

    public Long getLastLoginIpNumber() {
        return lastLoginIpNumber;
    }


    public Account setLastLoginIpNumber(Long lastLoginIpNumber) {
        this.lastLoginIpNumber = lastLoginIpNumber;
        return this;
    }

    public String getLastLoginSource() {
        return lastLoginSource;
    }


    public Account setLastLoginSource(String lastLoginSource) {
        this.lastLoginSource = lastLoginSource;
        return this;
    }

    /**
     * get 外键,modelName:Container,tableName:senpure_container
     *
     * @return
     */
    public Integer getContainerId() {
        return containerId;
    }

    /**
     * set 外键,modelName:Container,tableName:senpure_container
     *
     * @return
     */
    public Account setContainerId(Integer containerId) {
        this.containerId = containerId;
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
    public Account setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "Account{"
                + "id=" + id
                + ",version=" + version
                + ",account=" + account
                + ",password=" + password
                + ",name=" + name
                + ",createTime=" + createTime
                + ",createDate=" + createDate
                + ",ip=" + ip
                + ",ipNumber=" + ipNumber
                + ",source=" + source
                + ",banStr=" + banStr
                + ",banExpiryTime=" + banExpiryTime
                + ",banExpiryDate=" + banExpiryDate
                + ",loginTime=" + loginTime
                + ",loginDate=" + loginDate
                + ",loginType=" + loginType
                + ",online=" + online
                + ",accountState=" + accountState
                + ",client=" + client
                + ",clientVersion=" + clientVersion
                + ",clientVersionNumber=" + clientVersionNumber
                + ",lastLoginTime=" + lastLoginTime
                + ",lastLoginDate=" + lastLoginDate
                + ",lastLogoutTime=" + lastLogoutTime
                + ",lastLogoutDate=" + lastLogoutDate
                + ",lastLoginType=" + lastLoginType
                + ",lastLogoutType=" + lastLogoutType
                + ",lastLoginIp=" + lastLoginIp
                + ",lastLoginIpNumber=" + lastLoginIpNumber
                + ",lastLoginSource=" + lastLoginSource
                + ",containerId=" + containerId
                + "}";
    }

}