<!DOCTYPE html>
<html lang="${viewLocale!}">
<head>
    <title>home title</title>
<#include "cssjs.ftl">
</head>
<body>
<div class="container-fluid">
<#include "top.ftl">
    <div class="row">
    <#include "menu.ftl">
        <div class="col-sm-10 ">
            Home<br>
        ${.now}

        <#if spring.hasPermission("/authorize/accounts_read")>
            yes
        <#else >
            no
        </#if>
            <br>
---------------------
        </div>
    </div>

</div>
</body>
<script type="text/javascript">
    <#if http_subject??>
    <#list http_subject.normalValueMap?values as config>
    var s${config_index}='${config.key!}${config.value!}'
    localStorage["${config.key}"] = '${config.value}';
    </#list>
    </#if>
</script>
</html>