<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>课程管理</title>
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
            /*.file input {
                position: absolute;
                font-size: 100px;
                right: 0;
                top: 0;
                opacity: 0;
            }*/
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

            .img {
                /* max-width: 100px; */
                width: 100px;
                /* max-height: 120px; */
            }
            tr td {
                /* 课程图片高度 */
                /*line-height: 120px !important;*/
            }
        </style>
    </head>
    <body>
        <div class="panel panel-default" id="userPic">
            <div class="panel-heading">
                <h3 class="panel-title">课程管理</h3>
            </div>
            <div>
                <input type="button" value="添加课程" class="btn btn-primary" id="doAddCourse" style="margin: 5px 5px 5px 15px;" />
                <input type="button" value="查询" class="btn btn-success" id="doSearch" style="margin: 5px 5px 5px 0;" />
                <input type="button" class="btn btn-primary" id="show-course-search" value="展开搜索" />
                <input type="button" value="收起搜索" class="btn btn-primary" id="upp-course-search" style="display: none;" />
                <input type="button" value="清空查询条件" class="btn btn-warning" id="form-clear-btn" />
            </div>
            <div class="panel-body">
                <div class="show-course-search" style="display: none;">
                    <form class="form-horizontal search-course-form">
                        <div class="form-group">
                            <div class="form-group col-xs-6">
                                <label for="author-name-search" class="col-xs-3 control-label">作者：</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" id="author-name-search" name="author" placeholder="请输入作者" />
                                </div>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="course-name-search" class="col-xs-3 control-label">课程名称：</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" id="course-name-search" name="courseName" placeholder="请输入课程名称" />
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-group col-xs-6">
                                <label for="course-status-search" class="col-xs-3 control-label">状态：</label>
                                <div class="col-xs-8">
                                    <select class="form-control" id="course-status-search" name="status" >
                                        <option value="" >全部</option>
                                        <option value="1" >启用</option>
                                        <option value="-1" >禁用</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="course-type-id-search" class="col-xs-3 control-label">课程类别：</label>
                                <div class="col-xs-8">
                                    <select class="form-control" id="course-type-id-search" name="courseTypeId" >
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
                    </form>
                </div>

                <!-- add modal -->
                <div class="modal fade" tabindex="-1" id="addCourseModal">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">添加课程</h4>
                            </div>
                            <div class="modal-body text-center">
                                <form class="form-horizontal add-form">
                                    <div class="row text-right">
                                        <label for="add-course-name" class="col-xs-4 control-label">课程名称：</label>
                                        <div class="col-xs-4">
                                            <input type="text" class="form-control" id="add-course-name" name="courseName" />
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-right">
                                        <label for="add-course-info" class="col-xs-4 control-label">课程简介：</label>
                                        <div class="col-xs-4">
                                            <input type="text" class="form-control" id="add-course-info" name="courseInfo" />
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-right">
                                        <label for="add-course-author" class="col-xs-4 control-label">作者：</label>
                                        <div class="col-xs-4">
                                            <input type="text" class="form-control" id="add-course-author" name="author" />
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-right">
                                        <label class="col-xs-4 control-label">封面图片：</label>
                                        <div class="col-xs-4">
                                            <a href="javascript:imageUpload('#add-cover-image');" id="add-cover-image-url" class="file" >选择文件</a>
                                            <input id="add-cover-image" class="form-control" accept="image/*" type="file" name="coverImage" style="display: none;" onchange="imageChange(this)" />
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-right">
                                        <label for="add-recommendation-grade" class="col-xs-4 control-label">推荐等级：</label>
                                        <div class="col-xs-4">
                                            <select class="form-control" id="add-recommendation-grade" name="recommendationGrade" >
                                                <option value="" >请选择</option>
                                                <option value="0" >普通</option>
                                                <option value="1" >推荐</option>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-right">
                                        <label for="add-course-type-id" class="col-xs-4 control-label">课程类别：</label>
                                        <div class="col-xs-4">
                                            <select class="form-control" id="add-course-type-id" name="courseTypeId" >
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-primary curse-btn addOne">确定</button>
                                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- modify modal -->
                <div class="modal fade" tabindex="-1" id="modifyCourseModal">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">修改课程</h4>
                            </div>
                            <div class="modal-body text-center">
                                <form class="form-horizontal add-form">
                                    <div class="form-group">
                                        <div class="row text-right" id="course-id-input">
                                        <label for="modify-course-id" class="col-xs-4 control-label">课程编号：</label>
                                        <div class="col-xs-4">
                                            <input type="text" class="form-control" id="modify-course-id" name="id" readonly />
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-right">
                                        <label for="modify-course-name" class="col-xs-4 control-label">课程名称：</label>
                                        <div class="col-xs-4">
                                            <input type="text" class="form-control" id="modify-course-name" name="courseName" />
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-right">
                                        <label for="modify-course-info" class="col-xs-4 control-label">课程简介：</label>
                                        <div class="col-xs-4">
                                            <input type="text" class="form-control" id="modify-course-info" name="courseInfo" />
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-right">
                                        <label for="modify-course-author" class="col-xs-4 control-label">作者：</label>
                                        <div class="col-xs-4">
                                            <input type="text" class="form-control" id="modify-course-author" name="author" />
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-right">
                                        <label class="col-xs-4 control-label">封面图片：</label>
                                        <div class="col-xs-4">
                                            <a href="javascript:imageUpload('#modify-cover-image');" class="file" id="modify-cover-image-url" >选择文件</a>
                                            <input id="modify-cover-image" accept="image/*" type="file" name="coverImage" style="display: none;" onchange="imageChange(this)"  />
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-right">
                                        <label for="modify-recommendation-grade" class="col-xs-4 control-label">推荐等级：</label>
                                        <div class="col-xs-4">
                                            <select class="form-control" id="modify-recommendation-grade" name="recommendation_grade" >
                                                <option value="0" >普通</option>
                                                <option value="1" >推荐</option>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row text-right">
                                        <label for="modify-course-type-id" class="col-xs-4 control-label">课程类别：</label>
                                        <div class="col-xs-4">
                                            <select class="form-control" id="modify-course-type-id" name="courseTypeId" >
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-primary curse-btn updateOne">确定</button>
                                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="show-list">
                    <table class="table table-bordered table-hover" style="text-align: center;">
                        <thead>
                        <tr class="text-danger">
                            <th class="text-center">编号</th>
                            <th class="text-center">课程名称</th>
                            <th class="text-center">作者</th>
                            <th class="text-center">封面图片</th>
                            <th class="text-center">点击量</th>
                            <th class="text-center">推荐等级</th>
                            <th class="text-center">课程类别</th>
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
    </body>
</html>
<script src="${pageContext.request.contextPath}/js/_my.js"></script>
<script src="${pageContext.request.contextPath}/js/template-web.js"></script>
<script type="text/javascript">
    let stack = new Stack();

    $(function(){
        init();

        // 显示隐藏查询列表
        $('#show-course-search').click(function() {
            $.ajax({
                type : 'post',
                url : baseUrl + '/course_type/three_level.do',
                data : {},
                dataType : 'json',
                success : (data) => {
                    if (data.statusCode === '00000') {
                        // console.log(data);
                        let list = data.data;
                        let html = template('list-course-type-data', {
                            'list': list,
                            'isModify': -1
                        });
                        $('#course-type-id-search').html(html);
                        $('#show-course-search').hide();
                        $('#upp-course-search').show();
                        $('.show-course-search').slideDown(500);
                    } else {
                        window.parent.sonPageMsg(data);
                        console.log(data);
                    }
                }
            })
        });
        $('#upp-course-search').click(function() {
            $('#show-course-search').show();
            $('#upp-course-search').hide();
            $('.show-course-search').slideUp(500);
        });

        // doSearch
        $('#doSearch').on('click', () => {
            let condition = _serialize($('.search-course-form'));
            stack.replace(new AjaxParam('/course/list.do', condition))
            sendAjax('/course/list.do', condition);
        })

        // add modal
        $('#doAddCourse').on('click', () => {
            $.ajax({
                type : 'post',
                url : baseUrl + '/course_type/three_level.do',
                data : {},
                dataType : 'json',
                success : (data) => {
                    if (data.statusCode === '00000') {
                        // console.log(data);
                        let list = data.data;
                        let html = template('list-course-type-data', {
                            'list': list,
                            'isModify': -1
                        });
                        $('#add-course-type-id').html(html);

                        $('.add-form').get(0).reset();
                        $('#add-cover-image-url').html('选择文件');
                        $('#addCourseModal').modal('show');
                    } else {
                        window.parent.sonPageMsg(data);
                        console.log(data);
                    }
                }
            })
        })

        // addOne
        $('.addOne').on('click', () => {
            let formdate = new FormData();
            formdate.append('courseName', $('#add-course-name').val());
            formdate.append('courseInfo', $('#add-course-info').val());
            formdate.append('author', $('#add-course-author').val());
            formdate.append('coverImage', $('#add-cover-image')[0].files[0]);
            formdate.append('recommendationGrade', $('#add-recommendation-grade').val());
            formdate.append('courseTypeId', $('#add-course-type-id').val());
            $.ajax({
                type : 'post',
                url : baseUrl + '/course/add.do',
                data : formdate,
                contentType : false,
                processData: false,
                dataType : 'json',
                success : (data) => {
                    if (data.statusCode === '00000') {
                        $('#addCourseModal').modal('hide');
                        let ajaxParam = stack.peek();
                        sendAjax(ajaxParam.url, ajaxParam.data);
                    } else {
                        window.parent.sonPageMsg(data)
                        console.log(data);
                    }
                }
            })
        })

        // updateOne
        $('.updateOne').on('click', () => {
            let formdate = new FormData();
            new FormData($('#add-form')[0]);
            formdate.append('id', $('#modify-course-id').val());
            formdate.append('courseName', $('#modify-course-name').val());
            formdate.append('courseInfo', $('#modify-course-info').val());
            formdate.append('author', $('#modify-course-author').val());
            formdate.append('coverImage', $('#modify-cover-image')[0].files[0]);
            formdate.append('recommendationGrade', $('#modify-recommendation-grade').val());
            formdate.append('courseTypeId', $('#modify-course-type-id').val());
            $.ajax({
                type : 'post',
                url : baseUrl + '/course/modify.do',
                data : formdate,
                contentType : false,
                processData: false,
                dataType : 'json',
                success : (data) => {
                    if (data.statusCode === '00000') {
                        $('#modifyCourseModal').modal('hide');
                        let ajaxParam = stack.peek();
                        sendAjax(ajaxParam.url, ajaxParam.data);
                    } else {
                        window.parent.sonPageMsg(data)
                        console.log(data);
                    }
                }
            })
        })

        // clear search
        $('#form-clear-btn').on('click', () => {
            let form = $('.search-course-form');
            form.get(0).reset();
            let ajaxParam = stack.peek();
            // console.log(ajaxParam)
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
            stack.replace(new AjaxParam('/course/list.do', str))
            // console.log(stack.toString());
            sendAjax('/course/list.do', str);
        };
    });

    // 上传图片
    function imageUpload(item){
        $(item).click();
    }
    function imageChange(item){
        //获取选中的第一个文件
        let file = item.files[0];
        if (file) {
            $(".file").html(file.name);
        } else {
            $(".file").html("选择文件");
        }
    }

    // init
    function init() {
        let stackStr = sessionStorage.getItem("back_courseSet");
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
            sendAjax('/course/list.do');
            // 初次 ajax 请求
            stack.push(new AjaxParam('/course/list.do', ''))
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
                        // 本地服务器，如果是阿里云 OSS，去掉 fileUrl
                        let html = template('list-data', {
                            'list': list,
                            'filePath': fileUrl
                        });
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

    // getCourse
    function getCourse(e) {
        $.ajax({
            type : 'post',
            url : baseUrl + '/course_type/three_level.do',
            data : {},
            dataType : 'json',
            success : (data) => {
                if (data.statusCode === '00000') {
                    // console.log(data);
                    let list = data.data;
                    let html = template('list-course-type-data', {
                        'list': list,
                        'isModify': 1
                    });
                    $('#modify-course-type-id').html(html);

                    let id = $($(e).parent().parent().children().get(0)).text();
                    $.ajax({
                        type : 'post',
                        url : baseUrl + '/course/get.do',
                        data : {'id': id},
                        dataType : 'json',
                        success : (data) => {
                            if (data.statusCode === '00000') {
                                // console.log(data);
                                let course = data.data;
                                $('#modify-course-id').val(course.id);
                                $('#modify-course-info').val(course.courseInfo);
                                $('#modify-course-name').val(course.courseName);
                                $('#modify-course-author').val(course.author);
                                $('#modify-recommendation-grade').val(course.recommendationGrade);
                                $('#modify-course-type-id').val(course.courseTypeId);

                                $('#modify-cover-image-url').html('选择文件');
                                $('#modifyCourseModal').modal('show');
                            } else {
                                console.log(data);
                                window.parent.sonPageMsg(data);
                            }
                        }
                    })
                } else {
                    window.parent.sonPageMsg(data);
                    console.log(data);
                }
            }
        })
    }

    // listChapters
    function listChapters(e) {
        // 课程章节显示
        let id = $($(e).parent().parent().children().get(0)).text();
        let ss = new Stack();
        ss.push(new AjaxParam('/chapter/list.do', 'courseId=' + id));
        sessionStorage.setItem('back_courseResourceSet', ss.toString());
        $('#frame-id', window.parent.document).attr('src', 'show_back_courseResourceSet.do');
    }

    // modifyStatus
    function modifyStatus(e) {
        let id = $($(e).parent().parent().children().get(0)).text();
        let status = $(e).attr('data-status');
        $.ajax({
            type : 'post',
            url : baseUrl + '/course/modify_status.do',
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
        sessionStorage.setItem('back_courseSet', stack.toString());
    })
</script>
<script id="list-course-type-data" type="text/html">
    {{if isModify == -1}}
        <option value="" >请选择</option>
    {{/if}}
    {{each list item}}
        <option value="{{item.id}}">{{item.typeName}}</option>
    {{/each}}
</script>
<script id="list-data" type="text/html">
    {{each list item}}
        {{if item.status == 1}}
        <tr>
        {{else if item.status == -1}}
        <tr class="disabled">
        {{/if}}
            <td>{{item.id}}</td>
            <td>{{item.courseName}}</td>
            <td>{{item.author}}</td>
            <td><img class="img" src="{{filePath + item.coverImageUrl}}" alt="{{item.courseName}}"/></td>
            <td>{{item.clickNumber}}</td>
            {{if item.recommendationGrade == 0}}
            <td>普通</td>
            {{else if item.recommendationGrade == 1}}
            <td>推荐</td>
            {{/if}}
            <td>{{item.courseType.typeName}}</td>
            <td>{{item.createDate}}</td>
            {{if item.status == 1}}
            <td>启用</td>
            {{else if item.status == -1}}
            <td>禁用</td>
            {{/if}}
            <td class="text-center">
                <input type="button" class="btn btn-warning btn-sm course-modify" onclick="getCourse(this)" value="修改" />
                {{if item.status == 1}}
                <input type="button" class="btn btn-danger btn-sm" data-status="-1" onclick="modifyStatus(this)" value="禁用" />
                {{else if item.status == -1}}
                <input type="button" class="btn btn-success btn-sm" data-status="1" onclick="modifyStatus(this)" value="启用" />
                {{/if}}
                <input type="button" class="btn btn-info btn-sm course-detail" onclick="listChapters(this)" value="章节详情" />
            </td>
        </tr>
    {{/each}}
</script>
