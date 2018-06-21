<!DOCTYPE html>
<html lang="${viewLocale!"zh-CN"}" xmlns="http://www.w3.org/1999/html">
<head>
    <title>${nameRule(name)} list-/${module}/${pluralize(nameRule(name))}/${r'${criteria.page!}'}</title>
${r'<#include "../cssjs.ftl">'}
</head>
<body>

<div class="container-fluid">
${r'<#include "../top.ftl">'}
    <div class="row">            <!--center row   start -->
    ${r'<#include "../menu.ftl">'}
        <div class="col-sm-10 "> <!--center col-sm-10 start -->
            <form id="criteria" action="/${module}/${pluralize(nameRule(name))}"
                  method="post" modal-form="true"
                  auto-refresh="true" refresh-interval="300000"
                  class="form-inline">
            ${r'<#if code??&&code!=1 >
                <div class="input-error text-danger">
                ${message!}
                </div>
            </#if>'}
                <!--
                <div class="form-group margin-top-10">
                    <label class="control-label " for="${id.name}">
                    ${labelFormat(id.name)}
                    </label>
                    <div class="input-group ">
						<span class="input-group-addon "> <span
                                class="fa fa-key"></span>
						</span>
                        <input name="${id.name}"
                               class="form-control" id="${id.name}"
                               placeholder="${id.name}"
                               value="${r'${criteria.'}${id.name}!}"
                        />
                    </div>
                    <div class="input-error text-danger">
                    ${r'<@spring.valid '} "${id.name}"/>
                    </div>
                </div>form-group end  -->
            <#list findModeFields as field>
                <div class="form-group margin-top-10">
                    <label class="control-label " for="${field.name}">
                    ${labelFormat(field.name)}
                    </label>
                    <div class="input-group ">
						<span class="input-group-addon "> <span
                                class="glyphicon glyphicon-th"></span>
						</span>
                        <input name="${field.name}"
                               class="form-control" id="${field.name}"
                               placeholder="${field.name}"
                               value="${r'${criteria.'}${field.name}!}"
                        />
                    </div>
                    <div class="input-error text-danger">
                    ${r'<@spring.valid '} "${field.name}"/>
                    </div>
                </div><!--form-group end  -->
            </#list>
            <#if findModeFields?size gte 3>
                <br>
            </#if>
            <#if dateField.name!="123456789">
                <div class="form-group margin-top-10">

                    <label for="startDate">
                    ${r'<@spring.message "label.time.start"/>'}
                    </label>
                    <div class="input-group">
                            <span class="input-group-addon btn " datetimepicker-target='#startDate'><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        <!-- data-date-autoclose="true"
                        data-today-highlight="true" -->
                    ${r'<input type="text" id="startDate" name="startDate" class="form-control"
                                   data-date-format="${datetime_format?replace("mm","ii")?replace("MM","mm")?replace("HH","hh")}"
                                   data-date-language="${viewLocale!"zh-CN"}" data-min-view="1" datetimepicker-receive="yes"
                                   value="${criteria.startDate!}"'}
                        placeholder="${dateField.name}"
                        >
                    </div>
                    <label for="endDate">
                    ${r'<@spring.message "label.time.end"/>'}
                    </label>
                    <div class="input-group">
                            <span class="input-group-addon btn " datetimepicker-target='#endDate'><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                    ${r'<input type="text" id="endDate" name="endDate" class="form-control"
                                   data-date-format="${datetime_format?replace("mm","ii")?replace("MM","mm")?replace("HH","hh")}"
                                   data-date-language="${viewLocale!"zh-CN"}" data-min-view="1" datetimepicker-receive="yes"
                                   data-date-type="end"
                                   value="${criteria.endDate!}"'}
                        placeholder="${dateField.name}"
                        >

                    </div>
                    <div class="input-error text-danger">
                    ${r'<@spring.valid "startDate" />'}
                          ${r'<@spring.valid "endDate" />'}
                    </div>
                </div>
            </#if>
                <!--hide input-->
                <input type="hidden" name="datePattern" value="${r'${criteria.datePattern!"yyyy-MM-dd HH:mm:ss"}'}">
                <input type="hidden" name="pageSize" value="${r'${criteria.pageSize!}'}">

                <!--hide input-->
                <div class="criteria-order">
            <#list modelFieldMap?values as field>
            <#if field.order&&field.htmlShow>
                <input type="hidden" name="${field.name}Order" id="${field.name}Order-pagination"
                       value="${r'${criteria.'}${field.name}Order!}">
            </#if>
            </#list>
                </div>
                <div class="form-group margin-top-10">
                    <button class="btn btn-success  faa-parent animated-hover " modal-button="true">
                        <span class="glyphicon glyphicon-search faa-tada faa-slow"></span>
                    ${r'<@spring.message "button.query"/>'}
                    </button>
                </div>
            </form>

        ${r'<#if total??&&items??>'}
        ${r'<#if total gt 0&&(items?size>0)>'}
            <div class="margin-top-10 "></div>
        ${r'<div <#include "../pageDivAttr.ftl">>'}
            <table fixed-table-title="true" fixed-table-widht-offset="678"
                   class="margin-top-5 table table-striped table-bordered table-hover table-condensed ">
                <tr>
                    <th>
                    ${r'<@spring.message "table.index"/>'}
                    </th>
                    <th>
                    ${id.name}
                    </th>
                <#list modelFieldMap?values as field>
                    <#if field.htmlShow>
                        <#if field.order>
                        <th data-order="true" data-form="#criteria-pagination"
                            data-value="${r'${criteria.'}${field.name}Order!}"
                            data-field="${field.name}Order" data-init="<#if field.date>DESC<#else >ASC</#if>"
                         >
                        <#else >
                        <th>
                        </#if>
                        ${field.name}
                        </th>
                    </#if>
                </#list>
                    <th>
                    ${r' <@spring.message "label.operation"/>'}
                    </th>
                </tr>
            ${r'<#list items as item>'}
                <tr>
                    <td>${r'${item_index+1}'}</td>
                    <td>
                    <#if child??>
                     <a href="/${child.module}/${pluralize(nameRule(child.name))}?${childField.name}=${r'${item.'}${id.name}?c}">${r'${item.'}${id.name}?c}</a>
                        <#else >
                        ${r'${item.'}${id.name}?c}
                    </#if>
                    </td>
                <#list modelFieldMap?values as field>
                    <#if field.htmlShow>
                    <td>
                    <#if field.date>
                    ${r'<#if item.'}${field.name}??>
                        <#if field.clazzType=="Long">
                        ${r'${item.'}${field.name}?number_to_datetime?string(datetime_format)}
                        <#else >
                        ${r'${item.'}${field.name}?datetime?string(datetime_format)}
                        </#if>
                    ${r'</#if>'}
                    <#else >
                        <#if field.clazzType=="Long"||field.clazzType=="long"||field.clazzType=="Integer"||field.clazzType=="int">
                        ${r'${item.'}${field.name}?c}
                        <#elseif field.clazzType=="Boolean"||field.clazzType=="boolean">
                        ${r'${item.'}${field.name}?c}
                        <#else>
                        ${r'${item.'}${field.name}!}
                        </#if>
                    </#if>
                    </td>
                    </#if>
                </#list>
                    <td>
                        <a href="/${module}/${nameRule(name)}/${r'${item.'}${id.name}?c}"
                           onclick="delete${name}Confirm(this,'${r'${item.'}${id.name}?c}');return false;"
                        >
                        ${r'<@spring.message "operation.delete"/>'}
                    </td>
                    </a>
                </tr>
            ${r'</#list>'}
            </table>
        </div>
    ${r'</#if>'}
    ${r'<#else>
                <div class="text-danger margin-top-10">
                    <@spring.message "label.result.empty"/>
                </div>'}
    ${r'</#if>'}
    </div>                   <!--center col-sm-10 end -->
</div>                       <!--center row   end -->
</div>

<div class="modal fade" id="confirm" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title text-warning">${r'<@spring.message  code="label.action.confirm.delete"/>'}</h4>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">${r'<@spring.message  code="button.cancel"/>'}</button>
                <button type="button"
                        class="btn btn-success">${r'<@spring.message  code="button.ok"/>'}</button>
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
                        class="btn btn-success">${r'<@spring.message  code="button.ok"/>'}</button>
            </div>
        </div>
    </div>
</div>

</body>

<script type="text/javascript">

    function

    delete${name}()
    {
        $("#confirm").modal('hide');
        var id = $("#confirm .modal-footer .btn-success").attr("action-value");
        var modalTime = setTimeout(function () {
            var $element = $('<div></div>').attr("modal-text", "${r'<@spring.message  code="tip.action.delete"/>'}");
            showFormModal($element);

        }, 400);
        var time = new Date().getTime()
        $.ajax({
            "url": "/${module}/${nameRule(name)}/" + id, "success": function (resp) {
                window.clearTimeout(modalTime);

                var f = function () {
                    $("#success .modal-title").html( "${r'<@spring.message  code="label.action.confirm"/>'}");
                    $("#success .modal-body").html(resp.message);
                    $("#success").modal('show');
                    setTimeout(function () {

                        var page = "${r'${criteria.page!"1"}'}";
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

    function delete${name}Confirm(obj, id) {
        $("#confirm .modal-title").html("${r'<@spring.message  code="label.action.confirm.delete"/>'}");
        var names = new Array();
        names[0] = "${id.name}";
    <#list modelFieldMap?values as field>
        names[${field_index+1}] = "${field.name}";
    </#list>
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
        $("#confirm .modal-footer .btn-success").one("click", delete${name});
    }

</script>
</html>