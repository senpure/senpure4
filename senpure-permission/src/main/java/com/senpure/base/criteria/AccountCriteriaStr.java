package com.senpure.base.criteria;

import com.senpure.base.criterion.CriteriaStr;
import com.senpure.base.struct.PatternDate;
import com.senpure.base.validator.DynamicDate;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class AccountCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 1379989838L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    private String account;
    //table [senpure_account][column = account] order
    private String accountOrder;
    private String password;
    private String name;
    //table [senpure_account][column = name] order
    private String nameOrder;
    //table [senpure_account][column = create_time] order
    private String createTimeOrder;
    private String createDate;
    @DynamicDate
    private PatternDate createDateValid = new PatternDate();
    //当前ip
    private String ip;
    //数字IP，只存一个最接近真实IP的数据
    private String ipNumber;
    //当前来源，火狐，360，手机等
    private String source;
    //账号禁止登录的说明
    private String banStr;
    //table [senpure_account][column = ban_expiry_time] order
    private String banExpiryTimeOrder;
    private String banExpiryDate;
    @DynamicDate
    private PatternDate banExpiryDateValid = new PatternDate();
    //table [senpure_account][column = login_time] order
    private String loginTimeOrder;
    //本次登录时间
    private String loginDate;
    @DynamicDate
    private PatternDate loginDateValid = new PatternDate();
    private String loginType;
    //table [senpure_account][column = login_type] order
    private String loginTypeOrder;
    private String online;
    private String accountState;
    private String client;
    private String clientVersion;
    private String clientVersionNumber;
    //table [senpure_account][column = last_login_time] order
    private String lastLoginTimeOrder;
    private String lastLoginDate;
    @DynamicDate
    private PatternDate lastLoginDateValid = new PatternDate();
    //table [senpure_account][column = last_logout_time] order
    private String lastLogoutTimeOrder;
    private String lastLogoutDate;
    @DynamicDate
    private PatternDate lastLogoutDateValid = new PatternDate();
    private String lastLoginType;
    //table [senpure_account][column = last_login_type] order
    private String lastLoginTypeOrder;
    private String lastLogoutType;
    //table [senpure_account][column = last_logout_type] order
    private String lastLogoutTypeOrder;
    private String lastLoginIp;
    private String lastLoginIpNumber;
    private String lastLoginSource;
    //外键,modelName:Container,tableName:senpure_container
    private String containerId;
    //table [senpure_account][column = container_id] order
    private String containerIdOrder;

    public AccountCriteria toAccountCriteria() {
        AccountCriteria criteria = new AccountCriteria();
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
        if (account != null) {
            criteria.setAccount(account);
        }
        //table [senpure_account][column = account] order
        if (accountOrder != null) {
            criteria.setAccountOrder(accountOrder);
        }
        if (password != null) {
            criteria.setPassword(password);
        }
        if (name != null) {
            criteria.setName(name);
        }
        //table [senpure_account][column = name] order
        if (nameOrder != null) {
            criteria.setNameOrder(nameOrder);
        }
        //table [senpure_account][column = create_time] order
        if (createTimeOrder != null) {
            criteria.setCreateTimeOrder(createTimeOrder);
        }
        if (createDate != null) {
            criteria.setCreateDate(createDateValid.getDate());
            if (createDateValid.getDate() != null) {
                criteria.setCreateTime(createDateValid.getDate().getTime());
            }
        }
        //当前ip
        if (ip != null) {
            criteria.setIp(ip);
        }
        //数字IP，只存一个最接近真实IP的数据
        if (ipNumber != null) {
            criteria.setIpNumber(Long.valueOf(ipNumber));
        }
        //当前来源，火狐，360，手机等
        if (source != null) {
            criteria.setSource(source);
        }
        //账号禁止登录的说明
        if (banStr != null) {
            criteria.setBanStr(banStr);
        }
        //table [senpure_account][column = ban_expiry_time] order
        if (banExpiryTimeOrder != null) {
            criteria.setBanExpiryTimeOrder(banExpiryTimeOrder);
        }
        if (banExpiryDate != null) {
            criteria.setBanExpiryDate(banExpiryDateValid.getDate());
            if (banExpiryDateValid.getDate() != null) {
                criteria.setBanExpiryTime(banExpiryDateValid.getDate().getTime());
            }
        }
        //table [senpure_account][column = login_time] order
        if (loginTimeOrder != null) {
            criteria.setLoginTimeOrder(loginTimeOrder);
        }
        //本次登录时间
        if (loginDate != null) {
            criteria.setLoginDate(loginDateValid.getDate());
            if (loginDateValid.getDate() != null) {
                criteria.setLoginTime(loginDateValid.getDate().getTime());
            }
        }
        if (loginType != null) {
            criteria.setLoginType(loginType);
        }
        //table [senpure_account][column = login_type] order
        if (loginTypeOrder != null) {
            criteria.setLoginTypeOrder(loginTypeOrder);
        }
        if (online != null) {
            criteria.setOnline(Boolean.valueOf(online));
        }
        if (accountState != null) {
            criteria.setAccountState(accountState);
        }
        if (client != null) {
            criteria.setClient(client);
        }
        if (clientVersion != null) {
            criteria.setClientVersion(clientVersion);
        }
        if (clientVersionNumber != null) {
            criteria.setClientVersionNumber(Integer.valueOf(clientVersionNumber));
        }
        //table [senpure_account][column = last_login_time] order
        if (lastLoginTimeOrder != null) {
            criteria.setLastLoginTimeOrder(lastLoginTimeOrder);
        }
        if (lastLoginDate != null) {
            criteria.setLastLoginDate(lastLoginDateValid.getDate());
            if (lastLoginDateValid.getDate() != null) {
                criteria.setLastLoginTime(lastLoginDateValid.getDate().getTime());
            }
        }
        //table [senpure_account][column = last_logout_time] order
        if (lastLogoutTimeOrder != null) {
            criteria.setLastLogoutTimeOrder(lastLogoutTimeOrder);
        }
        if (lastLogoutDate != null) {
            criteria.setLastLogoutDate(lastLogoutDateValid.getDate());
            if (lastLogoutDateValid.getDate() != null) {
                criteria.setLastLogoutTime(lastLogoutDateValid.getDate().getTime());
            }
        }
        if (lastLoginType != null) {
            criteria.setLastLoginType(lastLoginType);
        }
        //table [senpure_account][column = last_login_type] order
        if (lastLoginTypeOrder != null) {
            criteria.setLastLoginTypeOrder(lastLoginTypeOrder);
        }
        if (lastLogoutType != null) {
            criteria.setLastLogoutType(lastLogoutType);
        }
        //table [senpure_account][column = last_logout_type] order
        if (lastLogoutTypeOrder != null) {
            criteria.setLastLogoutTypeOrder(lastLogoutTypeOrder);
        }
        if (lastLoginIp != null) {
            criteria.setLastLoginIp(lastLoginIp);
        }
        if (lastLoginIpNumber != null) {
            criteria.setLastLoginIpNumber(Long.valueOf(lastLoginIpNumber));
        }
        if (lastLoginSource != null) {
            criteria.setLastLoginSource(lastLoginSource);
        }
        //外键,modelName:Container,tableName:senpure_container
        if (containerId != null) {
            criteria.setContainerId(Integer.valueOf(containerId));
        }
        //table [senpure_account][column = container_id] order
        if (containerIdOrder != null) {
            criteria.setContainerIdOrder(containerIdOrder);
        }
        return criteria;
    }

    @Override
    public void setDatePattern(String datePattern) {
        super.setDatePattern(datePattern);
        createDateValid.setPattern(datePattern);
        banExpiryDateValid.setPattern(datePattern);
        loginDateValid.setPattern(datePattern);
        lastLoginDateValid.setPattern(datePattern);
        lastLogoutDateValid.setPattern(datePattern);
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("AccountCriteriaStr{");
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
        if (banExpiryDate != null) {
            sb.append("banExpiryDate=").append(banExpiryDate).append(",");
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
        if (lastLoginDate != null) {
            sb.append("lastLoginDate=").append(lastLoginDate).append(",");
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

    @Override
    protected void afterStr(StringBuilder sb) {
        if (accountOrder != null) {
            sb.append("accountOrder=").append(accountOrder).append(",");
        }
        if (nameOrder != null) {
            sb.append("nameOrder=").append(nameOrder).append(",");
        }
        if (createTimeOrder != null) {
            sb.append("createTimeOrder=").append(createTimeOrder).append(",");
        }
        if (banExpiryTimeOrder != null) {
            sb.append("banExpiryTimeOrder=").append(banExpiryTimeOrder).append(",");
        }
        if (loginTimeOrder != null) {
            sb.append("loginTimeOrder=").append(loginTimeOrder).append(",");
        }
        if (loginTypeOrder != null) {
            sb.append("loginTypeOrder=").append(loginTypeOrder).append(",");
        }
        if (lastLoginTimeOrder != null) {
            sb.append("lastLoginTimeOrder=").append(lastLoginTimeOrder).append(",");
        }
        if (lastLogoutTimeOrder != null) {
            sb.append("lastLogoutTimeOrder=").append(lastLogoutTimeOrder).append(",");
        }
        if (lastLoginTypeOrder != null) {
            sb.append("lastLoginTypeOrder=").append(lastLoginTypeOrder).append(",");
        }
        if (lastLogoutTypeOrder != null) {
            sb.append("lastLogoutTypeOrder=").append(lastLogoutTypeOrder).append(",");
        }
        if (containerIdOrder != null) {
            sb.append("containerIdOrder=").append(containerIdOrder).append(",");
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
    public AccountCriteriaStr setId(String id) {
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
    public AccountCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
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


    public AccountCriteriaStr setAccount(String account) {
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
    public AccountCriteriaStr setAccountOrder(String accountOrder) {
        if (accountOrder != null && accountOrder.trim().length() == 0) {
            return this;
        }
        this.accountOrder = accountOrder;
        return this;
    }


    public String getPassword() {
        return password;
    }


    public AccountCriteriaStr setPassword(String password) {
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


    public AccountCriteriaStr setName(String name) {
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
    public AccountCriteriaStr setNameOrder(String nameOrder) {
        if (nameOrder != null && nameOrder.trim().length() == 0) {
            return this;
        }
        this.nameOrder = nameOrder;
        return this;
    }


    /**
     * get table [senpure_account][column = create_time] order
     *
     * @return
     */
    public String getCreateTimeOrder() {
        return createTimeOrder;
    }

    /**
     * set table [senpure_account][column = create_time] order DESC||ASC
     *
     * @return
     */
    public AccountCriteriaStr setCreateTimeOrder(String createTimeOrder) {
        if (createTimeOrder != null && createTimeOrder.trim().length() == 0) {
            return this;
        }
        this.createTimeOrder = createTimeOrder;
        return this;
    }


    public String getCreateDate() {
        return createDate;
    }


    public AccountCriteriaStr setCreateDate(String createDate) {
        if (createDate != null && createDate.trim().length() == 0) {
            return this;
        }
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
    public AccountCriteriaStr setIp(String ip) {
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
    public String getIpNumber() {
        return ipNumber;
    }

    /**
     * set 数字IP，只存一个最接近真实IP的数据
     *
     * @return
     */
    public AccountCriteriaStr setIpNumber(String ipNumber) {
        if (ipNumber != null && ipNumber.trim().length() == 0) {
            return this;
        }
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
    public AccountCriteriaStr setSource(String source) {
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
    public AccountCriteriaStr setBanStr(String banStr) {
        if (banStr != null && banStr.trim().length() == 0) {
            return this;
        }
        this.banStr = banStr;
        return this;
    }


    /**
     * get table [senpure_account][column = ban_expiry_time] order
     *
     * @return
     */
    public String getBanExpiryTimeOrder() {
        return banExpiryTimeOrder;
    }

    /**
     * set table [senpure_account][column = ban_expiry_time] order DESC||ASC
     *
     * @return
     */
    public AccountCriteriaStr setBanExpiryTimeOrder(String banExpiryTimeOrder) {
        if (banExpiryTimeOrder != null && banExpiryTimeOrder.trim().length() == 0) {
            return this;
        }
        this.banExpiryTimeOrder = banExpiryTimeOrder;
        return this;
    }


    public String getBanExpiryDate() {
        return banExpiryDate;
    }


    public AccountCriteriaStr setBanExpiryDate(String banExpiryDate) {
        if (banExpiryDate != null && banExpiryDate.trim().length() == 0) {
            return this;
        }
        this.banExpiryDate = banExpiryDate;
        return this;
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
     * set table [senpure_account][column = login_time] order DESC||ASC
     *
     * @return
     */
    public AccountCriteriaStr setLoginTimeOrder(String loginTimeOrder) {
        if (loginTimeOrder != null && loginTimeOrder.trim().length() == 0) {
            return this;
        }
        this.loginTimeOrder = loginTimeOrder;
        return this;
    }


    /**
     * get 本次登录时间
     *
     * @return
     */
    public String getLoginDate() {
        return loginDate;
    }

    /**
     * set 本次登录时间
     *
     * @return
     */
    public AccountCriteriaStr setLoginDate(String loginDate) {
        if (loginDate != null && loginDate.trim().length() == 0) {
            return this;
        }
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


    public AccountCriteriaStr setLoginType(String loginType) {
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
    public AccountCriteriaStr setLoginTypeOrder(String loginTypeOrder) {
        if (loginTypeOrder != null && loginTypeOrder.trim().length() == 0) {
            return this;
        }
        this.loginTypeOrder = loginTypeOrder;
        return this;
    }


    public String getOnline() {
        return online;
    }


    public AccountCriteriaStr setOnline(String online) {
        if (online != null && online.trim().length() == 0) {
            return this;
        }
        this.online = online;
        return this;
    }


    public String getAccountState() {
        return accountState;
    }


    public AccountCriteriaStr setAccountState(String accountState) {
        if (accountState != null && accountState.trim().length() == 0) {
            return this;
        }
        this.accountState = accountState;
        return this;
    }


    public String getClient() {
        return client;
    }


    public AccountCriteriaStr setClient(String client) {
        if (client != null && client.trim().length() == 0) {
            return this;
        }
        this.client = client;
        return this;
    }


    public String getClientVersion() {
        return clientVersion;
    }


    public AccountCriteriaStr setClientVersion(String clientVersion) {
        if (clientVersion != null && clientVersion.trim().length() == 0) {
            return this;
        }
        this.clientVersion = clientVersion;
        return this;
    }


    public String getClientVersionNumber() {
        return clientVersionNumber;
    }


    public AccountCriteriaStr setClientVersionNumber(String clientVersionNumber) {
        if (clientVersionNumber != null && clientVersionNumber.trim().length() == 0) {
            return this;
        }
        this.clientVersionNumber = clientVersionNumber;
        return this;
    }


    /**
     * get table [senpure_account][column = last_login_time] order
     *
     * @return
     */
    public String getLastLoginTimeOrder() {
        return lastLoginTimeOrder;
    }

    /**
     * set table [senpure_account][column = last_login_time] order DESC||ASC
     *
     * @return
     */
    public AccountCriteriaStr setLastLoginTimeOrder(String lastLoginTimeOrder) {
        if (lastLoginTimeOrder != null && lastLoginTimeOrder.trim().length() == 0) {
            return this;
        }
        this.lastLoginTimeOrder = lastLoginTimeOrder;
        return this;
    }


    public String getLastLoginDate() {
        return lastLoginDate;
    }


    public AccountCriteriaStr setLastLoginDate(String lastLoginDate) {
        if (lastLoginDate != null && lastLoginDate.trim().length() == 0) {
            return this;
        }
        this.lastLoginDate = lastLoginDate;
        return this;
    }


    /**
     * get table [senpure_account][column = last_logout_time] order
     *
     * @return
     */
    public String getLastLogoutTimeOrder() {
        return lastLogoutTimeOrder;
    }

    /**
     * set table [senpure_account][column = last_logout_time] order DESC||ASC
     *
     * @return
     */
    public AccountCriteriaStr setLastLogoutTimeOrder(String lastLogoutTimeOrder) {
        if (lastLogoutTimeOrder != null && lastLogoutTimeOrder.trim().length() == 0) {
            return this;
        }
        this.lastLogoutTimeOrder = lastLogoutTimeOrder;
        return this;
    }


    public String getLastLogoutDate() {
        return lastLogoutDate;
    }


    public AccountCriteriaStr setLastLogoutDate(String lastLogoutDate) {
        if (lastLogoutDate != null && lastLogoutDate.trim().length() == 0) {
            return this;
        }
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


    public AccountCriteriaStr setLastLoginType(String lastLoginType) {
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
    public AccountCriteriaStr setLastLoginTypeOrder(String lastLoginTypeOrder) {
        if (lastLoginTypeOrder != null && lastLoginTypeOrder.trim().length() == 0) {
            return this;
        }
        this.lastLoginTypeOrder = lastLoginTypeOrder;
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


    public AccountCriteriaStr setLastLogoutType(String lastLogoutType) {
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
    public AccountCriteriaStr setLastLogoutTypeOrder(String lastLogoutTypeOrder) {
        if (lastLogoutTypeOrder != null && lastLogoutTypeOrder.trim().length() == 0) {
            return this;
        }
        this.lastLogoutTypeOrder = lastLogoutTypeOrder;
        return this;
    }


    public String getLastLoginIp() {
        return lastLoginIp;
    }


    public AccountCriteriaStr setLastLoginIp(String lastLoginIp) {
        if (lastLoginIp != null && lastLoginIp.trim().length() == 0) {
            return this;
        }
        this.lastLoginIp = lastLoginIp;
        return this;
    }


    public String getLastLoginIpNumber() {
        return lastLoginIpNumber;
    }


    public AccountCriteriaStr setLastLoginIpNumber(String lastLoginIpNumber) {
        if (lastLoginIpNumber != null && lastLoginIpNumber.trim().length() == 0) {
            return this;
        }
        this.lastLoginIpNumber = lastLoginIpNumber;
        return this;
    }


    public String getLastLoginSource() {
        return lastLoginSource;
    }


    public AccountCriteriaStr setLastLoginSource(String lastLoginSource) {
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
    public String getContainerId() {
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
    public AccountCriteriaStr setContainerId(String containerId) {
        if (containerId != null && containerId.trim().length() == 0) {
            return this;
        }
        this.containerId = containerId;
        return this;
    }

    /**
     * set table [senpure_account][column = container_id] order DESC||ASC
     *
     * @return
     */
    public AccountCriteriaStr setContainerIdOrder(String containerIdOrder) {
        if (containerIdOrder != null && containerIdOrder.trim().length() == 0) {
            return this;
        }
        this.containerIdOrder = containerIdOrder;
        return this;
    }


}