<%--
  Created by IntelliJ IDEA.
  User: Leo
  Date: 2018/5/17
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>头像上传</title>
    <form action=" /user/upload/Leo" enctype="multipart/form-data" method="post">

        <tr>
            <td>请选择文件：</td>
            <td><input type="file" name="userImg"> </td>

        </tr>
        <tr>
            <td><input type="submit" value="上传"></td>
        </tr>
    </form>
</head>
<body>

</body>
</html>
