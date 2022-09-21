<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>NetClass</title>
    </head>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logn.png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/_style.css"/>
    <style>
        ol li {
            margin-top: 10px;
        }
        a {
            color: #fefeff;
            text-decoration: none;
            border-bottom: 2px solid #fefeff;
        }
    </style>
    <body>
        <div id="page">
            <div id="container">
                <h1>:)</h1>
                <h2>Hello NetClass!</h2>
                <ol>
                    <li>
                        <h5><a href="${pageContext.request.contextPath}/show_front_index.do"><span>前台首页</span></a></h5>
                    </li>
                    <li>
                        <h5><a href="${pageContext.request.contextPath}/show_back_login.do"><span>后台登录页面</span></a></h5>
                    </li>
                </ol>
            </div>
        </div>
    </body>
</html>