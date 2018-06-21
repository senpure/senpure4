package com.senpure.base.service;

import com.senpure.base.model.ContainerPermission;
import com.senpure.base.criteria.ContainerPermissionCriteria;
import com.senpure.base.mapper.ContainerPermissionMapper;
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
@CacheConfig(cacheNames = "containerPermission")
public class ContainerPermissionService extends BaseService {

    @Autowired
    private ContainerPermissionMapper containerPermissionMapper;

    @CacheEvict(key = "#id")
    public void clearCache(Long id) {
    }

    @CacheEvict(allEntries = true)
    public void clearCache() {
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public ContainerPermission find(Long id) {
        return containerPermissionMapper.find(id);
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public ContainerPermission findOnlyCache(Long id) {
        return null;
    }

    @CachePut(key = "#id", unless = "#result == null")
    public ContainerPermission findSkipCache(Long id) {
        return containerPermissionMapper.find(id);
    }

    public int count() {
        return containerPermissionMapper.count();
    }

    public List<ContainerPermission> findAll() {
        return containerPermissionMapper.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#id")
    public boolean delete(Long id) {
        int result = containerPermissionMapper.delete(id);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#criteria.id", allEntries = true)
    public int delete(ContainerPermissionCriteria criteria) {
        return containerPermissionMapper.deleteByCriteria(criteria);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(ContainerPermission containerPermission) {
        containerPermission.setId(idGenerator.nextId());
        int result = containerPermissionMapper.save(containerPermission);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(List<ContainerPermission> containerPermissions) {
        if (containerPermissions == null || containerPermissions.size() == 0) {
            return 0;
        }
        for (ContainerPermission containerPermission : containerPermissions) {
            containerPermission.setId(idGenerator.nextId());
        }
        return containerPermissionMapper.saveBatch(containerPermissions);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(ContainerPermissionCriteria criteria) {
        criteria.setId(idGenerator.nextId());
        int result = containerPermissionMapper.save(criteria.toContainerPermission());
        return result == 1;
    }

    /**
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#containerPermission.id")
    public boolean update(ContainerPermission containerPermission) {
        int updateCount = containerPermissionMapper.update(containerPermission);
        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(containerPermission.getClass() + ",[" + containerPermission.getId() + "],版本号冲突,版本号[" + containerPermission.getVersion() + "]");
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
    public int update(ContainerPermissionCriteria criteria) {
        int updateCount = containerPermissionMapper.updateByCriteria(criteria);
        if (updateCount == 0 && criteria.getVersion() != null
                && criteria.getId() != null) {
            throw new OptimisticLockingFailureException(criteria.getClass() + ",[" + criteria.getId() + "],版本号冲突,版本号[" + criteria.getVersion() + "]");
        }
        return updateCount;
    }

    @Transactional(readOnly = true)
    public ResultMap findPage(ContainerPermissionCriteria criteria) {
        ResultMap resultMap = ResultMap.success();
        //是否是主键查找
        if (criteria.getId() != null) {
            ContainerPermission containerPermission = containerPermissionMapper.find(criteria.getId());
            if (containerPermission != null) {
                List<ContainerPermission> containerPermissions = new ArrayList<>(16);
                containerPermissions.add(containerPermission);
                resultMap.putTotal(1);
                resultMap.putItems(containerPermissions);
            } else {
                resultMap.putTotal(0);
            }
            return resultMap;
        }
        int total = containerPermissionMapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<ContainerPermission> containerPermissions = containerPermissionMapper.findByCriteria(criteria);
        resultMap.putItems(containerPermissions);
        return resultMap;
    }

    public List<ContainerPermission> find(ContainerPermissionCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            List<ContainerPermission> containerPermissions = new ArrayList<>(16);
            ContainerPermission containerPermission = containerPermissionMapper.find(criteria.getId());
            if (containerPermission != null) {
                containerPermissions.add(containerPermission);
            }
            return containerPermissions;
        }
        return containerPermissionMapper.findByCriteria(criteria);
    }

    public ContainerPermission findOne(ContainerPermissionCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            return containerPermissionMapper.find(criteria.getId());
        }
        List<ContainerPermission> containerPermissions = containerPermissionMapper.findByCriteria(criteria);
        if (containerPermissions.size() == 0) {
            return null;
        }
        return containerPermissions.get(0);
    }

    public List<ContainerPermission> findByContainerId(Integer containerId) {
        ContainerPermissionCriteria criteria = new ContainerPermissionCriteria();
        criteria.setUsePage(false);
        criteria.setContainerId(containerId);
        List<ContainerPermission> containerPermissions = containerPermissionMapper.findByCriteria(criteria);
        return containerPermissions;
    }

    public List<ContainerPermission> findByPermissionId(Long permissionId) {
        ContainerPermissionCriteria criteria = new ContainerPermissionCriteria();
        criteria.setUsePage(false);
        criteria.setPermissionId(permissionId);
        List<ContainerPermission> containerPermissions = containerPermissionMapper.findByCriteria(criteria);
        return containerPermissions;
    }

}