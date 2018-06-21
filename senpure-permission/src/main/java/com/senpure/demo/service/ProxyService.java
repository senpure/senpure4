package com.senpure.demo.service;

import com.senpure.demo.model.Proxy;
import com.senpure.demo.criteria.ProxyCriteria;
import com.senpure.demo.mapper.ProxyMapper;
import com.senpure.base.exception.OptimisticLockingFailureException;
import com.senpure.base.service.BaseService;
import com.senpure.base.result.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 该类对 [Proxy]的增删查改有本地缓存,只缓存主键，不缓存查询缓存
 * <li>find(Long id):按主键缓存</li>
 * <li>findAll():按主键缓存</li>
 * <li>delete(Long id):按主键清除缓存</li>
 * <li>delete(ProxyCriteria criteria):清除<strong>所有</strong>Proxy缓存</li>
 * <li>update(Proxy proxy):按主键清除缓存</li>
 * <li>update(ProxyCriteria criteria):有主键时按主键移除缓存，没有主键时清除<strong>所有</strong>Proxy缓存 </li>
 *
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
@Service
public class ProxyService extends BaseService {

    private ConcurrentMap<String, Proxy> localCache = new ConcurrentHashMap(128);
    @Autowired
    private ProxyMapper proxyMapper;

    private String cacheKey(Long id) {
        return "proxy:" + id;
    }

    public void clearCache(Long id) {
        localCache.remove(cacheKey(id));
    }

    public void clearCache() {
        localCache.clear();
    }

    /**
     * 按主键本地缓存
     *
     * @return
     */
    public Proxy find(Long id) {
        String cacheKey = cacheKey(id);
        Proxy proxy = localCache.get(cacheKey);
        if (proxy == null) {
            proxy = proxyMapper.find(id);
            if (proxy != null) {
                localCache.putIfAbsent(cacheKey, proxy);
                return localCache.get(cacheKey);
            }
        }
        return proxy;
    }

    public Proxy findOnlyCache(Long id) {
        return localCache.get(cacheKey(id));
    }

    public Proxy findSkipCache(Long id) {
        return proxyMapper.find(id);
    }

    public int count() {
        return proxyMapper.count();
    }

    /**
     * 每一个结果会按主键本地缓存
     *
     * @return
     */
    public List<Proxy> findAll() {
        List<Proxy> proxies = proxyMapper.findAll();
        for (Proxy proxy : proxies) {
            localCache.put(cacheKey(proxy.getId()), proxy);
        }
        return proxies;
    }

    /**
     * 按主键清除本地缓存
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        localCache.remove(cacheKey(id));
        int result = proxyMapper.delete(id);
        return result == 1;
    }

    /**
     * 会清除<strong>所有<strong>Proxy缓存
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int delete(ProxyCriteria criteria) {
        int result = proxyMapper.deleteByCriteria(criteria);
        if (result > 0) {
            clearCache();
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Proxy proxy) {
        proxy.setId(idGenerator.nextId());
        int result = proxyMapper.save(proxy);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(List<Proxy> proxies) {
        if (proxies == null || proxies.size() == 0) {
            return 0;
        }
        for (Proxy proxy : proxies) {
            proxy.setId(idGenerator.nextId());
        }
        int size = proxyMapper.saveBatch(proxies);
        return size;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(ProxyCriteria criteria) {
        criteria.setId(idGenerator.nextId());
        int result = proxyMapper.save(criteria.toProxy());
        return result == 1;
    }

    /**
     * 按主键移除缓存<br>
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Proxy proxy) {
        localCache.remove(cacheKey(proxy.getId()));
        int updateCount = proxyMapper.update(proxy);
        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(proxy.getClass() + ",[" + proxy.getId() + "],版本号冲突,版本号[" + proxy.getVersion() + "]");
        }
        return true;
    }

    /**
     * 有主键时按主键移除缓存，没有主键时清空<strong>所有</strong>Proxy缓存<br>
     * 当版本号，和主键不为空时，更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int update(ProxyCriteria criteria) {
        if (criteria.getId() != null) {
            localCache.remove(cacheKey(criteria.getId()));
        }
        int updateCount = proxyMapper.updateByCriteria(criteria);
        if (updateCount == 0 && criteria.getVersion() != null
                && criteria.getId() != null) {
            throw new OptimisticLockingFailureException(criteria.getClass() + ",[" + criteria.getId() + "],版本号冲突,版本号[" + criteria.getVersion() + "]");
        }
        if (criteria.getId() != null && updateCount > 0) {
            localCache.clear();
        }
        return updateCount;
    }

    @Transactional(readOnly = true)
    public ResultMap findPage(ProxyCriteria criteria) {
        ResultMap resultMap = ResultMap.success();
        //是否是主键查找
        if (criteria.getId() != null) {
            Proxy proxy = proxyMapper.find(criteria.getId());
            if (proxy != null) {
                List<Proxy> proxies = new ArrayList<>(16);
                proxies.add(proxy);
                resultMap.putTotal(1);
                resultMap.putItems(proxies);
            } else {
                resultMap.putTotal(0);
            }
            return resultMap;
        }
        int total = proxyMapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<Proxy> proxies = proxyMapper.findByCriteria(criteria);
        resultMap.putItems(proxies);
        return resultMap;
    }

    public List<Proxy> find(ProxyCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            List<Proxy> proxies = new ArrayList<>(16);
            Proxy proxy = proxyMapper.find(criteria.getId());
            if (proxy != null) {
                proxies.add(proxy);
            }
            return proxies;
        }
        return proxyMapper.findByCriteria(criteria);
    }

    public Proxy findOne(ProxyCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            return proxyMapper.find(criteria.getId());
        }
        List<Proxy> proxies = proxyMapper.findByCriteria(criteria);
        if (proxies.size() == 0) {
            return null;
        }
        return proxies.get(0);
    }

}