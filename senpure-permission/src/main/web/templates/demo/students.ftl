<!DOCTYPE html>
<html lang="${viewLocale!"zh-CN"}" xmlns="http://www.w3.org/1999/html">
<head>
    <title>students 列表</title>
<#include "../cssjs.ftl">
</head>
<body>
<div class="container-fluid">
<#include "../top.ftl">
    <div class="row">            <!--center row   start -->
    <#include "../menu.ftl">
        <div class="col-sm-10 "> <!--center col-sm-10 start -->

            <form id="criteria" action="/demo/students"
                  method="post" modal-form="true"
                  auto-refresh="true" refresh-interval="300000"
                  class="form-inline">
            <#if code??&&code!=1 >
                <div class="input-error text-danger">
                ${message!}
                </div>
            </#if>
                <input type="hidden" name="datePattern" value="${criteria.datePattern!"yyyy-MM-dd HH:mm:ss"}">
                <div class="form-group">
                    <label class="control-label " for="name">
                    <@spring.message "label.account.format"/>
                    </label>
                    <div class="input-group ">
						<span class="input-group-addon "> <span
                                class="glyphicon glyphicon-user"></span>
						</span>
                        <input pattern="^[\w]{3,24}$" name="name"
                               class="form-control" id="name"
                               placeholder="<@spring.message code="tip.account.placeholder" />"
                               value="${criteria.name!}"
                        />
                        <span class="glyphicon  form-control-feedback"
                              aria-hidden="true"></span>
                    </div>
                    <div class="input-error text-danger">
                    <@spring.bind "criteria.name" />
                            <@spring.showErrors ""/>
                    </div>
                </div><!--form-group end  -->
                <div class="form-group ">
                    <label class="control-label " for="age">
                    <@spring.message "label.account"/>
                    </label>
                    <div class="input-group ">
						<span class="input-group-addon "> <span
                                class="glyphicon glyphicon-user"></span>
						</span>
                        <input pattern="^[\w]{3,24}$" name="age"
                               class="form-control" id="age"
                               placeholder="<@spring.message code="tip.account.placeholder" />"
                               value="${criteria.age!}"
                        />
                        <span class="glyphicon  form-control-feedback"
                              aria-hidden="true"></span>
                    </div>
                    <div class="input-error text-danger">
                    <@spring.bind "criteria.age" />
                            <@spring.showErrors ""/>
                    </div>
                </div><!--form-group end  -->

                <div class="criteria-br"></div>

                <div class="form-group">

                    <label for="startDate">
                        开始时间
                    </label>
                    <div class="input-group">
                            <span class="input-group-addon btn " datetimepicker-target='#startDate'><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        <!-- data-date-autoclose="true"
                        data-today-highlight="true" -->
                        <input type="text" id="startDate" name="startDate" class="form-control"
                               data-date-format="${datetime_format?replace("mm","ii")?replace("MM","mm")?replace("HH","hh")}"
                               data-date-language="${viewLocale!"zh-CN"}" data-min-view="1" datetimepicker-receive="yes"
                               value="${criteria.startDate!}"
                        >
                    </div>
                    <label for="endDate">结束时间</label>

                    <div class="input-group">
                            <span class="input-group-addon btn " datetimepicker-target='#endDate'><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        <input type="text" id="endDate" name="endDate" class="form-control"
                               data-date-format="${datetime_format?replace("mm","ii")?replace("MM","mm")?replace("HH","hh")}"
                               data-date-language="${viewLocale!"zh-CN"}" data-min-view="1" datetimepicker-receive="yes"
                               data-date-type="end"
                               value="${criteria.endDate!}"
                        >

                    </div>
                    <div class="input-error text-danger">
                    <#if validator??>
                    ${validator.endDate!}
                    </#if>
                         <@spring.valid "startDate" />
                    </div>
                </div>
                <div class="form-group ">
                    <button class="btn btn-success  faa-parent animated-hover " modal-button="true">
                        <span class="glyphicon glyphicon-search faa-tada faa-slow"></span>
                    <@spring.message "button.query"/>
                    </button>
                </div>
            </form>

        <#if total??&&items??>
            <#if total gt 0&&(items?size>0)>
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
                                外号
                            </th>
                            <th>
                                年龄
                            </th>
                            <th>
                                学号
                            </th>
                            <th>
                                联系电话
                            </th>
                            <th
                                    入学时间
                            </th>
                            <th>
                                <@spring.message "label.operation"/>
                            </th>
                        </tr>
                        <#list items as item>
                            <tr>
                                <td>${item_index+1}</td>
                                <td>${item.name}</td>
                                <td>${item.nick}
                                </td>
                                <td>${item.age}
                                </td>
                                <td>${item.num}
                                </td>
                                <td>${item.phoneNumber}
                                </td>
                                <td>
                                ${item.createTime?number_to_datetime?string(datetime_format)}</td>

                                <td>

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