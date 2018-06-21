package com.senpure.base.service;

import com.senpure.base.model.Menu;
import com.senpure.base.criteria.MenuCriteria;
import com.senpure.base.mapper.MenuMapper;
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
@CacheConfig(cacheNames = "menu")
public class MenuService extends BaseService {

    @Autowired
    private MenuMapper menuMapper;

    @CacheEvict(key = "#id")
    public void clearCache(Integer id) {
    }

    @CacheEvict(allEntries = true)
    public void clearCache() {
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public Menu find(Integer id) {
        return menuMapper.find(id);
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public Menu findOnlyCache(Integer id) {
        return null;
    }

    @CachePut(key = "#id", unless = "#result == null")
    public Menu findSkipCache(Integer id) {
        return menuMapper.find(id);
    }

    public int count() {
        return menuMapper.count();
    }

    public List<Menu> findAll() {
        return menuMapper.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#id")
    public boolean delete(Integer id) {
        int result = menuMapper.delete(id);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#criteria.id", allEntries = true)
    public int delete(MenuCriteria criteria) {
        return menuMapper.deleteByCriteria(criteria);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Menu menu) {
        //TODO 注意是否有主键
        //menu.setId();
        int result = menuMapper.save(menu);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(List<Menu> menus) {
        if (menus == null || menus.size() == 0) {
            return 0;
        }
        //TODO 注意是否有主键
        for (Menu menu : menus) {
            //menu.setId();
        }
        return menuMapper.saveBatch(menus);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(MenuCriteria criteria) {
        //TODO 注意是否有主键
        //criteria.setId();
        int result = menuMapper.save(criteria.toMenu());
        return result == 1;
    }

    /**
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#menu.id")
    public boolean update(Menu menu) {
        int updateCount = menuMapper.update(menu);
        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(menu.getClass() + ",[" + menu.getId() + "],版本号冲突,版本号[" + menu.getVersion() + "]");
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
    public int update(MenuCriteria criteria) {
        int updateCount = menuMapper.updateByCriteria(criteria);
        if (updateCount == 0 && criteria.getVersion() != null
                && criteria.getId() != null) {
            throw new OptimisticLockingFailureException(criteria.getClass() + ",[" + criteria.getId() + "],版本号冲突,版本号[" + criteria.getVersion() + "]");
        }
        return updateCount;
    }

    @Transactional(readOnly = true)
    public ResultMap findPage(MenuCriteria criteria) {
        ResultMap resultMap = ResultMap.success();
        //是否是主键查找
        if (criteria.getId() != null) {
            Menu menu = menuMapper.find(criteria.getId());
            if (menu != null) {
                List<Menu> menus = new ArrayList<>(16);
                menus.add(menu);
                resultMap.putTotal(1);
                resultMap.putItems(menus);
            } else {
                resultMap.putTotal(0);
            }
            return resultMap;
        }
        int total = menuMapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<Menu> menus = menuMapper.findByCriteria(criteria);
        resultMap.putItems(menus);
        return resultMap;
    }

    public List<Menu> find(MenuCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            List<Menu> menus = new ArrayList<>(16);
            Menu menu = menuMapper.find(criteria.getId());
            if (menu != null) {
                menus.add(menu);
            }
            return menus;
        }
        return menuMapper.findByCriteria(criteria);
    }

    public Menu findOne(MenuCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            return menuMapper.find(criteria.getId());
        }
        List<Menu> menus = menuMapper.findByCriteria(criteria);
        if (menus.size() == 0) {
            return null;
        }
        return menus.get(0);
    }

    public List<Menu> findByParentId(Integer parentId) {
        MenuCriteria criteria = new MenuCriteria();
        criteria.setUsePage(false);
        criteria.setParentId(parentId);
        List<Menu> menus = menuMapper.findByCriteria(criteria);
        return menus;
    }

    public Menu findByI18nKey(String i18nKey) {
        MenuCriteria criteria = new MenuCriteria();
        criteria.setUsePage(false);
        criteria.setI18nKey(i18nKey);
        List<Menu> menus = menuMapper.findByCriteria(criteria);
        if (menus.size() == 0) {
            return null;
        }
        return menus.get(0);
    }

}