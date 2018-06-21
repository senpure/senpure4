<!DOCTYPE html>
<html lang="${viewLocale!"zh-CN"}" xmlns="http://www.w3.org/1999/html">
<head>
    <title>server-error:${code!}</title>
<#include "cssjs.ftl">
</head>
<body>

<div class="container-fluid">
<#include "top.ftl">
    <div class="row">            <!--center row   start -->
    <#include "menu.ftl">
        <div class="col-sm-10 "> <!--center col-sm-10 start -->
            ${message!}
        </div>                   <!--center col-sm-10 end -->
    </div>                       <!--center row   end -->
</div>

</body>
</html>