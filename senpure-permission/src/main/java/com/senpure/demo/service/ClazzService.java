package com.senpure.demo.service;

import com.senpure.demo.model.Clazz;
import com.senpure.demo.criteria.ClazzCriteria;
import com.senpure.demo.mapper.ClazzMapper;
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
 * 该类对 [Clazz]的增删查改有本地缓存,只缓存主键，不缓存查询缓存
 * <li>find(Long id):按主键缓存</li>
 * <li>findAll():按主键缓存</li>
 * <li>delete(Long id):按主键清除缓存</li>
 * <li>delete(ClazzCriteria criteria):清除<strong>所有</strong>Clazz缓存</li>
 * <li>update(Clazz clazz):按主键清除缓存</li>
 * <li>update(ClazzCriteria criteria):有主键时按主键移除缓存，没有主键时清除<strong>所有</strong>Clazz缓存 </li>
 *
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
@Service
public class ClazzService extends BaseService {

    private ConcurrentMap<String, Clazz> localCache = new ConcurrentHashMap(128);
    @Autowired
    private ClazzMapper clazzMapper;

    private String cacheKey(Long id) {
        return "clazz:" + id;
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
    public Clazz find(Long id) {
        String cacheKey = cacheKey(id);
        Clazz clazz = localCache.get(cacheKey);
        if (clazz == null) {
            clazz = clazzMapper.find(id);
            if (clazz != null) {
                localCache.putIfAbsent(cacheKey, clazz);
                return localCache.get(cacheKey);
            }
        }
        return clazz;
    }

    public Clazz findOnlyCache(Long id) {
        return localCache.get(cacheKey(id));
    }

    public Clazz findSkipCache(Long id) {
        return clazzMapper.find(id);
    }

    public int count() {
        return clazzMapper.count();
    }

    /**
     * 每一个结果会按主键本地缓存
     *
     * @return
     */
    public List<Clazz> findAll() {
        List<Clazz> clazzs = clazzMapper.findAll();
        for (Clazz clazz : clazzs) {
            localCache.put(cacheKey(clazz.getId()), clazz);
        }
        return clazzs;
    }

    /**
     * 按主键清除本地缓存
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        localCache.remove(cacheKey(id));
        int result = clazzMapper.delete(id);
        return result == 1;
    }

    /**
     * 会清除<strong>所有<strong>Clazz缓存
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int delete(ClazzCriteria criteria) {
        int result = clazzMapper.deleteByCriteria(criteria);
        if (result > 0) {
            clearCache();
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Clazz clazz) {
        clazz.setId(idGenerator.nextId());
        int result = clazzMapper.save(clazz);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(List<Clazz> clazzs) {
        if (clazzs == null || clazzs.size() == 0) {
            return 0;
        }
        for (Clazz clazz : clazzs) {
            clazz.setId(idGenerator.nextId());
        }
        int size = clazzMapper.saveBatch(clazzs);
        return size;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(ClazzCriteria criteria) {
        criteria.setId(idGenerator.nextId());
        int result = clazzMapper.save(criteria.toClazz());
        return result == 1;
    }

    /**
     * 按主键移除缓存<br>
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Clazz clazz) {
        localCache.remove(cacheKey(clazz.getId()));
        int updateCount = clazzMapper.update(clazz);
        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(clazz.getClass() + ",[" + clazz.getId() + "],版本号冲突,版本号[" + clazz.getVersion() + "]");
        }
        return true;
    }

    /**
     * 有主键时按主键移除缓存，没有主键时清空<strong>所有</strong>Clazz缓存<br>
     * 当版本号，和主键不为空时，更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int update(ClazzCriteria criteria) {
        if (criteria.getId() != null) {
            localCache.remove(cacheKey(criteria.getId()));
        }
        int updateCount = clazzMapper.updateByCriteria(criteria);
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
    public ResultMap findPage(ClazzCriteria criteria) {
        ResultMap resultMap = ResultMap.success();
        //是否是主键查找
        if (criteria.getId() != null) {
            Clazz clazz = clazzMapper.find(criteria.getId());
            if (clazz != null) {
                List<Clazz> clazzs = new ArrayList<>(16);
                clazzs.add(clazz);
                resultMap.putTotal(1);
                resultMap.putItems(clazzs);
            } else {
                resultMap.putTotal(0);
            }
            return resultMap;
        }
        int total = clazzMapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<Clazz> clazzs = clazzMapper.findByCriteria(criteria);
        resultMap.putItems(clazzs);
        return resultMap;
    }

    public List<Clazz> find(ClazzCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            List<Clazz> clazzs = new ArrayList<>(16);
            Clazz clazz = clazzMapper.find(criteria.getId());
            if (clazz != null) {
                clazzs.add(clazz);
            }
            return clazzs;
        }
        return clazzMapper.findByCriteria(criteria);
    }

    public Clazz findOne(ClazzCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            return clazzMapper.find(criteria.getId());
        }
        List<Clazz> clazzs = clazzMapper.findByCriteria(criteria);
        if (clazzs.size() == 0) {
            return null;
        }
        return clazzs.get(0);
    }

}