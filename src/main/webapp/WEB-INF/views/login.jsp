<%--
  Created by IntelliJ IDEA.
  User: caots
  Date: 12/23/2018
  Time: 9:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Spring MVC-Security Login Form</h1>
<h2>${message}</h2>
<form name='loginForm' action="<c:url value='j_spring_security_login' />" method='POST'>
    <!--nhờ Mấy Filler mà trỏ đk vào Spring security config-->
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='email'></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password'/></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="login"/></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
