<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>课程类别管理</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/_my.css" />
        <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
    </head>
    <body>
        <!-- 课程类别管理 -->
        <div class="panel panel-default" id="departmentSet">
            <div class="panel-heading">
                <h3 class="panel-title">课程类别管理</h3>
            </div>
            <div class="panel-body">
                <input type="button" value="添加课程类别" class="btn btn-primary" id="doAddCourseType" />
                <!-- 自己的 parentId -->
                <input type="hidden" id="input-parent-id" value="" />
                <input type="button" value="返回上级列表" class="btn btn-success" id="back" style="float: right;" />
                <br>
                <br>
                <!-- add modal -->
                <div class="modal fade" tabindex="-1" id="addCourseTypeModal">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">添加类别</h4>
                            </div>
                            <div class="modal-body text-center">
                                <form action="#" class="form-horizontal add-form">
                                    <div class="form-group">
                                        <div class="form-group row text-right">
                                            <label for="add-courseType-name" class="col-sm-4 control-label">类别名称：</label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" id="add-courseType-name" name="typeName" />
                                            </div>
                                        </div>
                                        <br>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-primary addOne">确定</button>
                                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- modify modal -->
                <div class="modal fade" tabindex="-1" id="modifyCourseTypeModal">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">修改类别</h4>
                            </div>
                            <div class="modal-body text-center">
                                <form action="#" class="form-horizontal modify-form">
                                    <div class="form-group">
                                        <div class="form-group row text-right" id="courseType-id-input" >
                                            <label for="modify-courseType-id" class="col-sm-4 control-label">编号：</label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" id="modify-courseType-id" name="id" readonly />
                                            </div>
                                        </div>
                                        <br>
                                        <div class="form-group row text-right">
                                            <label for="modify-courseType-name" class="col-sm-4 control-label">类别名称：</label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" id="modify-courseType-name" name="typeName" />
                                            </div>
                                        </div>
                                        <br>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-primary updateOne">确定</button>
                                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="show-list">
                    <table class="table table-bordered table-hover" style='text-align: center;'>
                        <thead>
                        <tr class="text-danger">
                            <th class="text-center widthP10">编号</th>
                            <th class="text-center">名称</th>
                            <th class="text-center widthP20">状态</th>
                            <th class="text-center widthP20">操作</th>
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
<script type="text/javascript" charset="utf-8">
    let stack = new Stack();

    $(function(){
        init();

        //分页回调函数,点击按钮事件
        myoptions.onPageClicked = function(event, originalEvent, type, page) {
            //ajax回调接收数据成功时再重新初始化分页按钮
            // 自己的 parentId
            let parentId = $('#input-parent-id').val();
            let str = '';
            if (parentId) {
                str = 'parentId=' + parentId;
                if (page) {
                    str += '&' + 'pageNo=' + page;
                }
            } else {
                if (page) {
                    str = 'pageNo=' + page;
                }
            }
            // 点击分页按钮后，替换栈顶的 ajax 请求
            stack.replace(new AjaxParam('/course_type/list.do', str))
            // console.log(stack.toString());
            sendAjax('/course_type/list.do', str);
        };

        // add modal
        $('#doAddCourseType').on('click', () => {
            $('.add-form').get(0).reset();
            $('#addCourseTypeModal').modal('show');
        })

        // addOne
        $('.addOne').on('click', () => {
            let data = $('.add-form').serialize();
            data += '&parentId=' + $('#input-parent-id').val();
            $.ajax({
                type : 'post',
                url : baseUrl + '/course_type/add.do',
                data : data,
                dataType : 'json',
                success : (data) => {
                    // console.log(data)
                    if (data.statusCode === '00000') {
                        $('#addCourseTypeModal').modal('hide');
                        $('.add-form').get(0).reset();
                        let ajaxParam = stack.peek();
                        sendAjax(ajaxParam.url, ajaxParam.data);
                    } else {
                        console.log(data)
                        window.parent.sonPageMsg(data);
                    }
                }
            })
        })

        // updateOne
        $('.updateOne').on('click', () => {
            let data = $('.modify-form').serialize();
            $.ajax({
                type : 'post',
                url : baseUrl + '/course_type/modify.do',
                data : data,
                dataType : 'json',
                success : (data) => {
                    if (data.statusCode === '00000') {
                        $('#modifyCourseTypeModal').modal('hide');
                        let ajaxParam = stack.peek();
                        sendAjax(ajaxParam.url, ajaxParam.data);
                    } else {
                        console.log(data)
                        window.parent.sonPageMsg(data);
                    }
                }
            })
        })

        //返回父类别列表页面
        $("#back").on("click", () => {
            let ajaxParam;
            if (stack.size() > 1) {
                stack.pop();
            }
            ajaxParam = stack.peek();
            sendAjax(ajaxParam.url, ajaxParam.data);
        });
    });

    // 初始化请求和 stack
    function init() {
        let stackStr = sessionStorage.getItem("back_courseTypeSet");
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
            sendAjax('/course_type/list.do');
            // 初次 ajax 请求
            stack.push(new AjaxParam('/course_type/list.do', ''))
        }
    }

    // 发送 ajax
    function sendAjax(url, str, parentId) {
        $.ajax({
            type : 'post',
            url : baseUrl + url,
            data : str,
            dataType : 'json',
            success : (data) => {
                if (data.statusCode === '00000') {
                    // console.log(data);
                    let pageInfo = data.data;
                    if (pageInfo.size) {
                        myPaginatorFun("myPages", pageInfo.pageNum, pageInfo.pages);
                        let list = pageInfo.list;
                        let tplData = {
                            level: stack.size(),
                            list: list
                        };
                        let html = template('list-data', tplData);
                        $('#tb').html(html);
                        $('#input-parent-id').val(list[0].parentId);
                    } else {
                        myPaginatorFun("myPages", 1, 1);
                        $('#tb').html('');
                        $('#input-parent-id').val(parentId);
                    }
                } else {
                    console.log(data)
                    window.parent.sonPageMsg(data);
                }
            }
        })
    }

    // getOne
    function getType(e) {
        let id = $($(e).parent().parent().children().get(0)).text();
        $.ajax({
            type : 'get',
            url : baseUrl + '/course_type/get.do',
            data : {'id': id},
            dataType : 'json',
            success : (data) => {
                if (data.statusCode === '00000') {
                    let info = data.data;
                    $('#modify-courseType-id').val(info.id);
                    $('#modify-courseType-name').val(info.typeName);
                    $('#modifyCourseTypeModal').modal('show');
                } else {
                    console.log(data)
                    window.parent.sonPageMsg(data);
                }
            }
        })
    }

    // getSons
    function getSons(e) {
        let id = $($(e).parent().parent().children().get(0)).text();
        let str = 'parentId=' + id;
        stack.push(new AjaxParam('/course_type/list.do', str));
        // console.log(stack.toString())
        sendAjax('/course_type/list.do', str, id);
    }

    // modifyStatus
    function modifyStatus(e) {
        let id = $($(e).parent().parent().children().get(0)).text();
        let status = $(e).attr('data-status');
        $.ajax({
            type : 'post',
            url : baseUrl + '/course_type/modify_status.do',
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
        sessionStorage.setItem("back_courseTypeSet", stack.toString());
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
            <td>{{item.typeName}}</td>
            {{if item.status == 1}}
            <td>启用</td>
            {{else if item.status == -1}}
            <td>禁用</td>
            {{/if}}
            <td class="text-center">
                <input type="button" class="btn btn-warning btn-sm courseType-modify" onclick="getType(this)" value="修改" />
                {{if item.status == 1}}
                <input type="button" class="btn btn-danger btn-sm" data-status="-1" onclick="modifyStatus(this)" value="禁用" />
                {{else if item.status == -1}}
                <input type="button" class="btn btn-success btn-sm" data-status="1" onclick="modifyStatus(this)" value="启用" />
                {{/if}}
                {{if level != 3}}
                <input type="button" class="btn btn-info btn-sm course-type-detail" onclick="getSons(this)" value="查询子类别" />
                {{/if}}
            </td>
        </tr>
    {{/each}}
</script>
