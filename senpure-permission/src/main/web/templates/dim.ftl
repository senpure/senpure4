<!DOCTYPE html>
<html lang="${viewLocale!}">
<head>
<#include "cssjs.ftl">
</head>
<body>

<div class="container-fluid">
<#include "top.ftl">
    <div class="row">
    <#include "menu.ftl">
        <div class="col-sm-10 ">

            ${message}
        </div><!--col-sm-10 end -->
    </div><!--row   end -->
</div>
</body>
</html>