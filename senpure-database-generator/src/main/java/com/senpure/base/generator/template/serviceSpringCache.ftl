package ${servicePackage};

import ${modelPackage}.${name};
import ${criteriaPackage}.${name}Criteria;
import ${mapperPackage}.${name}Mapper;
<#if version??>
import com.senpure.base.exception.OptimisticLockingFailureException;
</#if>
<#if modelPackage !="com.senpure.base.model">
import com.senpure.base.service.BaseService;
</#if>
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
 * @version ${.now?datetime}
 */
@Service
@CacheConfig(cacheNames = "${nameRule(name)}")
public class ${name}Service extends BaseService {

    @Autowired
    private ${name}Mapper ${nameRule(name)}Mapper;

    @CacheEvict(key = "#${id.name}")
    public void clearCache(${id.clazzType} ${id.name}) {
    }

    @CacheEvict(allEntries = true)
    public void clearCache() {
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public ${name} find(${id.clazzType} ${id.name}) {
        return ${nameRule(name)}Mapper.find(${id.name});
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public ${name} findOnlyCache(${id.clazzType} ${id.name}) {
        return null;
    }

    @CachePut(key = "#id", unless = "#result == null")
    public ${name} findSkipCache(${id.clazzType} ${id.name}) {
        return ${nameRule(name)}Mapper.find(${id.name});
    }

    public int count() {
        return ${nameRule(name)}Mapper.count();
    }

    public List<${name}> findAll() {
        return ${nameRule(name)}Mapper.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#${id.name}")
    public boolean delete(${id.clazzType} ${id.name}) {
        int result = ${nameRule(name)}Mapper.delete(${id.name});
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#criteria.${id.name}", allEntries = true)
    public int delete(${name?cap_first}Criteria criteria) {
        return ${nameRule(name)}Mapper.deleteByCriteria(criteria);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(${name?cap_first} ${nameRule(name)}) {
<#if !id.databaseId >
    <#if id.clazzType =="Long" || id.clazzType =="long">
        ${nameRule(name)}.set${id.name?cap_first}(idGenerator.nextId());
    <#else >
        //TODO 注意是否有主键
        //${nameRule(name)}.set${id.name?cap_first}();
    </#if>
</#if>
        int result = ${nameRule(name)}Mapper.save(${nameRule(name)});
        return result == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(List<${name?cap_first}> ${pluralize(nameRule(name))}) {
        if (${pluralize(nameRule(name))} == null || ${pluralize(nameRule(name))}.size() == 0) {
            return 0;
        }
<#if !id.databaseId >
    <#if id.clazzType =="Long" || id.clazzType =="long">
        for (${name} ${nameRule(name)} : ${pluralize(nameRule(name))}) {
            ${nameRule(name)}.set${id.name?cap_first}(idGenerator.nextId());
        }
    <#else >
        //TODO 注意是否有主键
        for (${name} ${nameRule(name)} : ${pluralize(nameRule(name))}) {
            //${nameRule(name)}.set${id.name?cap_first}();
        }
    </#if>
</#if>
        return ${nameRule(name)}Mapper.saveBatch(${pluralize(nameRule(name))});
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(${name?cap_first}Criteria criteria) {
<#if !id.databaseId >
    <#if id.clazzType =="Long" || id.clazzType =="long">
        criteria.set${id.name?cap_first}(idGenerator.nextId());
    <#else >
        //TODO 注意是否有主键
        //criteria.set${id.name?cap_first}();
    </#if>
</#if>
        int result = ${nameRule(name)}Mapper.save(criteria.to${name?cap_first}());
        return result == 1;
    }

<#if version??>
    /**
     * 更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#${nameRule(name)}.${id.name}")
    public boolean update(${name?cap_first} ${nameRule(name)}) {
        int updateCount = ${nameRule(name)}Mapper.update(${nameRule(name)});
        if (updateCount == 0) {
            throw new OptimisticLockingFailureException(${nameRule(name)}.getClass() + ",[" + ${nameRule(name)}.get${id.name?cap_first}() + "],版本号冲突,版本号[" + ${nameRule(name)}.get${version.name?cap_first}() + "]");
        }
        return true;
    }

    /**
     * 当版本号，和主键不为空时，更新失败会抛出OptimisticLockingFailureException
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#criteria.${id.name}", allEntries = true)
    public int update(${name?cap_first}Criteria criteria) {
        int updateCount = ${nameRule(name)}Mapper.updateByCriteria(criteria);
        if (updateCount == 0 && criteria.get${version.name?cap_first}() != null
                && criteria.get${id.name?cap_first}() != null) {
            throw new OptimisticLockingFailureException(criteria.getClass() + ",[" + criteria.get${id.name?cap_first}() + "],版本号冲突,版本号[" + criteria.get${version.name?cap_first}() + "]");
        }
        return updateCount;
    }
<#else >
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#${nameRule(name)}.${id.name}")
    public boolean update(${name?cap_first} ${nameRule(name)}) {
        int updateCount = ${nameRule(name)}Mapper.update(${nameRule(name)});
        if (updateCount == 0) {
            return false;
        }
        return true;
    }
</#if>

    @Transactional(readOnly = true)
    public ResultMap findPage(${name?cap_first}Criteria criteria) {
        ResultMap resultMap = ResultMap.success();
        //是否是主键查找
        <#if id.javaNullable>
        if (criteria.get${id.name?cap_first}() != null) {
        <#else>
        if (criteria.get${id.name?cap_first}() > 0) {
        </#if>
            ${name} ${nameRule(name)} = ${nameRule(name)}Mapper.find(criteria.get${id.name?cap_first}());
            if (${nameRule(name)} != null) {
                List<${name}> ${pluralize(nameRule(name))} = new ArrayList<>(16);
                ${pluralize(nameRule(name))}.add(${nameRule(name)});
                resultMap.putTotal(1);
                resultMap.putItems(${pluralize(nameRule(name))});
            } else {
                resultMap.putTotal(0);
            }
            return resultMap;
        }
        int total = ${nameRule(name)}Mapper.countByCriteria(criteria);
        resultMap.putTotal(total);
        if (total == 0) {
            return resultMap;
        }
        //检查页数是否合法
        checkPage(criteria, total);
        List<${name}> ${pluralize(nameRule(name))} = ${nameRule(name)}Mapper.findByCriteria(criteria);
        resultMap.putItems(${pluralize(nameRule(name))});
        return resultMap;
    }

    public List<${name}> find(${name?cap_first}Criteria criteria) {
        //是否是主键查找
        <#if id.javaNullable>
        if (criteria.get${id.name?cap_first}() != null) {
        <#else>
        if (criteria.get${id.name?cap_first}() > 0) {
        </#if>
            List<${name}> ${pluralize(nameRule(name))} = new ArrayList<>(16);
            ${name} ${nameRule(name)} = ${nameRule(name)}Mapper.find(criteria.get${id.name?cap_first}());
            if (${nameRule(name)} != null) {
                ${pluralize(nameRule(name))}.add(${nameRule(name)});
            }
            return ${pluralize(nameRule(name))};
        }
        return ${nameRule(name)}Mapper.findByCriteria(criteria);
    }

    public ${name} findOne(${name?cap_first}Criteria criteria) {
        //是否是主键查找
        <#if id.javaNullable>
        if (criteria.get${id.name?cap_first}() != null) {
        <#else>
        if (criteria.get${id.name?cap_first}() > 0 ) {
        </#if>
            return ${nameRule(name)}Mapper.find(criteria.get${id.name?cap_first}());
        }
        List<${name}> ${pluralize(nameRule(name))} = ${nameRule(name)}Mapper.findByCriteria(criteria);
        if (${pluralize(nameRule(name))}.size() == 0) {
            return null;
        }
        return ${pluralize(nameRule(name))}.get(0);
    }
<#list findModeFields as field>

    public <#if field.findOne>${name}<#else >List<${name}></#if> findBy${field.name?cap_first}(${field.clazzType} ${field.name}) {
        ${name}Criteria criteria = new ${name}Criteria();
        criteria.setUsePage(false);
        criteria.set${field.name?cap_first}(${field.name});
        List<${name}> ${pluralize(nameRule(name))} = ${nameRule(name)}Mapper.findByCriteria(criteria);
<#if field.findOne>
        if (${pluralize(nameRule(name))}.size() == 0) {
            return null;
        }
        return ${pluralize(nameRule(name))}.get(0);
<#else>
        return ${pluralize(nameRule(name))};
</#if>
    }
</#list>

}