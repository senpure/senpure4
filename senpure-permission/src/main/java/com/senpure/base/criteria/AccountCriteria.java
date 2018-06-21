package com.senpure.base.criteria;

import com.senpure.base.model.Account;
import com.senpure.base.criterion.Criteria;

import java.io.Serializable;
import java.util.Date;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class AccountCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 1379989838L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private String account;
    //table [senpure_account][column = account] order
    private String accountOrder;
    private String password;
    private String name;
    //table [senpure_account][column = name] order
    private String nameOrder;
    private Long createTime;
    //table [senpure_account][column = create_time] order
    private String createTimeOrder;
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
    //table [senpure_account][column = ban_expiry_time] order
    private String banExpiryTimeOrder;
    private Date banExpiryDate;
    //本次登录时间
    private Long loginTime;
    //table [senpure_account][column = login_time] order
    private String loginTimeOrder;
    //本次登录时间
    private Date loginDate;
    private String loginType;
    //table [senpure_account][column = login_type] order
    private String loginTypeOrder;
    private Boolean online;
    private String accountState;
    private String client;
    private String clientVersion;
    private Integer clientVersionNumber;
    private Long lastLoginTime;
    //table [senpure_account][column = last_login_time] order
    private String lastLoginTimeOrder;
    private Date lastLoginDate;
    private Long lastLogoutTime;
    //table [senpure_account][column = last_logout_time] order
    private String lastLogoutTimeOrder;
    private Date lastLogoutDate;
    private String lastLoginType;
    //table [senpure_account][column = last_login_type] order
    private String lastLoginTypeOrder;
    private String lastLogoutType;
    //table [senpure_account][column = last_logout_type] order
    private String lastLogoutTypeOrder;
    private String lastLoginIp;
    private Long lastLoginIpNumber;
    private String lastLoginSource;
    //外键,modelName:Container,tableName:senpure_container
    private Integer containerId;
    //table [senpure_account][column = container_id] order
    private String containerIdOrder;

    public static Account toAccount(AccountCriteria criteria, Account account) {
        account.setId(criteria.getId());
        account.setAccount(criteria.getAccount());
        account.setPassword(criteria.getPassword());
        account.setName(criteria.getName());
        account.setCreateTime(criteria.getCreateTime());
        account.setCreateDate(criteria.getCreateDate());
        account.setIp(criteria.getIp());
        account.setIpNumber(criteria.getIpNumber());
        account.setSource(criteria.getSource());
        account.setBanStr(criteria.getBanStr());
        account.setBanExpiryTime(criteria.getBanExpiryTime());
        account.setBanExpiryDate(criteria.getBanExpiryDate());
        account.setLoginTime(criteria.getLoginTime());
        account.setLoginDate(criteria.getLoginDate());
        account.setLoginType(criteria.getLoginType());
        account.setOnline(criteria.getOnline());
        account.setAccountState(criteria.getAccountState());
        account.setClient(criteria.getClient());
        account.setClientVersion(criteria.getClientVersion());
        account.setClientVersionNumber(criteria.getClientVersionNumber());
        account.setLastLoginTime(criteria.getLastLoginTime());
        account.setLastLoginDate(criteria.getLastLoginDate());
        account.setLastLogoutTime(criteria.getLastLogoutTime());
        account.setLastLogoutDate(criteria.getLastLogoutDate());
        account.setLastLoginType(criteria.getLastLoginType());
        account.setLastLogoutType(criteria.getLastLogoutType());
        account.setLastLoginIp(criteria.getLastLoginIp());
        account.setLastLoginIpNumber(criteria.getLastLoginIpNumber());
        account.setLastLoginSource(criteria.getLastLoginSource());
        account.setContainerId(criteria.getContainerId());
        account.setVersion(criteria.getVersion());
        return account;
    }

    public Account toAccount() {
        Account account = new Account();
        return toAccount(this, account);
    }

    /**
     * 将AccountCriteria 的有效值(不为空),赋值给 Account
     *
     * @return Account
     */
    public Account effective(Account account) {
        if (getId() != null) {
            account.setId(getId());
        }
        if (getAccount() != null) {
            account.setAccount(getAccount());
        }
        if (getPassword() != null) {
            account.setPassword(getPassword());
        }
        if (getName() != null) {
            account.setName(getName());
        }
        if (getCreateTime() != null) {
            account.setCreateTime(getCreateTime());
        }
        if (getCreateDate() != null) {
            account.setCreateDate(getCreateDate());
        }
        if (getIp() != null) {
            account.setIp(getIp());
        }
        if (getIpNumber() != null) {
            account.setIpNumber(getIpNumber());
        }
        if (getSource() != null) {
            account.setSource(getSource());
        }
        if (getBanStr() != null) {
            account.setBanStr(getBanStr());
        }
        if (getBanExpiryTime() != null) {
            account.setBanExpiryTime(getBanExpiryTime());
        }
        if (getBanExpiryDate() != null) {
            account.setBanExpiryDate(getBanExpiryDate());
        }
        if (getLoginTime() != null) {
            account.setLoginTime(getLoginTime());
        }
        if (getLoginDate() != null) {
            account.setLoginDate(getLoginDate());
        }
        if (getLoginType() != null) {
            account.setLoginType(getLoginType());
        }
        if (getOnline() != null) {
            account.setOnline(getOnline());
        }
        if (getAccountState() != null) {
            account.setAccountState(getAccountState());
        }
        if (getClient() != null) {
            account.setClient(getClient());
        }
        if (getClientVersion() != null) {
            account.setClientVersion(getClientVersion());
        }
        if (getClientVersionNumber() != null) {
            account.setClientVersionNumber(getClientVersionNumber());
        }
        if (getLastLoginTime() != null) {
            account.setLastLoginTime(getLastLoginTime());
        }
        if (getLastLoginDate() != null) {
            account.setLastLoginDate(getLastLoginDate());
        }
        if (getLastLogoutTime() != null) {
            account.setLastLogoutTime(getLastLogoutTime());
        }
        if (getLastLogoutDate() != null) {
            account.setLastLogoutDate(getLastLogoutDate());
        }
        if (getLastLoginType() != null) {
            account.setLastLoginType(getLastLoginType());
        }
        if (getLastLogoutType() != null) {
            account.setLastLogoutType(getLastLogoutType());
        }
        if (getLastLoginIp() != null) {
            account.setLastLoginIp(getLastLoginIp());
        }
        if (getLastLoginIpNumber() != null) {
            account.setLastLoginIpNumber(getLastLoginIpNumber());
        }
        if (getLastLoginSource() != null) {
            account.setLastLoginSource(getLastLoginSource());
        }
        if (getContainerId() != null) {
            account.setContainerId(getContainerId());
        }
        if (getVersion() != null) {
            account.setVersion(getVersion());
        }
        return account;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("AccountCriteria{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (account != null) {
            sb.append("account=").append(account).append(",");
        }
        if (password != null) {
            sb.append("password=").append(password).append(",");
        }
        if (name != null) {
            sb.append("name=").append(name).append(",");
        }
        if (createTime != null) {
            sb.append("createTime=").append(createTime).append(",");
        }
        if (createDate != null) {
            sb.append("createDate=").append(createDate).append(",");
        }
        if (ip != null) {
            sb.append("ip=").append(ip).append(",");
        }
        if (ipNumber != null) {
            sb.append("ipNumber=").append(ipNumber).append(",");
        }
        if (source != null) {
            sb.append("source=").append(source).append(",");
        }
        if (banStr != null) {
            sb.append("banStr=").append(banStr).append(",");
        }
        if (banExpiryTime != null) {
            sb.append("banExpiryTime=").append(banExpiryTime).append(",");
        }
        if (banExpiryDate != null) {
            sb.append("banExpiryDate=").append(banExpiryDate).append(",");
        }
        if (loginTime != null) {
            sb.append("loginTime=").append(loginTime).append(",");
        }
        if (loginDate != null) {
            sb.append("loginDate=").append(loginDate).append(",");
        }
        if (loginType != null) {
            sb.append("loginType=").append(loginType).append(",");
        }
        if (online != null) {
            sb.append("online=").append(online).append(",");
        }
        if (accountState != null) {
            sb.append("accountState=").append(accountState).append(",");
        }
        if (client != null) {
            sb.append("client=").append(client).append(",");
        }
        if (clientVersion != null) {
            sb.append("clientVersion=").append(clientVersion).append(",");
        }
        if (clientVersionNumber != null) {
            sb.append("clientVersionNumber=").append(clientVersionNumber).append(",");
        }
        if (lastLoginTime != null) {
            sb.append("lastLoginTime=").append(lastLoginTime).append(",");
        }
        if (lastLoginDate != null) {
            sb.append("lastLoginDate=").append(lastLoginDate).append(",");
        }
        if (lastLogoutTime != null) {
            sb.append("lastLogoutTime=").append(lastLogoutTime).append(",");
        }
        if (lastLogoutDate != null) {
            sb.append("lastLogoutDate=").append(lastLogoutDate).append(",");
        }
        if (lastLoginType != null) {
            sb.append("lastLoginType=").append(lastLoginType).append(",");
        }
        if (lastLogoutType != null) {
            sb.append("lastLogoutType=").append(lastLogoutType).append(",");
        }
        if (lastLoginIp != null) {
            sb.append("lastLoginIp=").append(lastLoginIp).append(",");
        }
        if (lastLoginIpNumber != null) {
            sb.append("lastLoginIpNumber=").append(lastLoginIpNumber).append(",");
        }
        if (lastLoginSource != null) {
            sb.append("lastLoginSource=").append(lastLoginSource).append(",");
        }
        if (containerId != null) {
            sb.append("containerId=").append(containerId).append(",");
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
    public AccountCriteria setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAccount() {
        return account;
    }

    /**
     * get table [senpure_account][column = account] order
     *
     * @return
     */
    public String getAccountOrder() {
        return accountOrder;
    }


    public AccountCriteria setAccount(String account) {
        if (account != null && account.trim().length() == 0) {
            return this;
        }
        this.account = account;
        return this;
    }

    /**
     * set table [senpure_account][column = account] order DESC||ASC
     *
     * @return
     */
    public AccountCriteria setAccountOrder(String accountOrder) {
        this.accountOrder = accountOrder;
        putSort("account", accountOrder);
        return this;
    }


    public String getPassword() {
        return password;
    }


    public AccountCriteria setPassword(String password) {
        if (password != null && password.trim().length() == 0) {
            return this;
        }
        this.password = password;
        return this;
    }


    public String getName() {
        return name;
    }

    /**
     * get table [senpure_account][column = name] order
     *
     * @return
     */
    public String getNameOrder() {
        return nameOrder;
    }


    public AccountCriteria setName(String name) {
        if (name != null && name.trim().length() == 0) {
            return this;
        }
        this.name = name;
        return this;
    }

    /**
     * set table [senpure_account][column = name] order DESC||ASC
     *
     * @return
     */
    public AccountCriteria setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
        putSort("name", nameOrder);
        return this;
    }


    public Long getCreateTime() {
        return createTime;
    }

    /**
     * get table [senpure_account][column = create_time] order
     *
     * @return
     */
    public String getCreateTimeOrder() {
        return createTimeOrder;
    }


    public AccountCriteria setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * set table [senpure_account][column = create_time] order DESC||ASC
     *
     * @return
     */
    public AccountCriteria setCreateTimeOrder(String createTimeOrder) {
        this.createTimeOrder = createTimeOrder;
        putSort("create_time", createTimeOrder);
        return this;
    }


    public Date getCreateDate() {
        return createDate;
    }


    public AccountCriteria setCreateDate(Date createDate) {
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
    public AccountCriteria setIp(String ip) {
        if (ip != null && ip.trim().length() == 0) {
            return this;
        }
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
    public AccountCriteria setIpNumber(Long ipNumber) {
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
    public AccountCriteria setSource(String source) {
        if (source != null && source.trim().length() == 0) {
            return this;
        }
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
    public AccountCriteria setBanStr(String banStr) {
        if (banStr != null && banStr.trim().length() == 0) {
            return this;
        }
        this.banStr = banStr;
        return this;
    }


    public Long getBanExpiryTime() {
        return banExpiryTime;
    }

    /**
     * get table [senpure_account][column = ban_expiry_time] order
     *
     * @return
     */
    public String getBanExpiryTimeOrder() {
        return banExpiryTimeOrder;
    }


    public AccountCriteria setBanExpiryTime(Long banExpiryTime) {
        this.banExpiryTime = banExpiryTime;
        return this;
    }

    /**
     * set table [senpure_account][column = ban_expiry_time] order DESC||ASC
     *
     * @return
     */
    public AccountCriteria setBanExpiryTimeOrder(String banExpiryTimeOrder) {
        this.banExpiryTimeOrder = banExpiryTimeOrder;
        putSort("ban_expiry_time", banExpiryTimeOrder);
        return this;
    }


    public Date getBanExpiryDate() {
        return banExpiryDate;
    }


    public AccountCriteria setBanExpiryDate(Date banExpiryDate) {
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
     * get table [senpure_account][column = login_time] order
     *
     * @return
     */
    public String getLoginTimeOrder() {
        return loginTimeOrder;
    }

    /**
     * set 本次登录时间
     *
     * @return
     */
    public AccountCriteria setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
        return this;
    }

    /**
     * set table [senpure_account][column = login_time] order DESC||ASC
     *
     * @return
     */
    public AccountCriteria setLoginTimeOrder(String loginTimeOrder) {
        this.loginTimeOrder = loginTimeOrder;
        putSort("login_time", loginTimeOrder);
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
    public AccountCriteria setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
        return this;
    }


    public String getLoginType() {
        return loginType;
    }

    /**
     * get table [senpure_account][column = login_type] order
     *
     * @return
     */
    public String getLoginTypeOrder() {
        return loginTypeOrder;
    }


    public AccountCriteria setLoginType(String loginType) {
        if (loginType != null && loginType.trim().length() == 0) {
            return this;
        }
        this.loginType = loginType;
        return this;
    }

    /**
     * set table [senpure_account][column = login_type] order DESC||ASC
     *
     * @return
     */
    public AccountCriteria setLoginTypeOrder(String loginTypeOrder) {
        this.loginTypeOrder = loginTypeOrder;
        putSort("login_type", loginTypeOrder);
        return this;
    }


    public Boolean getOnline() {
        return online;
    }


    public AccountCriteria setOnline(Boolean online) {
        this.online = online;
        return this;
    }


    public String getAccountState() {
        return accountState;
    }


    public AccountCriteria setAccountState(String accountState) {
        if (accountState != null && accountState.trim().length() == 0) {
            return this;
        }
        this.accountState = accountState;
        return this;
    }


    public String getClient() {
        return client;
    }


    public AccountCriteria setClient(String client) {
        if (client != null && client.trim().length() == 0) {
            return this;
        }
        this.client = client;
        return this;
    }


    public String getClientVersion() {
        return clientVersion;
    }


    public AccountCriteria setClientVersion(String clientVersion) {
        if (clientVersion != null && clientVersion.trim().length() == 0) {
            return this;
        }
        this.clientVersion = clientVersion;
        return this;
    }


    public Integer getClientVersionNumber() {
        return clientVersionNumber;
    }


    public AccountCriteria setClientVersionNumber(Integer clientVersionNumber) {
        this.clientVersionNumber = clientVersionNumber;
        return this;
    }


    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * get table [senpure_account][column = last_login_time] order
     *
     * @return
     */
    public String getLastLoginTimeOrder() {
        return lastLoginTimeOrder;
    }


    public AccountCriteria setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
        return this;
    }

    /**
     * set table [senpure_account][column = last_login_time] order DESC||ASC
     *
     * @return
     */
    public AccountCriteria setLastLoginTimeOrder(String lastLoginTimeOrder) {
        this.lastLoginTimeOrder = lastLoginTimeOrder;
        putSort("last_login_time", lastLoginTimeOrder);
        return this;
    }


    public Date getLastLoginDate() {
        return lastLoginDate;
    }


    public AccountCriteria setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }


    public Long getLastLogoutTime() {
        return lastLogoutTime;
    }

    /**
     * get table [senpure_account][column = last_logout_time] order
     *
     * @return
     */
    public String getLastLogoutTimeOrder() {
        return lastLogoutTimeOrder;
    }


    public AccountCriteria setLastLogoutTime(Long lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
        return this;
    }

    /**
     * set table [senpure_account][column = last_logout_time] order DESC||ASC
     *
     * @return
     */
    public AccountCriteria setLastLogoutTimeOrder(String lastLogoutTimeOrder) {
        this.lastLogoutTimeOrder = lastLogoutTimeOrder;
        putSort("last_logout_time", lastLogoutTimeOrder);
        return this;
    }


    public Date getLastLogoutDate() {
        return lastLogoutDate;
    }


    public AccountCriteria setLastLogoutDate(Date lastLogoutDate) {
        this.lastLogoutDate = lastLogoutDate;
        return this;
    }


    public String getLastLoginType() {
        return lastLoginType;
    }

    /**
     * get table [senpure_account][column = last_login_type] order
     *
     * @return
     */
    public String getLastLoginTypeOrder() {
        return lastLoginTypeOrder;
    }


    public AccountCriteria setLastLoginType(String lastLoginType) {
        if (lastLoginType != null && lastLoginType.trim().length() == 0) {
            return this;
        }
        this.lastLoginType = lastLoginType;
        return this;
    }

    /**
     * set table [senpure_account][column = last_login_type] order DESC||ASC
     *
     * @return
     */
    public AccountCriteria setLastLoginTypeOrder(String lastLoginTypeOrder) {
        this.lastLoginTypeOrder = lastLoginTypeOrder;
        putSort("last_login_type", lastLoginTypeOrder);
        return this;
    }


    public String getLastLogoutType() {
        return lastLogoutType;
    }

    /**
     * get table [senpure_account][column = last_logout_type] order
     *
     * @return
     */
    public String getLastLogoutTypeOrder() {
        return lastLogoutTypeOrder;
    }


    public AccountCriteria setLastLogoutType(String lastLogoutType) {
        if (lastLogoutType != null && lastLogoutType.trim().length() == 0) {
            return this;
        }
        this.lastLogoutType = lastLogoutType;
        return this;
    }

    /**
     * set table [senpure_account][column = last_logout_type] order DESC||ASC
     *
     * @return
     */
    public AccountCriteria setLastLogoutTypeOrder(String lastLogoutTypeOrder) {
        this.lastLogoutTypeOrder = lastLogoutTypeOrder;
        putSort("last_logout_type", lastLogoutTypeOrder);
        return this;
    }


    public String getLastLoginIp() {
        return lastLoginIp;
    }


    public AccountCriteria setLastLoginIp(String lastLoginIp) {
        if (lastLoginIp != null && lastLoginIp.trim().length() == 0) {
            return this;
        }
        this.lastLoginIp = lastLoginIp;
        return this;
    }


    public Long getLastLoginIpNumber() {
        return lastLoginIpNumber;
    }


    public AccountCriteria setLastLoginIpNumber(Long lastLoginIpNumber) {
        this.lastLoginIpNumber = lastLoginIpNumber;
        return this;
    }


    public String getLastLoginSource() {
        return lastLoginSource;
    }


    public AccountCriteria setLastLoginSource(String lastLoginSource) {
        if (lastLoginSource != null && lastLoginSource.trim().length() == 0) {
            return this;
        }
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
     * get table [senpure_account][column = container_id] order
     *
     * @return
     */
    public String getContainerIdOrder() {
        return containerIdOrder;
    }

    /**
     * set 外键,modelName:Container,tableName:senpure_container
     *
     * @return
     */
    public AccountCriteria setContainerId(Integer containerId) {
        this.containerId = containerId;
        return this;
    }

    /**
     * set table [senpure_account][column = container_id] order DESC||ASC
     *
     * @return
     */
    public AccountCriteria setContainerIdOrder(String containerIdOrder) {
        this.containerIdOrder = containerIdOrder;
        putSort("container_id", containerIdOrder);
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
    public AccountCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}