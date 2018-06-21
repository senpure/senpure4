package ${criteriaPackage};

import ${modelPackage}.${name};
import com.senpure.base.criterion.Criteria;

import java.io.Serializable;
<#if hasDate>
import java.util.Date;
</#if>

/**<#if hasExplain>
 * ${explain}
 *</#if>
 * @author senpure-generator
 * @version ${.now?datetime}
 */
public class ${name}Criteria extends Criteria implements Serializable {
    private static final long serialVersionUID = ${serial(modelFieldMap)}L;

<#if id.hasExplain>
    //${id.explain}
</#if>
    ${id.accessType} ${id.clazzType} ${id.name};
<#if version??>
    <#if version.hasExplain>
    //${version.explain}
    </#if>
    ${version.accessType} ${version.clazzType} ${version.name};
</#if>
<#list modelFieldMap?values as field>
<#if field.hasExplain>
    //${field.explain}
</#if>
    ${field.accessType} ${field.clazzType} ${field.name};
    <#if field.order&&field.htmlShow>
    //table [${tableName}][column = ${field.column}] order
    ${field.accessType} String ${field.name}Order;
    </#if>
</#list>

    public static ${name} to${name}(${name}Criteria criteria, ${name} ${nameRule(name)}) {
        ${nameRule(name)}.set${id.name?cap_first}(criteria.get${id.name?cap_first}());
    <#list modelFieldMap?values as field>
        ${nameRule(name)}.set${field.name?cap_first}(criteria.<#if field.clazzType=="boolean">is<#else>get</#if>${field.name?cap_first}());
    </#list>
<#if version??>
    <#assign field = version />
        ${nameRule(name)}.set${field.name?cap_first}(criteria.<#if field.clazzType=="boolean">is<#else>get</#if>${field.name?cap_first}());
</#if>
        return ${nameRule(name)};
    }

    public ${name} to${name}() {
        ${name} ${nameRule(name)} = new ${name}();
        return to${name}(this, ${nameRule(name)});
    }

    /**
     * 将${name}Criteria 的有效值(不为空),赋值给 ${name}
     *
     * @return ${name}
     */
    public ${name} effective(${name} ${nameRule(name)}) {
        <#if id.javaNullable>
        if (get${id.name?cap_first}() != null) {
            ${nameRule(name)}.set${id.name?cap_first}(get${id.name?cap_first}());
        }
        <#else>
        ${nameRule(name)}.set${id.name?cap_first}(get${id.name?cap_first}());
        </#if>
<#list modelFieldMap?values as field>
    <#if field.javaNullable>
        if (<#if field.clazzType=="boolean">is<#else>get</#if>${field.name?cap_first}() != null) {
            ${nameRule(name)}.set${field.name?cap_first}(<#if field.clazzType=="boolean">is<#else>get</#if>${field.name?cap_first}());
        }
    <#else>
        ${nameRule(name)}.set${field.name?cap_first}(<#if field.clazzType=="boolean">is<#else>get</#if>${field.name?cap_first}());
    </#if>
</#list>
    <#if version??>
        <#assign field = version />
    <#if field.javaNullable>
        if (get${field.name?cap_first}() != null) {
            ${nameRule(name)}.set${field.name?cap_first}(get${field.name?cap_first}());
        }
    <#else>
        ${nameRule(name)}.set${field.name?cap_first}(get${field.name?cap_first}());
    </#if>
</#if>
        return ${nameRule(name)};
    }
<#if !hasDate>

    @Override
    public boolean isHasDate() {
        return false;
    }
</#if>

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("${name}Criteria{");
    <#if id.javaNullable>
        if (${id.name} != null) {
            sb.append("${id.name}=").append(${id.name}).append(",");
        }
<#else >
        sb.append("${id.name}=").append(${id.name}).append(",");
</#if>
<#if version??>
    <#if version.javaNullable>
        if (${version.name} != null) {
            sb.append("${version.name}=").append(${version.name}).append(",");
        }
    <#else >
        sb.append("${version.name}=").append(${version.name}).append(",");
    </#if>
</#if>
<#list modelFieldMap?values as field>
    <#if field.javaNullable>
        if (${field.name} != null) {
            sb.append("${field.name}=").append(${field.name}).append(",");
        }
    <#else >
        sb.append("${field.name}=").append(${field.name}).append(",");
    </#if>
</#list>
    }
<#assign field = id />
<#assign name >${name}Criteria</#assign>

<#include "getset.ftl">

<#list modelFieldMap?values as field>
    <#include "getsetStringNull.ftl">
    <#if field_has_next >

    </#if>
</#list>

<#if version??>
    <#assign field = version />
    <#include "getset.ftl">
</#if>

}