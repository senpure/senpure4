package com.senpure.base.mapper;

import com.senpure.base.model.Menu;
import com.senpure.base.criteria.MenuCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
@Mapper
public interface MenuMapper {

    Menu find(Integer id);

    /**
     * 根据主键删除
     *
     * @return 影响的行数
     */
    int delete(Integer id);

    /**
     * <b>主键会无效化,不会进行条件对比</b>
     *
     * @return 影响的行数
     */
    int deleteByCriteria(MenuCriteria criteria);

    /**
     * 取对象的值，直接插入数据库(包括空值)
     * version字段(版本控制)，被初始化为1
     *
     * @return 影响的行数
     */
    int save(Menu menu);

    /**
     * 取对象的值，直接插入数据库(包括空值)
     * version字段(版本控制)，被初始化为1
     *
     * @return 影响的行数
     */
    int saveBatch(List<Menu> menus);

    /**
     * 会进行对象的空值判断，不为空才更新，以主键进行where判断
     * version字段(版本控制)，必须有有效值
     *
     * @return 影响的行数
     */
    int update(Menu menu);

    /**
     * 直接将值覆盖到数据库，不会做为空判断，以主键进行where判断
     * version字段(版本控制)，必须有有效值
     *
     * @return 影响的行数
     */
    int cover(Menu menu);

    /**
     * 会进行对象的空值判断，不为空才更新，主键无值时，可以进行批量更新
     *
     * @return 影响的行数
     */
    int updateByCriteria(MenuCriteria criteria);

    int count();

    List<Menu> findAll();

    /**
     * <b>主键会无效化,不会进行条件对比</b>
     *
     * @return 满足条件的总行数
     */
    int countByCriteria(MenuCriteria criteria);

    /**
     * <b>主键会无效化,不会进行条件对比</b>
     *
     * @return 满足条件的记录
     */
    List<Menu> findByCriteria(MenuCriteria criteria);
}
