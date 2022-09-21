<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>用户资源管理</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/_my.css" />
        <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
        <style>
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

            .width120 {
                min-width: 120px;
            }
            .width90 {
                min-width: 90px;
            }
            .width50 {
                min-width: 50px;
            }
        </style>
    </head>
    <body>
        <div class="panel panel-default" id="userPic">
            <div class="panel-heading">
                <h3 class="panel-title">用户资源管理</h3>
            </div>
            <div>
                <input type="button" value="查询" class="btn btn-success" id="doSearch" style="margin: 5px 5px 5px 15px;" />
                <input type="button" class="btn btn-primary" id="show-user-resource-search" value="展开搜索" />
                <input type="button" value="收起搜索" class="btn btn-primary" id="upp-user-resource-search" style="display: none;" />
                <input type="button" value="清空查询条件" class="btn btn-warning" id="form-clear-btn" />
            </div>

            <div class="panel-body">
                <div class="show-user-resource-search" style="display: none;">
                    <form class="form-horizontal search-resource-form">
                        <div class="form-group">
                            <div class="form-group col-xs-6">
                                <label for="user-resource-title-search" class="col-xs-3 control-label">标题：</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" id="user-resource-title-search" name="title" placeholder="请输入标题" />
                                </div>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="user-name-search" class="col-xs-3 control-label">用户名：</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" id="user-name-search" name="userLoginName" placeholder="请输入用户名" />
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
                                <label class="col-xs-3 control-label">状态：</label>
                                <div class="col-xs-8">
                                    <select class="form-control" name="status">
                                        <option value="" >全部</option>
                                        <option value="1" >启用</option>
                                        <option value="-1" >禁用</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group col-xs-6">
                                <label class="col-xs-3 control-label">文件类型：</label>
                                <div class="col-xs-8">
                                    <select class="form-control" name="fileType">
                                        <option value="" >全部</option>
                                        <option value="MP4" >mp4</option>
                                        <option value="PDF" >pdf</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                    </form>
                </div>

                <div class="show-list">
                    <table class="table table-bordered table-hover" style="text-align: center;">
                        <thead>
                        <tr class="text-danger">
                            <th class="text-center width50">编号</th>
                            <th class="text-center">资源标题</th>
                            <th class="text-center">资源相对路径</th>
                            <th class="text-center width50">文件原始名称</th>
                            <th class="text-center width50">文件大小</th>
                            <th class="text-center width50">文件类型</th>
                            <th class="text-center width90">上传时间</th>
                            <th class="text-center width50">消费类型</th>
                            <th class="text-center width50">消费值</th>
                            <th class="text-center width50">上传用户</th>
                            <th class="text-center width50">状态</th>
                            <th class="text-center width120">操作</th>
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
<script type="text/javascript">
    let stack = new Stack();

    $(function(){
        init();

        // 显示隐藏查询列表
        $('#show-user-resource-search').click(function() {
            $('#show-user-resource-search').hide();
            $('#upp-user-resource-search').show();
            $('.show-user-resource-search').slideDown(500);
        });
        $('#upp-user-resource-search').click(function() {
            $('#show-user-resource-search').show();
            $('#upp-user-resource-search').hide();
            $('.show-user-resource-search').slideUp(500);
        });

        // doSearch
        $('#doSearch').on('click', () => {
            let condition = _serialize($('.search-resource-form'));
            stack.replace(new AjaxParam('/resource/list_with_user.do', condition));
            // console.log(stack.toString());
            sendAjax('/resource/list_with_user.do', condition);
        })

        // clear search
        $('#form-clear-btn').on('click', () => {
            let form = $('.search-resource-form');
            form.get(0).reset();
            let ajaxParam = stack.peek();
            stack.replace(new AjaxParam(ajaxParam.url, ''));
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
            stack.replace(new AjaxParam('/resource/list_with_user.do', str))
            // console.log(stack.toString());
            sendAjax('/resource/list_with_user.do', str);
        };
    });

    // init
    function init() {
        let stackStr = sessionStorage.getItem("back_resourceSet");
        // console.log(stackStr)
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
            sendAjax('/resource/list_with_user.do');
            stack.push(new AjaxParam('/resource/list_with_user.do', ''))
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

    // getDetail
    function getDetail(id) {
        window.open('${pageContext.request.contextPath}/resource/search.do?id=' + id, '_blank');
    }

    // modifyStatus
    function modifyStatus(e) {
        let id = $($(e).parent().parent().children().get(0)).text();
        let status = $(e).attr('data-status');
        $.ajax({
            type : 'post',
            url : baseUrl + '/resource/modify_status.do',
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
        sessionStorage.setItem('back_resourceSet', stack.toString());
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
        <td>{{item.title}}</td>
        <td>{{item.path}}</td>
        <td>{{item.originalName}}</td>
        <td>{{item.fileSize}}</td>
        <td>{{item.fileType}}</td>
        <td>{{item.createDate}}</td>
        {{if item.costType == 0}}
        <td>积分</td>
        {{else if item.costType == 1}}
        <td>金币</td>
        {{/if}}
        <td>{{item.costNumber}}</td>
        <td>{{item.user.loginName}}</td>
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
            <input type="button" class="btn btn-info btn-sm resource-detail" onclick="getDetail('{{item.id}}')" value="详情" />
        </td>
    </tr>
    {{/each}}
</script>