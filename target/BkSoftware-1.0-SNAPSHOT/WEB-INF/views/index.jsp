<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/9/2018
  Time: 10:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<form:form method="POST" action="upload-file" enctype="multipart/form-data">
    File: <input type="file" name="multipartFile"/> <br/> <br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
