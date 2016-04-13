<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/13
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>文件上传表单</title>
</head>
<body>
<h3>文件上传：</h3>
请选择要上传的文件：<br />
<form action="UploadServlet" method="post"
      enctype="multipart/form-data">
  <input type="file" name="file" size="50" />
  <br />
  <input type="submit" value="上传文件" />
</form>
</body>
</html>
