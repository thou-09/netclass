<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>评论审核</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/_my.css" />
        <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
    </head>
    <body>
        <div class="panel panel-default" id="userSet">
            <div class="panel-heading">
                <h3 class="panel-title">评论审核</h3>
            </div>
            <div>
                <input type="button" value="查询" class="btn btn-success" id="doSearch" style="margin: 5px 5px 5px 15px;" />
                <input type="button" class="btn btn-primary" id="show-user-search" value="展开搜索" />
                <input type="button" value="收起搜索" class="btn btn-primary" id="upp-user-search" style="display: none;" />
                <input type="button" value="清空查询条件" class="btn btn-warning" id="form-clear-btn" />
            </div>

            <div class="panel-body">
                <div class="showusersearch" style="display: none;">
                    <form class="form-horizontal search-comment-form">
                        <div class="form-group">
                            <div class="form-group col-xs-6">
                                <label for="user-name" class="col-xs-3 control-label">用户名：</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" id="user-name" name="userLoginName" placeholder="请输入用户名" />
                                </div>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="user-comment" class="col-xs-3 control-label">评论内容：</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" id="user-comment" name="context" placeholder="请输入评论内容" />
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-group col-xs-6">
                                <label class="col-xs-3 control-label">开始日期：</label>
                                <div class="col-xs-8">
                                    <input type="date" class="form-control"
                                           placeholder="请选择评论开始时间" name="createDateStart" />
                                </div>
                            </div>
                            <div class="form-group col-xs-6">
                                <label class="col-xs-3 control-label">结束日期：</label>
                                <div class="col-xs-8">
                                    <input type="date" class="form-control"
                                           placeholder="请选择评论结束时间" name="createDateEnd" />
                                </div>
                            </div>
                        </div>

                    </form>
                </div>

                <div class="show-list">
                    <table class="table table-bordered table-hover" style='text-align: center;'>
                        <thead>
                        <tr class="text-danger">
                            <th class="text-center">编号</th>
                            <th class="text-center widthP40">评论内容</th>
                            <th class="text-center">用户名</th>
                            <th class="text-center">评论时间</th>
                            <th class="text-center">赞</th>
                            <th class="text-center">状态</th>
                            <th class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody id="tb"></tbody>
                    </table>
                </div>

                <!-- 分页 -->
                <div style="text-align: center;" >
                    <ul id="myPages" ></ul>
                </div>
            </div>
        </div>
    </body>
</html>
<script src="${pageContext.request.contextPath}/js/_my.js"></script>
<script src="${pageContext.request.contextPath}/js/template-web.js"></script>
<script type="text/javascript" >
    let stack = new Stack();

    $(function(){
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

        // doSearch
        $('#doSearch').on('click', () => {
            let condition = _serialize($('.search-comment-form'));
            if (condition === '') {
                condition = 'status=2';
            } else {
                condition = condition + '&status=2';
            }
            stack.replace(new AjaxParam('/comment/list.do', condition))
            sendAjax('/comment/list.do', condition);
        });

        // clear search
        $('#form-clear-btn').on('click', () => {
            let form = $('.search-comment-form');
            form.get(0).reset();
            let ajaxParam = stack.peek();
            stack.replace(new AjaxParam(ajaxParam.url, 'status=2'));
            // console.log(stack.toString())
            form.on('reset', (e) => {
                $(e.currentTarget).find('input[type=date]').removeClass('selected');
            })
        })

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
            stack.replace(new AjaxParam('/comment/list.do', str))
            // console.log(stack.toString());
            sendAjax('/comment/list.do', str);
        };
    });

    // init
    function init() {
        let stackStr = sessionStorage.getItem("back_commentManageSet");
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
            sendAjax('/comment/list.do', 'status=2');
            stack.push(new AjaxParam('/comment/list.do', 'status=2'));
        }
    }

    // sendAjax
    function sendAjax(url, data) {
        $.ajax({
            type : 'post',
            url : baseUrl + url,
            data : data,
            dataType : 'json',
            success : (data) => {
                if (data.statusCode === '00000') {
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

    // modifyStatus
    function modifyStatus(e) {
        let id = $($(e).parent().parent().children().get(0)).text();
        let status = $(e).attr('data-status');
        $.ajax({
            type : 'post',
            url : baseUrl + '/comment/modify_status.do',
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
                    console.log(data)
                    window.parent.sonPageMsg(data);
                }
            }
        })
    }

    // 页面关闭，缓存 stack
    $(window).on('beforeunload', () => {
        sessionStorage.setItem('back_commentManageSet', stack.toString());
    })
</script>
<script id="list-data" type="text/html">
    {{each list item}}
    <tr>
        <td>{{item.id}}</td>
        <td>{{item.context}}</td>
        <td>{{item.user.loginName}}</td>
        <td>{{item.createDate}}</td>
        <td>{{item.priseCount}}</td>
        <td>待审核</td>
        <td class="text-center">
            <input type="button" class="btn btn-success btn-sm" data-status="1" onclick="modifyStatus(this)" value="通过" />
            <input type="button" class="btn btn-danger btn-sm" data-status="-1" onclick="modifyStatus(this)" value="禁用" />
        </td>
    </tr>
    {{/each}}
</script>