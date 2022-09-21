<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>优学堂</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logn.png">
        <!-- js -->
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/swiper.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/front-index.js"></script>
        <!-- css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/swiper.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/font_1/iconfont.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/front-index.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/_my.css" />
        <style>
            html,
            body {
                height: 100%;
            }
            body>.wrap-cc {
                min-height: calc(100% - 75px);
            }
        </style>
    </head>
    <body>
        <div class="wrap-cc">
            <div class="content-cc">
                <!-- head -->
                <nav class="navbar navbar-default">
                    <div class="container" style="width: 1038px">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <a href="${pageContext.request.contextPath}/show_front_index.do">
                                <img src="${pageContext.request.contextPath}/images/com-logo1.png" alt="" width="120" style="margin-right: 20px;">
                            </a>
                        </div>
                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li><a href="#" class="vertical-middle white">免费课程</a></li>
                                <li><a href="#" class="vertical-middle white">职业路径</a></li>
                            </ul>
                            <form class="navbar-form navbar-left searchInput" style="padding:10px;">
                                <div class="form-group">
                                    <input type="text" style="color: #fefeff;" class="form-control"
                                           placeholder="Search" id="selectName">
                                </div>
                                <button type="button" data-form="1" class="btn btn-default" onclick="doSearch(this)">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </form>
                            <ul class="nav navbar-nav navbar-right">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
                                        <img class="img-circle" src="${pageContext.request.contextPath}/images/user-profile.png" id="userImg">
                                        <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu userinfo cc">
                                        <li>
                                            <img src="${pageContext.request.contextPath}/images/user-profile.png" class="img-circle" alt="">
                                            <div class="">
                                                <p>${sessionScope.loginUser.nickname}</p>
                                                <p>金币<span>${sessionScope.loginUser.totalGold}</span>&nbsp;积分 <span>${sessionScope.loginUser.totalPoint}</span></p>
                                            </div>
                                        </li>
                                        <li>
                                            <a href="${pageContext.request.contextPath}/show_front_mycourse.do">
                                                <i class="glyphicon glyphicon-edit"></i>我的课程
                                            </a>
                                            <a href="${pageContext.request.contextPath}/show_front_record.do">
                                                <i class="glyphicon glyphicon-record"></i>积分记录
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#" data-toggle="modal" data-target="#userSet">
                                                <i class="glyphicon glyphicon-cog"></i>个人设置
                                            </a>
                                            <a href="javascript:void(0);"><i class="glyphicon glyphicon-off"></i>退出登录</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <!-- /.navbar-collapse -->
                    </div>
                    <!-- /.container-fluid -->
                </nav>
                <!-- nav -->
                <div class="contain">
                    <!-- 左侧 -->
                    <div class="course-nav-row">
                        <span class="hd f-left"> 方向：</span>
                        <div>
                            <ul class="ln" id="type1">

                            </ul>
                        </div>
                    </div>
                    <div class="course-nav-row">
                        <span class="hd f-left"> 分类：</span>
                        <div>
                            <ul class="ln" id="type3">
                                <li class="course-nav-item on"><a href="javascript:void(0);" data-type-id="">全部</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="course-nav-row">
                        <span class="hd f-left"> 类型：</span>
                        <div>
                            <ul class="ln">
                                <li class="course-nav-item on"><a href="javascript:void(0);" data-publish="1" onclick="doSearch(this)">网站</a></li>
                                <li class="course-nav-item"><a href="javascript:void(0);" data-publish="2" onclick="doSearch(this)">个人</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- 实战推荐 -->
                <div class="course">
                    <div class="course-tool-bar">
                        <div class="tool-left f-left">
                            <a href="javascript:void(0);" class="present" data-sort="1" onclick="doSearch(this)">最新</a>
                            <a href="javascript:void(0);" data-sort="2" onclick="doSearch(this)">最热</a>
                        </div>
                        <div class="tool-right f-right">
                            <span class="tool-item">
                                <a href="javascript:void(0);" class="hide-learned tool-chk"
                                   data-hide="1" onclick="doSearch(this)">隐藏已参加课程</a>
                            </span>
                            <span class="tool-item tool-page">
                                <span class="pager-num">
                                    <b class="pager-cur">2</b> / <em class="pager-total">10</em>
                                </span>
                                <a href="javascript:void(0);" data-direction="-1" onclick="doSearch(this)" class="pager-action pager-prev">
                                    <i class="icon iconfont icon-jiankuohaocudazuo"></i>
                                </a>
                                <a href="javascript:void(0);" data-direction="1" onclick="doSearch(this)" class="pager-action pager-next">
                                    <i class="icon iconfont icon-jiankuohaocudayou"></i>
                                </a>
                            </span>
                        </div>
                        <div class="tool-iseasy f-right">
                            <strong>难度：</strong>
                            <a href="#" class="sort-item active">全部</a>
                            <a href="#" class="sort-item">初级</a>
                            <a href="#" class="sort-item">中级</a>
                            <a href="#" class="sort-item">高级</a>
                        </div>
                    </div>
                    <div id="course-box" class="course-box">

                    </div>
                </div>

                <!-- userSet -->
                <div class="modal fade" id="userSet" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">个人信息</h4>
                            </div>
                            <form action="#" class="form-horizontal" method="post">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">旧密码：</label>
                                        <div class="col-sm-6">
                                            <input class="form-control" type="text" name="password" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">新密码：</label>
                                        <div class="col-sm-6">
                                            <input class="form-control" type="password" name="newPassword" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">确认密码：</label>
                                        <div class="col-sm-6">
                                            <input class="form-control" type="password" name="rePassword" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">昵称：</label>
                                        <div class="col-sm-6">
                                            <input class="form-control" type="text" name="nickname" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">邮箱：</label>
                                        <div class="col-sm-6">
                                            <input class="form-control" type="text" name="email" />
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-info" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                                    <button type="reset" class="btn btn-info">重&nbsp;&nbsp;置</button>
                                    <button type="submit" class="btn btn-info">确&nbsp;&nbsp;定</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-cc">
            <div class="foots">
                <div>
                    版权所有： 南京网博
                </div>
                <div>
                    Copyright © 2017 imooc.com All Rights Reserved | 京ICP备 13046642号-2
                </div>
            </div>
        </div>
    </body>
</html>
<script src="${pageContext.request.contextPath}/js/_my.js"></script>
<script src="${pageContext.request.contextPath}/js/template-web.js"></script>
<script>
    let stack = new Stack();
    let typeList = [];

    $(function() {
        init();
    })

    // init
    function init() {
        loadCourseType();

        // 暂不实现
        $(".tool-iseasy>a").on('click', function() {
            $(this).addClass('active').siblings().removeClass("active");
        })
    }

    // load course type
    function loadCourseType() {
        $.ajax({
            type : 'post',
            url : baseUrl + '/course_type/all.do',
            data : {},
            dataType : 'json',
            success : (data) => {
                if (data.statusCode === '00000') {
                    typeList = data.data;
                    let html = template('list-type1', {'list': typeList});
                    $('#type1').html(html);

                    initSelect();
                } else {
                    console.log(data);
                    alert(data.data);
                }
            }
        })
    }

    // init select
    function initSelect() {
        let stackStr = sessionStorage.getItem("front_select");
        // console.log(stackStr);
        for (let s of stackStr.split('|')) {
            let url = s.split(',')[0].substring(s.split(',')[0].indexOf(':') + 1);
            let data = s.split(',')[1].substring(s.split(',')[1].indexOf(':') + 1);
            let ajaxParam = new AjaxParam(url, data);

            // 隐藏已学过的课程（已购买的资源）
            if (/hideLearned=(true|false)/.test(data)) {
                let hideLearned = data.match(/hideLearned=(true|false)/)[1];
                if (hideLearned === 'true') {
                    $(".tool-chk").css({
                        "background": "url(${pageContext.request.contextPath}/images/sw-on.fw.png) no-repeat"
                    });
                }
                if (hideLearned === 'false') {
                    $(".tool-chk").css({
                        "background": "url(${pageContext.request.contextPath}/images/sw-off.png) no-repeat"
                    });
                }
            }
            if (/sortType=([12])/.test(data)) {
                let sortType = data.match(/sortType=([12])/)[1];
                $('div.tool-left a[data-sort="' + sortType + '"]')
                    .addClass('present').siblings().removeClass("present");
            }
            // 网站发布 or 个人发布
            if (/^\/course/.test(url)) {
                // 网站发布
                let publishType = '1';
                $('li.course-nav-item a[data-publish="' + publishType + '"]')
                    .parent().addClass("on").siblings().removeClass("on");
                $('.tool-chk').text('隐藏已参加课程');
            } else {
                // 个人发布
                let publishType = '2';
                $('li.course-nav-item a[data-publish="' + publishType + '"]')
                    .parent().addClass("on").siblings().removeClass("on");
                $('.tool-chk').text('隐藏已购买资源');
            }
            // 一级分类 or 三级分类
            if (/typeLevel=([13])/.test(data)) {
                let courseTypeId = '';
                let typeLevel = data.match(/typeLevel=([13])/)[1];
                if (/courseTypeId=([0-9]+)/.test(data)) {
                    courseTypeId = data.match(/courseTypeId=([0-9]+)/)[1];
                }
                if (typeLevel === '1') {
                    if (courseTypeId) {
                        $('li.course-nav-item a[data-type-id="' + courseTypeId + '"][data-level="1"]')
                            .parent().addClass("on").siblings().removeClass("on");
                        loadType3(courseTypeId);
                    }
                }
                if (typeLevel === '3') {
                    if (courseTypeId) {
                        for (let type1 of typeList) {
                            for (let type2 of type1.courseTypes) {
                                for (let type3 of type2.courseTypes) {
                                    if (type3.id === Number(courseTypeId)) {
                                        $('li.course-nav-item a[data-type-id="' + type1.id + '"][data-level="1"]')
                                            .parent().addClass("on").siblings().removeClass("on");
                                        loadType3(type1.id);
                                        $('li.course-nav-item a[data-type-id="' + courseTypeId + '"][data-level="3"]')
                                            .parent().addClass("on").siblings().removeClass("on");
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // 分页
            if (/pageNo=([1-9][0-9]*)/.test(data)) {
                let pageNo = data.match(/pageNo=([1-9][0-9]*)/)[1];
                $('.pager-cur').text(pageNo);
            }
            // courseName or title
            if (/^\/course/.test(url)) {
                if (/(courseName=)([^&]+)(&+?)/.test(data)) {
                    let selectName = data.match(/(courseName=)([^&]+)(&+?)/)[2];
                    $('#selectName').val(selectName);
                }
            } else {
                if (/(title=)([^&]+)(&+?)/.test(data)) {
                    let selectName = data.match(/(title=)([^&]+)(&+?)/)[2];
                    $('#selectName').val(selectName);
                }
            }
            stack.push(ajaxParam);
        }
        let ajaxParam = stack.peek();
        sendAjax(ajaxParam.url, ajaxParam.data);
    }

    // load type3
    function loadType3(typeId) {
        for (let type of typeList) {
            if (type.id === Number(typeId)) {
                let list = [];
                for (let t of type.courseTypes) {
                    for (let tt of t.courseTypes) {
                        list.push(tt);
                    }
                }
                let html = template('list-type3', {'list': list});
                $('#type3').html(html);
            }
        }
        if (!typeId) {
            $('#type3').html('<li class="course-nav-item on"><a href="javascript:void(0);">全部</a></li>');
        }
    }

    // doSearch
    function doSearch(e) {
        let data = stack.peek().data;
        let url = stack.peek().url;

        let level = $(e).attr('data-level');
        let publish = $(e).attr('data-publish');
        let sort = $(e).attr('data-sort');
        let hide = $(e).attr('data-hide');
        let direction = $(e).attr('data-direction');
        let form = $(e).attr('data-form');
        // 1 级类别 or 3 级类别
        if (level === '1' || level === '3') {
            let typeId = $(e).attr('data-type-id');
            if (level === '1') {
                loadType3(typeId);
            }
            if (typeId) {
                let p1 = /(courseTypeId=)([0-9]+)/;
                let p2 = /(typeLevel=)([13])/;
                if (p1.test(data)) {
                    data = data.replace(p1, '$1' + typeId);
                } else {
                    data = data + '&courseTypeId=' + typeId;
                }
                if (p2.test(data)) {
                    data = data.replace(p2, '$1' + level);
                } else {
                    data = data + '&typeLevel=' + level;
                }
            } else {
                let p1 = /(&?)(courseTypeId=)([0-9]+)/;
                let p2 = /(typeLevel=)([13])/;
                if (p1.test(data)) {
                    data = data.replace(p1, '');
                }
                if (p2.test(data)) {
                    data = data.replace(p2, '$1' + level);
                } else {
                    data = data + '&typeLevel=' + level;
                }
            }
            $('li.course-nav-item a[data-publish="1"]').parent().addClass("on").siblings().removeClass("on");
            $('.pager-cur').text('1');
            data = data.replace(/(pageNo=)([1-9][0-9]*)/, '$1' + '1');
            $(e).parent().addClass("on").siblings().removeClass("on");

            stack.replace(new AjaxParam('/course/front_select.do', data))
        }
        //  others
        if (!level) {
            // 发布方式：网站 or 个人
            if (publish) {
                let p1 = /(&?)(typeLevel=)([13])/;
                let p2 = /(&?)(courseTypeId=)([0-9]+)/;
                let p3 = /(courseName=)([^&]+)(&+?)/;
                let p4 = /(title=)([^&]+)(&+?)/;
                if (publish === '1') {
                    if (!p1.test(data)) {
                        data = data + '&typeLevel=1';
                    }
                    data = data.replace(p4, 'courseName=' + $('#selectName').val() + '&');
                    url = '/course/front_select.do';
                    $('.tool-chk').text('隐藏已参加课程');
                }
                if (publish === '2') {
                    data = data.replace(p1, '');
                    data = data.replace(p2, '');
                    data = data.replace(p3, 'title=' + $('#selectName').val() + '&');
                    url = '/resource/front_select.do';
                    $('.tool-chk').text('隐藏已购买资源');
                    $('li.course-nav-item a[data-type-id=""]').parent().addClass("on").siblings().removeClass("on");
                    loadType3(null);
                }
                $('.pager-cur').text('1');
                data = data.replace(/(pageNo=)([1-9][0-9]*)/, '$1' + '1');
                $(e).parent().addClass("on").siblings().removeClass("on");
            }
            // 排序方式：最新 or 最热
            if (sort) {
                let p = /(sortType=)([12])/;
                if (sort === '1') {
                    data = data.replace(p, '$1' + '1');
                }
                if (sort === '2') {
                    data = data.replace(p, '$1' + '2');
                }
                $(e).addClass('present').siblings().removeClass("present");
            }
            // 隐藏已学习课程
            if (hide) {
                let p1 =  /(hideLearned=)(true)/;
                let p2 =  /(hideLearned=)(false)/;
                if (p1.test(data)) {
                    data = data.replace(p1, '$1' + 'false');
                    $(".tool-chk").css({
                        "background": "url(${pageContext.request.contextPath}/images/sw-off.png) no-repeat"
                    });
                } else {
                    if (p2.test(data)) {
                        data = data.replace(p2, '$1' + 'true');
                        $(".tool-chk").css({
                            "background": "url(${pageContext.request.contextPath}/images/sw-on.fw.png) no-repeat"
                        });
                    }
                }
            }
            // 前一页，后一页
            if (direction) {
                let p = /(pageNo=)([1-9][0-9]*)/;
                let no = Number(data.match(p)[2]);
                if (direction === '-1') {
                    if (no <= 1) {
                        no = 1;
                        return;
                    } else {
                        no--;
                    }
                    data = data.replace(p, '$1' + no);
                }
                if (direction === '1') {
                    let pages = Number($('.pager-total').text());
                    if (no >= pages) {
                        no = pages;
                        return;
                    } else {
                        no++;
                    }
                    data = data.replace(p, '$1' + no);
                }
                $('.pager-cur').text(no);
            }
            // 表单课程名称
            if (form) {
                let selectName = $('#selectName').val();
                let p1 = /(courseName=)([^&]+)(&+?)/;
                let p2 = /(title=)([^&]+)(&+?)/;
                if (selectName.trim() !== '') {
                    if (url.match(/^\/course/)) {
                        if (p1.test(data)) {
                            data = data.replace(p2, '')
                            data = data.replace(p1, '$1' + selectName + '&')
                        } else {
                            data = 'courseName=' + selectName + '&' + data;
                        }
                    }
                    if (url.match(/^\/resource/)) {
                        if (p2.test(data)) {
                            data = data.replace(p1, '')
                            data = data.replace(p2, '$1' + selectName + '&')
                        } else {
                            data = 'title=' + selectName + '&' + data;
                        }
                    }
                } else {
                    if (url.match(/^\/course/)) {
                        data =  data.replace(p1, '')
                    }
                    if (url.match(/^\/resource/)) {
                        if (p2.test(data)) {
                            data =  data.replace(p2, '')
                        }
                    }
                }
                $('.pager-cur').text('1');
                data = data.replace(/(pageNo=)([1-9][0-9]*)/, '$1' + '1');
            }
            stack.replace(new AjaxParam(url, data))
        }
        // console.log(stack.peek());
        let ajaxParam = stack.peek();
        sendAjax(ajaxParam.url, ajaxParam.data);
    }

    // sendAjax
    function sendAjax(url, str) {
        // console.log(stack.peek())
        $.ajax({
            type : 'post',
            url : baseUrl + url,
            data : str,
            dataType : 'json',
            success : (data) => {
                if (data.statusCode === '00000') {
                    // console.log(data);
                    let list = data.data.list;
                    let html;
                    if (url.startsWith('/resource')) {
                        html = template('list-resource', {
                            'list': list,
                            'filePath': fileUrl
                        });
                    }
                    if (url.startsWith('/course')) {
                        html = template('list-course', {
                            'list': list,
                            'filePath': fileUrl
                        });
                    }
                    $('#course-box').html(html);
                    $('.pager-total').text(data.data.pages);
                } else {
                    console.log(data);
                    alert(data.data)
                }
            }
        })
    }

    // checkOwned
    function checkOwned() {
        event.preventDefault();
        let a = $(event.currentTarget);
        let id = a.attr('data-id');
        let href = a.attr('href');
        $.ajax({
            type : 'get',
            url : baseUrl + '/resource/owned.do',
            data : {'resourceId': id},
            dataType : 'json',
            success : (data) => {
                if (data.statusCode === '00000') {
                    if (data.data === true) {
                        window.location.href = href;
                    } else {
                        let cc = a.children('.cost-cost');
                        let purchase = confirm('需要先购买资源才可以观看，花费：'
                            + $(cc).children('.cost-number').val()
                            + $(cc).children('.cost-type').val());
                        if (purchase) {
                            $.ajax({
                                type : 'get',
                                url : baseUrl + '/resource/purchase.do',
                                data : {'resourceId': id},
                                dataType : 'json',
                                success : (data) => {
                                    if (data.statusCode === '00000') {
                                        alert("购买成功");
                                        window.location.href = href;
                                    } else {
                                        console.log(data);
                                        alert(data.data);
                                    }
                                }
                            })
                        }
                    }
                } else {
                    console.log(data);
                    alert(data.data);
                }
            }
        })
    }

    // 页面关闭，缓存 stack
    $(window).on('beforeunload', () => {
        sessionStorage.setItem("front_select", stack.toString());
    })
</script>
<script id="list-type1" type="text/html">
    <li class="course-nav-item on"><a href="javascript:void(0);" data-level="1" data-type-id="" onclick="doSearch(this)">全部</a></li>
    {{each list type}}
    <li class="course-nav-item">
        <a href="javascript:void(0);" onclick="doSearch(this)" data-level="1" data-type-id="{{type.id}}">{{type.typeName}}</a>
    </li>
    {{/each}}
</script>
<script id="list-type3" type="text/html">
    <li class="course-nav-item on"><a href="javascript:void(0);" data-level="3" data-type-id="" onclick="doSearch(this)">全部</a></li>
    {{each list type}}
    <li class="course-nav-item">
        <a href="javascript:void(0);" onclick="doSearch(this)" data-level="3" data-type-id="{{type.id}}">{{type.typeName}}</a>
    </li>
    {{/each}}
</script>
<script id="list-course" type="text/html">
    {{each list item}}
    <div class="course-item">
        <div class="item-t">
            <img src="{{filePath + item.coverImageUrl}}" alt="">
            <div class="java">
                <label>{{item.courseType.typeName}}</label>
            </div>
        </div>
        <div class="item-b">
            <a href="${pageContext.request.contextPath}/course/course.do?id={{item.id}}">
                <h4>{{item.courseName}}<br/>{{item.courseInfo}}</h4>
            </a>
            <p class="title">
                <span>实战</span>
                <span>高级</span>
                <span>￥{{item.totalCost + 1}}</span>
                <span>4星</span>
            </p>
            <p class="price">￥ {{item.totalCost}}.00</p>
        </div>
    </div>
    {{/each}}
</script>
<script id="list-resource" type="text/html">
    {{each list item}}
    <div class="course-item">
        <div class="item-t">
            <img src="{{filePath + item.coverImageUrl}}" alt="">
        </div>
        <div class="item-b">
            <a onclick="checkOwned()" data-id="{{item.id}}" href="${pageContext.request.contextPath}/resource/search_front.do?id={{item.id}}">
                <div class="cost-cost">
                    {{if item.costType == 0}}
                    <input type="hidden" class="cost-type" value="积分">
                    {{else if item.costType == 1}}
                    <input type="hidden" class="cost-type" value="金币">
                    {{/if}}
                    <input type="hidden" class="cost-number" value="{{item.costNumber}}">
                </div>
                <h4>{{item.title}}</h4>
            </a>
            <p class="title">
                <span>上传者</span>
                <span>{{item.user.nickname}}</span>
                <span>￥{{item.costNumber + 1}}</span>
            </p>
            <p class="price">￥ {{item.costNumber}}.00</p>
        </div>
    </div>
    {{/each}}
</script>