var SENPURE = SENPURE || window['SENPURE'] || {};
SENPURE.lastActionTime = new Date().getTime();
SENPURE.minutes5 = 1000 * 60 * 5;
SENPURE.AUTO_REFRESH_INTERVAL = SENPURE.AUTO_REFRESH_INTERVAL || 1000 * 60 * 5;
SENPURE.ORDER_NONE = "";
SENPURE.ORDER_DESC = "DESC";
SENPURE.ORDER_ASC = "ASC";

$(function () {

    // 小屏幕小的最顶上提示，点击消失
    $("#top-menu-button").on("click", function (event) {
        $(event.target).find(".badge").html("");

    });
    // 简单的点击当前提示消失
    $(".senpure-menu li").on("click", function (event) {
        $(event.target).find(".badge").html("");

    });
    $('[datetimepicker-target]').each(function (index, element) {
        // var tid=this.attr('datetimepicker-target');
        // jb jquery object
        var jb = $(element);
        var inputTarget = $(jb.attr('datetimepicker-target'));

        jb.on('click', function () {

            if (inputTarget.val() == '') {
                var date = new Date();
                var date2 = new Date();

                //var showDate = date.getFullYear() + '-';
                //var _date = date.getMonth() + 1;
                if (-date < 10) {
                    //_date = '0' + _date;
                }
                //showDate += _date + '-' + date.getDate();
                var se = inputTarget.attr('data-date-type');
                if (se && se == 'end') {
                    //showDate += ' 23:59:59';
                    date2.setUTCHours(23);
                    date2.setUTCMinutes(59);
                    date2.setUTCSeconds(59);
                    date2.setUTCMilliseconds(999);
                } else {
                    //showDate += ' 00:00:00';
                    date2.setUTCHours(0);

                    date2.setUTCMinutes(0);
                    date2.setUTCSeconds(0);
                    date2.setUTCMilliseconds(0);
                }

                var dp = inputTarget.datetimepicker.DPGlobal;

                var showDate2 = dp
                    .formatDate(
                        date2,
                        dp
                            .parseFormat(
                                inputTarget
                                    .attr('data-date-format'),
                                'standard'),
                        inputTarget.attr('data-date-language'),
                        'standard');

                inputTarget.val(showDate2);

            }

            inputTarget.datetimepicker('show');
        });
        inputTarget.on('click', function () {

            if (inputTarget.val() == '') {
                var date = new Date();
                var date2 = new Date();

                //var showDate = date.getFullYear() + '-';
                var _date = date.getMonth() + 1;
                if (-date < 10) {
                    _date = '0' + _date;
                }
                //	showDate += _date + '-' + date.getDate();
                var se = inputTarget.attr('data-date-type');
                if (se && se == 'end') {
                    //showDate += ' 23:59:59';
                    date2.setUTCHours(23);
                    date2.setUTCMinutes(59);
                    date2.setUTCSeconds(59);
                    date2.setUTCMilliseconds(999);
                } else {
                    //showDate += ' 00:00:00';
                    date2.setUTCHours(0);
                    date2.setUTCMinutes(0);
                    date2.setUTCSeconds(0);
                    date2.setUTCMilliseconds(0);
                }

                var dp = inputTarget.datetimepicker.DPGlobal;

                var showDate2 = dp
                    .formatDate(
                        date2,
                        dp
                            .parseFormat(
                                inputTarget
                                    .attr('data-date-format'),
                                'standard'),
                        inputTarget.attr('data-date-language'),
                        'standard');

                inputTarget.val(showDate2);

            }

            inputTarget.datetimepicker('show');
        });
    });

    $('[datetimepicker-receive]').each(function (index, element) {

        $(element).datetimepicker({

            'autoclose': true,
            'todayHighlight': true

        });
    });

    // 时间处理结束

    // 分页处理
    $('.table-responsive').each(function (index, element) {
        var jb = $(element);

        var total = jb.attr('data-total');
        var fenye = {};

        if (total) {
            fenye.total = total;
            fenye.page = jb.attr('data-page');
            fenye.pageSize = jb.attr('data-page-size');
            //fenye.first = jb.attr('data-first');
            //fenye.max = jb.attr('data-max');
            //如果fenye.formPage的表单不存在复制fenye.form作为分页时候的form表单
            fenye.form = jb.attr('data-form');
            //使用data-form-page用作分页时候的form表单
            fenye.formPage = jb.attr('data-form-page');
            fenye.totalText = jb.attr('data-total-text');
            fenye.firstText = jb.attr('data-first-text');
            fenye.lastText = jb.attr('data-last-text');
            fenye.pageShowText = jb.attr('data-page-show-text');
            fenye.excelText = jb.attr('data-excel-text');
            fenye.excelWaitClose = jb.attr('data-excel-wait-close');
            fenye.pageShowData = jb.attr('data-page-show-data');
            fenye.element = element;
            fenye.type = 'top';
            if ($(fenye.formPage).length==0) {
                generatePageCriteria(fenye.form, element)
                fenye.formPage = fenye.form + "-pagination";
            }
            fenye.form=fenye.formPage;
            generatorPagination(fenye);
            // fenye.type = 'b0ttom';
            // generatorPagination(fenye);

        }

    });

    // 分页处理结束

    // 表单处理


    if ($('form[modal-form=true]').length > 0) {
        if ($('#form-modal').length == 0) {
            var html = '<div id="form-modal" class="modal" tabindex="-1" role="dialog" aria-labelledby="please wait">';

            html += '<div class="modal-dialog">';
            html += '	<div class="modal-content" >';

            html += '<div class="modal-body"><span>&nbsp; </span><span class="fa fa-spinner faa-spin animated"></span>&nbsp; <span class="text"></span>';

            html += '</div></div></div></div>';
            $('body').append(html);

        }

        $('#form-modal').on('show.bs.modal', function (e) {

            // var $this = $(this);
            var $modal_dialog = $('#form-modal .modal-dialog');
            // console.log('$modal_dialog
            // .length='+$modal_dialog .length);
            var m_top = '';
            // $('.modal-content').append($(document).width() <
            // 769);

            if ($(document).width() < 769) {
                m_top = ($(window).height() - $modal_dialog.height())
                    / 3;
            } else {
                m_top = ($(window).height() - $modal_dialog.height())
                    / 2 - 50;
            }

            $modal_dialog.css({
                'margin': m_top + 'px auto'
            });

        });

        $(window).on('unload', function () {

            $('#form-modal').modal('hide');
            // console.log('unload');

        })
    }
    $('form[modal-form=true]').each(function (index, element) {

        var $element = $(element);

        // var $b = $element.find('[modal-button=true]');
        // // 有按钮就不注册提交事件了,手机上始终不支持延迟模态框
        //
        // if ($b.length > 0) {
        // console.log('采用click事件....');
        // $b.on('click', function() {
        //
        // //$b.closest("form")
        //
        // window.setTimeout(function() {
        // showFormModal($element);
        // }, 700);
        //
        // });
        // return;
        // }
        var notuc = true;
        if (navigator.userAgent.toLowerCase().indexOf('ucbrowser') > -1) {
            notuc = false;
        }
        $element.submit(function (e) {

            var dw = $(document).width();

            // 大多数手机不支持延迟模态框
            if (dw < 769 && notuc) {
                showFormModal($element);
            }

            else {
                window.setTimeout(function () {
                    showFormModal($element);
                }, 700);
            }

        });

    });

    // 表单处理 结束
    // 自动刷新表单

    if ($('form[auto-refresh=true]').length > 0) {
        $(window).scroll(refreshTime);
        $('body').on('click mousemove keydown scroll', refreshTime);
        $('form[auto-refresh=true]').each(function (index, e) {

            var $e = $(e);
            var time = $e.attr('refresh-interval') || SENPURE.AUTO_REFRESH_INTERVAL;
            setInterval(function () {
                checkRefresh($e, time)

            }, 1000)

        });

    }

    // 自动刷新页面完毕

    // 表格排序

    $('th[data-order=true] , td[data-order=true]').each(function (index, e) {
        var $e = $(e);
        var currentOrder = $e.attr('data-value');
        var nextOrder = getNextOrder(currentOrder, $e.attr('data-init'));
        $e.addClass("cursor-pointer").click(function () {
            var fid = $e.attr('data-form');

            var field = $e.attr('data-field');
           var $forder = $(fid + ' .criteria-order');
            if ($forder.length > 0) {
                $forder.find('input').each(function (index, e) {

                    $e = $(e);
                    if ($e.attr('name') == field) {
                        $e.val(nextOrder)
                    } else {
                        $e.val(SENPURE.ORDER_NONE);
                    }

                });
            } else {
                $(fid + ' input[name=' + field + ']').val(nextOrder);
            }
            $(fid + ' input[name=page]').val(1);
            var pageActio = $(fid).attr("page-action");
            if (pageActio) {
                $(fid).attr("action", pageActio + "/1")
            }
            $(fid).submit();
        });

        if (currentOrder == SENPURE.ORDER_ASC) {
            var html = '&nbsp;<span class="glyphicon glyphicon-triangle-top  text-muted"></span>';
            $e.append(html);
        } else if (currentOrder == SENPURE.ORDER_DESC) {
            var html = '&nbsp;<span class="glyphicon glyphicon-triangle-bottom  text-muted "></span>';
            $e.append(html);
        }
        else {
            var html = '&nbsp;<span class="fa fa-sort text-muted "></span>';
            $e.append(html);

        }

    });// 表格排序完毕

    // 表格头固定

    $('table[fixed-table-title=true]').each(function (index, e) {
        var $e = $(e);
        var tableId = "fixed-table-" + index;
        var html = '<table id=\"' + tableId + '\" style="display:none">';
        html += '</table>';

        var $tr = $e.find("tr:first-child");
        var $ftr = $e.find("tr:first-child").clone(true).addClass('text-muted');
        $e.before(html);
       var $ftable = $('#' + tableId);
        $ftable.attr('class', $e.attr('class'));
        $ftr.css({
            'display': 'none',
            'border-bottom': '1px solid  '
        });
        $ftr.prependTo($e);
        $(window).scroll(function () {
            var windowWidth = $(document).width();
            var titleTop = $(window).width() < 769 ? 50 : 0;
            var top = $(window).scrollTop();
            var tableTop = $e.offset().top;
            // console.log('tableTop ' + tableTop);
            // $ftr = $ftable.find("tr:first-child");
            var currentWidths = new Array();
            var tableWidth = 0;
            if (top > tableTop && top < $e.height() + tableTop) {
                $tr.find('th,td').each(function (index, cell) {
                    tableWidth = tableWidth + $(cell).outerWidth();
                    currentWidths[index] = $(cell).width();
                });
                if (tableWidth > windowWidth) {
                    return;
                }
                if (currentWidths.length < 5 && windowWidth > 769) {
                    currentWidths[0] = currentWidths[0] - 1;
                    currentWidths[1] = currentWidths[1] - 1;
                    currentWidths[2] = currentWidths[2] + 1;
                }
                else if (currentWidths.length > 5) {
                    currentWidths[4] = currentWidths[4] + 1;
                }
                $ftr.find('th,td').each(function (index, cell) {
                    $(cell).width(currentWidths[index]);
                });
                // console.log("tableWidth:"+tableWidth+",window "+windowWidth)

                $ftr.css({
                    'display': 'block',
                    'position': 'fixed',
                    'top': titleTop,
                    'z-index': 2

                });

            } else {
                $ftr.css({
                    'display': 'none'
                });
            }

        });
    });
    // var top = $(window).scrollTop();
    // 这个方法是当前滚动条滚动的距离
    // $(window).height()获取当前窗体的高度
    // $(document).height()获取当前文档的高度
    // console.log(top);
    // tr:first-child
    // 表格头固定完毕

    // 处理回到top

    var toTopHtml = '<div id="to-top" class="to-top" > ';
    toTopHtml += '<span class="glyphicon glyphicon-arrow-up"></span>'
    toTopHtml += '</div>';
    $('body').append(toTopHtml);
    var $top = $('#to-top');
    // console.log($top.offset().left+','+$top.offset().top);
    $top.mousedown(moveDivMouseStart).mousemove(moveDivMousemove)
        .mouseup(moveDivMouseend).mouseout(moveDivMouseend)
        .click(moveDivClickHandler);

    $top.on('touchstart', touchDivStart);
    $top.on('touchmove', touchDivMove);
    // $(document).width()
    // localStorage

    $top.on('touchend', touchDivEnd);
    var topLeft = localStorage['topLeft'];
    if (!topLeft) {

        topLeft = $(window).width() - $top.width() - 19;
        // console.log(topLeft);
    }
    var topTop = localStorage['topTop'];
    if (!topTop) {
        topTop = $(window).height() - $top.height() - 5;
    } else {

        if (topTop.substr(0, topTop.length - 2) > $(window).height()) {
            topTop = $(window).height() - $top.height() - 5;
        }
    }
    // console.log(topTop + "," + $(window).height());
    $top.css({
        'top': topTop,
        'left': topLeft
    });
    var topShow = false;
    $(window).scroll(function () {

        if ($(window).scrollTop() > 500) {
            if (!topShow) {

                $top.fadeIn();
                topShow = true;
            }

        } else {
            if (topShow) {
                topShow = false;
                $top.fadeOut();
            }
        }
    });
    // 处理回到top完毕
});

function getNextOrder(order, init) {
    if (order == SENPURE.ORDER_DESC) {
        return SENPURE.ORDER_ASC;
    } else if (order == SENPURE.ORDER_ASC) {
        return SENPURE.ORDER_DESC;
    } else {
        return init == 'undefined' ? SENPURE.ORDER_ASC : init;
    }
}
function showFormModal($element) {

    var text = $element.attr('modal-text');
    if (text && $.trim(text).length > 0) {
    } else {
        text = 'Loading';
    }

    $('#form-modal').find('.modal-content .text').html(text);
    var i = 0;
    // 忘后面加 。 遗憾的是大多数手机浏览器不支持。。。
    setInterval(function () {
            i++;
            if (i == 7) {
                i = 0;
                $('#form-modal').find('.modal-content .text').html(text);
            } else {

                $('#form-modal').find('.modal-content .text').append(' 。');
            }

        }

        , 800);
    $('#form-modal').find('.modal-content .modal-header').remove();

    $('#form-modal').modal({
        'backdrop': 'static',
        'keyboard': 'false'
    });

};
// -----以上为文档加载完后执行------------

// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
var currentPath = window.document.location.href;
// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName = window.document.location.pathname;
var pos = currentPath.indexOf(pathName);
// 获取主机地址，如： http://localhost:8083
var hostPath = currentPath.substring(0, pos);
// 获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var rootPath = hostPath + projectName;
rootPath = hostPath;
var rootMenus = new Array();
var badge = 0;

function showMenu(json, hometext) {
    // console.log("--------" + json);
    // console.log('根目录：' + rootPath);
    if (json.length == 0) {
        json = "[]"
    }
    var menus = $.parseJSON(json);

    // for (var i=0;i<menus.length;i++)
    // {  console.log(writeObj(menus[i]));}

    // console.log("----------------------------------");
    menus.sort(sortMenu);
    // for (var i=0;i<menus.length;i++)
    // {  console.log(writeObj(menus[i]));}
    var menuLength = menus.length;
    for (var i = 0; i < menuLength; i++) {
        prepMenu(menus, menus[i])
    }

    var html = ' <ul  class="nav nav-pills nav-stacked">'
    html += '<li role="presentation" class="bg-success rounded ">';

    html += '<a href="' + rootPath
        + '/home" class="faa-parent animated-hover">';
    // glyphicon glyphicon-home
    html += '<span class="glyphicon glyphicon-th-large faa-tada "></span>';
    html += '&nbsp;&nbsp;' + hometext;
    html += '</a>';

    html += '</li>';
    // console.log("root" + JSON.stringify(rootMenus));

    html += generatorMenu(rootMenus);
    // console.log(generatorMenu(menus, html));
    html += '</ul>';

    // console.log(html);

    var senpureMenuLeft = $('#senpure-menu-left');
    senpureMenuLeft.html(html);
    ;
    // console.log(menus.length);

    var senpureMenuTop = $('#senpure-menu-top');
    // console.log('badge:' + badge);
    if (badge > 0) {
        $('#top-badge').html(badge);
    }
    var topHtml = '<ul class="nav nav-pills nav-stacked">';
    topHtml += generatorMenuTop(rootMenus);
    topHtml += '</ul>';
    senpureMenuTop.html(topHtml);

}

// 默认折叠
function generatorMenu(menus) {

    var html = '';
    var menuLength = menus.length;
    for (var i = 0; i < menuLength; i++) {
        var menu = menus[i];
        if (menu.hasLeaf) {
            html += '<li  role="presentation" ';
            html += 'onclick="menuClick(' + menu.id
                + ',\'senpure-menu-left-\')"';
            html += '>';
            html += '<a href="#senpure-menu-left-node-' + menu.id + '"';
            html += 'id ="senpure-menu-left-' + menu.id + '"';

            // console.log('读取数据:' + localStorage['senpure-menu-left-' +
            // menu.id]);
            var collapsed = true;

            if ('open' == localStorage['senpure-menu-left-' + menu.id]) {
                collapsed = false;
            }
            if (collapsed) {
                html += ' class="collapsed faa-parent animated-hover" data-toggle="collapse">';
            }

            else {
                html += ' class="faa-parent animated-hover" data-toggle="collapse">';
            }
            html += '<span class="' + menu.icon + '"></span>';
            html += menu.text;
            html += '<span class="pull-right glyphicon glyphicon-chevron-toggle "></span>';
            // console.log('徽章:' + menu.badgeNumber);
            var badge1 = '';
            // console.log('----------badge'+badge);
            if (collapsed && menu.badgeNumber > 0) {
                badge1 = menu.badgeNumber;
            }
            // console.log('----------badge'+badge);
            html += '<span class="badge pull-right">' + badge1 + '</span>';
            html += '</a></li>';
            html += '<li role="presentation" >';

            html += '<ul id="senpure-menu-left-node-' + menu.id;
            html += '" class="nav nav-pills nav-stacked senpure-menu-leaf-container collapse';
            // console.log('-----------------------------------' + collapsed);
            if (collapsed) {
                // console.log('折叠，使用折叠class ');
            } else {
                // console.log('没有折叠，使用展开class ');
                html += ' in';
            }
            // console.log('-----------------------------------' + collapsed);
            html += '">';
            html += generatorMenu(menu.leafs);
            // console.log('zijicaid' + generatorMenu(menu.leafs));
            html += '</ul></li>';

        } else {
            html += '<li role="presentation" ><a class="faa-parent animated-hover" href="'

            if (menu.uri) {
                html += rootPath + menu.uri + '">';
            }

            else {
                html += pathName + '#">';
            }
            html += '<span class="' + menu.icon + '"></span>';
            var badge2 = '';
            if (menu.badgeNumber > 0) {
                badge2 = menu.badgeNumber;
            }
            // console.log('----------badge'+badge);
            html += '<span class="badge pull-right">' + badge2 + '</span>';
            html += menu.text + '</a></li>';

        }

    }
    return html;

}

// 默认展开
function generatorMenuTop(menus) {
    var html = '';
    var menuLength = menus.length;
    for (var i = 0; i < menuLength; i++) {
        var menu = menus[i];
        if (menu.hasLeaf) {
            html += '<li  role="presentation" ';
            html += 'onclick="menuClick(' + menu.id + ',\'senpure-menu-top-\')"';
            html += '>';
            html += '<a href="#senpure-menu-top-node-' + menu.id + '"';
            html += 'id ="senpure-menu-top-' + menu.id + '"';

            var collapsed = false;

            if ('collapsed' == localStorage['senpure-menu-top-' + menu.id]) {
                collapsed = true;
            }
            if (collapsed) {
                html += ' class="collapsed faa-parent animated-hover" data-toggle="collapse">';
            }

            else {
                html += ' class="faa-parent animated-hover" data-toggle="collapse">';
            }
            html += '<span class="' + menu.icon + '"></span>';
            html += menu.text;
            html += '<span class="pull-right glyphicon glyphicon-chevron-toggle "></span>';
            // console.log('徽章:' + menu.badgeNumber);
            var badge1 = '';
            ;

            if (collapsed && menu.badgeNumber > 0) {
                badge1 = menu.badgeNumber;
            }
            html += '<span class="badge pull-right">' + badge1 + '</span>';
            html += '</a></li>';
            html += '<li role="presentation" >';

            html += '<ul id="senpure-menu-top-node-' + menu.id;
            html += '" class="nav nav-pills nav-stacked senpure-menu-leaf-container collapse';

            if (collapsed) {
                // console.log('折叠，使用折叠class ');
            } else {
                // console.log('没有折叠，使用展开class ');
                html += ' in';
            }
            // console.log('----------------------' + menu.text + collapsed);
            html += '">';
            html += generatorMenuTop(menu.leafs);
            // console.log('zijicaid' + generatorMenu(menu.leafs));
            html += '</ul></li>';

        } else {
            html += '<li role="presentation" ><a class="faa-parent animated-hover" href="'

            if (menu.uri) {
                html += rootPath + menu.uri + '">';
            }

            else {
                html += pathName + '#">';
            }

            var badge2 = '';
            if (menu.badgeNumber > 0) {
                badge2 = menu.badgeNumber;
            }
            html += '<span class="' + menu.icon + '"></span>';
            html += menu.text;
            html += '<span class="badge pull-right">' + badge2 + '</span>';

            html += '</a></li>';

        }

    }
    return html;

}

function prepMenu(menus, menu) {
    // console.log(menu.text + ',id:' + menu.id + ',pid:' + menu.parentId);
    // console.log(menu);
    if (menu.parentId > 0) {
        var parent = null;
        var menuLength = menus.length;

        for (var i = 0; i < menuLength; i++) {
            if (menus[i].id == menu.parentId) {
                parent = menus[i];
                parent.hasLeaf = true;

                parent.leafs.push(menu);

                break;
            }
        }
        if (parent != null) {

            parent.badgeNumber += menu.badgeNumber;
            if (!menu.hasLeaf) {
                badge += menu.badgeNumber;
            }

            if (parent.parentId > 0) {

                prepMenu(menus, parent);
            }
        }

    } else if (menu.parentId == 0) {

        rootMenus.push(menu);
    }
}

function sortMenu(one, two) {
    if (one.parentId == two.parentId) {

        var temp = one.sort < two.sort ? -1 : one.sort > two.sort ? 1 : 0
        if (temp == 0) {
            temp = one.id < two.id ? -1 : one.id > two.id ? 1 : 0
        }
        return temp;
    }
    return one.id < two.id ? -1 : one.id > two.id ? 1 : 0;
}

function menuClick(id, type) {
    // console.log('click-------');
    var storageId = type + id;
    // console.log($("#" + storageId).attr('id'));
    if ($("#" + storageId).hasClass('collapsed')) {
        // console.log("含collapsed：true ")
        localStorage[storageId] = 'open';
    } else {
        localStorage[storageId] = 'collapsed';
        // console.log("含collapsed：false ")
    }

}

//生成分页条件form 防止用户改变条件后，在点击分页，产生效果不一致的情况
function generatePageCriteria(fromId, element) {
    var $target = $(fromId).clone();
    $target.attr("id", $target.attr("id") + "-pagination");
    $target.css({"display": "none"});
    $target.find("*").each(function (index, element) {
        $(element).removeAttr("id");

    })
    $(element).before($target);
    $(fromId).removeAttr("auto-refresh").removeAttr("refresh-interval");
}

function generatorPagination(data) {
    // console.debug('生成分页');
    var cpage = Math.floor(data.page);

    // console.debug('cpage ' + cpage);
    var firstpage = cpage == 1 ? true : false;
    //总页数
    var page = Math.floor(data.total / data.pageSize);
    if (data.total % data.pageSize != 0) {
        page++;
    }
    if (cpage > page) {
        cpage = page;
    }
    if (cpage < 1) {
        cpage = 1;
    }
    var lastpage = cpage == page ? true : false;

    var html = '<ul class="pagination ';

    html += 'margin-top-0 margin-bottom-0">';

    html += '<li class="cursor-arrow"><span>'
        + data.totalText.replace('{0}', '<span class="text-danger">'
            + data.total + '</span>') + ' </span></li>';

    html += '<li><span class="pagination-select">';

    html += data.pageShowText;
    var sdata = $.parseJSON(data.pageShowData);
    html += '<select onchange="paginationClick(&#34;' + data.form + '&#34;,'
        + 1 + ',this.value);">';
    var sl = sdata.length;
    for (var i = 0; i < sl; i++) {
        var s = sdata[i];
        html += '<option value="' + s.value + '"';

        if (s.value == data.pageSize) {
            html += 'selected="selected"';
        }
        html += '>' + s.text + '</option>';
    }

    html += '</select>';
    html += '</span></li>';
    var action = $(data.form).attr("page-action");
    if (!action) {
        action = $(data.form).attr("action")
    }
    if (!firstpage) {

        html += '<li><a href="' + action + "/1" + '" onclick="paginationClick(&#34;' + data.form
            + '&#34;,' + 1 + ',' + data.pageSize + ');return false;" >'
            + data.firstText + '</a></li>';
        html += '<li><a href="' + action + "/" + (cpage - 1) + '" onclick="paginationClick(&#34;' + data.form
            + '&#34;,' + (cpage - 1) + ',' + data.pageSize
            + ');return false;" >'
            + ' <span aria-hidden="true">&laquo;</span></a></li>';
    }

    var start = cpage - 4;
    start = start < 1 ? 1 : start;

    var end = start + 8;
    end = end > page ? page : end;
    // console.log('cpage'+cpage+',start'+start+",end"+end);
    for (var i = start; i <= end; i++) {
        html += '<li';
        if (i == cpage) {
            html += ' class="active" ';
        }
        html += '>'
        html += '<a href="' + action + "/" + i + '" onclick="paginationClick(&#34;' + data.form
            + '&#34;,' + i + ',' + data.pageSize
            + ');return false;">' + i + '</a> </li>';
    }

    if (!lastpage) {

        html += '<li><a href="' + action + "/" + (cpage + 1) + '"' +
            ' onclick="paginationClick(&#34;'
            + data.form
            + '&#34;,'
            + (cpage + 1)
            + ','
            + data.pageSize
            + ');return false;"  > <span aria-hidden="true">&raquo;</span></a></li>';

        html += '<li><a href="' + action + "/" + page + '" ' +
            ' onclick="paginationClick(&#34;' + data.form
            + '&#34;,' + page + ',' + data.pageSize
            + ');;return false;">' + data.lastText + '</a></li>';
    }
    if (data.excelText) {

        html += '<li><a href="' + action
            + '/excel" onclick= "paginationExcel(&#34;' + data.form
            + '&#34;,&#34;' + data.excelWaitClose
            + '&#34;); return false;">' + data.excelText + '</a></li>';
    }
    html += '</ul>';

    $(data.element).before(html);

    $(data.element).after(html);

}

function paginationChange(value) {
    // console.log(value);
}

function paginationClick(formId, page, pageSize) {
    $(formId + ' input[name=page]').val(page);
    $(formId + ' input[name=pageSize]').val(pageSize);
   var  $f = $(formId);
    var action = $f.attr("page-action");
    if (!action) {
        action = $f.attr("action")
    }
    $f.attr("action", action + "/" + page)
    $f.submit();

}

function paginationExcel(formId, closeText) {
    // console.log('click----' + start + ',max' + max)
    // console.log($(formId + ' input[name=firstResult]').val());

    // console.log($(formId + ' input[name=firstResult]').val());
   var  $f = $(formId)
    // console.log('action'+$f.attr('action'));
    var old = $f.attr('action');

    if ($f.attr('modal-form') == 'true') {

        setTimeout(function () {

            var close = '<div class="modal-header" style="padding-bottom: 2px;" >';
            close += ' <button type="button" class="close"  data-dismiss="modal" aria-label="Close">';

            close += '<span aria-hidden="true">&times;</span>';
            close += '</button>';
            close += ' <span>&nbsp; ';
            close += closeText == 'undefined' ? 'You can close it' : closeText;

            close += ' </span> ';
            close += '<div>';
            // console.log(close);
            $('#form-modal .modal-content').prepend(close);
        }, 8000)

        setTimeout(function () {
            $('#form-modal .modal-content .modal-header').remove();
        }, 60000);
        setTimeout(function () {
            $('#form-modal').modal('hide');
        }, 60000);
    }
    $f.attr('action', old + '/excel').submit();
    $f.attr('action', old)
}

function refreshTime() {
    // console.log('refreshTime----------');
    SENPURE.lastActionTime = new Date().getTime();
}

function checkRefresh(form, time) {

    if (new Date().getTime() - SENPURE.lastActionTime >= time) {
        SENPURE.lastActionTime = new Date().getTime();
        form.submit();
    }
}

function restForm(action,method)
{
    $( '#rest-form input[name=_method]').val(method);
    $("#rest-form").attr("action", action);
    $("#rest-form").submit();
}
function toTop() {
    // html,body 必须两样填写
    $('html,body').animate({
        scrollTop: 0
    }, 'slow');
}

var moveAbsX, moveAbsY;
var dragMove = false;

var dragClick = false;
var moveCursor = 'pointer';
var moveCursorChange = false;

function moveDivMouseStart(event) {
    dragMove = false
    dragClick = true;
    var $target = $("#to-top");
    moveCursor = $target.css('cursor');
    setTimeout(function () {
        if (dragClick) {

            $target.css({
                'cursor': 'move'
            });
            moveCursorChange = true;
        }

    }, 400);
    // console.log(writeObj($target.offset()));

    moveAbsX = event.screenX - $target.offset().left - $(window).scrollLeft();
    moveAbsY = event.screenY - ($target.offset().top - $(window).scrollTop());
    // console.log(event.screenX + "," + event.screenY);
    // console.log($target.offset().left + "," + $(window).scrollLeft());
    // console.log($target.offset().top + "," + $(window).scrollTop());
    // console.log('moveAbsX:' + moveAbsX + ",moveAbsY:" + moveAbsY);
}

function writeObj(obj) {
    var description = "";
    for (var i in obj) {
        var property = obj[i];

        description += i + " = " + property + "\n";
    }
    return description;
}

function moveDivMousemove(event) {
    if (dragClick) {
        dragMove = true;
        var $target = $("#to-top");
       var $w = $(window);
        var width = $w.width();

        var height = $w.height();
        var left = event.screenX - moveAbsX;
        var top = event.screenY - moveAbsY;
        left = left < 1 ? 0 : left + $target.width() > width ? width
            - $target.width() : left;

        top = top < 0 ? 0 : top + $target.height() > height ? height
            - $target.height() : top;

        $target.css({

            'left': left,
            'top': top,
            'cursor': 'move'
        });

    }

}

function moveDivMouseend(event) {
    if (dragMove) {
        localStorage['topTop'] = $("#to-top").css('top');
        localStorage['topLeft'] = $("#to-top").css('left');
    }

    $("#to-top").css({
        'cursor': moveCursor
    });
    dragClick = false;

}

function moveDivClickHandler(event) {

    if (dragMove) {

        return;
    }
    moveDivClick(event);
}

function moveDivClick(event) {
    toTop();

}

function touchDivStart(event) {

    // event.originalEvent.changedTouches[0])
    dragMove = false
    dragClick = true;
    var $target = $("#to-top");
    moveAbsX = event.originalEvent.changedTouches[0].screenX
        - $target.offset().left - $(window).scrollLeft();
    moveAbsY = event.originalEvent.changedTouches[0].screenY
        - ($target.offset().top - $(window).scrollTop());

}

function touchDivMove(event) {
    event.preventDefault();
    if (dragClick) {
        dragMove = true;
        var $target = $("#to-top");
        $w = $(window);
        var width = $w.width();

        var height = $w.height();
        var left = event.originalEvent.changedTouches[0].screenX - moveAbsX;
        var top = event.originalEvent.changedTouches[0].screenY - moveAbsY;
        left = left < 1 ? 0 : left + $target.width() > width ? width
            - $target.width() : left;

        top = top < 0 ? 0 : top + $target.height() > height ? height
            - $target.height() : top;

        $target.css({

            'left': left,
            'top': top,
            'cursor': 'move'
        });

    }

}

function touchDivEnd(event) {
    if (dragMove) {
        localStorage['topTop'] = $("#to-top").css('top');
        localStorage['topLeft'] = $("#to-top").css('left');
    }

    dragClick = false;

}
