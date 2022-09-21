<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>系统繁忙</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logn.png">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/_style.css"/>
    </head>
    <style>
        a {
            color: #fefeff;
            text-decoration: none;
            border-bottom: 2px solid #fefeff;
        }
    </style>
    <!-- partial:index.partial.html -->
    <!-- Thanks to @xontab -->
    <div id="page">
        <div id="container">
            <h1>:(</h1>
            <h2>系统正在逐步升级，请联系管理员.</h2>
            <div id="details">
                <h5 style="font-size: 22px; display: inline-block"><a href="javascript:window.history.back()">返回上一级</a></h5>
                <h5 style="font-size: 22px; margin-left: 80px; display: inline-block"><a href="${pageContext.request.contextPath}/">回到首页</a></h5>
            </div>
        </div>
    </div>
</html>
