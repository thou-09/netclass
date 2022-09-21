<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>积分金币</title>
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
                    <div class="container">
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
                                            <a href="#">
                                                <i class="glyphicon glyphicon-record"></i>积分记录
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#" data-toggle="modal" data-target="#userSet">
                                                <i class="glyphicon glyphicon-cog"></i>个人设置
                                            </a>
                                            <a id="logout-exit" href="javascript:void(0);"><i class="glyphicon glyphicon-off"></i>退出登录</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <!-- /.navbar-collapse -->
                    </div>
                    <!-- /.container-fluid -->
                </nav>
                <div class="container padding-20">
                    <div class="row ">
                        <div class="col-md-3">
                            <p class="big-title">积分记录</p>
                        </div>
                        <div class="col-md-3 col-md-offset-6 convert">
                            <p>当前积分：<span>${sessionScope.loginUser.totalPoint}</span></p>
                            <p>当前金币：<span>${sessionScope.loginUser.totalGold}</span>
                                <button class="btn btn-warning" data-toggle="modal" data-target="#record">兑换金币</button>
                            </p>
                        </div>
                    </div>
                    <table class="table table-hover table-striped  table-responsive padding-20 margin-top-20 ">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>数值</th>
                            <th>类型</th>
                            <th>详情</th>
                            <th>时间</th>
                        </tr>
                        </thead>
                        <tbody id="tb">

                        </tbody>
                    </table>
                </div>
                <!-- 分页 -->
                <div style="text-align: center;" >
                    <ul id="myPages" ></ul>
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
                    <form action="#" class="form-horizontal user-modify-form" method="post">
                        <input type="hidden" name="id" value="${sessionScope.loginUser.id}" />
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
                            <button type="submit" class="btn btn-info">修&nbsp;&nbsp;改</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="modal fade" id="record" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myRecordModalLabel">兑换金币(10积分=1金币)</h4>
                    </div>
                    <form action="#" class="form-horizontal exchange-form" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <div class="col-sm-6 col-sm-offset-2 text-right">
                                    <input class="form-control exchange-input" type="text" />
                                </div>
                                <label class="col-sm-4 control-label" style="text-align: left;">金币</label>
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
<script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
<script src="${pageContext.request.contextPath}/js/_my.js"></script>
<script src="${pageContext.request.contextPath}/js/template-web.js"></script>
<script>
    let stack = new Stack();

    $(function() {
        init();

        $(".arrow").click(function() {
            $(this).parent().next().toggle();
        })

        $(".title-ul>li").on('click', function() {
            console.log($(this));
            $(this).addClass('current').siblings().removeClass("current");
        })

        // logout
        $('#logout-exit').on('click', () => {
            $.ajax({
                type : 'get',
                url : baseUrl + '/user/user_logout.do',
                data : {},
                dataType : 'json',
                success : (data) => {
                    if (data.statusCode === '00000') {
                        sessionStorage.clear();
                        $(window).off('beforeunload');
                        window.location.href = '${pageContext.request.contextPath}/show_front_index.do';
                    } else {
                        console.log(data);
                        alert(data.data);
                    }
                }
            })
        })

        // exchange
        $('.exchange-form').on('submit', (e) => {
            e.preventDefault();
            let num = $('.exchange-input').val();
            $.ajax({
                type : 'get',
                url : baseUrl + '/goldPoint/exchange.do',
                data : {
                    'userId': '${sessionScope.loginUser.id}',
                    'goldCount': num
                },
                dataType : 'json',
                success : (data) => {
                    if (data.statusCode === '00000') {
                        $('#record').modal('hide');
                        $('.exchange-input').val('');
                        window.location.reload();
                    } else {
                        console.log(data);
                        alert(data.data);
                    }
                }
            })
        })

        // modify
        $('.user-modify-form').on('submit', (e) => {
            e.preventDefault();
            let data = $('.user-modify-form').serialize();
            $.ajax({
                type : 'post',
                url : baseUrl + '/user/modify4_front.do',
                data : data,
                dataType : 'json',
                success : (data) => {
                    if (data.statusCode === '00000') {
                        $('#userSet').modal('hide');
                        window.location.reload();
                    } else {
                        console.log(data);
                        alert(data.data);
                    }
                }
            })
        })

        $('#userSet').on('show.bs.modal', () => {
            $('.user-modify-form').get(0).reset();
        })

        myoptions.onPageClicked = function(event, originalEvent, type, page) {
            let str = '';
            if (stack.peek().data) {
                let pattern = /(pageNo=)([0-9]*)/;
                str = stack.peek().data;
                if (page) {
                    if (pattern.test(str)) {
                        str = str.replace(/(pageNo=)([0-9]*)/, '$1' + page);
                    } else {
                        str += '&pageNo=' + page;
                    }
                }
            } else {
                if (page) {
                    str = 'pageNo=' + page + '&userId=' + '${sessionScope.loginUser.id}';
                }
            }
            // 点击分页按钮后，替换栈顶的 ajax 请求
            stack.replace(new AjaxParam('/goldPoint/list.do', str))
            sendAjax('/goldPoint/list.do', str);
        };
    })

    // init
    function init() {
        let stackStr = sessionStorage.getItem("front_record");
        if (stackStr) {
            for (let s of stackStr.split('|')) {
                let url = s.split(',')[0].substring(s.split(',')[0].indexOf(':') + 1);
                let data = s.split(',')[1].substring(s.split(',')[1].indexOf(':') + 1);
                let ajaxParam = new AjaxParam(url, data);
                stack.push(ajaxParam);
            }
            let ajaxParam = stack.peek();
            sendAjax(ajaxParam.url, ajaxParam.data);
        } else {
            stack.push(new AjaxParam('/goldPoint/list.do', 'userId=' + '${sessionScope.loginUser.id}'));
            sendAjax(stack.peek().url, stack.peek().data);
        }
    }

    // sendAjax
    function sendAjax(url, str) {
        $.ajax({
            type : 'post',
            url : baseUrl + url,
            data : str,
            dataType : 'json',
            success : (data) => {
                if (data.statusCode === '00000') {
                    // console.log(data);
                    let pageInfo = data.data;
                    if (pageInfo.total) {
                        myPaginatorFun("myPages", pageInfo.pageNum, pageInfo.pages);
                        let list = pageInfo.list;
                        let html = template('list-data', {'list': list});
                        $('#tb').html(html);
                    } else {
                        myPaginatorFun("myPages", 1, 1);
                        $('#tb').html('');
                    }
                } else {
                    console.log(data);
                    window.parent.sonPageMsg(data);
                }
            }
        })
    }

    // 页面关闭，缓存 stack
    $(window).on('beforeunload', () => {
        sessionStorage.setItem("front_record", stack.toString());
    })
</script>
<script id="list-data" type="text/html">
    {{each list item index}}
    <tr>
        <td>{{item.id}}</td>
        {{if item.pointCount != null}}
        <td>{{item.pointCount}}</td>
        <td>积分</td>
        {{else item.goldCount != null}}
        <td>{{item.goldCount}}</td>
        <td>金币</td>
        {{/if}}
        <td>{{item.info}}</td>
        <td>{{item.createDate}}</td>
    </tr>
    {{/each}}
</script>