<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>课程章节管理</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/_my.css" />
        <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
        <style type="text/css" >
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
        </style>
    </head>
    <body>
        <div class="panel panel-default" id="userSet">
            <div class="panel-heading">
                <h3 class="panel-title">课程章节管理</h3>
            </div>
            <div>
                <input type="hidden" id="course-id" value="">
                <input type="button" value="添加章节" class="btn btn-primary" id="doAddCourseResource" style="margin: 5px 5px 5px 15px;" />
                <input type="button" value="查询" class="btn btn-success" id="doSearch" style="margin: 5px 5px 5px 0;" />
                <input type="button" class="btn btn-primary" id="show-course-resource-search" value="展开搜索" />
                <input type="button" value="收起搜索" class="btn btn-primary" id="upp-course-resource-search" style="display: none;" />
                <input type="button" value="清空查询条件" class="btn btn-warning" id="form-clear-btn" />
                <input type="button" value="返回课程列表" class="btn btn-success" id="back" style="margin: 5px 15px 5px 0;float: right;" />
            </div>
            <div class="panel-body">
                <div class="show-course-resource-search" style="display: none;">
                    <form class="form-horizontal search-course-resource-form">
                        <div class="form-group">
                            <input type="hidden" name="courseId" id="search-course-id" value="">
                            <div class="form-group col-xs-6">
                                <label for="course-resource-title-search" class="col-xs-3 control-label">标题：</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" id="course-resource-title-search" placeholder="请输入标题" name="title" />
                                </div>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="course-resource-info-search" class="col-xs-3 control-label">简介：</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" id="course-resource-info-search" placeholder="请输入简介" name="info" />
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
                            <th class="text-center">标题</th>
                            <th class="text-center">简介</th>
                            <th class="text-center">创建时间</th>
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

        <!-- add modal -->
        <div class="modal fade" tabindex="-1" id="addChapterModal" >
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title" >添加章节</h4>
                    </div>
                    <div class="modal-body text-center">
                        <form class="form-horizontal add-form">
                            <div class="form-group">
                                <!-- course-id,没有时,移除 '返回课程列表' 按钮 -->
                                <input type="hidden" id="add-course-id" name="course_id" value=""/>
                                <div class="row text-right">
                                    <label for="add-chapter-title" class="col-xs-4 control-label">章节标题：</label>
                                    <div class="col-xs-4">
                                        <input type="text" class="form-control" id="add-chapter-title" name="chapterTitle" />
                                    </div>
                                </div>
                                <br>
                                <div class="row text-right">
                                    <label for="add-chapter-info" class="col-xs-4 control-label">内容详情：</label>
                                    <div class="col-xs-4">
                                        <input type="text" class="form-control" id="add-chapter-info" name="chapterInfo" />
                                    </div>
                                </div>
                                <br/>
                                <div class="row text-right">
                                    <label class="col-xs-4 control-label">资源：</label>
                                    <div class="col-xs-4">
                                        <a href="javascript:fileUpload('#add-chapter-file');" id="add-file" class="file" >选择文件</a>
                                        <input type="file" accept=".mp4, .pdf" name="course_resource_file" style="display: none;" onchange="fileChange(this)" id="add-chapter-file" />
                                    </div>
                                </div>
                                <br/>
                                <div class="row text-right">
                                    <label for="add-chapter-file-title" class="col-xs-4 control-label">资源标题：</label>
                                    <div class="col-xs-4">
                                        <input type="text" class="form-control" id="add-chapter-file-title" name="resourceTitle" />
                                    </div>
                                </div>
                                <br>
                                <div class="row text-right">
                                    <label for="add-chapter-file-cost-type" class="col-xs-4 control-label">查看资源花费类型：</label>
                                    <div class="col-xs-4">
                                        <select class="form-control" id="add-chapter-file-cost-type" name="resourceCostType" >
                                            <option value="" >请选择</option>
                                            <option value="0" >积分</option>
                                            <option value="1" >金币</option>
                                        </select>
                                    </div>
                                </div>
                                <br>
                                <div class="row text-right">
                                    <label for="add-chapter-file-cost-type-val" class="col-xs-4 control-label">花费值：</label>
                                    <div class="col-xs-4">
                                        <input type="text" class="form-control" id="add-chapter-file-cost-type-val" name="resourceCostNumber" />
                                    </div>
                                </div>
                                <br>
                                <div class="row text-right">
                                    <label for="add-chapter-resource-type-id" class="col-xs-4 control-label">资源类型：</label>
                                    <div class="col-xs-4">
                                        <select class="form-control" id="add-chapter-resource-type-id" name="resourceFileType" >
                                            <option value="" >请选择</option>
                                            <option value="MP4" >mp4</option>
                                            <option value="PDF" >pdf</option>
                                        </select>
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
        <div class="modal fade" tabindex="-1" id="modifyChapterModal" >
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title" >修改章节</h4>
                    </div>
                    <div class="modal-body text-center">
                        <form class="form-horizontal modify-form">
                            <div class="form-group">
                                <input type="hidden" id="modify-resource-id" name="resource_id" value=""/>
                                <div class="row text-right" >
                                    <label for="modify-chapter-id" class="col-xs-4 control-label">章节编号：</label>
                                    <div class="col-xs-4">
                                        <input type="text" class="form-control" id="modify-chapter-id" readonly/>
                                    </div>
                                </div>
                                <br>
                                <div class="row text-right">
                                    <label for="modify-chapter-title" class="col-xs-4 control-label">章节标题：</label>
                                    <div class="col-xs-4">
                                        <input type="text" class="form-control" id="modify-chapter-title" />
                                    </div>
                                </div>
                                <br>
                                <div class="row text-right">
                                    <label for="modify-chapter-info" class="col-xs-4 control-label">内容详情：</label>
                                    <div class="col-xs-4">
                                        <input type="text" class="form-control" id="modify-chapter-info" />
                                    </div>
                                </div>
                                <br/>
                                <div class="row text-right">
                                    <label class="col-xs-4 control-label">资源：</label>
                                    <div class="col-xs-4">
                                        <a href="javascript:fileUpload('#modify-chapter-file');" id="modify-file" class="file" >选择文件</a>
                                        <input type="file" accept=".pdf, .mp4" name="course_resource_file" style="display: none;" onchange="fileChange(this)" id="modify-chapter-file" />
                                    </div>
                                </div>
                                <br/>
                                <div class="row text-right">
                                    <label for="modify-chapter-file-title" class="col-xs-4 control-label">资源标题：</label>
                                    <div class="col-xs-4">
                                        <input type="text" class="form-control" id="modify-chapter-file-title" />
                                    </div>
                                </div>
                                <br>
                                <div class="row text-right">
                                    <label for="modify-chapter-file-cost-type" class="col-xs-4 control-label">查看资源花费类型：</label>
                                    <div class="col-xs-4">
                                        <select class="form-control" id="modify-chapter-file-cost-type" name="file_cost_type_id" >
                                            <option value="0" >积分</option>
                                            <option value="1" >金币</option>
                                        </select>
                                    </div>
                                </div>
                                <br>
                                <div class="row text-right">
                                    <label for="modify-chapter-file-cost-type-val" class="col-xs-4 control-label">花费值：</label>
                                    <div class="col-xs-4">
                                        <input type="text" class="form-control" id="modify-chapter-file-cost-type-val" />
                                    </div>
                                </div>
                                <br>
                                <div class="row text-right">
                                    <label for="modify-chapter-resource-type-id" class="col-xs-4 control-label">资源类型：</label>
                                    <div class="col-xs-4">
                                        <select class="form-control" id="modify-chapter-resource-type-id" name="resource_type_id" >
                                            <option value="MP4" >mp4</option>
                                            <option value="PDF" >pdf</option>
                                        </select>
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
    </body>
</html>
<script src="${pageContext.request.contextPath}/js/_my.js"></script>
<script src="${pageContext.request.contextPath}/js/template-web.js"></script>
<script type="text/javascript">
    let stack = new Stack();

    $(function(){
        init();

        // 显示隐藏查询列表
        $('#show-course-resource-search').click(function() {
            $('#show-course-resource-search').hide();
            $('#upp-course-resource-search').show();
            $('.show-course-resource-search').slideDown(500);
        });
        $('#upp-course-resource-search').click(function() {
            $('#show-course-resource-search').show();
            $('#upp-course-resource-search').hide();
            $('.show-course-resource-search').slideUp(500);
        });

        // 返回课程列表
        $('#back').on('click', () => {
            stack.clear();
            $('#frame-id', window.parent.document).attr('src', 'show_back_courseSet.do');
        });

        // add modal
        $('#doAddCourseResource').on('click', () => {
            $('.add-form').get(0).reset();
            $('#add-file').html('选择文件');
            $('#addChapterModal').modal('show');
        });

        // addOne
        $('.addOne').on('click', () => {
            let formData = new FormData();
            formData.append('chapterTitle', $('#add-chapter-title').val());
            formData.append('chapterInfo', $('#add-chapter-info').val());
            formData.append('file', $('#add-chapter-file')[0].files[0]);
            formData.append('resourceTitle', $('#add-chapter-file-title').val());
            formData.append('resourceCostType', $('#add-chapter-file-cost-type').val());
            formData.append('resourceCostNumber', $('#add-chapter-file-cost-type-val').val());
            formData.append('resourceFileType', $('#add-chapter-resource-type-id').val());
            formData.append('courseId', $('#add-course-id').val());
            formData.append('userId', '${sessionScope.loginAdmin.id}');
            $.ajax({
                type : 'post',
                url : baseUrl + '/chapter/add.do',
                data : formData,
                contentType: false,
                processData: false,
                dataType : 'json',
                success : (data) => {
                    if (data.statusCode === '00000') {
                        $('#addChapterModal').modal('hide');
                        let ajaxParam = stack.peek();
                        sendAjax(ajaxParam.url, ajaxParam.data);
                    } else {
                        console.log(data);
                        window.parent.sonPageMsg(data);
                    }
                }
            })
        })

        // doSearch
        $('#doSearch').on('click', () => {
            let condition = _serialize($('.search-course-resource-form'));
            stack.replace(new AjaxParam('/chapter/list.do', condition));
            // console.log(stack.toString());
            sendAjax('/chapter/list.do', condition);
        })

        // clear search
        $('#form-clear-btn').on('click', () => {
            let form = $('.search-course-resource-form');
            form.get(0).reset();
            let ajaxParam = stack.peek();
            stack.replace(new AjaxParam(ajaxParam.url, 'courseId=' + $('#course-id').val()));
            form.on('reset', (e) => {
                $(e.currentTarget).find('input[type=date]').removeClass('selected');
            })
        })

        // updateOne
        $('.updateOne').on('click', () => {
            let formData = new FormData();
            formData.append('chapterId', $('#modify-chapter-id').val());
            formData.append('resourceId', $('#modify-resource-id').val());
            formData.append('chapterTitle', $('#modify-chapter-title').val());
            formData.append('chapterInfo', $('#modify-chapter-info').val());
            formData.append('file', $('#modify-chapter-file')[0].files[0]);
            formData.append('resourceTitle', $('#modify-chapter-file-title').val());
            formData.append('resourceCostType', $('#modify-chapter-file-cost-type').val());
            formData.append('resourceCostNumber', $('#modify-chapter-file-cost-type-val').val());
            formData.append('resourceFileType', $('#modify-chapter-resource-type-id').val());
            $.ajax({
                type : 'post',
                url : baseUrl + '/chapter/modify.do',
                data : formData,
                contentType: false,
                processData: false,
                dataType : 'json',
                success : (data) => {
                    if (data.statusCode === '00000') {
                        $('#modifyChapterModal').modal('hide');
                        let ajaxParam = stack.peek();
                        sendAjax(ajaxParam.url, ajaxParam.data);
                    } else {
                        console.log(data);
                        window.parent.sonPageMsg(data);
                    }
                }
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
            stack.replace(new AjaxParam('/chapter/list.do', str))
            // console.log(stack.toString());
            sendAjax('/chapter/list.do', str);
        };
    });

    // init
    function init() {
        let stackStr = sessionStorage.getItem("back_courseResourceSet");
        // console.log(stackStr)
        if (stackStr) {
            for (let s of stackStr.split('|')) {
                let url = s.split(',')[0].substring(s.split(',')[0].indexOf(':') + 1);
                let data = s.split(',')[1].substring(s.split(',')[1].indexOf(':') + 1);
                let ajaxParam = new AjaxParam(url, data);
                stack.push(ajaxParam);
                let courseId = data.match(/(courseId=)([0-9]*)/)[2];

                $('#course-id').val(courseId);
                $('#add-course-id').val(courseId);
                $('#search-course-id').val(courseId);
            }
            let ajaxParam = stack.peek();
            sendAjax(ajaxParam.url, ajaxParam.data);
        } else {
            // 不使用，当前页面只能通过课程界面跳转
            // 初次访问页面时发送 ajax 请求
            // sendAjax('/chapter/list.do');
            // 初次 ajax 请求
            // stack.push(new AjaxParam('/chapter/list.do', ''))
        }
    }

    // 上传文件
    function fileUpload(item){
        $(item).click();
    }
    function fileChange(item){
        //获取选中的第一个文件
        let file = item.files[0];
        if (file) {
            $(".file").html(file.name);
        } else {
            $(".file").html("选择文件");
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

    // getChapter
    function getChapter(e) {
        let id = $($(e).parent().parent().children().get(0)).text();
        $.ajax({
            type : 'get',
            url : baseUrl + '/chapter/get.do',
            data : {'id': id},
            dataType : 'json',
            success : (data) => {
                if (data.statusCode === '00000') {
                    // console.log(data);
                    let chapter = data.data;
                    $('#modify-chapter-id').val(chapter.id);
                    $('#modify-chapter-title').val(chapter.title);
                    $('#modify-chapter-info').val(chapter.info);
                    $('#modify-resource-id').val(chapter.resource.id)
                    $('#modify-chapter-file-title').val(chapter.resource.title);
                    $('#modify-chapter-file-cost-type').val(chapter.resource.costType);
                    $('#modify-chapter-file-cost-type-val').val(chapter.resource.costNumber);
                    $('#modify-chapter-resource-type-id').val(chapter.resource.fileType);

                    $('#modify-file').html('选择文件');
                    $('#modifyChapterModal').modal('show');
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
            url : baseUrl + '/chapter/modify_status.do',
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

    // listComments
    function listComments(e) {
        // 相关评论
        let id = $($(e).parent().parent().children().get(0)).text();
        let ss = new Stack();
        ss.push(new AjaxParam('/comment/list.do', 'resourceId=' + id));
        sessionStorage.setItem('back_commentSet', ss.toString());
        $('#frame-id', window.parent.document).attr('src', 'show_back_commentSet.do');
    }

    // 页面关闭，缓存 stack
    $(window).on('beforeunload', () => {
        sessionStorage.setItem('back_courseResourceSet', stack.toString());
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
            <td>{{item.info}}</td>
            <td>{{item.createDate}}</td>
            {{if item.status == 1}}
            <td>启用</td>
            {{else if item.status == -1}}
            <td>禁用</td>
            {{/if}}
            <td class="text-center">
                <input type="button" class="btn btn-warning btn-sm course-resource-modify-btn" onclick="getChapter(this)" value="修改">
                {{if item.status == 1}}
                <input type="button" class="btn btn-danger btn-sm" data-status="-1" onclick="modifyStatus(this)" value="禁用" />
                {{else if item.status == -1}}
                <input type="button" class="btn btn-success btn-sm" data-status="1" onclick="modifyStatus(this)" value="启用" />
                {{/if}}
                <input type="button" class="btn btn-info btn-sm resource-detail" onclick="getDetail('{{item.resourceId}}')" value="详情" />
                <input type="button" class="btn btn-primary btn-sm comment-detail" onclick="listComments(this)" value="相关评论" />
            </td>
        </tr>
    {{/each}}
</script>