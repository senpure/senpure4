package com.senpure.book.mapper;

import com.senpure.book.model.Author;
import com.senpure.book.criteria.AuthorCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author senpure-generator
 * @version 2018-1-25 15:15:45
 */
@Mapper
public interface AuthorMapper {

    Author find(Long id);

    /**
     * 根据主键删除
     *
     * @return 影响的行数
     */
    int delete(Long id);

    /**
     * <b>主键会无效化,不会进行条件对比</b>
     *
     * @return 影响的行数
     */
    int deleteByCriteria(AuthorCriteria criteria);

    /**
     * 取对象的值，直接插入数据库(包括空值)
     * version字段(版本控制)，被初始化为1
     *
     * @return 影响的行数
     */
    int save(Author author);

    /**
     * 取对象的值，直接插入数据库(包括空值)
     * version字段(版本控制)，被初始化为1
     *
     * @return 影响的行数
     */
    int saveBatch(List<Author> authors);

    /**
     * 会进行对象的空值判断，不为空才更新，以主键进行where判断
     * version字段(版本控制)，必须有有效值
     *
     * @return 影响的行数
     */
    int update(Author author);

    /**
     * 直接将值覆盖到数据库，不会做为空判断，以主键进行where判断
     * version字段(版本控制)，必须有有效值
     *
     * @return 影响的行数
     */
    int cover(Author author);

    /**
     * 会进行对象的空值判断，不为空才更新，主键无值时，可以进行批量更新
     *
     * @return 影响的行数
     */
    int updateByCriteria(AuthorCriteria criteria);

    int count();

    List<Author> findAll();

    /**
     * <b>主键会无效化,不会进行条件对比</b>
     *
     * @return 满足条件的总行数
     */
    int countByCriteria(AuthorCriteria criteria);

    /**
     * <b>主键会无效化,不会进行条件对比</b>
     *
     * @return 满足条件的记录
     */
    List<Author> findByCriteria(AuthorCriteria criteria);
}
