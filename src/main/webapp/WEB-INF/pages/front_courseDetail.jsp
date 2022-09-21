<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>课程详情</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logn.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
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
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="javascript:window.history.back()" class="vertical-middle"><i class="glyphicon glyphicon-menu-left"></i></a></li>
                            <li class="vertical-middle text-color">
                                <c:choose>
                                    <c:when test="${requestScope.resource.chapter != null}">
                                        ${requestScope.resource.chapter.course.courseName}
                                    </c:when>
                                    <c:otherwise>
                                        ${requestScope.resource.title}
                                    </c:otherwise>
                                </c:choose>
                            </li>
                            <li class="vertical-middle little-title">
                                <c:choose>
                                    <c:when test="${requestScope.resource.chapter != null}">
                                        ${requestScope.resource.chapter.title} \ ${requestScope.resource.title}
                                    </c:when>
                                    <c:otherwise>
                                        上传者：${requestScope.resource.user.nickname}
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </ul>
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
                        <!-- /.navbar-collapse -->
                    </div>
                    <!-- /.container-fluid -->
                </nav>
                <div class="container-fluid padding-0 bgColor">
                    <video id="vv" style="width: 100%; height:470px;" controls="controls">
                    </video>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-9">
                            <ul class="title-ul">
                                <li class="current">评论</li>
                                <li>问答</li>
                                <li>笔记</li>
                            </ul>
                            <div class="row comment-area">
                                <div class="col-md-1"><img src="${pageContext.request.contextPath}/images/user-profile.png" alt="" class="img-circle " width="35" height="35"></div>
                                <div class="col-md-9">
                                    <textarea id="comment-context" cols="70" rows="10" style="resize:none; padding: 8px"></textarea>
                                </div>
                                <div class="col-md-2 col-md-offset-10 ">
                                    <button class="btn btn-success" onclick="comment()">发表评论</button>
                                </div>
                            </div>
                            <ul id="commentDetail" class="margin-bottom-90">
                                <c:forEach items="${requestScope.resource.comments}" var="comment">
                                    <li>
                                        <div class="row comment-area">
                                            <div class="col-md-1"><img src="${pageContext.request.contextPath}/images/user-profile.png" alt="" class="img-circle " width="35" height="35"></div>
                                            <div class="col-md-10 comment-info">
                                                <div class="col-md-12">${comment.user.nickname}</div>
                                                <div class="col-md-12">${comment.context}</div>
                                                <div class="col-md-12">
                                                    <div>
                                                        时间：<span><fmt:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd HH:mm:dd"/></span>
                                                    </div>
                                                    <div>
                                                        <span>举报</span>
                                                        <span class="addPraise" ><i class="icon iconfont icon-zan"></i>${comment.priseCount == null ? 0 : comment.priseCount}</span>
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
    </body>
</html>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/_my.js"></script>
<script src="${pageContext.request.contextPath}/js/template-web.js"></script>
<script>
    $(function() {
        loadTop3();

        let vv = $('#vv');
        vv.attr('src', fileUrl + '${requestScope.path}')
            .attr('poster', fileUrl + '${requestScope.coverImageUrl}');
        vv.get(0).currentTime = '${requestScope.see}';

        $(".title-ul>li").on('click', function() {
            // console.log($(this));
            $(this).addClass('current').siblings().removeClass("current");
        })

        $('.addPraise').bind('click', function(){
            alert('点赞成功');
        });
    })

    // comment
    function comment() {
        $.ajax({
            type : 'post',
            url : baseUrl + '/comment/add.do',
            data : {
                'userId': '${sessionScope.loginUser.id}',
                'resourceId': '${requestScope.resource.id}',
                'context': $('#comment-context').val()
            },
            dataType : 'json',
            success : (data) => {
                if (data.statusCode === '00000') {
                    alert("发布成功，等待审核");
                    let href = window.location.href
                    href = href.replace(/see=([0-9.]+)/, 'see=' + $('#vv').get(0).currentTime)
                    window.location.href = href;
                } else {
                    console.log(data);
                    alert(data.data);
                }
            }
        })
    }

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

    // 页面关闭，保存观看时间点
    $(window).on('beforeunload', () => {
        let curTime = $('#vv').get(0).currentTime;
        $.ajax({
            type : 'post',
            url : baseUrl + '/resource/update_ur.do',
            data : {
                'seeTime': curTime,
                'userId': '${sessionScope.loginUser.id}',
                'resourceId': '${requestScope.resource.id}'
            },
            dataType : 'json'
        })
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