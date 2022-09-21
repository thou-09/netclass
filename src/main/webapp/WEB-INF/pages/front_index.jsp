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
        <script src="${pageContext.request.contextPath}/js/template-web.js"></script>
        <!-- css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/swiper.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/font_1/iconfont.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/front-index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/_my.css" />
        <style>
            html,
            body {
                height: 100%;
            }
            body>.wrap-cc {
                min-height: 100%;
            }
        </style>
    </head>
    <body>
        <div class="wrap-cc">
            <div class="content-cc">
                <!-- head -->
                <nav class="navbar navbar-default">
                    <div class="container-fluid" style="background: #fff;box-shadow: 5px 5px 5px #eef;padding-left: 20px;">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <!--  <a class="navbar-brand" href="#">Brand</a> -->
                            <img src="${pageContext.request.contextPath}/images/com-logo1.png" alt="" width="120" style="margin-right: 20px;">
                        </div>
                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li><a href="#" class="vertical-middle black">免费课程</a></li>
                                <li><a href="#" class="vertical-middle black">职业路径</a></li>
                            </ul>
                            <form id="search-form" action="${pageContext.request.contextPath}/show_front_select.do" method="get"
                                  class="navbar-form navbar-left searchInput" style="padding:10px;">
                                <div class="form-group">
                                    <input type="text" class="" placeholder="Search by 课程名称" id="courseName">
                                </div>
                                <button type="submit" class="btn btn-default "><span class="glyphicon glyphicon-search"></span></button>
                            </form>
                            <div id="loginOff" class="regist ccc">
                                <span style="margin-right: 20px;font-size: 14px;">下载APP</span>
                                <a href="javascript:openUserModal(false);" class="ccc">登录</a> &nbsp;&nbsp;/&nbsp;&nbsp;
                                <a href="javascript:openUserModal(true);" class="ccc">注册</a>
                            </div>
                            <ul id="loginOn" class="nav navbar-nav navbar-right">
                                <li class="nav navbar-nav signIn">
                                    <div class="signBtn hoverRed">签到</div>
                                    <div class="expe">+5积分</div>
                                </li>
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
                                                <p>金币 <span>${sessionScope.loginUser.totalGold}</span>&nbsp;积分 <span>${sessionScope.loginUser.totalPoint}</span></p>
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
                                            <a href="javascript:void(0);">
                                                <i class="glyphicon glyphicon-off"></i>退出登录
                                            </a>
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
                <div class="main">
                    <!-- 左侧 -->
                    <div id="typeItemDiv" class="menu-left"></div>
                    <!-- 右侧 -->
                    <div class="menu-right">
                        <!-- banner -->
                        <div class="banner">
                            <div class="swiper-container">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide">
                                        <img src="${pageContext.request.contextPath}/images/banner01.jpg" alt="">
                                    </div>
                                    <div class="swiper-slide">
                                        <img src="${pageContext.request.contextPath}/images/banner02.jpg" alt="">
                                    </div>
                                    <div class="swiper-slide">
                                        <img src="${pageContext.request.contextPath}/images/banner03.jpg" alt="">
                                    </div>
                                    <div class="swiper-slide">
                                        <img src="${pageContext.request.contextPath}/images/banner04.jpg" alt="">
                                    </div>
                                    <div class="swiper-slide">
                                        <img src="${pageContext.request.contextPath}/images/banner05.jpg" alt="">
                                    </div>
                                </div>
                                <!-- Add Pagination -->
                                <div class="swiper-pagination"></div>
                            </div>
                        </div>
                        <!-- 课程信息 -->
                        <div class="tips">
                            <div class="path-banner">
                                <a href="#">
                                    <i class="i-web"></i>
                                    <p class="tit">Web前端攻城狮</p>
                                    <p class="desc">互联网时代最火爆的技术</p>
                                </a>
                                <a href="#">
                                    <i class="i-java"></i>
                                    <p class="tit">Java攻城狮</p>
                                    <p class="desc">健壮、安全、适用广泛</p>
                                </a>
                                <a href="#">
                                    <i class="i-android"></i>
                                    <p class="tit">Android攻城狮</p>
                                    <p class="desc">移动设备市场份额第一</p>
                                </a>
                                <a href="#">
                                    <i class="i-php"></i>
                                    <p class="tit">PHP攻城狮</p>
                                    <p class="desc">世界上最好的语言：）</p>
                                </a>
                                <a href="#">
                                    <i class="i-ios"></i>
                                    <p class="tit">iOS攻城狮</p>
                                    <p class="desc">可能是全球最好用的系统</p>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 实战推荐 -->
                <div id="top10"></div>
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

        <!-- 个人设置 userSet modal -->
        <div class="modal fade" id="userSet" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">个人信息</h4>
                    </div>
                    <form action="#" class="form-horizontal user-setting-form" method="post">
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
        <!-- 注册 regist modal -->
        <div class="modal fade" id="regist_modal" tabindex="-1" role="dialog" aria-labelledby="myRegistLabel">
            <div class="modal-dialog modal-md" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myRegistLabel">注册</h4>
                    </div>
                    <form action="#" class="form-horizontal user-register-form" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">登录名：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" name="loginName" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">密码：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="password" name="password" />
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
                            <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                            <button type="submit" class="btn btn-info">注&nbsp;&nbsp;册</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- 登录 login modal -->
        <div class="modal fade" id="login_modal" tabindex="-1" role="dialog" aria-labelledby="myLoginLabel">
            <div class="modal-dialog modal-md" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myLoginLabel">登录</h4>
                    </div>
                    <form action="#" class="form-horizontal user-login-form" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">登录名：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" name="loginName" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">密码：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="password" name="password" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">7天免登录：</label>
                                <div class="col-sm-6">
                                    <input style="height: 34px" class="checkbox" type="checkbox" name="autoLogin" />
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-warning" style="float: left" id="forget-btn">忘记密码</button>
                            <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                            <button type="submit" class="btn btn-info">登&nbsp;&nbsp;录</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- forget modal -->
        <div class="modal fade" id="forget_modal" tabindex="-1" role="dialog" aria-labelledby="myForgetLabel">
            <div class="modal-dialog modal-md" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myForgetLabel">忘记密码</h4>
                    </div>
                    <form action="#" class="form-horizontal user-forget-form" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">登录名：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" name="loginName" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">邮箱：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" name="email" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">新密码：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="password" name="password" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">确认密码：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="password" name="rePassword" />
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                            <button type="submit" class="btn btn-info">修&nbsp;&nbsp;改</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
<script src="${pageContext.request.contextPath}/js/_my.js"></script>
<script>
    $(function() {
        init();

        // forget modal
        $('#forget-btn').on('click', () => {
            let loginModal = $('#login_modal');
            loginModal.modal('hide');
            loginModal.on('hidden.bs.modal', () => {
                $('.user-forget-form').get(0).reset();
                $('#forget_modal').modal('show');
            })
        })

        // forget
        $('.user-forget-form').on('submit', (e) => {
            e.preventDefault();
            let data = $('.user-forget-form').serialize();
            $.ajax({
                type : 'post',
                url : baseUrl + '/user/forget.do',
                data : data,
                dataType : 'json',
                success : (data) => {
                    if (data.statusCode === '00000') {
                        alert('修改密码成功');
                        $('#forget_modal').modal('hide');
                    } else {
                        console.log(data);
                        alert(data.data);
                    }
                }
            })
        })

        // user register form
        $('.user-register-form').on('submit', (e) => {
            e.preventDefault();
            let data = $('.user-register-form').serialize();
            console.log(data);
            $.ajax({
                type : 'post',
                url : baseUrl + '/user/register.do',
                data : data,
                dataType : 'json',
                success : (data) => {
                    // console.log(data);
                    if (data.statusCode === '00000') {
                        alert("注册成功")
                        $("#regist_modal").modal("hide");
                    } else {
                        alert(data.data)
                    }
                }
            })
        })

        // user login form
        $('.user-login-form').on('submit', (e) => {
            e.preventDefault();
            let data = $('.user-login-form').serialize();
            $.ajax({
                type : 'post',
                url : baseUrl + '/user/login.do',
                data : data,
                dataType : 'json',
                success : (data) => {
                    // console.log(data);
                    if (data.statusCode === '00000') {
                        window.location.reload();
                    } else {
                        alert(data.data)
                    }
                }
            })
        })

    });

    // openUserModal
    function openUserModal(isRegist) {
        // 注册
        if (isRegist) {
            $('.user-register-form').get(0).reset();
            $("#regist_modal").modal("show");
            return;
        }
        // 登录
        $('.user-login-form').get(0).reset();
        $("#login_modal").modal("show");
    }

    // init
    function init() {
        // 加载课程类别
        loadCourseType();

        // 加载 top10
        loadTop10();

        // 登录
        let loginUser = '${sessionScope.loginUser}';
        if (loginUser) {
            // 已登录
            $("#loginOff").hide();
            $("#loginOn").show();

            // 签到
            let signInToday = '${sessionScope.loginUser.signInToday}';
            if (signInToday) {
                // $(".expe").show().addClass('animated forward fadeOutUp');
                $(".signBtn").html("已签到").addClass('gray').removeClass('hoverRed');
            } else {
                $(".signBtn").on('click', signIn)
            }
        } else {
            // 未登录
            $("#loginOn").hide();
            $("#loginOff").show();
        }
    }

    // sign in
    function signIn() {
        $.ajax({
            type : 'post',
            url : baseUrl + '/user/sign_in.do',
            data : {},
            dataType : 'json',
            success : (data) => {
                console.log(data);
                if (data.statusCode === '00000') {
                    // $(".expe").show().addClass('animated forward fadeOutUp');
                    // $(".signBtn").html("已签到").unbind("click").addClass('gray').removeClass('hoverRed');
                    window.location.reload();
                } else {
                    alert(data.data);
                }
            }
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
                    let list = data.data;
                    for (let item of list) {
                        $.ajax({
                            type : 'post',
                            url : baseUrl + '/course/top4.do',
                            data : {'courseTypeId': item.id},
                            dataType : 'json',
                            async : false,
                            success : (data) => {
                                if (data.statusCode === '00000') {
                                    item.top4 = data.data;
                                } else {
                                    console.log(data);
                                    alert(data.data);
                                }
                            }
                        })
                    }
                    // console.log(list);
                    let html = template('typeItemList', {
                        'list': list,
                        'filePath': fileUrl
                    });
                    $('#typeItemDiv').html(html);
                } else {
                    console.log(data);
                    alert(data.data);
                }
            }
        })
    }

    // load top10
    function loadTop10() {
        $.ajax({
            type : 'post',
            url : baseUrl + '/course/top10.do',
            data : {},
            dataType : 'json',
            success : (data) => {
                if (data.statusCode === '00000') {
                    let list = data.data;
                    let html = template('list-top10', {
                        'list': list,
                        'filePath': fileUrl
                    });
                    $('#top10').html(html);
                } else {
                    console.log(data);
                    alert(data.data);
                }
            }
        })
    }

    // 表单提交前，缓存 front_select
    $('#search-form').on('submit', () => {
        let courseName = $('#courseName').val();
        let data = 'pageNo=1&pageSize=5&hideLearned=false&sortType=1&typeLevel=1';
        if (courseName) {
            data = 'courseName=' + courseName + '&' + data;
        }
        sessionStorage.setItem('front_select', new AjaxParam("/course/front_select.do", data).toString());
        return true;
    })

    // search
    function submitForm(name) {
        $('#courseName').val(name);
        $('#search-form').submit();
    }
</script>
<!-- template typeItemList -->
<script id="typeItemList" type="text/html" >
    {{each list type1}}
    <div class="item" data-type-id="{{type1.id}}">
        <a href="#">
            <span class="group">{{type1.typeName}}</span>
            <span class="tip">&gt;</span>
        </a>
        <div class="course-detail">
            {{each type1.courseTypes type2}}
            <div class="one">
                <div class="base" data-type-id="{{type2.id}}" >
                    <span>{{type2.typeName}}</span>
                    <div class="line"></div>
                </div>
                <p>
                    {{each type2.courseTypes type3}}
                    <a href="#" onclick="submitForm('{{type3.typeName}}')">
                        <span data-type-id="{{type3.id}}" >{{type3.typeName}}</span>
                    </a>
                    {{/each}}
                </p>
            </div>
            {{/each}}
            <!-- 当前分类下,点击量前四的课程 -->
            <div class="two">
                <div class="item-box" >
                    {{each type1.top4 course}}
                    <a href="${pageContext.request.contextPath}/course/course.do?id={{course.id}}">
                        <div class="item-course">
                            <div class="item-course-l">
                                <img src="{{filePath + course.coverImageUrl}}" alt="">
                            </div>
                            <div class="item-course-r">
                                <p>{{course.courseName}}</p>
                                <p>
                                    <span>职业路径</span>
                                    <span>5步/30课</span>
                                    <span>503人</span>
                                </p>
                                <p>￥{{course.totalCost}}</p>
                            </div>
                        </div>
                    </a>
                    {{/each}}
                </div>
            </div>
        </div>
    </div>
    {{/each}}
</script>
<script id="list-top10" type="text/html">
    <div class="course">
        <h3 class="types-title">
            <span class="tit-icon tit-icon-l"></span>
            <em>实</em>／<em>战</em>／<em>推</em>／<em>荐</em>
        </h3>
        <div class="course-box">
            {{each list course index}}
            {{if index < 5}}
            <div class="course-item">
                <div class="item-t">
                    <img src="{{filePath + course.coverImageUrl}}" alt="">
                    <div class="java">
                        <label>{{course.courseType.typeName}}</label>
                    </div>
                </div>
                <div class="item-b">
                    <a href="${pageContext.request.contextPath}/course/course.do?id={{course.id}}">
                        <h4>{{course.courseInfo}}</h4>
                    </a>
                    <p class="title">
                        <span>实战</span>
                        <span>高级</span>
                        <span>￥{{course.totalCost + 1}}</span>
                        <span>4星</span>
                    </p>
                    <p class="price">￥ {{course.totalCost}}.00</p>
                </div>
            </div>
            {{/if}}
            {{/each}}
        </div>
    </div>
    {{if list.length >= 5}}
    <div class="course">
        <div class="course-box">
            {{each list course index}}
            {{if index >= 5}}
            <div class="course-item">
                <div class="item-t">
                    <img src="{{filePath + course.coverImageUrl}}" alt="">
                    <div class="java">
                        <label>{{course.courseType.typeName}}</label>
                    </div>
                </div>
                <div class="item-b">
                    <a href="${pageContext.request.contextPath}/course/course.do?id={{course.id}}">
                        <h4>{{course.courseInfo}}</h4>
                    </a>
                    <p class="title">
                        <span>实战</span>
                        <span>高级</span>
                        <span>￥{{course.totalCost + 1}}</span>
                        <span>4星</span>
                    </p>
                    <p class="price">￥ {{course.totalCost}}.00</p>
                </div>
            </div>
            {{/if}}
            {{/each}}
        </div>
    </div>
    {{/if}}
</script>