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
            <form class="form-horizontal " action="/authorize/container/${criteria.containerId}/account" method="post"
                  autocomplete="false"
                  modal-form="true">
                <div class="input-error text-danger form-group form-group-lg">
                    <div class="col-sm-2"></div>
                    <div class="col-sm-4">
                    ${message!}
                    </div>
                </div>
                <div class="form-group form-group-lg">
                    <label for="name" class="col-sm-2 control-label"> <@spring.message  code="label.name"/></label>
                    <div class="input-group col-sm-4">
						<span class="input-group-addon "> <span
                                class="glyphicon glyphicon-asterisk"></span>
						</span>
                        <input required="required" name="name"
                               class="form-control input-lg" id="name">
                    </div>
                </div>
                <div class="form-group form-group-lg">
                    <div class="col-sm-2">
                    </div>
                    <div class="col-sm-4 text-danger">
                    <@spring.bind "criteria.name" />
                 <@spring.showErrors ""/>
                    </div>
                </div>

                <div class="form-group form-group-lg">
                    <label for="account"
                           class="col-sm-2 control-label"> <@spring.message  code="label.account"/></label>
                    <div class="input-group col-sm-4">
						<span class="input-group-addon "> <span
                                class="glyphicon glyphicon-user"></span>
						</span>
                        <input required="required" name="account"
                               class="form-control input-lg" id="account">
                    </div>
                </div>
                <div class="form-group form-group-lg">
                    <div class="col-sm-2">
                    </div>
                    <div class="col-sm-4 text-danger">
                    <@spring.bind "criteria.account" />
                 <@spring.showErrors ""/>

                    </div>
                </div>

                <div class="form-group form-group-lg">
                    <label for="password"
                           class="col-sm-2 control-label"> <@spring.message  code="label.password"/></label>
                    <div class="input-group col-sm-4">
						<span class="input-group-addon "> <span
                                class="glyphicon glyphicon-lock"></span>
						</span>
                        <input required="required" name="password"
                               class="form-control input-lg" id="password">
                    </div>
                </div>
                <div class="form-group form-group-lg">
                    <div class="col-sm-2">
                    </div>
                    <div class="col-sm-4 text-danger">
                    <@spring.bind "criteria.password" />
                 <@spring.showErrors ""/>
                    </div>
                </div>

                <div class="form-group form-group-lg">
                    <div class="col-sm-2"></div>
                    <div class="col-sm-4">
                        <button class="form-control  btn btn-success input-lg btn-lg" type="submit">
                        <@spring.message  code="button.ok"/>
                        </button>
                    </div>
                </div>


            </form>
        </div><!--col-sm-10 end -->
    </div><!--row   end -->
</div>
</body>
<script type="text/javascript">
    $(function () {

        var $name = $("input[name='name']");
        $name.on("input", function () {

            if( $name.val().length>1){
            $.ajax({
                "url": "/tools/pinyin/" + $name.val()
                , "success": function (resp) {

                    $("input[name='account']").val(resp);
                    $("input[name='password']").val(resp);
                }
                ,"method":"get"
            });}
        });
    });
</script>
</html>