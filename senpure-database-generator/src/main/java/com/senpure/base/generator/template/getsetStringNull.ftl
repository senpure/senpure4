<#if field.hasExplain>
    /**
     * <#if field.clazzType="boolean">is<#else>get</#if> ${field.explain}
     *
     * @return
     */
</#if>
    public ${field.clazzType} <#if field.clazzType="boolean">is<#else>get</#if>${field.name?cap_first}() {
        return ${field.name};
    }

<#if field.order&&field.htmlShow>
    /**
     * get table [${tableName}][column = ${field.column}] order
     *
     * @return
     */
    public String get${field.name?cap_first}Order() {
        return ${field.name}Order;
    }

</#if>
<#if field.hasExplain>
    /**
     * set ${field.explain}
     *
     * @return
     */</#if>
    public ${name} set${field.name?cap_first}(${field.clazzType} ${field.name}) {
        <#if field.clazzType=='String'>
        if (${field.name} != null && ${field.name}.trim().length() == 0) {
            this.${field.name} = null;
            return this;
        }
        </#if>
        this.${field.name} = ${field.name};
        return this;
    }

<#if field.order&&field.htmlShow>
    /**
     * set table [${tableName}][column = ${field.column}] order DESC||ASC
     *
     * @return
     */
    public ${name} set${field.name?cap_first}Order(String ${field.name}Order) {
        this.${field.name}Order = ${field.name}Order;
        putSort("${field.column}", ${field.name}Order);
        return this;
    }

</#if>
