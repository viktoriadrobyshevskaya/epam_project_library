<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalog</title>
</head>
<body>

<c:forEach var="book" items="${requestScope.get('books')}">
    <form action="bookOperation" method="POST">

        <input type="hidden" name="book_id" value=${book.getId()}>

        <p><c:out value="${book.getTitle()}"/>
        <c:out value="${book.getId_author()}"/>
        <c:out value="${book.getYearOfPublication()}"/>
        <c:out value="${book.getNumberOfCopies()}"/></p>
        <button name="remove" value="remove">remove</button>
        <button name="edit" value="edit">edit</button>
    </form>
</c:forEach>

</body>
</html>
