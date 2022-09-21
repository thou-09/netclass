<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>评论管理</title>
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
                <h3 class="panel-title">评论管理</h3>
            </div>
            <div>
                <!-- course-resource-id,没有时,移除此按钮 -->
                <input type="text" id="resource-id" name="course_resource_id" value=""  />
                <input type="button" value="查询" class="btn btn-success" id="doSearch" style="margin: 5px 5px 5px 15px;" />
                <input type="button" class="btn btn-primary" id="show-comment-search" value="展开搜索" />
                <input type="button" value="收起搜索" class="btn btn-primary" id="upp-comment-search" style="display: none;" />
                <input type="button" value="清空查询条件" class="btn btn-warning" id="form-clear-btn" />
                <input type="button" value="返回章节列表" class="btn btn-success" id="back" style="margin: 5px 15px 5px 0;float: right;" />
            </div>

            <div class="panel-body">
                <div class="show-comment-search" style="display: none;">
                    <form class="form-horizontal search-comment-form">
                        <div class="form-group">
                            <div class="form-group col-xs-6">
                                <label for="user-name-search" class="col-xs-3 control-label">用户名：</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" id="user-name-search" name="userLoginName" placeholder="请输入用户名" />
                                </div>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="user-comment-search" class="col-xs-3 control-label">内容：</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" id="user-comment-search" name="context" placeholder="请输入评论内容" />
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
                        <div class="form-group">
                            <div class="form-group col-xs-6">
                                <label for="course-resource-stauts-search" class="col-xs-3 control-label">状态：</label>
                                <div class="col-xs-8">
                                    <select class="form-control" id="course-resource-stauts-search" name="status" >
                                        <option value="" >全部</option>
                                        <option value="1" >启用</option>
                                        <option value="-1" >禁用</option>
                                    </select>
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
                            <th class="text-center widthP45">评论内容</th>
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
        $('#show-comment-search').click(function() {
            $('#show-comment-search').hide();
            $('#upp-comment-search').show();
            $('.show-comment-search').slideDown(500);
        });
        $('#upp-comment-search').click(function() {
            $('#show-comment-search').show();
            $('#upp-comment-search').hide();
            $('.show-comment-search').slideUp(500);
        });

        //返回章节列表
        $("#back").on('click', function(){
            stack.clear();
            $('#frame-id', window.parent.document).attr('src', 'show_back_courseResourceSet.do');
        });

        // doSearch
        $('#doSearch').on('click', () => {
            let condition = _serialize($('.search-comment-form'));
            if (condition === '') {
                condition = 'resourceId=' + $('#resource-id').val();
            } else {
                condition = condition + '&resourceId=' + $('#resource-id').val();
            }
            stack.replace(new AjaxParam('/comment/list.do', condition));
            // console.log(stack.toString())
            sendAjax('/comment/list.do', condition);
        })

        // clear search
        $('#form-clear-btn').on('click', () => {
            let form = $('.search-comment-form');
            form.get(0).reset();
            let ajaxParam = stack.peek();
            stack.replace(new AjaxParam(ajaxParam.url, 'resourceId=' + $('#resource-id').val()));
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
        let stackStr = sessionStorage.getItem("back_commentSet");
        // console.log(stackStr)
        if (stackStr) {
            for (let s of stackStr.split('|')) {
                let url = s.split(',')[0].substring(s.split(',')[0].indexOf(':') + 1);
                let data = s.split(',')[1].substring(s.split(',')[1].indexOf(':') + 1);
                let ajaxParam = new AjaxParam(url, data);
                stack.push(ajaxParam);
                let resourceId = data.match(/(resourceId=)([0-9]*)/)[2];
                $('#resource-id').val(resourceId);
            }
            let ajaxParam = stack.peek();
            sendAjax(ajaxParam.url, ajaxParam.data);
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
        sessionStorage.setItem('back_commentSet', stack.toString());
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
        <td>{{item.context}}</td>
        <td>{{item.user.loginName}}</td>
        <td>{{item.createDate}}</td>
        <td>{{item.priseCount}}</td>
        {{if item.status == 1}}
        <td>启用</td>
        {{else if item.status == -1}}
        <td>禁用</td>
        {{/if}}
        <td class="text-center">
            {{if item.status == 1}}
            <input type="button" class="btn btn-danger btn-sm" data-status="-1" onclick="modifyStatus(this)" value="禁用" />
            {{else if item.status == -1}}
            <input type="button" class="btn btn-success btn-sm" data-status="1" onclick="modifyStatus(this)" value="启用" />
            {{/if}}
        </td>
    </tr>
    {{/each}}
</script>