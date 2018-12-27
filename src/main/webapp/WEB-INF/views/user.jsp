<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/26/2018
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<h1>${pageContext.request.userPrincipal.name}</h1>
<a href="<c:url value="/admin-security" />">Admin Page</a> <br/>
<form action="<c:url value="/j_spring_security_logout" />" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <%--xác thực chống CSRF:  là kỹ thuật tấn công bằng cách sử dụng quyền chứng thực của người dùng đối với một website.--%>
    <%--Hiểu một cách nôm na, đây là kỹ thuật tấn công dựa vào mượn quyền trái phép.--%>
    <input type="submit" value="Logout"/>
</form>
</body>
</html>
