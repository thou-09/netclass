<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>优学堂精品课程管理系统登录</title>
        <meta charset="utf-8">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logn.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mycss.css" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/_my.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </head>
    <body>
        <!-- 使用自定义css样式 div-signin 完成元素居中-->
        <div class="containercc div-signin" style="position: relative; top: 50%; margin-top: -215px;">
            <div class="panel panel-primary div-shadow">
                <!-- h3标签加载自定义样式，完成文字居中和上下间距调整 -->
                <div class="panel-heading">
                    <h3> 优学堂精品课程管理系统</h3>
                    <span>High-quality Courses Manager System</span>
                </div>
                <div class="panel-body">
                    <!-- login form start -->
                    <form action="#" class="form-horizontal ccc login-form" method="post">
                        <div class="form-group">
                            <label class="col-xs-3 control-label">用户名：</label>
                            <div class="col-xs-9">
                                <input class="form-control" type="text" placeholder="请输入用户名" required name="loginName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                            <div class="col-xs-9">
                                <input class="form-control" type="password" placeholder="请输入密码" required name="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">验证码：</label>
                            <div class="col-xs-4">
                                <input class="form-control code-input" type="text" placeholder="请输入验证码" required name="code">
                            </div>
                            <div class="col-xs-2">
                                <!-- 验证码图片加载（需引入验证码文件）图像高度经过测试，建议不要修改 -->
                                <img class="img-rounded code-img" src="" alt="验证码" style="height: 32px; width: 70px;"/>
                                <input type="hidden" class="code-img-code" name="codeAnswer">
                            </div>
                            <div class="col-xs-2">
                                <button type="button" class="btn btn-link code-img-btn">看不清</button>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <div class="col-xs-9  col-xs-offset-3 padding-left-0">
                                <div class="col-xs-6">
                                    <button type="reset" class="btn btn-primary btn-block">重&nbsp;&nbsp;置</button>
                                </div>
                                <div class="col-xs-6">
                                    <button type="submit" class="btn btn-primary btn-block">登&nbsp;&nbsp;录</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!-- login form end -->
                </div>
            </div>
        </div>
        <!-- 页尾 版权声明 -->
        <div class="containercc" style="position: relative;top: 50%;" >
            <div class="col-xs-12 foot-css" id="ccc" >
                <p class="text-muted credit">
                    Copyright © 2017 南京网博 版权所有
                </p>
            </div>
        </div>
    </body>
</html>
<script src="${pageContext.request.contextPath}/js/_my.js"></script>
<script>
    $(() => {
        // 页面加载，获取验证码图片
        getCodeImage();

        // 获取验证码图片
        $('.code-img-btn').on('click', () => {
            getCodeImage();
        })
        // 表单提交
        $('.login-form').on('submit', (e) => {
            e.preventDefault();
            $.ajax({
                type : 'post',
                url : baseUrl + '/user/admin_login.do',
                data : $(e.currentTarget).serialize(),
                dataType : 'json',
                success : (data) => {
                    // console.log(data);
                    if (data.statusCode === '00000') {
                        window.location.href = baseUrl + '/show_back_home.do';
                    } else {
                        console.log(data);
                        alert(data.data);
                        getCodeImage();
                        $('.code-input').val('');
                    }
                }
            })
        })
    })

    function getCodeImage() {
        $.ajax({
            type : 'post',
            url : baseUrl + '/user/code_image.do',
            data : {},
            dataType : 'json',
            success : (data) => {
                // console.log(data);
                if (data.statusCode === '00000') {
                    let img = data.data;
                    $('.code-img').attr('src', img.imageStr);
                    $('.code-img-code').val(img.code);
                } else {
                    console.log(data);
                    alert(data.message);
                }
            }
        })
    }
</script>