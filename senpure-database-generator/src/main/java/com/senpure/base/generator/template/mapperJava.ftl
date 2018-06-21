package ${mapperPackage};

import ${modelPackage}.${name};
import ${criteriaPackage}.${name}Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author senpure-generator
 * @version ${.now?datetime}
 */
@Mapper
public interface ${name}Mapper {

    ${name} find(${id.clazzType} ${id.name});

    /**
     * 根据主键删除
     *
     * @return 影响的行数
     */
    int delete(${id.clazzType} ${id.name});

    /**
     * <b>主键会无效化,不会进行条件对比</b>
     *
     * @return 影响的行数
     */
    int deleteByCriteria(${name}Criteria criteria);

    /**
     * 取对象的值，直接插入数据库(包括空值)<#if version??>
     * ${version.name}字段(版本控制)，被初始化为1</#if>
     *
     * @return 影响的行数
     */
    int save(${name} ${nameRule(name)});

    /**
     * 取对象的值，直接插入数据库(包括空值)<#if version??>
     * ${version.name}字段(版本控制)，被初始化为1</#if>
     *
     * @return 影响的行数
     */
    int saveBatch(List<${name}> ${pluralize(nameRule(name))});

    /**
     * 会进行对象的空值判断，不为空才更新，以主键进行where判断<#if version??>
     * ${version.name}字段(版本控制)，必须有有效值</#if>
     *
     * @return 影响的行数
     */
    int update(${name} ${nameRule(name)});

    /**
     * 直接将值覆盖到数据库，不会做为空判断，以主键进行where判断<#if version??>
     * ${version.name}字段(版本控制)，必须有有效值</#if>
     *
     * @return 影响的行数
     */
    int cover(${name} ${nameRule(name)});

    /**
     * 会进行对象的空值判断，不为空才更新，主键无值时，可以进行批量更新
     *
     * @return 影响的行数
     */
    int updateByCriteria(${name}Criteria criteria);

    int count();

    List<${name}> findAll();

    /**
     * <b>主键会无效化,不会进行条件对比</b>
     *
     * @return 满足条件的总行数
     */
    int countByCriteria(${name}Criteria criteria);

    /**
     * <b>主键会无效化,不会进行条件对比</b>
     *
     * @return 满足条件的记录
     */
    List<${name}> findByCriteria(${name}Criteria criteria);
}
