<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>优学堂精品课程管理系统</title>
        <!-- 网页图标icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logn.png">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/_my.css" />
        <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    </head>

    <body>
        <div class="wrapper-cc clearfix">
            <div class="content-cc">
                <!-- header start -->
                <div class="clear-bottom head">
                    <div class="container head-cc">
                        <p>优学堂精品课程<span>后台管理系统</span></p>
                        <div class="welcome">
                            <div class="left">欢迎您：</div>
                            <div class="right">${sessionScope.loginAdmin.nickname}</div>
                            <div class="exit">
                                <a style="color: inherit;" onclick="logout()">退出</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- header end -->
                <!-- content start -->
                <div class="container-flude flude-cc" id="a">
                    <div class="row user-setting" style="margin-right: 0">
                        <div class="col-xs-2 user-wrap">
                            <ul class="list-group">
                                <li class="list-group-item sys-item active" data-name="back_userSet" >
                                    <i class="glyphicon glyphicon-user"></i> &nbsp;用户管理
                                </li>
                                <li class="list-group-item sys-item" data-name="back_courseSet" >
                                    <i class="glyphicon glyphicon-facetime-video"></i> &nbsp;课程管理
                                </li>
                                <li class="list-group-item sys-item" data-name="back_courseTypeSet" >
                                    <i class="glyphicon glyphicon-list"></i> &nbsp;课程类别管理
                                </li>
                                <li class="list-group-item sys-item" data-name="back_resourceSet" >
                                    <i class="glyphicon glyphicon-file"></i> &nbsp;资源管理
                                </li>
                                <li class="list-group-item sys-item" data-name="back_commentManageSet" >
                                    <i class="glyphicon glyphicon-comment"></i> &nbsp;评论审核
                                </li>
                            </ul>
                        </div>
                        <div class="col-xs-10" id="userPanel">
                            <iframe id="frame-id" src="${pageContext.request.contextPath}/show_back_userSet.do" width="100%" height="100%" frameborder="0" scrolling="yes"></iframe>
                        </div>
                    </div>
                </div>
                <!-- content end-->
            </div>
        </div>
        <div class="footer">
            <!-- footers start -->
            版权所有：南京网博 &nbsp; &nbsp; BY: thou
            <!-- footers end -->
        </div>
    </body>
</html>
<script src="${pageContext.request.contextPath}/js/_my.js"></script>
<script>
    $(function () {
        init();

        $(".user-setting li").click(function() {
            $(".user-setting li").removeClass("active");
            $(this).addClass("active");
        });
        // 点击切换页面
        $(".sys-item").on('click', function(){
            let name = $(this).attr("data-name");
            if (name === 'back_courseSet') {
                if (sessionStorage.getItem('back_courseResourceSet')) {
                    if (sessionStorage.getItem('back_commentSet')) {
                        $('#frame-id').attr('src', 'show_back_commentSet.do');
                    } else {
                        $('#frame-id').attr('src', 'show_back_courseResourceSet.do');
                    }
                } else {
                    $('#frame-id').attr('src', 'show_back_courseSet.do');
                }
            } else {
                $('#frame-id').attr('src', 'show_' + name + '.do');
            }
            sessionStorage.setItem('data-name', name);
        });
    });

    function init() {
        let dataName = sessionStorage.getItem('data-name');
        if (dataName) {
            let li = $($('.user-setting li[data-name='+ dataName +']').get(0));
            $(".user-setting li").removeClass("active");
            li.addClass("active");
            if (dataName === 'back_courseSet') {
                if (sessionStorage.getItem('back_courseResourceSet')) {
                    if (sessionStorage.getItem('back_commentSet')) {
                        $('#frame-id').attr('src', 'show_back_commentSet.do');
                    } else {
                        $('#frame-id').attr('src', 'show_back_courseResourceSet.do');
                    }
                } else {
                    $('#frame-id').attr('src', 'show_' + dataName + '.do');
                }
            } else {
                $('#frame-id').attr('src', 'show_' + dataName + '.do');
            }
        }
    }

    function sonPageMsg(data) {
        console.log(data)
        alert(data.data);
    }

    function logout() {
        $.ajax({
            type : 'post',
            url : baseUrl + '/user/admin_logout.do',
            data : {},
            dataType : 'json',
            async : false,
            success : (data) => {
                if (data.statusCode === '00000') {
                    sessionStorage.clear();
                    window.location.href = '${pageContext.request.contextPath}/';
                }
            }
        })
    }
</script>