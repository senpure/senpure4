package com.senpure.book.service;

import com.senpure.book.model.Book;
import com.senpure.book.criteria.BookCriteria;
import com.senpure.book.mapper.BookMapper;
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
@CacheConfig(cacheNames = "book")
public class BookService extends BaseService {

    @Autowired
    private BookMapper bookMapper;

    @CacheEvict(key = "#id")
    public void clearCache(Long id) {
    }

    @CacheEvict(allEntries = true)
    public void clearCache() {
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public Book find(Long id) {
        return bookMapper.find(id);
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public Book findOnlyCache(Long id) {
        return null;
    }

    @CachePut(key = "#id", unless = "#result == null")
    public Book findSkipCache(Long id) {
        return bookMapper.find(id);
    }

    public int count() {
        return bookMapper.count();
    }

    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#id")
    public boolean delete(Long id) {
        int result = bookMapper.delete(id);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#criteria.id", allEntries = true)
    public int delete(BookCriteria criteria) {
        return bookMapper.deleteByCriteria(criteria);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Book book) {
        book.setId(idGenerator.nextId());
        int result = bookMapper.save(book);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(List<Book> books) {
        if (books == null || books.size() == 0) {
            return 0;
        }
        for (Book book : books) {
            book.setId(idGenerator.nextId());
        }
        return bookMapper.saveBatch(books);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(BookCriteria criteria) {
        criteria.setId(idGenerator.nextId());
        int result = bookMapper.save(criteria.toBook());
        return result == 1;
    }

    /**
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#book.id")
    public boolean update(Book book) {
        int updateCount = bookMapper.update(book);
        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(book.getClass() + ",[" + book.getId() + "],版本号冲突,版本号[" + book.getVersion() + "]");
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
    public int update(BookCriteria criteria) {
        int updateCount = bookMapper.updateByCriteria(criteria);
        if (updateCount == 0 && criteria.getVersion() != null
                && criteria.getId() != null) {
            throw new OptimisticLockingFailureException(criteria.getClass() + ",[" + criteria.getId() + "],版本号冲突,版本号[" + criteria.getVersion() + "]");
        }
        return updateCount;
    }

    @Transactional(readOnly = true)
    public ResultMap findPage(BookCriteria criteria) {
        ResultMap resultMap = ResultMap.success();
        //是否是主键查找
        if (criteria.getId() != null) {
            Book book = bookMapper.find(criteria.getId());
            if (book != null) {
                List<Book> books = new ArrayList<>(16);
                books.add(book);
                resultMap.putTotal(1);
                resultMap.putItems(books);
            } else {
                resultMap.putTotal(0);
            }
            return resultMap;
        }
        int total = bookMapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<Book> books = bookMapper.findByCriteria(criteria);
        resultMap.putItems(books);
        return resultMap;
    }

    public List<Book> find(BookCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            List<Book> books = new ArrayList<>(16);
            Book book = bookMapper.find(criteria.getId());
            if (book != null) {
                books.add(book);
            }
            return books;
        }
        return bookMapper.findByCriteria(criteria);
    }

    public Book findOne(BookCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            return bookMapper.find(criteria.getId());
        }
        List<Book> books = bookMapper.findByCriteria(criteria);
        if (books.size() == 0) {
            return null;
        }
        return books.get(0);
    }

    public List<Book> findByAuthorId(Long authorId) {
        BookCriteria criteria = new BookCriteria();
        criteria.setUsePage(false);
        criteria.setAuthorId(authorId);
        List<Book> books = bookMapper.findByCriteria(criteria);
        return books;
    }

    public Book findByName(String name) {
        BookCriteria criteria = new BookCriteria();
        criteria.setUsePage(false);
        criteria.setName(name);
        List<Book> books = bookMapper.findByCriteria(criteria);
        if (books.size() == 0) {
            return null;
        }
        return books.get(0);
    }

}