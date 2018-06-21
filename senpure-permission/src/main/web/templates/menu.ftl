<div id="senpure-menu-left"
     class="senpure-menu col-sm-2 visible-md-block visible-lg-block ">

    <script type="text/javascript">

        showMenu('${menuJson!""}','<@spring.message  code="label.home"/>');

        <!--启动定位-->
        $('#senpure-menu-left').after('<div class="senpure-menu col-sm-2 visible-md-block visible-lg-block"></div>');
        $('#senpure-menu-left').affix({'offset': { top: 10 }});
        $('#subject-head').affix({'offset': { top: 10 }});

    </script>


</div>