package com.senpure.base.entity;

import com.senpure.base.PermissionConstant;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = PermissionConstant.DATA_BASE_PREFIX + "_ACCOUNT_ROLE")
public class AccountRole extends LongAndVersionEntity {

    /**
     *
     */
    private static final long serialVersionUID = -1594909852545869189L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId")
    private Role role;
    private Long expiryTime;
    private Date expiryDate;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
