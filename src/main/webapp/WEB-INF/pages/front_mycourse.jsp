<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>我的课程</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logn.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/font_0/iconfont.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/_my.css" />
        <style type="text/css">
            .file {
                position: relative;
                display: inline-block;
                background: #D0EEFF;
                border: 1px solid #99D3F5;
                border-radius: 4px;
                padding: 4px 12px;
                overflow: hidden;
                color: #1E88C7;
                text-decoration: none;
                text-indent: 0;
                line-height: 20px;
                width: 100%;
                text-align: center;
            }

            .file:hover {
                background: #AADFFD;
                border-color: #78C3F3;
                color: #004974;
                text-decoration: none;
            }

            .file:focus {
                background: #AADFFD;
                border-color: #78C3F3;
                color: #004974;
                text-decoration: none;
            }
            html,body {
                height: 100%;
            }

            body > .wrap-cc{
                min-height: 100%;
            }

            .content-cc{
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
                                            <a href="#">
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
                    <div class="container banner-mycourse">
                        <div class="row">
                            <p>${sessionScope.loginUser.nickname}</p>
                        </div>
                        <div class="row">
                            <img src="${pageContext.request.contextPath}/images/boy.png" alt="" width="18">&nbsp;&nbsp;
                            <span>学习时长</span>&nbsp;
                            <span>94小时</span>&nbsp;
                            <span>积分 ${sessionScope.loginUser.totalPoint}</span>&nbsp;
                            <span>金币 ${sessionScope.loginUser.totalGold}</span>
                        </div>
                        <div class="row">
                            这位同学很懒，木有签名的说~~
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <ul class="title-ul">
                            <li class="current" show='cc-course'>最近学习</li>
                            <li class="source" show='cc-source'>我的资源</li>
                        </ul>
                        <div>
                            <!-- 最近学习 -->
                            <ul class="mycourse-content cc-course">

                            </ul>
                            <!-- 我的资源 -->
                            <ul class="mycourse-content cc-source">
                                <li style="text-align: right;margin-top:20px; ">
                                    <button class="btn btn-primary" onclick="openAdd()" style="width: 100px;">添加资源</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!-- modify userInfo modal -->
                <div class="modal fade" id="userSet" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">个人信息</h4>
                            </div>
                            <form action="../../php/editPassword.php" class="form-horizontal" method="post">
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

                <!-- add modal 资源模态框 -->
                <div class="modal fade" id="user_source" tabindex="-1" role="dialog" aria-labelledby="mySourceModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="mySourceModalLabel">资源</h4>
                            </div>
                            <form action="#" id="add-resource-form" class="form-horizontal" method="post">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">标题：</label>
                                        <div class="col-sm-6">
                                            <input id="resourceTitle" class="form-control" type="text" name="title" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">资源：</label>
                                        <div class="col-sm-6">
                                            <a href="javascript:fileUpload('#resourceFile');" id="add-file" class="file">选择文件</a>
                                            <input id="resourceFile" accept=".pdf, .mp4" type="file" name="course_resource_file" style="display: none;" onchange="fileChange(this)" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">资源花费类型：</label>
                                        <div class="col-sm-6">
                                            <select class="form-control" id="resourceCostType" name="file_cost_type_id">
                                                <option value="">请选择</option>
                                                <option value="0">积分</option>
                                                <option value="1">金币</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">花费值：</label>
                                        <div class="col-sm-6">
                                            <input id="resourceCostNumber" class="form-control" type="text" name="cost_value" />
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-info" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                                    <button type="submit" class="btn btn-info">确&nbsp;&nbsp;定</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- add modal 资源模态框 -->
                <div class="modal fade" id="user_source_modify" tabindex="-1" role="dialog" aria-labelledby="mySourceModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title">修改资源</h4>
                                <input type="hidden" id="user-resource-id">
                            </div>
                            <form action="#" id="modify-resource-form" class="form-horizontal" method="post">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">标题：</label>
                                        <div class="col-sm-6">
                                            <input id="resourceTitle-modify" class="form-control" type="text" name="title" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">资源：</label>
                                        <div class="col-sm-6">
                                            <a href="javascript:fileUpload('#resourceFile-modify');" id="modify-file" class="file">选择文件</a>
                                            <input id="resourceFile-modify" accept=".pdf, .mp4" type="file" name="course_resource_file" style="display: none;" onchange="fileChange(this)" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">资源花费类型：</label>
                                        <div class="col-sm-6">
                                            <select class="form-control" id="resourceCostType-modify" name="file_cost_type_id">
                                                <option value="">请选择</option>
                                                <option value="0">积分</option>
                                                <option value="1">金币</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">花费值：</label>
                                        <div class="col-sm-6">
                                            <input id="resourceCostNumber-modify" class="form-control" type="text" name="cost_value" />
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-info" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
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
    let stack = new Stack();

    $(function() {
        init();

        $(".arrow").click(function() {
            $(this).parent().next().toggle();
        })

        $(".source-modify").on('click', function() {
            $("#user_source").modal("show");
        });

        // addOne
        $('#add-resource-form').on('submit', (e) => {
            e.preventDefault();
            let formData = new FormData();
            formData.append('file', $('#resourceFile')[0].files[0]);
            formData.append('resourceTitle', $('#resourceTitle').val());
            formData.append('resourceCostType', $('#resourceCostType').val());
            formData.append('resourceCostNumber', $('#resourceCostNumber').val());
            formData.append('userId', '${sessionScope.loginUser.id}');
            $.ajax({
                type : 'post',
                url : baseUrl + '/resource/add.do',
                data : formData,
                contentType: false,
                processData: false,
                dataType : 'json',
                success : (data) => {
                    if (data.statusCode === '00000') {
                        $('#user_source').modal('hide');
                        window.location.reload();
                    } else {
                        console.log(data);
                        alert(data.data);
                    }
                }
            })
        })

        // updateOne
        $('#modify-resource-form').on('submit', (e) => {
            e.preventDefault();
            let formData = new FormData();
            formData.append('file', $('#resourceFile-modify')[0].files[0]);
            formData.append('resourceTitle', $('#resourceTitle-modify').val());
            formData.append('resourceCostType', $('#resourceCostType-modify').val());
            formData.append('resourceCostNumber', $('#resourceCostNumber-modify').val());
            formData.append('resourceId', $('#user-resource-id').val());
            $.ajax({
                type : 'post',
                url : baseUrl + '/resource/modify.do',
                data : formData,
                contentType: false,
                processData: false,
                dataType : 'json',
                success : (data) => {
                    if (data.statusCode === '00000') {
                        $('#user_source_modify').modal('hide');
                        window.location.reload();
                    } else {
                        console.log(data);
                        alert(data.data);
                    }
                }
            })
        })
    })

    // init
    function init() {
        let stackStr = sessionStorage.getItem("front_mycourse");
        // console.log(stackStr);
        if (stackStr) {
            let url;
            let data;
            for (let s of stackStr.split('|')) {
                url = s.split(',')[0].substring(s.split(',')[0].indexOf(':') + 1);
                data = s.split(',')[1].substring(s.split(',')[1].indexOf(':') + 1);
                let ajaxParam = new AjaxParam(url, data);
                stack.push(ajaxParam);
            }
            let ajaxParam = stack.peek();
            sendAjax(ajaxParam.url, ajaxParam.data);
            if (url.startsWith('/resource/list_purchase')) {
                $('.cc-course').show().siblings().hide();
                $('.title-ul li[show="cc-course"]').addClass('current').siblings().on('click', liClick).removeClass("current");
            }
            if (url.startsWith('/resource/list_my')) {
                $('.cc-source').show().siblings().hide();
                $('.title-ul li[show="cc-source"]').addClass('current').siblings().on('click', liClick).removeClass("current");
            }
        } else {
            $('.title-ul li:nth-child(2)').on('click', liClick);
            stack.push(new AjaxParam('/resource/list_purchase.do', 'pageNo=1&pageSize=4&userId=' + ${sessionScope.loginUser.id}))
            sendAjax(stack.peek().url, stack.peek().data);
        }
    }

    // li click
    function liClick() {
        // console.log($(this).attr("show"));
        let show = $(this).attr("show");
        if (show === 'cc-course') {
            $('.cc-course').html('')

            let ajaxParam = new AjaxParam('/resource/list_purchase.do',
                'pageNo=1&pageSize=3&userId=' + ${sessionScope.loginUser.id});
            stack.replace(ajaxParam);
            sendAjax(ajaxParam.url, ajaxParam.data)
        }
        if (show === 'cc-source') {
            $('.cc-source').html(
                '<li style="text-align: right;margin-top:20px; ">'
                + '<button class="btn btn-primary" onclick="openAdd()" style="width: 100px;">添加资源</button>'
                + '</li>'
            )

            let ajaxParam = new AjaxParam('/resource/list_my.do',
                'pageNo=1&pageSize=4&userId=' + ${sessionScope.loginUser.id});
            stack.replace(ajaxParam);
            sendAjax(ajaxParam.url, ajaxParam.data)
        }
        $('.' + $(this).attr("show")).show().siblings().hide();
        $(this).off('click').addClass('current').siblings().on('click', liClick).removeClass("current");
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
                    // console.log(stack.peek(), data);
                    let list = data.data.list;
                    if (url.startsWith('/resource/list_my')) {
                        for (let rs of list) {
                            let date = rs.createDate.split(' ')[0];
                            let time = rs.createDate.split(' ')[1];
                            rs.createYear = date.split('-')[0];
                            rs.createTime = date.split('-')[1] + '月' + date.split('-')[2] + '日 ' + time;

                            if (rs.fileType === 'MP4') {
                                let totalTime = rs.totalTime;
                                rs.minute = parseInt(parseFloat(totalTime) / 60);
                                rs.second = parseFloat(totalTime) % 60;
                            }
                        }
                        let html = template('cc-source', {
                            'list': list,
                            'filePath': fileUrl
                        });
                        setTimeout(() => {
                            $('.cc-source').append(html);
                        }, 250)
                    }
                    if (url.startsWith('/resource/list_purchase')) {
                        for (let us of list) {
                            let date = us.updateDate.split(' ')[0];
                            let time = us.updateDate.split(' ')[1];
                            us.updateYear = date.split('-')[0];
                            us.updateTime = date.split('-')[1] + '月' + date.split('-')[2] + '日 ' + time;

                            if (us.resource.fileType === 'MP4') {
                                let totalTime = us.resource.totalTime;
                                us.minute = parseInt(parseFloat(totalTime) / 60);
                                us.second = parseFloat(totalTime) % 60;
                                us.seePercent = parseInt((us.seeTime / us.resource.totalTime) * 100);
                            }
                        }
                        let html = template('cc-course', {
                            'list': list,
                            'filePath': fileUrl
                        });
                        setTimeout(() => {
                            $('.cc-course').append(html);
                        }, 250)
                    }

                    // load more when scroll
                    let pageNum = data.data.pageNum;
                    let pages = data.data.pages;
                    $(window).on('scroll', () => {
                        let scrollTop = $(this).scrollTop();
                        let scrollHeight = $(document).height();
                        let clientHeight = $(this).height();
                        // console.log(scrollTop + 1, scrollHeight, clientHeight)
                        if ((scrollTop + 1) + clientHeight >= scrollHeight) {
                            $(window).off('scroll');
                            if (pageNum >= pages) {
                                return;
                            } else {
                                pageNum++;
                            }
                            str = str.replace(/(pageNo=)([0-9]+)/, '$1' + pageNum);
                            stack.replace(new AjaxParam(url, str))
                            sendAjax(url, str);
                        }
                    });
                } else {
                    console.log(data);
                    alert(data.data)
                }
            }
        })
    }

    // add modal
    function openAdd() {
        $('#add-resource-form').get(0).reset();
        $('#add-file').html('选择文件');
        $("#user_source").modal("show");
    }

    // getResource
    function getResource(id) {
        $.ajax({
            type : 'post',
            url : baseUrl + '/resource/get.do',
            data : {'id': id},
            dataType : 'json',
            success : (data) => {
                if (data.statusCode === '00000') {
                    console.log(data);
                    let resource = data.data;

                    $('#modify-resource-form').get(0).reset();
                    $('#resourceTitle-modify').val(resource.title);
                    $('#resourceCostType-modify').val(resource.costType);
                    $('#modify-file').html('选择文件');
                    $('#resourceCostNumber-modify').val(resource.costNumber);
                    $('#user-resource-id').val(resource.id);
                    $('#user_source_modify').modal('show');
                } else {
                    console.log(data);
                    alert(data.data);
                }
            }
        })
    }

    // 文件上传
    function fileUpload(item) {
        $(item).click();
    }
    function fileChange(item) {
        //获取选中的第一个文件
        let file = item.files[0];
        if (file) {
            $(".file").html(file.name);
        } else {
            $(".file").html("选择文件");
        }
    }

    // deleteResource
    function deleteResource(id) {
        console.log(id)
        $.ajax({
            type : 'post',
            url : baseUrl + '/resource/delete.do',
            data : {'id': id},
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
    }

    // 表单提交前，缓存 front_select
    $('#search-form').on('submit', () => {
        let data = 'pageNo=1&pageSize=5&hideLearned=false&sortType=1&typeLevel=1';
        let courseName = $('#courseName').val();
        if (courseName) {
            data = 'courseName=' + courseName + '&' + data;
        }
        sessionStorage.setItem('front_select', new AjaxParam("/course/front_select.do", data).toString());
        return true;
    })

    // 页面关闭，缓存 stack
    $(window).on('beforeunload', () => {
        sessionStorage.setItem("front_mycourse", stack.toString());
    })
</script>
<script id="cc-source" type="text/html">
    {{each list item}}
    <li>
        <div class="col-md-1 col-sm-1">
            <strong>{{item.createYear}}</strong>
            <div>
                {{item.createTime}}
            </div>
        </div>
        <div class="col-md-11 col-sm-11">
            <span class="circle"></span>
            <div class="row  border-bottom">
                <div class="col-md-3">
                    <img src="{{filePath + item.coverImageUrl}}" alt="" height="120" class="mycourseImg">
                </div>
                {{if item.status == -1}}
                <div class="col-md-8 mycourse-info forbidden">
                {{else if item.status == 1}}
                <div class="col-md-8 mycourse-info">
                {{/if}}
                    <p class="padding-top-25">
                        <span>
                            {{if item.status == 1}}
                            <a href="${pageContext.request.contextPath}/resource/search_front.do?id={{item.id}}">{{item.title}}</a>
                            {{else if item.status == -1}}
                            <a href="javascript:alert('该资源已被禁用')" class="not-active">{{item.title}}</a>
                            {{/if}}
                        </span>
                        <span>{{item.fileSize}}MB</span>
                    </p>
                    <p class="padding-10">
                        <span>{{item.fileType}}</span> &nbsp;&nbsp;&nbsp;&nbsp;
                        {{if item.fileType == 'MP4'}}
                        <span>
                            {{if item.minute > 0}}
                                {{item.minute}}分
                            {{/if}}
                            {{item.second}}秒
                        </span> &nbsp;&nbsp;&nbsp;&nbsp;
                        {{/if}}
                        <span>{{item.originalName}}</span>
                    </p>
                    <div>
                        <div>
                            {{if item.costType == 0}}
                                积分
                            {{else if item.costType == 1}}
                                金币
                            {{/if}}
                        </div>
                        <div>{{item.costNumber}}</div>
                        <div>点击量：&nbsp;&nbsp;{{item.clickCount}}</div>
                        <div class="nostyle">
                            {{if item.status == 1}}
                            <button class="btn btn-warning source-modify" onclick="getResource('{{item.id}}')">修改</button>
                            {{else if item.status == -1}}
                            <button class="btn btn-default source-modify" disabled>修改</button>
                            {{/if}}
                            <button class="btn btn-danger" onclick="deleteResource('{{item.id}}')">删除</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </li>
    {{/each}}
</script>
<script id="cc-course" type="text/html">
    {{each list item}}
    <li>
        <div class="col-md-1 col-sm-1">
            <strong>{{item.updateYear}}</strong>
            <div>
                {{item.updateTime}}
            </div>
        </div>
        <div class="col-md-11 col-sm-11">
            <span class="circle"></span>
            <div class="row border-bottom">
                <div class="col-md-3">
                    <img src="{{filePath + item.resource.coverImageUrl}}" alt="" height="120" class="mycourseImg">
                </div>
                <div class="col-md-8 mycourse-info">
                    <p class="padding-top-25">
                        <span><a href="${pageContext.request.contextPath}/resource/search_front.do?id={{item.resource.id}}">{{item.resource.title}}</a></span>
                        <span></span>
                    </p>
                    <p class="padding-10">
                        {{if item.resource.fileType == 'MP4'}}
                        <span>已看{{item.seePercent}}%</span>&nbsp;&nbsp;&nbsp;&nbsp;
                        <span>
                            {{if item.minute > 0}}
                                {{item.minute}}分
                            {{/if}}
                            {{item.second}}秒
                        </span>&nbsp;&nbsp;&nbsp;&nbsp;
                        {{/if}}
                        <span>{{item.resource.fileType}}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                        <span>{{item.resource.originalName}}</span> &nbsp;&nbsp;&nbsp;&nbsp;
                        <span>{{item.resource.fileSize}}MB</span>
                    </p>
                    <div>
                        <div>笔记0</div>
                        <div>代码0</div>
                        <div>问答0</div>
                        {{if item.resource.fileType == 'MP4'}}
                        <div><a class="continue-see" href="${pageContext.request.contextPath}/resource/search_front.do?id={{item.resource.id}}&see={{item.seeTime}}">继续学习</a></div>
                        {{else if item.resource.fileType == 'PDF'}}
                        <div><a class="continue-see" href="${pageContext.request.contextPath}/resource/search_front.do?id={{item.resource.id}}">继续学习</a></div>
                        {{/if}}
                    </div>
                </div>
            </div>
        </div>
    </li>
    {{/each}}
</script>