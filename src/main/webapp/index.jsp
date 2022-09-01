<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<html>
<head>
    <title>Welcome Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body>

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
        <jsp:include page="welcomeMenu.jsp"/>
    </c:otherwise>
</c:choose>
</body>
</html>
