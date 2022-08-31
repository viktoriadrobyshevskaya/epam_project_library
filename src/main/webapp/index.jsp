<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<html>
<head>
    <title>Welcome Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<h3> Welcome to Library, <span style="color: blue; font-family: cursive"><c:out value="${sessionScope.get('user').getLogin()}"/> </span> ! </h3>


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
