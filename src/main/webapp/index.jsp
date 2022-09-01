<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>

<h3> Добро пожаловать в библиотеку, <span style="color: blue; font-family: cursive"><c:out value="${sessionScope.get('user').getLogin()}"/> </span> ! </h3>

<c:choose>
    <c:when test="${currentPage eq 'books'}">
        <jsp:include page="books.jsp"/>

    </c:when>

    <c:when test="${currentPage eq 'users'}">
        <jsp:include page="users.jsp"/>
    </c:when>

    <c:when test="${currentPage eq 'authors'}">
        <jsp:include page="authors.jsp"/>
    </c:when>

    <c:when test="${currentPage eq 'orders'}">
        <jsp:include page="orders.jsp"/>
    </c:when>

    <c:otherwise>
        <jsp:include page="menuList.jsp"/>
    </c:otherwise>
</c:choose>
</body>
</html>
