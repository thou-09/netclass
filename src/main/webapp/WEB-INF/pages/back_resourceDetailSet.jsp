<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>资源详情</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/_my.css" />
        <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
        <style type="text/css">
            .my-video {
                margin: 30px auto auto auto;

            }
            .bgColor {
                background: black;
            }
            .padding-0 {
                padding: 0 0 !important;
            }
        </style>
    </head>

    <body>
        <div class="panel panel-default" id="userSet">
            <div class="panel-heading">
                <h3 class="panel-title">资源详情</h3>
            </div>
            <div style="margin-top: 40px;">
                <div class="container-fluid padding-0 bgColor">
                    <video id="vv" style="width: 100%; height:470px;" poster="" src="" controls>
                    </video>
                </div>
            </div>
        </div>
    </body>
</html>
<script src="${pageContext.request.contextPath}/js/_my.js"></script>
<script>
    $(() => {
        $('#vv').attr('src', fileUrl + '${requestScope.path}').attr('poster', fileUrl + '${requestScope.coverImageUrl}')
    })
</script>