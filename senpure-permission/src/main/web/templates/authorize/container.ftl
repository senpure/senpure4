<!DOCTYPE html>
<html lang="${viewLocale!"zh-CN"}" xmlns="http://www.w3.org/1999/html">
<head>
    <title>account 列表</title>
<#include "../cssjs.ftl">
</head>
<body>
<div class="container-fluid">
<#include "../top.ftl">
    <div class="row">            <!--center row   start -->
    <#include "../menu.ftl">
        <div class="col-sm-10 "> <!--center col-sm-10 start -->

            <form class="form-inline" action="/authorize/container" method="post" autocomplete="false"
                  modal-form="true">
                <div class="input-error text-danger">
                ${message!}
                </div>
                <div class="form-group">
                    <label for="name">     <@spring.message "label.group.name"/>
                    </label>
                    <div class="input-group">
                        <span class="input-group-addon "><span class="glyphicon glyphicon-fire"></span></span>
                        <input type="text" class="form-control" name="name">

                    </div>

                    <div class="input-error text-danger form-group">
                    <@spring.bind "criteria.name" />
                        <@spring.showErrors ""/>
                    </div>
                </div>
                <div class="form-group">
                    <button style="width: 100%;" class="btn btn-success  faa-parent animated-hover "
                            modal-button="true"><span class="glyphicon glyphicon-pencil faa-tada faa-slow"></span>
                    <@spring.message "button.create"/>
                    </button>
                </div>
            </form>

            <div class="clearfix margin-bottom-10"></div>


        <#if total??&&items??>
            <#if total gt 0&&(items?size>0)>
                <form id="criteria-pagination" action="/authorize/accounts"
                      method="post" modal-form="true"
                      class="form-inline"
                      auto-refresh="true" refresh-interval="300000">
                    <@spring.formHiddenInput "criteria.pageSize" />

                    <div class="criteria-order">
                    <#--
                        <input type="hidden" name="createTimeOrder" id="createTimeOrder-pagination"
                               value="${criteria.createTimeOrder}">
                               -->
                    </div> <!--排序信息-->
                </form><!--条件form-->
                <div class="margin-top-10 "></div>
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
                            <th>
                                <@spring.message "label.create.date"/>
                            </th>
                            <th>
                                <@spring.message "label.operation"/>
                            </th>
                        </tr>
                        <#list items as item>
                            <tr>
                                <td>${item_index+1}</td>
                                <td>
                                    <a href="/authorize/container/${item.id}/accounts">
                                    ${item.name}
                                    </a>
                              </td>
                                <td>
                                ${item.createTime?number_to_datetime?string(datetime_format)}</td>
                                <td>
                                    <!--权限管理-->
                                    <a href="/authorize/container/${item.id}/permissions">
                                        <@spring.message "label.permission.manager"/>
                                    </a>
                                    ||
                                    <a href="/authorize/container/${item.id}/roles">   <!--角色管理-->
                                        <@spring.message "label.role.manager"/>
                                    </a>
                                    ||
                                    <a><a href="/authorize/container/${item.id}/account">
                                        <@spring.message "label.account.create"/>
                                    </a>
                                </td>

                            </tr>
                        </#list>
                    </table>
                </div><!--items content div-->
            </#if>
        <#else>
            <div class="text-danger margin-top-10">
                <@spring.message "label.result.empty"/>
            </div>
        </#if>
        </div>                   <!--center col-sm-10 end -->
    </div>                       <!--center row   end -->
</div>

</body>
</html>