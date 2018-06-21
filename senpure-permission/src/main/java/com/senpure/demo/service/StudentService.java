package com.senpure.demo.service;

import com.senpure.demo.model.Student;
import com.senpure.demo.criteria.StudentCriteria;
import com.senpure.demo.mapper.StudentMapper;
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
 * 该类对 [Student]的增删查改有本地缓存,只缓存主键，不缓存查询缓存
 * <li>find(Long id):按主键缓存</li>
 * <li>findAll():按主键缓存</li>
 * <li>delete(Long id):按主键清除缓存</li>
 * <li>delete(StudentCriteria criteria):清除<strong>所有</strong>Student缓存</li>
 * <li>update(Student student):按主键清除缓存</li>
 * <li>update(StudentCriteria criteria):有主键时按主键移除缓存，没有主键时清除<strong>所有</strong>Student缓存 </li>
 *
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
@Service
public class StudentService extends BaseService {

    private ConcurrentMap<String, Student> localCache = new ConcurrentHashMap(128);
    @Autowired
    private StudentMapper studentMapper;

    private String cacheKey(Long id) {
        return "student:" + id;
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
    public Student find(Long id) {
        String cacheKey = cacheKey(id);
        Student student = localCache.get(cacheKey);
        if (student == null) {
            student = studentMapper.find(id);
            if (student != null) {
                localCache.putIfAbsent(cacheKey, student);
                return localCache.get(cacheKey);
            }
        }
        return student;
    }

    public Student findOnlyCache(Long id) {
        return localCache.get(cacheKey(id));
    }

    public Student findSkipCache(Long id) {
        return studentMapper.find(id);
    }

    public int count() {
        return studentMapper.count();
    }

    /**
     * 每一个结果会按主键本地缓存
     *
     * @return
     */
    public List<Student> findAll() {
        List<Student> students = studentMapper.findAll();
        for (Student student : students) {
            localCache.put(cacheKey(student.getId()), student);
        }
        return students;
    }

    /**
     * 按主键清除本地缓存
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        localCache.remove(cacheKey(id));
        int result = studentMapper.delete(id);
        return result == 1;
    }

    /**
     * 会清除<strong>所有<strong>Student缓存
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int delete(StudentCriteria criteria) {
        int result = studentMapper.deleteByCriteria(criteria);
        if (result > 0) {
            clearCache();
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Student student) {
        student.setId(idGenerator.nextId());
        int result = studentMapper.save(student);
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(List<Student> students) {
        if (students == null || students.size() == 0) {
            return 0;
        }
        for (Student student : students) {
            student.setId(idGenerator.nextId());
        }
        int size = studentMapper.saveBatch(students);
        return size;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(StudentCriteria criteria) {
        criteria.setId(idGenerator.nextId());
        int result = studentMapper.save(criteria.toStudent());
        return result == 1;
    }

    /**
     * 按主键移除缓存<br>
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Student student) {
        localCache.remove(cacheKey(student.getId()));
        int updateCount = studentMapper.update(student);
        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(student.getClass() + ",[" + student.getId() + "],版本号冲突,版本号[" + student.getVersion() + "]");
        }
        return true;
    }

    /**
     * 有主键时按主键移除缓存，没有主键时清空<strong>所有</strong>Student缓存<br>
     * 当版本号，和主键不为空时，更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int update(StudentCriteria criteria) {
        if (criteria.getId() != null) {
            localCache.remove(cacheKey(criteria.getId()));
        }
        int updateCount = studentMapper.updateByCriteria(criteria);
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
    public ResultMap findPage(StudentCriteria criteria) {
        ResultMap resultMap = ResultMap.success();
        //是否是主键查找
        if (criteria.getId() != null) {
            Student student = studentMapper.find(criteria.getId());
            if (student != null) {
                List<Student> students = new ArrayList<>(16);
                students.add(student);
                resultMap.putTotal(1);
                resultMap.putItems(students);
            } else {
                resultMap.putTotal(0);
            }
            return resultMap;
        }
        int total = studentMapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<Student> students = studentMapper.findByCriteria(criteria);
        resultMap.putItems(students);
        return resultMap;
    }

    public List<Student> find(StudentCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            List<Student> students = new ArrayList<>(16);
            Student student = studentMapper.find(criteria.getId());
            if (student != null) {
                students.add(student);
            }
            return students;
        }
        return studentMapper.findByCriteria(criteria);
    }

    public Student findOne(StudentCriteria criteria) {
        //是否是主键查找
        if (criteria.getId() != null) {
            return studentMapper.find(criteria.getId());
        }
        List<Student> students = studentMapper.findByCriteria(criteria);
        if (students.size() == 0) {
            return null;
        }
        return students.get(0);
    }

    public List<Student> findByClazzId(Long clazzId) {
        StudentCriteria criteria = new StudentCriteria();
        criteria.setUsePage(false);
        criteria.setClazzId(clazzId);
        List<Student> students = studentMapper.findByCriteria(criteria);
        return students;
    }

    public Student findByName(String name) {
        StudentCriteria criteria = new StudentCriteria();
        criteria.setUsePage(false);
        criteria.setName(name);
        List<Student> students = studentMapper.findByCriteria(criteria);
        if (students.size() == 0) {
            return null;
        }
        return students.get(0);
    }

    public List<Student> findByNick(String nick) {
        StudentCriteria criteria = new StudentCriteria();
        criteria.setUsePage(false);
        criteria.setNick(nick);
        List<Student> students = studentMapper.findByCriteria(criteria);
        return students;
    }

}