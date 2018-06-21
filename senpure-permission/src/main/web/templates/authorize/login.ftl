<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="viewport"
      content="width=device-width, initial-scale=1.0, maximum-scale=1.0,user-scalable=0">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta charset="UTF-8">
<title>title</title>
<link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen" />
<style type="text/css">
    .input-error{
        padding-left:40px;
    }
</style>
<script src="/resources/js/jquery.js" type="text/javascript"></script>
<script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function(){
        $("input").on("input",function (event){
            var target=event.target;
            var jb= $("#"+target.id)
            if(target.checkValidity()){
                jb.closest(".form-group").removeClass("has-error").addClass("has-success");
                jb.next().removeClass("glyphicon-warning-sign").addClass("glyphicon glyphicon-ok form-control-feedback");

            }
            else{
                jb.closest(".form-group").addClass("has-error");
                jb.next().addClass("glyphicon glyphicon-warning-sign form-control-feedback");

            }

        });

        $("form").on("keydown",function(event){
            console.log(event.keyCode);
            if(event.keyCode==13&&event.target.id=="account")
            {
                event.preventDefault();
                password.focus();

            }

        });

    });

</script>
</head>
<body>
<body class="bg-info">

<div style="margin-top: 180px;" class="visible-lg-block"></div>
<div style="margin-top: 100px;" class="visible-md-block "></div>
<div style="margin-top: 10px;" class="visible-ms-block"></div>
<div style="margin-top: 5px;" class="visible-xs-block "></div>

<div class="container">
    <div class="row">
        <div class="col-sm-4 col-xs-1"></div>
        <form action="/authorize/login" method="post"
              class="col-sm-4 col-xs-11  bg-info " id="form"
              autocomplete="off" style="padding :20px">
            <div class="input-error text-danger">
            ${message!}
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label " for="account">
                <@spring.message "label.account"/>
                </label>
                <div class="input-group ">
						<span class="input-group-addon "> <span
                                class="glyphicon glyphicon-user"></span>
						</span>
                    <input pattern="^[\w]{3,24}$" required="required" name="account"
                           class="form-control input-lg" id="account"
                           placeholder="<@spring.message code="tip.account.placeholder" />"
                           value="${criteria.account! }"
                    />
                    <span class="glyphicon  form-control-feedback"
                          aria-hidden="true"></span>
                </div>
                <div class="input-error text-danger">
                <@spring.bind "criteria.account" />
                 <@spring.showErrors ""/>
                </div>

            </div>
            <div class="form-group form-group-lg">
                <label class="control-label " for="password">
                <@spring.message
                            code="label.password"/>
                </label>
                <div class="input-group ">
						<span class="input-group-addon "> <span
                                class="glyphicon glyphicon-lock"></span></span>


                    <input
                            pattern="^[\w]{3,24}$" required="required" type="password"
                            name="password" class="form-control " id="password"
                            value="${criteria.password! }"
                            placeholder="<@spring.message code="tip.password.placeholder" />"/>
                    <span class="glyphicon  form-control-feedback"
                          aria-hidden="true"></span>

                </div>

                <div class="input-error text-danger">
                <@spring.bind "criteria.password" />
                 <@spring.showErrors ""/>

                </div>
            </div>
            <div class="form-group">
                <button class="form-control btn btn-success input-lg btn-lg" type="submit">
                <@spring.message  code="button.login"/>
                </button>
            </div>
            <div class="row text-muted">
                    	<span class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
                      <@spring.formCheckbox path ="criteria.remember"/>

                   	  	<label for="remember"
                               onclick="$('#remember1').click()"
                        ><small><@spring.message  code="label.remember"/></small></label>
                        </span>

            </div>
        </form>

    </div>
</div>

</body>
</html>