package com.senpure.demo.service;

import com.senpure.demo.model.Notice;
import com.senpure.demo.criteria.NoticeCriteria;
import com.senpure.demo.mapper.NoticeMapper;
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
 * 该类对 [Notice]的增删查改有本地缓存,只缓存主键，不缓存查询缓存
 * <li>find(Long id):按主键缓存</li>
 * <li>findAll():按主键缓存</li>
 * <li>delete(Long id):按主键清除缓存</li>
 * <li>delete(NoticeCriteria criteria):清除<strong>所有</strong>Notice缓存</li>
 * <li>update(Notice notice):按主键清除缓存</li>
 * <li>update(NoticeCriteria criteria):有主键时按主键移除缓存，没有主键时清除<strong>所有</strong>Notice缓存 </li>
 *
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
@Service
public class NoticeService extends BaseService {

    private ConcurrentMap<String, Notice> localCache = new ConcurrentHashMap(128);
    @Autowired
    private NoticeMapper noticeMapper;

    private String cacheKey(Long id) {
        return "notice:" + id;
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
    public Notice find(Long id) {
        String cacheKey = cacheKey(id);
        Notice notice = localCache.get(cacheKey);
        if (notice == null) {
            notice = noticeMapper.find(id);
            if (notice != null) {
                localCache.putIfAbsent(cacheKey, notice);
                return localCache.get(cacheKey);
            }
        }
        return notice;
    }

    public Notice findOnlyCache(Long id) {
        return localCache.get(cacheKey(id));
    }

    public Notice findSkipCache(Long id) {
        return noticeMapper.find(id);
    }

    public int count() {
        return noticeMapper.count();
    }

    /**
     * 每一个结果会按主键本地缓存
     *
     * @return
     */
    public List<Notice> findAll() {
        List<Notice> notices = noticeMapper.findAll();
        for (Notice notice : notices) {
            localCache.put(cacheKey(notice.getId()), notice);
        }
        return notices;
    }

    /**
     * 按主键清除本地缓存
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        localCache.remove(cacheKey(id));
        int result = noticeMapper.delete(id);
        return result == 1;
    }

    /**
     * 会清除<strong>所有<strong>Notice缓存
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int delete(NoticeCriteria criteria) {
        int result = noticeMapper.deleteByCriteria(criteria);
        if (result > 0) {
            clearCache();
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Notice notice) {
        notice.setId(idGenerator.nextId());
        int result = noticeMapper.save(notice);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(List<Notice> notices) {
        if (notices == null || notices.size() == 0) {
            return 0;
        }
        for (Notice notice : notices) {
            notice.setId(idGenerator.nextId());
        }
        int size = noticeMapper.saveBatch(notices);
        return size;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(NoticeCriteria criteria) {
        criteria.setId(idGenerator.nextId());
        int result = noticeMapper.save(criteria.toNotice());
        return result == 1;
    }

    /**
     * 按主键移除缓存<br>
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Notice notice) {
        localCache.remove(cacheKey(notice.getId()));
        int updateCount = noticeMapper.update(notice);
        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(notice.getClass() + ",[" + notice.getId() + "],版本号冲突,版本号[" + notice.getVersion() + "]");
        }
        return true;
    }

    /**
     * 有主键时按主键移除缓存，没有主键时清空<strong>所有</strong>Notice缓存<br>
     * 当版本号，和主键不为空时，更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int update(NoticeCriteria criteria) {
        if (criteria.getId() != null) {
            localCache.remove(cacheKey(criteria.getId()));
        }
        int updateCount = noticeMapper.updateByCriteria(criteria);
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
    public ResultMap findPage(NoticeCriteria criteria) {
        ResultMap resultMap = ResultMap.success();
        //是否是主键查找
        if (criteria.getId() != null) {
            Notice notice = noticeMapper.find(criteria.getId());
            if (notice != null) {
                List<Notice> notices = new ArrayList<>(16);
                notices.add(notice);
                resultMap.putTotal(1);
                resultMap.putItems(notices);
            } else {
                resultMap.putTotal(0);
            }
            return resultMap;
        }
        int total = noticeMapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<Notice> notices = noticeMapper.findByCriteria(criteria);
        resultMap.putItems(notices);
        return resultMap;
    }

    public List<Notice> find(NoticeCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            List<Notice> notices = new ArrayList<>(16);
            Notice notice = noticeMapper.find(criteria.getId());
            if (notice != null) {
                notices.add(notice);
            }
            return notices;
        }
        return noticeMapper.findByCriteria(criteria);
    }

    public Notice findOne(NoticeCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            return noticeMapper.find(criteria.getId());
        }
        List<Notice> notices = noticeMapper.findByCriteria(criteria);
        if (notices.size() == 0) {
            return null;
        }
        return notices.get(0);
    }

}