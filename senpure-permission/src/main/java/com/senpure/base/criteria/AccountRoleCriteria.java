package com.senpure.base.criteria;

import com.senpure.base.model.AccountRole;
import com.senpure.base.criterion.Criteria;

import java.io.Serializable;
import java.util.Date;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class AccountRoleCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 220887430L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    private Long expiryTime;
    //table [senpure_account_role][column = expiry_time] order
    private String expiryTimeOrder;
    private Date expiryDate;
    //外键,modelName:Account,tableName:senpure_account
    private Long accountId;
    //table [senpure_account_role][column = account_id] order
    private String accountIdOrder;
    //外键,modelName:Role,tableName:senpure_role
    private Long roleId;
    //table [senpure_account_role][column = role_id] order
    private String roleIdOrder;

    public static AccountRole toAccountRole(AccountRoleCriteria criteria, AccountRole accountRole) {
        accountRole.setId(criteria.getId());
        accountRole.setExpiryTime(criteria.getExpiryTime());
        accountRole.setExpiryDate(criteria.getExpiryDate());
        accountRole.setAccountId(criteria.getAccountId());
        accountRole.setRoleId(criteria.getRoleId());
        accountRole.setVersion(criteria.getVersion());
        return accountRole;
    }

    public AccountRole toAccountRole() {
        AccountRole accountRole = new AccountRole();
        return toAccountRole(this, accountRole);
    }

    /**
     * 将AccountRoleCriteria 的有效值(不为空),赋值给 AccountRole
     *
     * @return AccountRole
     */
    public AccountRole effective(AccountRole accountRole) {
        if (getId() != null) {
            accountRole.setId(getId());
        }
        if (getExpiryTime() != null) {
            accountRole.setExpiryTime(getExpiryTime());
        }
        if (getExpiryDate() != null) {
            accountRole.setExpiryDate(getExpiryDate());
        }
        if (getAccountId() != null) {
            accountRole.setAccountId(getAccountId());
        }
        if (getRoleId() != null) {
            accountRole.setRoleId(getRoleId());
        }
        if (getVersion() != null) {
            accountRole.setVersion(getVersion());
        }
        return accountRole;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("AccountRoleCriteria{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (expiryTime != null) {
            sb.append("expiryTime=").append(expiryTime).append(",");
        }
        if (expiryDate != null) {
            sb.append("expiryDate=").append(expiryDate).append(",");
        }
        if (accountId != null) {
            sb.append("accountId=").append(accountId).append(",");
        }
        if (roleId != null) {
            sb.append("roleId=").append(roleId).append(",");
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
    public AccountRoleCriteria setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    /**
     * get table [senpure_account_role][column = expiry_time] order
     *
     * @return
     */
    public String getExpiryTimeOrder() {
        return expiryTimeOrder;
    }


    public AccountRoleCriteria setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
        return this;
    }

    /**
     * set table [senpure_account_role][column = expiry_time] order DESC||ASC
     *
     * @return
     */
    public AccountRoleCriteria setExpiryTimeOrder(String expiryTimeOrder) {
        this.expiryTimeOrder = expiryTimeOrder;
        putSort("expiry_time", expiryTimeOrder);
        return this;
    }


    public Date getExpiryDate() {
        return expiryDate;
    }


    public AccountRoleCriteria setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }


    /**
     * get 外键,modelName:Account,tableName:senpure_account
     *
     * @return
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * get table [senpure_account_role][column = account_id] order
     *
     * @return
     */
    public String getAccountIdOrder() {
        return accountIdOrder;
    }

    /**
     * set 外键,modelName:Account,tableName:senpure_account
     *
     * @return
     */
    public AccountRoleCriteria setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    /**
     * set table [senpure_account_role][column = account_id] order DESC||ASC
     *
     * @return
     */
    public AccountRoleCriteria setAccountIdOrder(String accountIdOrder) {
        this.accountIdOrder = accountIdOrder;
        putSort("account_id", accountIdOrder);
        return this;
    }


    /**
     * get 外键,modelName:Role,tableName:senpure_role
     *
     * @return
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * get table [senpure_account_role][column = role_id] order
     *
     * @return
     */
    public String getRoleIdOrder() {
        return roleIdOrder;
    }

    /**
     * set 外键,modelName:Role,tableName:senpure_role
     *
     * @return
     */
    public AccountRoleCriteria setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    /**
     * set table [senpure_account_role][column = role_id] order DESC||ASC
     *
     * @return
     */
    public AccountRoleCriteria setRoleIdOrder(String roleIdOrder) {
        this.roleIdOrder = roleIdOrder;
        putSort("role_id", roleIdOrder);
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
    public AccountRoleCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}