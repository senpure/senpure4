package com.senpure.base.service;

import com.senpure.base.model.PermissionMenu;
import com.senpure.base.criteria.PermissionMenuCriteria;
import com.senpure.base.mapper.PermissionMenuMapper;
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
@CacheConfig(cacheNames = "permissionMenu")
public class PermissionMenuService extends BaseService {

    @Autowired
    private PermissionMenuMapper permissionMenuMapper;

    @CacheEvict(key = "#id")
    public void clearCache(Long id) {
    }

    @CacheEvict(allEntries = true)
    public void clearCache() {
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public PermissionMenu find(Long id) {
        return permissionMenuMapper.find(id);
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public PermissionMenu findOnlyCache(Long id) {
        return null;
    }

    @CachePut(key = "#id", unless = "#result == null")
    public PermissionMenu findSkipCache(Long id) {
        return permissionMenuMapper.find(id);
    }

    public int count() {
        return permissionMenuMapper.count();
    }

    public List<PermissionMenu> findAll() {
        return permissionMenuMapper.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#id")
    public boolean delete(Long id) {
        int result = permissionMenuMapper.delete(id);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#criteria.id", allEntries = true)
    public int delete(PermissionMenuCriteria criteria) {
        return permissionMenuMapper.deleteByCriteria(criteria);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(PermissionMenu permissionMenu) {
        permissionMenu.setId(idGenerator.nextId());
        int result = permissionMenuMapper.save(permissionMenu);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(List<PermissionMenu> permissionMenus) {
        if (permissionMenus == null || permissionMenus.size() == 0) {
            return 0;
        }
        for (PermissionMenu permissionMenu : permissionMenus) {
            permissionMenu.setId(idGenerator.nextId());
        }
        return permissionMenuMapper.saveBatch(permissionMenus);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(PermissionMenuCriteria criteria) {
        criteria.setId(idGenerator.nextId());
        int result = permissionMenuMapper.save(criteria.toPermissionMenu());
        return result == 1;
    }

    /**
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#permissionMenu.id")
    public boolean update(PermissionMenu permissionMenu) {
        int updateCount = permissionMenuMapper.update(permissionMenu);
        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(permissionMenu.getClass() + ",[" + permissionMenu.getId() + "],版本号冲突,版本号[" + permissionMenu.getVersion() + "]");
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
    public int update(PermissionMenuCriteria criteria) {
        int updateCount = permissionMenuMapper.updateByCriteria(criteria);
        if (updateCount == 0 && criteria.getVersion() != null
                && criteria.getId() != null) {
            throw new OptimisticLockingFailureException(criteria.getClass() + ",[" + criteria.getId() + "],版本号冲突,版本号[" + criteria.getVersion() + "]");
        }
        return updateCount;
    }

    @Transactional(readOnly = true)
    public ResultMap findPage(PermissionMenuCriteria criteria) {
        ResultMap resultMap = ResultMap.success();
        //是否是主键查找
        if (criteria.getId() != null) {
            PermissionMenu permissionMenu = permissionMenuMapper.find(criteria.getId());
            if (permissionMenu != null) {
                List<PermissionMenu> permissionMenus = new ArrayList<>(16);
                permissionMenus.add(permissionMenu);
                resultMap.putTotal(1);
                resultMap.putItems(permissionMenus);
            } else {
                resultMap.putTotal(0);
            }
            return resultMap;
        }
        int total = permissionMenuMapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<PermissionMenu> permissionMenus = permissionMenuMapper.findByCriteria(criteria);
        resultMap.putItems(permissionMenus);
        return resultMap;
    }

    public List<PermissionMenu> find(PermissionMenuCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            List<PermissionMenu> permissionMenus = new ArrayList<>(16);
            PermissionMenu permissionMenu = permissionMenuMapper.find(criteria.getId());
            if (permissionMenu != null) {
                permissionMenus.add(permissionMenu);
            }
            return permissionMenus;
        }
        return permissionMenuMapper.findByCriteria(criteria);
    }

    public PermissionMenu findOne(PermissionMenuCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            return permissionMenuMapper.find(criteria.getId());
        }
        List<PermissionMenu> permissionMenus = permissionMenuMapper.findByCriteria(criteria);
        if (permissionMenus.size() == 0) {
            return null;
        }
        return permissionMenus.get(0);
    }

    public List<PermissionMenu> findByMenuId(Integer menuId) {
        PermissionMenuCriteria criteria = new PermissionMenuCriteria();
        criteria.setUsePage(false);
        criteria.setMenuId(menuId);
        List<PermissionMenu> permissionMenus = permissionMenuMapper.findByCriteria(criteria);
        return permissionMenus;
    }

    public List<PermissionMenu> findByPermissionName(String permissionName) {
        PermissionMenuCriteria criteria = new PermissionMenuCriteria();
        criteria.setUsePage(false);
        criteria.setPermissionName(permissionName);
        List<PermissionMenu> permissionMenus = permissionMenuMapper.findByCriteria(criteria);
        return permissionMenus;
    }

}