package com.senpure.base.entity;

import com.senpure.base.PermissionConstant;

import javax.persistence.*;

/**
 * Created by DZ on 2016-06-28 13:58
 */
@Entity
@Table(name = PermissionConstant.DATA_BASE_PREFIX+ "_ACCOUNT_VALUE")
public class AccountValue extends LongAndVersionEntity{
    private static final long serialVersionUID = 1068563978298321852L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;
    @Column(name = "accountKey",length = 64,nullable = false)
    private String key;
    @Column(name = "accountValue",length = 128,nullable = false)
    private String value;
    private String description;
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
