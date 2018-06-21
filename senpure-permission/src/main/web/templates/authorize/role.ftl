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
            <a href="/authorize/container/${criteria.containerId}/role"
               class="btn btn-success pull-left faa-parent animated-hover"> <span
                    class="glyphicon glyphicon-log-out faa-passing "></span> <@spring.message "label.role.create"/>
            </a>
            <div class="clearfix margin-bottom-10"></div>
        <#if total??&&items??>
            <#if total gt 0&&(items?size>0)>
                <form id="criteria-pagination" action="/authorize/container/${criteria.containerId}/roles" method="post" modal-form="true"

                      auto-refresh="true" refresh-interval="300000">
                    <@spring.formHiddenInput "criteria.pageSize"  'jkl="jkl"'/>
                    <@spring.formHiddenInput "criteria.containerId"  />
                    <div class="criteria-order">
                        <input type="hidden" name="createTimeOrder" id="createTimeOrder-pagination"
                               value="${criteria.createTimeOrder!}"/>
                    </div>
                </form>
                <div <#include "../pageDivAttr.ftl">>
                    <table fixed-table-title="true" fixed-table-widht-offset="678"
                           class="margin-top-5 table table-striped table-bordered table-hover table-condensed ">
                        <tr>
                            <th>
                                <@spring.message "table.index"/>
                            </th>
                            <th>
                                <@spring.message "label.name"/>
                            </th>
                            <th data-order="true" data-form="#criteria-pagination"
                                data-value="${criteria.createTimeOrder!}"
                                data-field="createTimeOrder" data-init="ASC">
                                <@spring.message "label.create.date"/>
                            </th>
                            <th>
                                <@spring.message "label.operation"/>
                            </th>
                        </tr>
                        <#list items as item>
                            <tr>
                                <td>${item_index+1}</td>
                                <td>${item.name}</td>
                                <td>${item.createDate?datetime}</td>
                                <td>
                                    <a href="/authorize/role/${item.id?c}/permissions">
                                        <@spring.message "label.permission.manager"/>
                                    </a>
                                   </td>
                            </tr>
                        </#list>
                    </table>
                </div>
            <#else>
                <div class="text-danger margin-top-10">
                    <@spring.message "label.result.empty"/>
                </div>
            </#if>
        </#if>
        </div><!-- col-sm-10 end-->
    </div>

</div>
</body>
</html>