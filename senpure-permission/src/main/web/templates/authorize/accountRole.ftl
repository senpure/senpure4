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
                <#if items?size==0>
                    <div class="text-danger margin-top-10">
                        <@spring.message "label.result.empty"/>
                    </div>
                </#if>
                <#list items as item>
                    <div class="col-sm-3">
                        <div class="checkbox text-l " title="${item.description!item.name}">
                            <label>
                                <#if item.has>
                                    <input type="checkbox" checked roleId="${item.id?c}">
                                    <span class="text-success">   ${item.name}</span>
                                <#else>
                                    <input type="checkbox" roleId="${item.id?c}">
                                    <span class="text-danger">   ${item.name}</span>
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
    var accountId = '${accountId?c}'
    $("input[type=checkbox]").on("click", function (event) {
        var checked=$(event.target).prop("checked");
        $.post("/authorize/account/"+accountId+"/role/"+$(event.target).attr("roleId"),
                {
                   // "roleId": $(event.target).attr("roleId"),
                   // "accountId":accountId ,
                    "award": checked,
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