<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>课程</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logn.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/font_1/iconfont.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/font_0/iconfont.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/_my.css" />
        <style>
            html,
            body {
                height: 100%;
            }

            body>.wrap-cc {
                min-height: 100%;
            }

            .content-cc {
                /* padding-bottom 等于 footer 的高度 */
                padding-bottom: 80px;
            }

            .footer-cc {
                width: 100%;
                height: 80px;
                /* margin-top 为 footer 高度的负值 */
                margin-top: -80px;
            }
        </style>
    </head>
    <body>
        <div class="wrap-cc">
            <div class="content-cc">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <!--  <a class="navbar-brand" href="#">Brand</a> -->
                            <a href="${pageContext.request.contextPath}/show_front_index.do">
                                <img src="${pageContext.request.contextPath}/images/com-logo1.png" alt="" width="120" style="margin-right: 20px;">
                            </a>
                        </div>
                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li><a href="#" class="vertical-middle">免费课程</a></li>
                                <li><a href="#" class="vertical-middle">职业路径</a></li>
                            </ul>
                            <form id="search-form" action="${pageContext.request.contextPath}/show_front_select.do" class="navbar-form navbar-left searchInput" style="padding:10px;">
                                <div class="form-group">
                                    <input style="color: #fefeff;" id="courseName"
                                           type="text" class="form-control " placeholder="Search by 课程名称">
                                </div>
                                <button type="submit" class="btn btn-default "><span class="glyphicon glyphicon-search"></span></button>
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
                <div class="container-fluid banner">
                    <div class="container banner-contain">
                        <div class="row">
                            <p>课程&bsol;
                                ${requestScope.course.courseType.courseType.courseType.typeName}&bsol;
                                ${requestScope.course.courseType.courseType.typeName}&bsol;
                                ${requestScope.course.courseType.typeName}</p>
                        </div>
                        <div class="row">
                            <p>${requestScope.course.courseType.typeName}</p>
                        </div>
                        <div class="row">
                            <button class="btn    btn-success  col-md-2">
                                继续学习 | &nbsp;&nbsp; <i class="glyphicon glyphicon-star-empty">  </i>
                            </button>
                            <ul class="col-md-5">
                                <li>点击量
                                    <p>${requestScope.course.clickNumber}</p>
                                </li>
                                <li>课程时长
                                    <p>
                                        <c:if test="${(requestScope.course.timeCount / 3600) >= 1}">
                                            <fmt:formatNumber pattern="##" value="${requestScope.course.timeCount / 3600}" maxFractionDigits="0"/>小时
                                        </c:if>
                                        <c:if test="${(requestScope.course.timeCount / 60) >= 1}">
                                            <fmt:formatNumber pattern="##" value="${requestScope.course.timeCount / 60}" maxFractionDigits="0"/>分
                                        </c:if>
                                        <c:if test="${(requestScope.course.timeCount % 60) > 0}">
                                            <fmt:formatNumber value="${requestScope.course.timeCount % 60}" maxFractionDigits="0"/>秒
                                        </c:if>
                                </li>
                                <li>综合评分
                                    <p>9.7</p>
                                </li>
                            </ul>
                            <ul class="three-logo  col-md-4  col-md-offset-1 ">
                                <li>
                                    <i class="icon iconfont icon-weixin"></i>
                                </li>
                                <li>
                                    <i class="icon iconfont icon-weibo"></i>
                                </li>
                                <li>
                                    <i class="icon iconfont icon-qq"></i>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-9">
                            <div class="desp">${requestScope.course.courseInfo}</div>
                            <ul class="title-ul">
                                <li class="current" name="chapter">章节</li>
                                <li name="comment">评价</li>
                            </ul>
                            <ul class="course-content margin-bottom-90" id="chapter">
                                <c:forEach items="${requestScope.course.chapters}" var="chapter" varStatus="status">
                                    <li>
                                        <div class="row">
                                            <div class="col-md-12 course-title">
                                                <i class="icon  i-round iconfont icon-weibiaoti-"></i>
                                                第${status.index + 1}章
                                                <span>${chapter.title}</span>
                                                <i class="glyphicon glyphicon-triangle-bottom pull-right arrow"></i>
                                            </div>
                                            <ul class="lesson-title">
                                                <li class="col-md-11 col-md-offset-1 padding-10 ">
                                                    <span class="glyphicon glyphicon-triangle-right icon-right"></span>
                                                    <a class="check-owned" data-id="${chapter.resource.id}" href="${pageContext.request.contextPath}/resource/search_front.do?id=${chapter.resource.id}">
                                                        <span>
                                                            ${status.index + 1}-${1} ${chapter.resource.title}
                                                            <c:if test="${chapter.resource.totalTime != null}">
                                                                (<fmt:formatNumber pattern="##" value="${chapter.resource.totalTime / 60}" maxFractionDigits="0"/>:<fmt:formatNumber value="${chapter.resource.totalTime % 60}" maxFractionDigits="0"/>)
                                                            </c:if>
                                                        </span>
                                                        <span class="cost-cost">
                                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                                            <c:choose>
                                                                <c:when test="${chapter.resource.costType == 0}">
                                                                    <span class="cost-type">积分</span>&nbsp;&nbsp;<span class="cost-number">${chapter.resource.costNumber}</span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="cost-type">金币</span>&nbsp;&nbsp;<span class="cost-number">${chapter.resource.costNumber}</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </span>
                                                    </a>
                                                </li>
                                                <%--
                                                    <c:forEach items="${chapter.resource}" var="re" varStatus="status2">
                                                    <li class="col-md-11 col-md-offset-1 padding-10 ">
                                                        <span class="glyphicon glyphicon-triangle-right icon-right"></span>
                                                        <a href="${pageContext.request.contextPath}/resource/search.do?id=${re.id}" target="_blank">
                                                    <span>${status.index + 1}-${status2.index + 1} ${re.title}</span>
                                                        </a>
                                                    </li>
                                                </c:forEach>
                                                --%>
                                            </ul>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                            <ul id="comment" class="margin-bottom-90">
                                <c:forEach items="${requestScope.course.comments}" var="comment">
                                    <li>
                                        <div class="row comment-area">
                                            <div class="col-md-1">
                                                <img src="${pageContext.request.contextPath}/images/user-profile.png" alt="" class="img-circle " width="35" height="35">
                                            </div>
                                            <div class="col-md-10 comment-info">
                                                <div class="col-md-12">${comment.user.nickname}</div>
                                                <div class="col-md-12">${comment.context}</div>
                                                <div class="col-md-12">
                                                    <div>
                                                        时间：<span><fmt:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                                                    </div>
                                                    <div>
                                                        <span>举报</span>
                                                        <span data-comment="${comment.id}" class="praise"><i class="icon iconfont icon-zan"></i>${comment.priseCount == null ? 0 : comment.priseCount}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="col-md-3" id="good-course">

                        </div>
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
            <div class="footer">
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
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/_my.js"></script>
<script src="${pageContext.request.contextPath}/js/template-web.js"></script>
<script>
    $(function() {
        loadTop3();

        $(".arrow").click(function() {
            $(this).parent().next().toggle();
        })

        $('.praise').on('click', (e) => {
            $.ajax({
                type : 'get',
                url : baseUrl + '/comment/praise.do',
                data : {
                    'userId': '${sessionScope.loginUser.id}',
                    'commentId': $(e.currentTarget).attr('data-comment')
                },
                dataType : 'json',
                success : (data) => {
                    if (data.statusCode === '00000') {
                        window.location.reload();
                    } else {
                        console.log(data);
                        alert(data.data);
                    }
                }
            })
        })

        $('.check-owned').on('click', (e) => {
            e.preventDefault();
            let a = $(e.currentTarget);
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
                                + $(cc).children('.cost-number').text()
                                + $(cc).children('.cost-type').text());
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
        })

        $(".title-ul>li").on('click', function() {
            $(this).addClass('current').siblings().removeClass("current");
            let panelId = "#" + $(this).attr("name");
            $(this).parent().nextAll().css('display','none');
            $(panelId).css('display','block');
        })
    })

    // load top3
    function loadTop3() {
        $.ajax({
            type : 'post',
            url : baseUrl + '/course/top10.do',
            data : {},
            dataType : 'json',
            success : (data) => {
                if (data.statusCode === '00000') {
                    let list = data.data;
                    let html = template('list-good-course', {
                        'list': list,
                        'filePath': fileUrl
                    });
                    $('#good-course').html(html);
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
</script>
<script id="list-good-course" type="text/html">
    <div class="row teacher-msg">
        <div class="col-md-12 course-title padding-30">推荐课程</div>
    </div>
    {{each list course index}}
    {{if index < 3}}
    <div class="row recommd-course">
        <div class="col-md-4">
            <img src="{{filePath + course.coverImageUrl}}" alt="">
        </div>
        <div class="col-md-8">
            <div><a href="${pageContext.request.contextPath}/course/course.do?id={{course.id}}">{{course.courseName}}</a></div>
            <div>{{course.courseInfo}}</div>
        </div>
    </div>
    {{/if}}
    {{/each}}
</script>