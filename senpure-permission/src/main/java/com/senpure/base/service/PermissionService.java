package com.senpure.base.service;

import com.senpure.base.model.Permission;
import com.senpure.base.criteria.PermissionCriteria;
import com.senpure.base.mapper.PermissionMapper;
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
 * @version 2018-1-25 18:24:19
 */
@Service
@CacheConfig(cacheNames = "permission")
public class PermissionService extends BaseService {

    @Autowired
    private PermissionMapper permissionMapper;

    @CacheEvict(key = "#id")
    public void clearCache(Long id) {
    }

    @CacheEvict(allEntries = true)
    public void clearCache() {
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public Permission find(Long id) {
        return permissionMapper.find(id);
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public Permission findOnlyCache(Long id) {
        return null;
    }

    @CachePut(key = "#id", unless = "#result == null")
    public Permission findSkipCache(Long id) {
        return permissionMapper.find(id);
    }

    public int count() {
        return permissionMapper.count();
    }

    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#id")
    public boolean delete(Long id) {
        int result = permissionMapper.delete(id);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#criteria.id", allEntries = true)
    public int delete(PermissionCriteria criteria) {
        return permissionMapper.deleteByCriteria(criteria);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Permission permission) {
        permission.setId(idGenerator.nextId());
        int result = permissionMapper.save(permission);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(List<Permission> permissions) {
        if (permissions == null || permissions.size() == 0) {
            return 0;
        }
        for (Permission permission : permissions) {
            permission.setId(idGenerator.nextId());
        }
        return permissionMapper.saveBatch(permissions);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(PermissionCriteria criteria) {
        criteria.setId(idGenerator.nextId());
        int result = permissionMapper.save(criteria.toPermission());
        return result == 1;
    }

    /**
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#permission.id")
    public boolean update(Permission permission) {
        int updateCount = permissionMapper.update(permission);
        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(permission.getClass() + ",[" + permission.getId() + "],版本号冲突,版本号[" + permission.getVersion() + "]");
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
    public int update(PermissionCriteria criteria) {
        int updateCount = permissionMapper.updateByCriteria(criteria);
        if (updateCount == 0 && criteria.getVersion() != null
                && criteria.getId() != null) {
            throw new OptimisticLockingFailureException(criteria.getClass() + ",[" + criteria.getId() + "],版本号冲突,版本号[" + criteria.getVersion() + "]");
        }
        return updateCount;
    }

    @Transactional(readOnly = true)
    public ResultMap findPage(PermissionCriteria criteria) {
        ResultMap resultMap = ResultMap.success();
        //是否是主键查找
        if (criteria.getId() != null) {
            Permission permission = permissionMapper.find(criteria.getId());
            if (permission != null) {
                List<Permission> permissions = new ArrayList<>(16);
                permissions.add(permission);
                resultMap.putTotal(1);
                resultMap.putItems(permissions);
            } else {
                resultMap.putTotal(0);
            }
            return resultMap;
        }
        int total = permissionMapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<Permission> permissions = permissionMapper.findByCriteria(criteria);
        resultMap.putItems(permissions);
        return resultMap;
    }

    public List<Permission> find(PermissionCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            List<Permission> permissions = new ArrayList<>(16);
            Permission permission = permissionMapper.find(criteria.getId());
            if (permission != null) {
                permissions.add(permission);
            }
            return permissions;
        }
        return permissionMapper.findByCriteria(criteria);
    }

    public Permission findOne(PermissionCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            return permissionMapper.find(criteria.getId());
        }
        List<Permission> permissions = permissionMapper.findByCriteria(criteria);
        if (permissions.size() == 0) {
            return null;
        }
        return permissions.get(0);
    }

    public Permission findByName(String name) {
        PermissionCriteria criteria = new PermissionCriteria();
        criteria.setUsePage(false);
        criteria.setName(name);
        List<Permission> permissions = permissionMapper.findByCriteria(criteria);
        if (permissions.size() == 0) {
            return null;
        }
        return permissions.get(0);
    }

    public List<Permission> findByType(String type) {
        PermissionCriteria criteria = new PermissionCriteria();
        criteria.setUsePage(false);
        criteria.setType(type);
        List<Permission> permissions = permissionMapper.findByCriteria(criteria);
        return permissions;
    }

    public List<Permission> findByVerifyName(String verifyName) {
        PermissionCriteria criteria = new PermissionCriteria();
        criteria.setUsePage(false);
        criteria.setVerifyName(verifyName);
        List<Permission> permissions = permissionMapper.findByCriteria(criteria);
        return permissions;
    }

}