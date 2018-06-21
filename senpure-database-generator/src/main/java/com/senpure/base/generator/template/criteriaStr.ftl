package ${criteriaPackage};

import com.senpure.base.criterion.CriteriaStr;
<#if hasDate>
import com.senpure.base.struct.PatternDate;
import com.senpure.base.validator.DynamicDate;
</#if>

import java.io.Serializable;

<#global "int"="Integer"/>
/**<#if hasExplain>
 * ${explain}
 *</#if>
 * @author senpure-generator
 * @version ${.now?datetime}
 */
public class ${name}CriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = ${serial(modelFieldMap)}L;

<#if id.hasExplain>
    //${id.explain}
</#if>
    ${id.accessType} String ${id.name};
<#if version??>
    <#if version.hasExplain>
    //${version.explain}
    </#if>
    ${version.accessType} String ${version.name};
</#if>
<#list modelFieldMap?values as field>
 <#if field.strShow>
    <#if field.hasExplain>
    //${field.explain}
    </#if>
    ${field.accessType} String ${field.name};
</#if>
    <#if field.clazzType="Date">
    @DynamicDate
    ${field.accessType} PatternDate ${field.name}Valid = new PatternDate();
    </#if>
    <#if field.order&&field.htmlShow>
    //table [${tableName}][column = ${field.column}] order
    ${field.accessType} String ${field.name}Order;
    </#if>
</#list>

    public ${name}Criteria to${name}Criteria() {
        ${name}Criteria criteria = new ${name}Criteria();
        criteria.setUsePage(Boolean.valueOf(getUsePage()));
        criteria.setPage(Integer.valueOf(getPage()));
        criteria.setPageSize(Integer.valueOf(getPageSize()));
        criteria.setStartDate(getStartDateValid().getDate());
        criteria.setEndDate(getEndDateValid().getDate());
<#assign field = id/>
<#include "strFieldTo.ftl">
<#if version??>
<#assign field = version/>
<#include "strFieldTo.ftl">
</#if>
<#list modelFieldMap?values as field>
<#include "strFieldTo.ftl">
</#list>
        return criteria;
    }

<#if hasDate>
    @Override
    public void setDatePattern(String datePattern) {
        super.setDatePattern(datePattern);
        <#list modelFieldMap?values as field>
        <#if field.clazzType="Date">
        ${field.name}Valid.setPattern(datePattern);
        </#if>
        </#list>
    }
</#if>

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("${name}CriteriaStr{");
        if (${id.name} != null) {
            sb.append("${id.name}=").append(${id.name}).append(",");
        }
<#if version??>
        if (${version.name} != null) {
            sb.append("${version.name}=").append(${version.name}).append(",");
        }
</#if>
<#list modelFieldMap?values as field>
    <#if field.strShow>
        if (${field.name} != null) {
            sb.append("${field.name}=").append(${field.name}).append(",");
        }
    </#if>
</#list>
    }

    @Override
    protected void afterStr(StringBuilder sb) {
<#list modelFieldMap?values as field>
        <#if field.order&&field.htmlShow>
        if (${field.name}Order != null) {
            sb.append("${field.name}Order=").append(${field.name}Order).append(",");
        }
        </#if>
</#list>
        super.afterStr(sb);
    }

<#assign field = id />
<#assign name >${name}CriteriaStr</#assign>

<#include "getsetStringNullStr.ftl">

<#if version??>
    <#assign field = version />
    <#include "getsetStringNullStr.ftl">

</#if>
<#list modelFieldMap?values as field>
    <#include "getsetStringNullStr.ftl">
    <#if field_has_next >

    </#if>
</#list>

}