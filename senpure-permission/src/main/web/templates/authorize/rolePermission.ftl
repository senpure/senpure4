<!DOCTYPE html>
<html lang="${viewLocale!}">
<head>
<#include "../cssjs.ftl">
</head>
<body>

<div class="container-fluid">
<#include "../top.ftl">
    <div class="row">
    <#include "../menu.ftl">
        <div class="col-sm-10 ">
            <div class="row">
            <#if items??>
                <#list items as item>
                    <div class="col-sm-3">
                        <div class="checkbox text-l " title="${item.description!item.name}">
                            <label>
                                <#if item.has>
                                    <input type="checkbox" checked permissionId="${item.id?c}">
                                    <span class="text-success">   ${item.readableName}</span>
                                <#else>
                                    <input type="checkbox" permissionId="${item.id?c}">
                                    <span class="text-danger">   ${item.readableName}</span>
                                </#if>

                            </label>

                        </div>
                    </div>
                </#list>
            </#if>
            </div>

        </div><!--col-sm-10 end -->
    </div><!--row   end -->
</div>
</body>
<script type="text/javascript">
    var roleId = '${criteria.roleId?c}'
    $("input[type=checkbox]").on("click", function (event) {
        var checked=$(event.target).prop("checked");
        $.post("/authorize/role/"+roleId+"/permission/"+$(event.target).attr("permissionId"),
                {
                    "award": checked
                    ,
                    "_method":"put"
                }
        );

        if(checked)
        {

            $(event.target).next("span").removeClass("text-danger").addClass("text-success")
        }
        else {
            $(event.target).next("span").removeClass("text-success").addClass("text-danger")
        }


    });
</script>
</html>