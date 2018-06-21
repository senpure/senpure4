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

<#if field.hasExplain>
    /**
     * set ${field.explain}
     *
     * @return
     */</#if>
    public ${name} set${field.name?cap_first}(${field.clazzType} ${field.name}) {
        this.${field.name} = ${field.name};
        return this;
    }
