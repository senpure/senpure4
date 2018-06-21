<!DOCTYPE html>
<html lang="zh-CN" xmlns="http://www.w3.org/1999/html">
<head>
    <title>student list-/demo/students/${criteria.page!}</title>
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
                <!--
                <div class="form-group margin-top-10">
                    <label class="control-label " for="id">
                    id&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </label>
                    <div class="input-group ">
						<span class="input-group-addon "> <span
                                class="fa fa-key"></span>
						</span>
                        <input name="id"
                               class="form-control" id="id"
                               placeholder="id"
                               value="${criteria.id!}"
                        />
                    </div>
                    <div class="input-error text-danger">
                    <@spring.valid  "id"/>
                    </div>
                </div>form-group end  -->
                <div class="form-group margin-top-10">
                    <label class="control-label " for="clazzId">
                    clazzId&nbsp;&nbsp;
                    </label>
                    <div class="input-group ">
						<span class="input-group-addon "> <span
                                class="glyphicon glyphicon-th"></span>
						</span>
                        <input name="clazzId"
                               class="form-control" id="clazzId"
                               placeholder="clazzId"
                               value="${criteria.clazzId!}"
                        />
                    </div>
                    <div class="input-error text-danger">
                    <@spring.valid  "clazzId"/>
                    </div>
                </div><!--form-group end  -->
                <div class="form-group margin-top-10">
                    <label class="control-label " for="name">
                    name&nbsp;&nbsp;&nbsp;&nbsp;
                    </label>
                    <div class="input-group ">
						<span class="input-group-addon "> <span
                                class="glyphicon glyphicon-th"></span>
						</span>
                        <input name="name"
                               class="form-control" id="name"
                               placeholder="name"
                               value="${criteria.name!}"
                        />
                    </div>
                    <div class="input-error text-danger">
                    <@spring.valid  "name"/>
                    </div>
                </div><!--form-group end  -->
                <div class="form-group margin-top-10">
                    <label class="control-label " for="nick">
                    nick&nbsp;&nbsp;&nbsp;&nbsp;
                    </label>
                    <div class="input-group ">
						<span class="input-group-addon "> <span
                                class="glyphicon glyphicon-th"></span>
						</span>
                        <input name="nick"
                               class="form-control" id="nick"
                               placeholder="nick"
                               value="${criteria.nick!}"
                        />
                    </div>
                    <div class="input-error text-danger">
                    <@spring.valid  "nick"/>
                    </div>
                </div><!--form-group end  -->
                <br>
                <div class="form-group margin-top-10">

                    <label for="startDate">
                    <@spring.message "label.time.start"/>
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
                        placeholder="createTime"
                        >
                    </div>
                    <label for="endDate">
                    <@spring.message "label.time.end"/>
                    </label>
                    <div class="input-group">
                            <span class="input-group-addon btn " datetimepicker-target='#endDate'><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                    <input type="text" id="endDate" name="endDate" class="form-control"
                                   data-date-format="${datetime_format?replace("mm","ii")?replace("MM","mm")?replace("HH","hh")}"
                                   data-date-language="${viewLocale!"zh-CN"}" data-min-view="1" datetimepicker-receive="yes"
                                   data-date-type="end"
                                   value="${criteria.endDate!}"
                        placeholder="createTime"
                        >

                    </div>
                    <div class="input-error text-danger">
                    <@spring.valid "startDate" />
                          <@spring.valid "endDate" />
                    </div>
                </div>
                <!--hide input-->
                <input type="hidden" name="datePattern" value="${criteria.datePattern!"yyyy-MM-dd HH:mm:ss"}">
                <input type="hidden" name="pageSize" value="${criteria.pageSize!}">

                <!--hide input-->
                <div class="criteria-order">
                <input type="hidden" name="nameOrder" id="nameOrder-pagination"
                       value="${criteria.nameOrder!}">
                <input type="hidden" name="nickOrder" id="nickOrder-pagination"
                       value="${criteria.nickOrder!}">
                <input type="hidden" name="createTimeOrder" id="createTimeOrder-pagination"
                       value="${criteria.createTimeOrder!}">
                <input type="hidden" name="clazzIdOrder" id="clazzIdOrder-pagination"
                       value="${criteria.clazzIdOrder!}">
                </div>
                <div class="form-group margin-top-10">
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
                    id
                    </th>
                        <th data-order="true" data-form="#criteria-pagination"
                            data-value="${criteria.nameOrder!}"
                            data-field="nameOrder" data-init="ASC"
                         >
                        name
                        </th>
                        <th data-order="true" data-form="#criteria-pagination"
                            data-value="${criteria.nickOrder!}"
                            data-field="nickOrder" data-init="ASC"
                         >
                        nick
                        </th>
                        <th data-order="true" data-form="#criteria-pagination"
                            data-value="${criteria.createTimeOrder!}"
                            data-field="createTimeOrder" data-init="DESC"
                         >
                        createTime
                        </th>
                        <th>
                        phoneNumber
                        </th>
                        <th>
                        age
                        </th>
                        <th>
                        num
                        </th>
                        <th>
                        good
                        </th>
                        <th data-order="true" data-form="#criteria-pagination"
                            data-value="${criteria.clazzIdOrder!}"
                            data-field="clazzIdOrder" data-init="ASC"
                         >
                        clazzId
                        </th>
                    <th>
                     <@spring.message "label.operation"/>
                    </th>
                </tr>
            <#list items as item>
                <tr>
                    <td>${item_index+1}</td>
                    <td>
                        ${item.id?c}
                    </td>
                    <td>
                        ${item.name!}
                    </td>
                    <td>
                        ${item.nick!}
                    </td>
                    <td>
                    <#if item.createTime??>
                        ${item.createTime?number_to_datetime?string(datetime_format)}
                    </#if>
                    </td>
                    <td>
                        ${item.phoneNumber!}
                    </td>
                    <td>
                        ${item.age?c}
                    </td>
                    <td>
                        ${item.num?c}
                    </td>
                    <td>
                        ${item.good?c}
                    </td>
                    <td>
                        ${item.clazzId?c}
                    </td>
                    <td>
                        <a href="/demo/student/${item.id?c}"
                           onclick="deleteStudentConfirm(this,'${item.id?c}');return false;"
                        >
                        <@spring.message "operation.delete"/>
                    </td>
                    </a>
                </tr>
            </#list>
            </table>
        </div>
    </#if>
    <#else>
                <div class="text-danger margin-top-10">
                    <@spring.message "label.result.empty"/>
                </div>
    </#if>
    </div>                   <!--center col-sm-10 end -->
</div>                       <!--center row   end -->
</div>

<div class="modal fade" id="confirm" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title text-warning"><@spring.message  code="label.action.confirm.delete"/></h4>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal"><@spring.message  code="button.cancel"/></button>
                <button type="button"
                        class="btn btn-success"><@spring.message  code="button.ok"/></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="success" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title text-success"></h4>
            </div>
            <div class="modal-body text-danger">

            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" type="button"
                        class="btn btn-success"><@spring.message  code="button.ok"/></button>
            </div>
        </div>
    </div>
</div>

</body>

<script type="text/javascript">

    function

    deleteStudent()
    {
        $("#confirm").modal('hide');
        var id = $("#confirm .modal-footer .btn-success").attr("action-value");
        var modalTime = setTimeout(function () {
            var $element = $('<div></div>').attr("modal-text", "<@spring.message  code="tip.action.delete"/>");
            showFormModal($element);

        }, 400);
        var time = new Date().getTime()
        $.ajax({
            "url": "/demo/student/" + id, "success": function (resp) {
                window.clearTimeout(modalTime);

                var f = function () {
                    $("#success .modal-title").html( "<@spring.message  code="label.action.confirm"/>");
                    $("#success .modal-body").html(resp.message);
                    $("#success").modal('show');
                    setTimeout(function () {

                        var page = "${criteria.page!"1"}";
                        var $f = $("#criteria-pagination");
                        var action = $f.attr("page-action");
                        if (!action) {
                            var tempAction = $f.attr("action")
                            action = tempAction;
                        }
                        $f.attr("action", action + "/" + page);
                        $('#success').modal('hide');
                        $f.submit();
                        $f.attr("action", tempAction);
                    }, 2500);

                }
                var nowTime = new Date().getTime();
                var ttime = nowTime - time;
                if (ttime > 400) {
                    $("#form-modal").modal('hide');
                    f();
                }
                else if (ttime > 333) {
                    f();
                }
                else {
                    setTimeout(f, 333 - ttime);
                }
            }, "type": "post",
            "dataType": "json",
            "data": {
                "_method": "delete",
            }
        })

    }

    function deleteStudentConfirm(obj, id) {
        $("#confirm .modal-title").html("<@spring.message  code="label.action.confirm.delete"/>");
        var names = new Array();
        names[0] = "id";
        names[1] = "name";
        names[2] = "nick";
        names[3] = "createDate";
        names[4] = "createTime";
        names[5] = "phoneNumber";
        names[6] = "age";
        names[7] = "num";
        names[8] = "good";
        names[9] = "clazzId";
        var text = "";
        var $td = $(obj).parents('tr').children('td');
        var l = $td.length - 1;
        for (var i = 1; i < l; i++) {
            //console.log($td.eq(i).text())
            if (i <=2) {
                text = text + '<tr class="text-danger"><td>' + names[i - 1] + "</td><td>&nbsp;&nbsp;" + $td.eq(i).text() + "</td></tr>";
            }
            else {
                text = text + "<tr><td>" + names[i - 1] + "</td><td>&nbsp;&nbsp;" + $td.eq(i).text() + "</td></tr>";
            }
        }
        $("#confirm .modal-body").html(text);
        $("#confirm").modal('show')
        $("#confirm .modal-footer .btn-success").attr("action-value", id);
        $("#confirm .modal-footer .btn-success").one("click", deleteStudent);
    }

</script>
</html>