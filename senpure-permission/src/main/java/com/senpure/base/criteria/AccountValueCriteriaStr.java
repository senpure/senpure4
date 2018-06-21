package com.senpure.base.criteria;

import com.senpure.base.criterion.CriteriaStr;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:20
 */
public class AccountValueCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 846733638L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    private String key;
    //table [senpure_account_value][column = account_key] order
    private String keyOrder;
    private String value;
    private String description;
    //外键,modelName:Account,tableName:senpure_account
    private String accountId;
    //table [senpure_account_value][column = account_id] order
    private String accountIdOrder;

    public AccountValueCriteria toAccountValueCriteria() {
        AccountValueCriteria criteria = new AccountValueCriteria();
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
        if (key != null) {
            criteria.setKey(key);
        }
        //table [senpure_account_value][column = account_key] order
        if (keyOrder != null) {
            criteria.setKeyOrder(keyOrder);
        }
        if (value != null) {
            criteria.setValue(value);
        }
        if (description != null) {
            criteria.setDescription(description);
        }
        //外键,modelName:Account,tableName:senpure_account
        if (accountId != null) {
            criteria.setAccountId(Long.valueOf(accountId));
        }
        //table [senpure_account_value][column = account_id] order
        if (accountIdOrder != null) {
            criteria.setAccountIdOrder(accountIdOrder);
        }
        return criteria;
    }


    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("AccountValueCriteriaStr{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (key != null) {
            sb.append("key=").append(key).append(",");
        }
        if (value != null) {
            sb.append("value=").append(value).append(",");
        }
        if (description != null) {
            sb.append("description=").append(description).append(",");
        }
        if (accountId != null) {
            sb.append("accountId=").append(accountId).append(",");
        }
    }

    @Override
    protected void afterStr(StringBuilder sb) {
        if (keyOrder != null) {
            sb.append("keyOrder=").append(keyOrder).append(",");
        }
        if (accountIdOrder != null) {
            sb.append("accountIdOrder=").append(accountIdOrder).append(",");
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
    public AccountValueCriteriaStr setId(String id) {
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
    public AccountValueCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
        return this;
    }


    public String getKey() {
        return key;
    }

    /**
     * get table [senpure_account_value][column = account_key] order
     *
     * @return
     */
    public String getKeyOrder() {
        return keyOrder;
    }


    public AccountValueCriteriaStr setKey(String key) {
        if (key != null && key.trim().length() == 0) {
            return this;
        }
        this.key = key;
        return this;
    }

    /**
     * set table [senpure_account_value][column = account_key] order DESC||ASC
     *
     * @return
     */
    public AccountValueCriteriaStr setKeyOrder(String keyOrder) {
        if (keyOrder != null && keyOrder.trim().length() == 0) {
            return this;
        }
        this.keyOrder = keyOrder;
        return this;
    }


    public String getValue() {
        return value;
    }


    public AccountValueCriteriaStr setValue(String value) {
        if (value != null && value.trim().length() == 0) {
            return this;
        }
        this.value = value;
        return this;
    }


    public String getDescription() {
        return description;
    }


    public AccountValueCriteriaStr setDescription(String description) {
        if (description != null && description.trim().length() == 0) {
            return this;
        }
        this.description = description;
        return this;
    }


    /**
     * get 外键,modelName:Account,tableName:senpure_account
     *
     * @return
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * get table [senpure_account_value][column = account_id] order
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
    public AccountValueCriteriaStr setAccountId(String accountId) {
        if (accountId != null && accountId.trim().length() == 0) {
            return this;
        }
        this.accountId = accountId;
        return this;
    }

    /**
     * set table [senpure_account_value][column = account_id] order DESC||ASC
     *
     * @return
     */
    public AccountValueCriteriaStr setAccountIdOrder(String accountIdOrder) {
        if (accountIdOrder != null && accountIdOrder.trim().length() == 0) {
            return this;
        }
        this.accountIdOrder = accountIdOrder;
        return this;
    }


}