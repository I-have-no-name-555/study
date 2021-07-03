<%--
  Created by IntelliJ IDEA.
  User: 毫无背景陈平安
  Date: 2021/4/7
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>success</title>
</head>
<body>
<% %>
请求成功<br>
请求域数据：${requestScope.msg}<br>
session数据：${sessionScope.msg}<br>
pageContext:${pageScope.msg}<br>
applicationContext:${applicationScope.msg}<br>
</body>
</html>
