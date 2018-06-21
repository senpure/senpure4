package com.senpure.book.service;

import com.senpure.book.model.Author;
import com.senpure.book.criteria.AuthorCriteria;
import com.senpure.book.mapper.AuthorMapper;
import com.senpure.base.exception.OptimisticLockingFailureException;
import com.senpure.base.service.BaseService;
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
 * @version 2018-1-25 15:15:45
 */
@Service
@CacheConfig(cacheNames = "author")
public class AuthorService extends BaseService {

    @Autowired
    private AuthorMapper authorMapper;

    @CacheEvict(key = "#id")
    public void clearCache(Long id) {
    }

    @CacheEvict(allEntries = true)
    public void clearCache() {
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public Author find(Long id) {
        return authorMapper.find(id);
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public Author findOnlyCache(Long id) {
        return null;
    }

    @CachePut(key = "#id", unless = "#result == null")
    public Author findSkipCache(Long id) {
        return authorMapper.find(id);
    }

    public int count() {
        return authorMapper.count();
    }

    public List<Author> findAll() {
        return authorMapper.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#id")
    public boolean delete(Long id) {
        int result = authorMapper.delete(id);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#criteria.id", allEntries = true)
    public int delete(AuthorCriteria criteria) {
        return authorMapper.deleteByCriteria(criteria);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Author author) {
        author.setId(idGenerator.nextId());
        int result = authorMapper.save(author);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(List<Author> authors) {
        if (authors == null || authors.size() == 0) {
            return 0;
        }
        for (Author author : authors) {
            author.setId(idGenerator.nextId());
        }
        return authorMapper.saveBatch(authors);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(AuthorCriteria criteria) {
        criteria.setId(idGenerator.nextId());
        int result = authorMapper.save(criteria.toAuthor());
        return result == 1;
    }

    /**
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#author.id")
    public boolean update(Author author) {
        int updateCount = authorMapper.update(author);
        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(author.getClass() + ",[" + author.getId() + "],版本号冲突,版本号[" + author.getVersion() + "]");
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
    public int update(AuthorCriteria criteria) {
        int updateCount = authorMapper.updateByCriteria(criteria);
        if (updateCount == 0 && criteria.getVersion() != null
                && criteria.getId() != null) {
            throw new OptimisticLockingFailureException(criteria.getClass() + ",[" + criteria.getId() + "],版本号冲突,版本号[" + criteria.getVersion() + "]");
        }
        return updateCount;
    }

    @Transactional(readOnly = true)
    public ResultMap findPage(AuthorCriteria criteria) {
        ResultMap resultMap = ResultMap.success();
        //是否是主键查找
        if (criteria.getId() != null) {
            Author author = authorMapper.find(criteria.getId());
            if (author != null) {
                List<Author> authors = new ArrayList<>(16);
                authors.add(author);
                resultMap.putTotal(1);
                resultMap.putItems(authors);
            } else {
                resultMap.putTotal(0);
            }
            return resultMap;
        }
        int total = authorMapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<Author> authors = authorMapper.findByCriteria(criteria);
        resultMap.putItems(authors);
        return resultMap;
    }

    public List<Author> find(AuthorCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            List<Author> authors = new ArrayList<>(16);
            Author author = authorMapper.find(criteria.getId());
            if (author != null) {
                authors.add(author);
            }
            return authors;
        }
        return authorMapper.findByCriteria(criteria);
    }

    public Author findOne(AuthorCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            return authorMapper.find(criteria.getId());
        }
        List<Author> authors = authorMapper.findByCriteria(criteria);
        if (authors.size() == 0) {
            return null;
        }
        return authors.get(0);
    }

    public Author findByName(String name) {
        AuthorCriteria criteria = new AuthorCriteria();
        criteria.setUsePage(false);
        criteria.setName(name);
        List<Author> authors = authorMapper.findByCriteria(criteria);
        if (authors.size() == 0) {
            return null;
        }
        return authors.get(0);
    }

}