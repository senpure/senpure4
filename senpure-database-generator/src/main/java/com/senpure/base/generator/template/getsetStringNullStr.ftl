<#if field.strShow>
<#if field.hasExplain>
    /**
     * get ${field.explain}
     *
     * @return
     */
</#if>
    public String get${field.name?cap_first}() {
        return ${field.name};
    }

</#if>
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
<#if field.strShow>
<#if field.hasExplain>
    /**
     * set ${field.explain}
     *
     * @return
     */</#if>
    public ${name} set${field.name?cap_first}(String ${field.name}) {
        if (${field.name} != null && ${field.name}.trim().length() == 0) {
            return this;
        }
        this.${field.name} = ${field.name};
        return this;
    }

</#if>
<#if field.order&&field.htmlShow>
    /**
     * set table [${tableName}][column = ${field.column}] order DESC||ASC
     *
     * @return
     */
    public ${name} set${field.name?cap_first}Order(String ${field.name}Order) {
        if (${field.name}Order != null && ${field.name}Order.trim().length() == 0) {
            this.${field.name}Order = null;
            return this;
        }
        this.${field.name}Order = ${field.name}Order;
        return this;
    }

</#if>
