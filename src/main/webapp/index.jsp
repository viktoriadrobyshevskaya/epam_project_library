<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>

<h3> Welcome to Victory Library </h3>

<c:choose>
    <c:when test="${currentPage eq 'books'}">
        <jsp:include page="books.jsp"></jsp:include>

    </c:when>

    <c:when test="${currentPage eq 'users'}">
        <jsp:include page="users.jsp"></jsp:include>
    </c:when>

    <c:when test="${currentPage eq 'authors'}">
        <jsp:include page="authors.jsp"></jsp:include>
    </c:when>

    <c:otherwise>
        <jsp:include page="menuList.jsp"></jsp:include>
    </c:otherwise>
</c:choose>
</body>
</html>
