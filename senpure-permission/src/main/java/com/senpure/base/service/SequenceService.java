package com.senpure.base.service;

import com.senpure.base.criteria.SequenceCriteria;
import com.senpure.base.exception.OptimisticLockingFailureException;
import com.senpure.base.mapper.SequenceMapper;
import com.senpure.base.model.Sequence;
import com.senpure.base.result.ResultMap;
import com.senpure.base.util.Assert;
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
@CacheConfig(cacheNames = "sequence")
public class SequenceService extends BaseService {

    @Autowired
    private SequenceMapper sequenceMapper;

    @CacheEvict(key = "#id")
    public void clearCache(Long id) {
    }

    @CacheEvict(allEntries = true)
    public void clearCache() {
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public Sequence find(Long id) {
        return sequenceMapper.find(id);
    }

    public Sequence findSkipCache(Long id) {
        return sequenceMapper.find(id);
    }

    public int count() {
        return sequenceMapper.count();
    }

    public List<Sequence> findAll() {
        return sequenceMapper.findAll();
    }

    @Transactional
    @CacheEvict(key = "#id")
    public boolean delete(Long id) {
        int result = sequenceMapper.delete(id);
        return result == 1;
    }

    @Transactional
    @CacheEvict(key = "#criteria.id", allEntries = true)
    public int delete(SequenceCriteria criteria) {
        return sequenceMapper.deleteByCriteria(criteria);
    }

    @Transactional
    public boolean save(Sequence sequence) {
        sequence.setId(idGenerator.nextId());
        int result = sequenceMapper.save(sequence);
        return result == 1;
    }

    @Transactional
    public int save(List<Sequence> sequences) {
        if (sequences == null || sequences.size() == 0) {
            return 0;
        }
        for (Sequence sequence : sequences) {
            sequence.setId(idGenerator.nextId());
        }
        return sequenceMapper.saveBatch(sequences);
    }

    @Transactional
    public boolean save(SequenceCriteria criteria) {
        criteria.setId(idGenerator.nextId());
        int result = sequenceMapper.save(criteria.toSequence());
        return result == 1;
    }

    /**
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional
    @CacheEvict(key = "#sequence.id")
    public boolean update(Sequence sequence) {
        int updateCount = sequenceMapper.update(sequence);
        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(sequence.getClass() + ",[" + sequence.getId() + "],版本号冲突");
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
    public int update(SequenceCriteria criteria) {
        int updateCount = sequenceMapper.updateByCriteria(criteria);
        if (updateCount == 0 && criteria.getVersion() != null
                && criteria.getId() != null) {
            throw new OptimisticLockingFailureException(criteria.getClass() + ",[" + criteria.getId() + "],版本号冲突");
        }
        return updateCount;
    }

    public ResultMap findPage(SequenceCriteria criteria) {
        ResultMap resultMap = ResultMap.success();
        int total = sequenceMapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<Sequence> sequences = sequenceMapper.findByCriteria(criteria);
        resultMap.putItems(sequences);
        return resultMap;
    }

    public List<Sequence> find(SequenceCriteria criteria) {
        List<Sequence> sequences = sequenceMapper.findByCriteria(criteria);
        return sequences;
    }

    public Sequence findOne(SequenceCriteria criteria) {
        List<Sequence> sequences = sequenceMapper.findByCriteria(criteria);
        if (sequences.size() == 0) {
            return null;
        }
        return sequences.get(0);
    }

    @Transactional
    public int currentSequence(String type) {
        SequenceCriteria criteria = new SequenceCriteria();
        criteria.setType(type);
        List<Sequence> sequences = sequenceMapper.findByCriteria(criteria);
        Sequence sequence = null;
        if (sequences.size() == 0) {
            sequence = new Sequence();
            sequence.setSequence(1);
            sequence.setType(type);
            sequence.setDigit(6);
            sequence.setPrefix("");
            sequence.setSuffix("");
            sequence.setSpan(1);
            Assert.error(type + "不存在，请检查仔细");
        } else {
            sequence = sequences.get(0);
        }
        int currentSequence = sequence.getSequence();
        sequence.setSequence(currentSequence + sequence.getSpan());
        update(sequence);
        return currentSequence;
    }
}