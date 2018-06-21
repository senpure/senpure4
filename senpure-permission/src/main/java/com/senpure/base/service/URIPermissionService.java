package com.senpure.base.service;

import com.senpure.base.model.URIPermission;
import com.senpure.base.criteria.URIPermissionCriteria;
import com.senpure.base.mapper.URIPermissionMapper;
import com.senpure.base.exception.OptimisticLockingFailureException;
import com.senpure.base.result.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 该类对 [URIPermission]的增删查改有本地缓存,只缓存主键，不缓存查询缓存
 * <li>find(Long id):按主键缓存</li>
 * <li>findAll():按主键缓存</li>
 * <li>delete(Long id):按主键清除缓存</li>
 * <li>delete(URIPermissionCriteria criteria):清除<strong>所有</strong>URIPermission缓存</li>
 * <li>update(URIPermission uriPermission):按主键清除缓存</li>
 * <li>update(URIPermissionCriteria criteria):有主键时按主键移除缓存，没有主键时清除<strong>所有</strong>URIPermission缓存 </li>
 *
 * @author senpure-generate
 * @version 2018-1-16 11:20:00
 */
@Service
public class URIPermissionService extends BaseService {

    private ConcurrentMap<String, Object> localCache = new ConcurrentHashMap(128);

    private List<URIPermission> emptyList = new EmptyList();
    @Autowired
    private URIPermissionMapper uriPermissionMapper;

    private String cacheKey(Long id) {
        return "uriPermission:" + id;
    }

    private String cacheKey(String uriAndMethod) {
        return "uriPermission:uriAndMethod:list:" + uriAndMethod;
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
    public URIPermission find(Long id) {
        String cacheKey = cacheKey(id);
        URIPermission uriPermission = (URIPermission) localCache.get(cacheKey);
        if (uriPermission == null) {
            uriPermission = uriPermissionMapper.find(id);
            if (uriPermission != null) {
                localCache.putIfAbsent(cacheKey, uriPermission);
                return (URIPermission) localCache.get(cacheKey);
            }
        }
        return uriPermission;
    }

    public URIPermission findOnlyCache(Long id) {
        return (URIPermission) localCache.get(cacheKey(id));
    }

    public URIPermission findSkipCache(Long id) {
        return uriPermissionMapper.find(id);
    }

    public int count() {
        return uriPermissionMapper.count();
    }

    /**
     * 每一个结果会按主键本地缓存
     *
     * @return
     */
    public List<URIPermission> findAll() {
        List<URIPermission> uriPermissions = uriPermissionMapper.findAll();
        for (URIPermission uriPermission : uriPermissions) {
            localCache.put(cacheKey(uriPermission.getId()), uriPermission);
        }
        return uriPermissions;
    }

    /**
     * 按主键清除本地缓存
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        localCache.remove(cacheKey(id));
        int result = uriPermissionMapper.delete(id);
        return result == 1;
    }

    /**
     * 会清除<strong>所有<strong>URIPermission缓存
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int delete(URIPermissionCriteria criteria) {
        int result = uriPermissionMapper.deleteByCriteria(criteria);
        if (result > 0) {
            clearCache();
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(URIPermission uriPermission) {
        uriPermission.setId(idGenerator.nextId());
        int result = uriPermissionMapper.save(uriPermission);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(List<URIPermission> uriPermissions) {
        if (uriPermissions == null || uriPermissions.size() == 0) {
            return 0;
        }
        for (URIPermission uriPermission : uriPermissions) {
            uriPermission.setId(idGenerator.nextId());
        }
        int size = uriPermissionMapper.saveBatch(uriPermissions);
        return size;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(URIPermissionCriteria criteria) {
        criteria.setId(idGenerator.nextId());
        int result = uriPermissionMapper.save(criteria.toURIPermission());
        return result == 1;
    }

    /**
     * 按主键移除缓存<br>
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean update(URIPermission uriPermission) {
        localCache.remove(cacheKey(uriPermission.getId()));
        int updateCount = uriPermissionMapper.update(uriPermission);
        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(uriPermission.getClass() + ",[" + uriPermission.getId() + "],版本号冲突,版本号[" + uriPermission.getVersion() + "]");
        }
        return true;
    }

    /**
     * 有主键时按主键移除缓存，没有主键时清空<strong>所有</strong>URIPermission缓存<br>
     * 当版本号，和主键不为空时，更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int update(URIPermissionCriteria criteria) {
        if (criteria.getId() != null) {
            localCache.remove(cacheKey(criteria.getId()));
        }
        int updateCount = uriPermissionMapper.updateByCriteria(criteria);
        if (updateCount == 0 && criteria.getVersion() != null
                && criteria.getId() != null) {
            throw new OptimisticLockingFailureException(criteria.getClass() + ",[" + criteria.getId() + "],版本号冲突,版本号[" + criteria.getVersion() + "]");
        }
        if (criteria.getId() != null && updateCount > 0) {
            localCache.clear();
        }
        return updateCount;
    }

    public ResultMap findPage(URIPermissionCriteria criteria) {
        ResultMap resultMap = ResultMap.success();
        //是否是主键查找
        if (criteria.getId() != null) {
            URIPermission uriPermission = uriPermissionMapper.find(criteria.getId());
            if (uriPermission != null) {
                List<URIPermission> uriPermissions = new ArrayList<>(16);
                uriPermissions.add(uriPermission);
                resultMap.putTotal(1);
                resultMap.putItems(uriPermissions);
            } else {
                resultMap.putTotal(0);
            }
            return resultMap;
        }
        int total = uriPermissionMapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<URIPermission> uriPermissions = uriPermissionMapper.findByCriteria(criteria);
        resultMap.putItems(uriPermissions);
        return resultMap;
    }

    public List<URIPermission> find(URIPermissionCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            List<URIPermission> uriPermissions = new ArrayList<>(16);
            URIPermission uriPermission = uriPermissionMapper.find(criteria.getId());
            if (uriPermission != null) {
                uriPermissions.add(uriPermission);
            }
            return uriPermissions;
        }
        return uriPermissionMapper.findByCriteria(criteria);
    }

    public URIPermission findOne(URIPermissionCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            return uriPermissionMapper.find(criteria.getId());
        }
        List<URIPermission> uriPermissions = uriPermissionMapper.findByCriteria(criteria);
        if (uriPermissions.size() == 0) {
            return null;
        }
        return uriPermissions.get(0);
    }


    public List<URIPermission> findByUriAndMethod(String uriAndMethod) {
        String cacheKey = cacheKey(uriAndMethod);
        List<URIPermission> uriPermissions = (List<URIPermission>) localCache.get(cacheKey);
        if (uriPermissions == null) {
            URIPermissionCriteria criteria = new URIPermissionCriteria();
            criteria.setUsePage(false);
            criteria.setUriAndMethod(uriAndMethod);
            uriPermissions = uriPermissionMapper.findByCriteria(criteria);
            if (uriPermissions.size() > 0) {
                localCache.put(cacheKey, uriPermissions);
            }
        }
        return uriPermissions;
    }

    public List<URIPermission> findByUriAndMethodOnlyCache(String uriAndMethod) {
        String cacheKey = cacheKey(uriAndMethod);
        List<URIPermission> uriPermissions = (List<URIPermission>) localCache.get(cacheKey);
        if (uriPermissions == null) {
            uriPermissions = emptyList;
        }
        return uriPermissions;
    }

    public void putCacheByUriAndMethod() {
        clearCache();
        List<URIPermission> uriPermissions = uriPermissionMapper.findAll();
        for (URIPermission uriPermission : uriPermissions) {
            List<URIPermission> caches = (List<URIPermission>) localCache.get(uriPermission.getUriAndMethod());
            if (caches == null) {
                caches = new ArrayList<>();
                localCache.put(cacheKey(uriPermission.getUriAndMethod()), caches);
            }
            caches.add(uriPermission);
        }

        }

    public List<URIPermission> findByPermissionId(Long permissionId) {
        URIPermissionCriteria criteria = new URIPermissionCriteria();
        criteria.setUsePage(false);
        criteria.setPermissionId(permissionId);
        List<URIPermission> uriPermissions = uriPermissionMapper.findByCriteria(criteria);
        return uriPermissions;
    }

    private class EmptyList implements List {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public boolean add(Object o) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean addAll(Collection c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Object get(int index) {
            return null;
        }

        @Override
        public Object set(int index, Object element) {
            return null;
        }

        @Override
        public void add(int index, Object element) {

        }

        @Override
        public Object remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator listIterator() {
            return null;
        }

        @Override
        public ListIterator listIterator(int index) {
            return null;
        }

        @Override
        public List subList(int fromIndex, int toIndex) {
            return null;
        }

        @Override
        public boolean retainAll(Collection c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection c) {
            return false;
        }

        @Override
        public boolean containsAll(Collection c) {
            return false;
        }

        @Override
        public Object[] toArray(Object[] a) {
            return new Object[0];
        }
    }
}