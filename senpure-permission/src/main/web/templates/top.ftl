<nav class="navbar navbar-default navbar-fixed-top visible-xs-block visible-ms-block ">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button id="top-menu-button" type="button" class="navbar-toggle collapsed " data-toggle="collapse"
                    data-target="#senpure-menu-top">
                <!--  -->
                <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span id="top-badge" class="badge"></span>
            </button>
            <button type="button" style="padding: 0" class="navbar-toggle">
				<span> <a href="" class="btn btn-success pull-right faa-parent animated-hover"> <span
                        class="glyphicon glyphicon-log-out faa-passing "></span> <@spring.message "button.logout"/></a>
				</span>
            </button>
            <button type="button" style="padding: 0" class="navbar-toggle">
				<span> <a href="/home" class="btn btn-success pull-right"><span
                        class="glyphicon glyphicon-th-large faa-pulse animated"></span>&nbsp;主页</a>
				</span>
            </button>
            <div class="navbar-brand" style="padding-top: 9px;">
                <img alt="" class="img-circle " style="height: 35px;width: 35px;margin: 0px;"
                     src="/resources/head/head-girl""/>
            </div>
        </div>

        <div id="senpure-menu-top" class="collapse senpure-menu"></div>
        <!-- menu -->
    </div>
    <!-- /.container-fluid -->
</nav>

<!-- web 页面顶部 -->
<div style="padding-top: 8px;height: 100px;" class="visible-md-block visible-lg-block ">


    <img id="subject-head" alt="" style="height: 80px;width: 80px;" class="img-circle"
         src="/resources/head/head-girl"/>


    <div class="pull-right " style="border-bottom: 1px solid red">
        <label class="btn text-warning">
            服务器名
        </label>

        <div class="btn">
            公司名
        </div>
        <label class="btn text-warning">
        <#if http_subject?? >
        ${http_subject.name!}
        <#else >
            沒有登陸
        </#if>

        </label>
        <a href="/loginout" class="btn btn-success faa-parent animated-hover"> <span
                class="glyphicon glyphicon-log-out faa-passing "></span>&nbsp;
            退出</a>
    </div>


</div>


<!-- 导航默认高50px; -->
<div class="visible-xs-block visible-ms-block " style="margin-bottom: 50px;"></div>

<div id="toptemp" class="clearfix " style="margin-bottom: 5px;"></div>



