<%--
  Created by IntelliJ IDEA.
  User: caots
  Date: 12/22/2018
  Time: 10:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head><title><spring:message code="header.title"/></title></head>
<body>
<c:if test="${param.lang == 'vi' }">
    <a href="<spring:url value="/language?lang=vi"/>" style="color:red;">Vi</a>
    <a href="<spring:url value="/language?lang=en"/>">En</a>
</c:if>
<c:if test="${param.lang != 'vi' }">
    <a href="<spring:url value="/language?lang=vi"/>">Vi</a>
    <a href="<spring:url value="/language?lang=en"/>" style="color:red;">En</a>
</c:if>
<h1><spring:message code="body.title1"/></h1>
<h3><spring:message code="body.title2"/></h3>

</body>
</html>
