<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 毫无背景陈平安
  Date: 2021/4/7
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <a href="hello">我的第一个请求</a>
  <br>
  <a href="haha/handle01">在类上加RequestMapping注解</a>  <br>
  <a href="haha/handle02">method post</a>
  <form action="haha/handle02" method="post">
    <input type="submit">
    method post form
  </form>

  <a href="haha/ant01">ant ? </a><br>
  <a href="haha/ant02">ant * </a><br>
  <a href="haha/user/admin">@PathVariable </a><br>

  <br>restTest
  <a href="book/1">get</a>
  <form method="post" action="book/1">
    <input type="submit" value="post">
  </form>
  <form action="book/1" method="post">
    <input name="_method" value="delete">
    <input type="submit" value="delete">
  </form>
  <form action="book/1" method="post">
    <input name="_method" value="put">
    <input type="submit" value="put">
  </form>


  <a href="hello/handle01?user=admin">参数测试</a>

  <form action="hello/saveBook" method="post">
    书名：<input type="text" name="bookName"/><br>
    价格：<input type="text" name="price"/><br>
    作者：<input type="text" name="author"/><br>
    库存：<input type="text" name="stock"/><br>
    销量：<input type="text" name="sales"/><br>
    <input type="submit" value="提交">
  </form>
  <a href="hello/handle02">web原生api测试</a><br>
  <a href="data/handle01">map model modelMap测试</a><br>
  <a href="data/handle02">ModelAndView测试</a><br>
  <br>
  <a href="view/hello">视图解析测试../</a><br>
  <a href="view/handle01">视图解析测试forward</a><br>
  <a href="view/handle02">视图解析测试forward</a><br>
  <a href="view/handle03">视图解析测试redirect</a><br>
  <br>
  <a href="success">mvc view-controller测试</a><br>
  <form action="data/quickSave">
    <input name="bookInfo" value="富婆爱上你-无名氏-100.0-100-100">
    <input type="submit" value="提交">
  </form>
  <a href="httpEntityTest">testHttpEntity</a><br>
  <a href="responseBodyTest">responseBodyTest</a><br>
  <a href="download">downloadTest</a><br>
  <form action="upload" method="post" enctype="multipart/form-data">
    用户头像：<input type="file" name="headerimg"><br>
    用户名：<input type="text" name="username">
    <input type="submit">
  </form>
  <a href="interceptor/test01">拦截器测试</a><br>
  <a href="hello/exception">异常处理测试</a><br>
  </body>
</html>
