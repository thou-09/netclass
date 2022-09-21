<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>未登录</title>
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
            <h2>你可能没有登录，请登录后再来访问.</h2>
            <div id="details">
                <h5 style="font-size: 22px; display: inline-block"><a href="javascript:window.history.back()">返回上一级</a></h5>
                <h5 style="font-size: 22px; margin-left: 80px; display: inline-block"><a href="${pageContext.request.contextPath}/">回到首页</a></h5>
            </div>
        </div>
    </div>
</html>
