package com.senpure.base.service;

import com.senpure.base.PermissionConstant;
import com.senpure.base.criteria.AccountCriteria;
import com.senpure.base.exception.OptimisticLockingFailureException;
import com.senpure.base.mapper.AccountMapper;
import com.senpure.base.model.Account;
import com.senpure.base.result.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author senpure-generate
 * @version 2017-12-22 16:02:19
 */
@Service
@CacheConfig(cacheNames = "account")
public class AccountService extends BaseService {

    @Autowired
    private AccountMapper accountMapper;

    @CacheEvict(key = "#id")
    public void clearCache(Long id) {
    }

    @CacheEvict(allEntries = true)
    public void clearCache() {
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public Account find(Long id) {
        return accountMapper.find(id);
    }

    public Account findSkipCache(Long id) {
        return accountMapper.find(id);
    }

    public int count() {
        return accountMapper.count();
    }

    public List<Account> findAll() {
        return accountMapper.findAll();
    }

    @Transactional
    @CacheEvict(key = "#id")
    public boolean delete(Long id) {
        int result = accountMapper.delete(id);
        return result == 1;
    }

    @Transactional
    @CacheEvict(key = "#criteria.id", allEntries = true)
    public int delete(AccountCriteria criteria) {
        return accountMapper.deleteByCriteria(criteria);
    }

    @Transactional
    public boolean save(Account account) {
        account.setId(idGenerator.nextId());
        int result = accountMapper.save(account);
        return result == 1;
    }

    @Transactional
    public int save(List<Account> accounts) {
        if (accounts == null || accounts.size() == 0) {
            return 0;
        }
        for (Account account : accounts) {
            account.setId(idGenerator.nextId());
        }
        return accountMapper.saveBatch(accounts);
    }

    @Transactional
    public boolean save(AccountCriteria criteria) {
        criteria.setId(idGenerator.nextId());
        int result = accountMapper.save(criteria.toAccount());
        return result == 1;
    }

    /**
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional
    @CacheEvict(key = "#account.id")
    public boolean update(Account account) {
        int updateCount = accountMapper.update(account);
        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(account.getClass() + ",[" + account.getId() + "],版本号冲突");
        }
        return true;
    }

    /**
     * 当版本号，和主键不为空时，更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional
    @CacheEvict(key = "#criteria.id", allEntries = true)
    public int update(AccountCriteria criteria) {
        int updateCount = accountMapper.updateByCriteria(criteria);
        if (updateCount == 0 && criteria.getVersion() != null
                && criteria.getId() != null) {
            throw new OptimisticLockingFailureException(criteria.getClass() + ",[" + criteria.getId() + "],版本号冲突");
        }
        return updateCount;
    }

    public ResultMap findPage(AccountCriteria criteria) {
        ResultMap resultMap = ResultMap.success();
        int total = accountMapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<Account> accounts = accountMapper.findByCriteria(criteria);
        resultMap.putItems(accounts);
        return resultMap;
    }

    public List<Account> find(AccountCriteria criteria) {
        List<Account> accounts = accountMapper.findByCriteria(criteria);
        return accounts;
    }

    public Account findOne(AccountCriteria criteria) {
        List<Account> accounts = accountMapper.findByCriteria(criteria);
        if (accounts.size() == 0) {
            return null;
        }
        return accounts.get(0);
    }

    public Account findByAccount(String account) {
        AccountCriteria criteria = new AccountCriteria();
        criteria.setUsePage(false);
        criteria.setAccount(account);
        List<Account> accounts = accountMapper.findByCriteria(criteria);
        if (accounts.size() == 0) {
            return null;
        }
        return accounts.get(0);
    }

    public Account findByName(String name) {
        AccountCriteria criteria = new AccountCriteria();
        criteria.setUsePage(false);
        criteria.setName(name);
        List<Account> accounts = accountMapper.findByCriteria(criteria);
        if (accounts.size() == 0) {
            return null;
        }
        return accounts.get(0);
    }

    public List<Account> findByLoginType(String loginType) {
        AccountCriteria criteria = new AccountCriteria();
        criteria.setUsePage(false);
        criteria.setLoginType(loginType);
        List<Account> accounts = accountMapper.findByCriteria(criteria);
        return accounts;
    }

    public List<Account> findByLastLoginType(String lastLoginType) {
        AccountCriteria criteria = new AccountCriteria();
        criteria.setUsePage(false);
        criteria.setLastLoginType(lastLoginType);
        List<Account> accounts = accountMapper.findByCriteria(criteria);
        return accounts;
    }

    public List<Account> findByLastLogoutType(String lastLogoutType) {
        AccountCriteria criteria = new AccountCriteria();
        criteria.setUsePage(false);
        criteria.setLastLogoutType(lastLogoutType);
        List<Account> accounts = accountMapper.findByCriteria(criteria);
        return accounts;
    }

    public List<Account> findByContainerId(Integer containerId) {
        AccountCriteria criteria = new AccountCriteria();
        criteria.setUsePage(false);
        criteria.setContainerId(containerId);
        List<Account> accounts = accountMapper.findByCriteria(criteria);
        return accounts;
    }

    public Account defaultAccount(long createTime)
    {

        Account account=new Account();

        return defaultAccount(createTime,account);
    }

    public Account defaultAccount(long createTime,  Account account)
    {
        account.setVersion(0);
        account.setCreateDate(new Date(createTime));
        account.setCreateTime(createTime);
        account.setOnline(false);
        account.setAccountState(PermissionConstant.ACCOUNT_STATE_NORMAL);
        return account;
    }

}