package com.senpure.base.service;

import com.senpure.base.model.AccountValue;
import com.senpure.base.criteria.AccountValueCriteria;
import com.senpure.base.mapper.AccountValueMapper;
import com.senpure.base.exception.OptimisticLockingFailureException;
import com.senpure.base.result.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:20
 */
@Service
@CacheConfig(cacheNames = "accountValue")
public class AccountValueService extends BaseService {

    @Autowired
    private AccountValueMapper accountValueMapper;

    @CacheEvict(key = "#id")
    public void clearCache(Long id) {
    }

    @CacheEvict(allEntries = true)
    public void clearCache() {
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public AccountValue find(Long id) {
        return accountValueMapper.find(id);
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public AccountValue findOnlyCache(Long id) {
        return null;
    }

    @CachePut(key = "#id", unless = "#result == null")
    public AccountValue findSkipCache(Long id) {
        return accountValueMapper.find(id);
    }

    public int count() {
        return accountValueMapper.count();
    }

    public List<AccountValue> findAll() {
        return accountValueMapper.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#id")
    public boolean delete(Long id) {
        int result = accountValueMapper.delete(id);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#criteria.id", allEntries = true)
    public int delete(AccountValueCriteria criteria) {
        return accountValueMapper.deleteByCriteria(criteria);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(AccountValue accountValue) {
        accountValue.setId(idGenerator.nextId());
        int result = accountValueMapper.save(accountValue);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(List<AccountValue> accountValues) {
        if (accountValues == null || accountValues.size() == 0) {
            return 0;
        }
        for (AccountValue accountValue : accountValues) {
            accountValue.setId(idGenerator.nextId());
        }
        return accountValueMapper.saveBatch(accountValues);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(AccountValueCriteria criteria) {
        criteria.setId(idGenerator.nextId());
        int result = accountValueMapper.save(criteria.toAccountValue());
        return result == 1;
    }

    /**
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#accountValue.id")
    public boolean update(AccountValue accountValue) {
        int updateCount = accountValueMapper.update(accountValue);
        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(accountValue.getClass() + ",[" + accountValue.getId() + "],版本号冲突,版本号[" + accountValue.getVersion() + "]");
        }
        return true;
    }

    /**
     * 当版本号，和主键不为空时，更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#criteria.id", allEntries = true)
    public int update(AccountValueCriteria criteria) {
        int updateCount = accountValueMapper.updateByCriteria(criteria);
        if (updateCount == 0 && criteria.getVersion() != null
                && criteria.getId() != null) {
            throw new OptimisticLockingFailureException(criteria.getClass() + ",[" + criteria.getId() + "],版本号冲突,版本号[" + criteria.getVersion() + "]");
        }
        return updateCount;
    }

    @Transactional(readOnly = true)
    public ResultMap findPage(AccountValueCriteria criteria) {
        ResultMap resultMap = ResultMap.success();
        //是否是主键查找
        if (criteria.getId() != null) {
            AccountValue accountValue = accountValueMapper.find(criteria.getId());
            if (accountValue != null) {
                List<AccountValue> accountValues = new ArrayList<>(16);
                accountValues.add(accountValue);
                resultMap.putTotal(1);
                resultMap.putItems(accountValues);
            } else {
                resultMap.putTotal(0);
            }
            return resultMap;
        }
        int total = accountValueMapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<AccountValue> accountValues = accountValueMapper.findByCriteria(criteria);
        resultMap.putItems(accountValues);
        return resultMap;
    }

    public List<AccountValue> find(AccountValueCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            List<AccountValue> accountValues = new ArrayList<>(16);
            AccountValue accountValue = accountValueMapper.find(criteria.getId());
            if (accountValue != null) {
                accountValues.add(accountValue);
            }
            return accountValues;
        }
        return accountValueMapper.findByCriteria(criteria);
    }

    public AccountValue findOne(AccountValueCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            return accountValueMapper.find(criteria.getId());
        }
        List<AccountValue> accountValues = accountValueMapper.findByCriteria(criteria);
        if (accountValues.size() == 0) {
            return null;
        }
        return accountValues.get(0);
    }

    public List<AccountValue> findByAccountId(Long accountId) {
        AccountValueCriteria criteria = new AccountValueCriteria();
        criteria.setUsePage(false);
        criteria.setAccountId(accountId);
        List<AccountValue> accountValues = accountValueMapper.findByCriteria(criteria);
        return accountValues;
    }

    public AccountValue findByKey(String key) {
        AccountValueCriteria criteria = new AccountValueCriteria();
        criteria.setUsePage(false);
        criteria.setKey(key);
        List<AccountValue> accountValues = accountValueMapper.findByCriteria(criteria);
        if (accountValues.size() == 0) {
            return null;
        }
        return accountValues.get(0);
    }

}