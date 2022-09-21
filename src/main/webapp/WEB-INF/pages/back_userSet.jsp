<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>用户管理</title>
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/_my.css" />
        <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript"
                charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
    </head>
    <body>
        <div class="panel panel-default" id="userInfo">
            <div class="panel-heading">
                <h3 class="panel-title">用户管理</h3>
            </div>
            <div>
                <input type="button" value="查询" class="btn btn-success" id="doSearch" style="margin: 5px 5px 5px 15px;" />
                <input type="button" class="btn btn-primary" id="show-user-search" value="展开搜索" />
                <input type="button" value="收起搜索" class="btn btn-primary" id="upp-user-search" style="display: none;" />
                <input type="button" value="清空查询条件" class="btn btn-warning" id="form-clear-btn" />
            </div>

            <div class="panel-body">
                <div class="showusersearch" style="display: none;">
                    <form class="form-horizontal search-user-form">
                        <div class="form-group">
                            <div class="form-group col-xs-6">
                                <label for="user-name" class="col-xs-3 control-label">登录名：</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" id="user-name"
                                           placeholder="请输入登录名" name="loginName" />
                                </div>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="user-nickname" class="col-xs-3 control-label">昵称：</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" id="user-nickname"
                                           placeholder="请输入昵称" name="nickname" />
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-group col-xs-6">
                                <label for="user-email" class="col-xs-3 control-label">邮箱：</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" id="user-email"
                                           placeholder="请输入邮箱" name="email" />
                                </div>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="role-name" class="col-xs-3 control-label">角色：</label>
                                <div class="col-xs-8">
                                    <select class="form-control" id="role-name" name="role">
                                        <option value="">全部</option>
                                        <option value="normal">普通</option>
                                        <option value="admin">管理员</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-group col-xs-6">
                                <label class="col-xs-3 control-label">开始日期：</label>
                                <div class="col-xs-8">
                                    <input type="date" class="form-control"
                                           placeholder="请选择创建开始时间" name="createDateStart" />
                                </div>
                            </div>
                            <div class="form-group col-xs-6">
                                <label class="col-xs-3 control-label">结束日期：</label>
                                <div class="col-xs-8">
                                    <input type="date" class="form-control"
                                           placeholder="请选择创建结束时间" name="createDateEnd" />
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-group col-xs-6">
                                <label class="col-xs-3 control-label">开始日期：</label>
                                <div class="col-xs-8">
                                    <input type="date" class="form-control"
                                           placeholder="请选择登录开始时间" name="signDateStart" />
                                </div>
                            </div>
                            <div class="form-group col-xs-6">
                                <label class="col-xs-3 control-label">结束日期：</label>
                                <div class="col-xs-8">
                                    <input type="date" class="form-control"
                                           placeholder="请选择登录结束时间" name="signDateEnd" />
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="show-list">
                    <table class="table table-bordered table-hover"
                           style='text-align: center;'>
                        <thead>
                            <tr class="text-danger">
                                <th class="text-center">编号</th>
                                <th class="text-center">登录名</th>
                                <th class="text-center">角色</th>
                                <th class="text-center">昵称</th>
                                <th class="text-center">邮箱</th>
                                <th class="text-center">创建日期</th>
                                <th class="text-center">最近登录日期</th>
                                <th class="text-center">操作</th>
                            </tr>
                        </thead>
                        <tbody id="tb"></tbody>
                    </table>
                </div>

                <!-- 分页 -->
                <div style="text-align: center;">
                    <ul id="myPages"></ul>
                </div>

                <!-- modify modal -->
                <div class="modal fade" tabindex="-1" id="modifyModal">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">用户修改</h4>
                            </div>
                            <div class="modal-body text-center">
                                <form action="#" class="form-horizontal modify-form">
                                    <div class="form-group">
                                        <div class="form-group row text-right">
                                            <label for="user_id" class="col-xs-4 control-label">编号：</label>
                                            <div class="col-xs-4">
                                                <input type="text" class="form-control" id="user_id" name="id" readonly />
                                            </div>
                                        </div>
                                        <br>
                                        <div class="form-group row text-right">
                                            <label for="username" class="col-xs-4 control-label">昵称：</label>
                                            <div class="col-xs-4">
                                                <input type="text" class="form-control" id="username" name="nickname" required />
                                            </div>
                                        </div>
                                        <br>
                                        <div class="form-group row text-right">
                                            <label for="roleName" class="col-xs-4 control-label">角色：</label>
                                            <div class="col-xs-4">
                                                <select class="form-control" id="roleName" name="role" >
                                                    <option value="normal">普通</option>
                                                    <option value="admin">管理员</option>
                                                </select>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="form-group row text-right">
                                            <label for="password" class="col-xs-4 control-label">密码：</label>
                                            <div class="col-xs-4">
                                                <input type="text" class="form-control" id="password" name="password" required />
                                            </div>
                                        </div>
                                        <br>
                                        <div class="form-group row text-right">
                                            <label for="email" class="col-xs-4 control-label">邮箱：</label>
                                            <div class="col-xs-4">
                                                <input type="text" class="form-control" id="email" name="email" required />
                                            </div>
                                        </div>
                                        <br>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-warning updateOne">修改</button>
                                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
<script src="${pageContext.request.contextPath}/js/_my.js"></script>
<script src="${pageContext.request.contextPath}/js/template-web.js"></script>
<script type="text/javascript" charset="utf-8">
    let stack = new Stack();

    $(function() {
        init();

        // 显示隐藏查询列表
        $('#show-user-search').click(function() {
            $('#show-user-search').hide();
            $('#upp-user-search').show();
            $('.showusersearch').slideDown(500);
        });
        $('#upp-user-search').click(function() {
            $('#show-user-search').show();
            $('#upp-user-search').hide();
            $('.showusersearch').slideUp(500);
        });

        //分页回调函数,点击按钮事件
        myoptions.onPageClicked = function(event, originalEvent, type, page) {
            //ajax回调接收数据成功时再重新初始化分页按钮
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
                    str = 'pageNo=' + page;
                }
            }
            // 点击分页按钮后，替换栈顶的 ajax 请求
            stack.replace(new AjaxParam('/user/list.do', str))
            // console.log(stack.toString());
            sendAjax('/user/list.do', str);
        };

        // doSearch
        $('#doSearch').on('click', () => {
            let condition = _serialize($('.search-user-form'));
            stack.replace(new AjaxParam('/user/list.do', condition))
            sendAjax('/user/list.do', condition);
        })

        // clear search
        $('#form-clear-btn').on('click', () => {
            let form = $('.search-user-form');
            form.get(0).reset();
            let ajaxParam = stack.peek();
            stack.replace(new AjaxParam(ajaxParam.url, ''));
            form.on('reset', (e) => {
                $(e.currentTarget).find('input[type=date]').removeClass('selected');
            })
        })

        // updateOne
        $('.updateOne').on('click', () => {
            $.ajax({
                type : 'post',
                url : baseUrl + '/user/modify.do',
                data : $('.modify-form').serialize(),
                dataType : 'json',
                success : (data) => {
                    if (data.statusCode === '00000') {
                        $('#modifyModal').modal('hide');
                        let ajaxParam = stack.peek();
                        sendAjax(ajaxParam.url, ajaxParam.data);
                    } else {
                        console.log(data);
                        window.parent.sonPageMsg(data);
                    }
                }
            })
        })
    });

    // init
    function init() {
        let stackStr = sessionStorage.getItem("back_userSet");
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
            // 初次访问页面时发送 ajax 请求
            sendAjax('/user/list.do');
            // 初次 ajax 请求
            stack.push(new AjaxParam('/user/list.do', ''))
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

    // getUser
    function getUser(e) {
        let id = $($(e).parent().parent().children().get(0)).text();
        $.ajax({
            type : 'get',
            url : baseUrl + '/user/get.do',
            data : {'id': id},
            dataType : 'json',
            success : (data) => {
                if (data.statusCode === '00000') {
                    // console.log(data);
                    let user = data.data;
                    $('#user_id').val(user.id);
                    $('#username').val(user.nickname);
                    $('#roleName').val(user.role);
                    $('#password').val(user.password);
                    $('#email').val(user.email);

                    $('#modifyModal').modal('show');
                } else {
                    console.log(data);
                    window.parent.sonPageMsg(data);
                }
            }
        })
    }

    // modifyStatus
    function modifyStatus(e) {
        let id = $($(e).parent().parent().children().get(0)).text();
        let status = $(e).attr('data-status');
        $.ajax({
            type : 'get',
            url : baseUrl + '/user/modify.do',
            data : {
                'id':id,
                'status':status
            },
            dataType : 'json',
            success : (data) => {
                if (data.statusCode === '00000') {
                    let ajaxParam = stack.peek();
                    sendAjax(ajaxParam.url, ajaxParam.data);
                } else {
                    console.log(data);
                    window.parent.sonPageMsg(data);
                }
            }
        })
    }

    // 页面关闭，缓存 stack
    $(window).on('beforeunload', () => {
        sessionStorage.setItem("back_userSet", stack.toString());
    })
</script>
<script id="list-data" type="text/html">
    {{each list item}}
        {{if item.status == 1}}
        <tr>
        {{else if item.status == -1}}
        <tr class="disabled">
        {{/if}}
            <td>{{item.id}}</td>
            <td>{{item.loginName}}</td>
            {{if item.role == "normal"}}
            <td>普通用户</td>
            {{else if item.role == "admin"}}
            <td>管理员</td>
            {{/if}}
            <td>{{item.nickname}}</td>
            <td>{{item.email}}</td>
            <td>{{item.createDate}}</td>
            <td>{{item.signDate}}</td>
            <td class="text-center">
                <input type="button" class="btn btn-warning btn-sm doModify" onclick="getUser(this)" value="修改" />
                {{if item.status == 1}}
                <input type="button" class="btn btn-danger btn-sm" data-status="-1" onclick="modifyStatus(this)" value="禁用" />
                {{else if item.status == -1}}
                <input type="button" class="btn btn-success btn-sm" data-status="1" onclick="modifyStatus(this)" value="启用" />
                {{/if}}
            </td>
        </tr>
    {{/each}}
</script>
