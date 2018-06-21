package com.senpure.base.service;

import com.senpure.base.PermissionConstant;
import com.senpure.base.criteria.ContainerCriteria;
import com.senpure.base.exception.OptimisticLockingFailureException;
import com.senpure.base.mapper.ContainerMapper;
import com.senpure.base.model.Container;
import com.senpure.base.result.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author senpure-generate
 * @version 2017-10-19 16:54:47
 */
@Service
@CacheConfig(cacheNames = "container")
public class ContainerService extends BaseService {

    @Autowired
    private ContainerMapper containerMapper;
    @Autowired
    private SequenceService sequenceService;

    @CacheEvict(key = "#id")
    public void clearCache(Integer id) {
    }

    @CacheEvict(allEntries = true)
    public void clearCache() {
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public Container find(Integer id) {
        return containerMapper.find(id);
    }

    public Container findSkipCache(Integer id) {
        return containerMapper.find(id);
    }

    public int count() {
        return containerMapper.count();
    }

    public List<Container> findAll() {
        return containerMapper.findAll();
    }

    @Transactional
    @CacheEvict(key = "#id")
    public boolean delete(Integer id) {
        int result = containerMapper.delete(id);
        return result == 1;
    }

    @Transactional
    @CacheEvict(key = "#criteria.id", allEntries = true)
    public int delete(ContainerCriteria criteria) {
        return containerMapper.deleteByCriteria(criteria);
    }

    @Transactional
    public boolean save(Container container)  {
        container.setId(sequenceService.currentSequence(PermissionConstant.SEQUENCE_CONTAINER_ID));
        int result = containerMapper.save(container);



        return result == 1;
    }

    @Transactional
    public int save(List<Container> containers) {
        if (containers == null || containers.size() == 0) {
            return 0;
        }
        for (Container container : containers) {
            container.setId(sequenceService.currentSequence(PermissionConstant.SEQUENCE_CONTAINER_ID));
        }
        return containerMapper.saveBatch(containers);
    }

    @Transactional
    public boolean save(ContainerCriteria criteria) {
        criteria.setId(sequenceService.currentSequence(PermissionConstant.SEQUENCE_CONTAINER_ID));
        int result = containerMapper.save(criteria.toContainer());
        return result == 1;
    }

    /**
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional
    @CacheEvict(key = "#container.id")
    public boolean update(Container container) {
        int updateCount = containerMapper.update(container);

        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(container.getClass() + ",[" + container.getId() + "],版本号冲突,版本号[" + container.getVersion() + "]");
        }
        container.setVersion(container.getVersion()+1);
        return true;
    }

    /**
     * 当版本号，和主键不为空时，更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional
    @CacheEvict(key = "#criteria.id", allEntries = true)
    public int update(ContainerCriteria criteria) {
        int updateCount = containerMapper.updateByCriteria(criteria);
        if (updateCount == 0 && criteria.getVersion() != null
                && criteria.getId() != null) {
            throw new OptimisticLockingFailureException(criteria.getClass() + ",[" + criteria.getId() + "],版本号冲突,版本号[" + criteria.getVersion() + "]");
        }
        return updateCount;
    }

    public ResultMap findPage(ContainerCriteria criteria) {
        ResultMap resultMap = ResultMap.success();
        int total = containerMapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<Container> containers = containerMapper.findByCriteria(criteria);
        resultMap.putItems(containers);
        return resultMap;
    }

    public List<Container> find(ContainerCriteria criteria) {
        List<Container> containers = containerMapper.findByCriteria(criteria);
        return containers;
    }

    public Container findOne(ContainerCriteria criteria) {
        List<Container> containers = containerMapper.findByCriteria(criteria);
        if (containers.size() == 0) {
            return null;
        }
        return containers.get(0);
    }

    public static void main(String[] args) {


    }
}