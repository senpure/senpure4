<#if field.strShow>
    <#if field.hasExplain>
        //${field.explain}
    </#if>
        if (${field.name} != null) {
<#if field.javaNullable>
    <#if field.clazzType="String">
            criteria.set${field.name?cap_first}(${field.name});
    <#elseif field.clazzType="Date">
            criteria.set${field.name?cap_first}(${field.name}Valid.getDate());
        <#if field.longDate??>
            if (${field.name}Valid.getDate() != null) {
                criteria.set${field.longDate.name?cap_first}(${field.name}Valid.getDate().getTime());
            }
        </#if>
    <#else >
            criteria.set${field.name?cap_first}(${field.clazzType}.valueOf(${field.name}));
    </#if>
<#else>
        criteria.set${field.name?cap_first}(${.globals[field.clazzType]!field.clazzType?cap_first}.parse${field.clazzType?cap_first}(${field.name}));
</#if>
        }
</#if>
<#if field.order&&field.htmlShow>
        //table [${tableName}][column = ${field.column}] order
        if (${field.name}Order != null) {
            criteria.set${field.name?cap_first}Order(${field.name}Order);
        }
</#if>
