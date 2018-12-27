<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/25/2018
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>admin</title>
</head>
<body>
<h1>Admin Page</h1>
<h2>Welcome: ${pageContext.request.userPrincipal.name}</h2>
<form action="<c:url value="/j_spring_security_logout" />" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <%--xác thực chống CSRF:  là kỹ thuật tấn công bằng cách sử dụng quyền chứng thực của người dùng đối với một website.--%>
    <%--Hiểu một cách nôm na, đây là kỹ thuật tấn công dựa vào mượn quyền trái phép.--%>
    <input type="submit" value="Logout"/>
</form>
</body>
</html>
