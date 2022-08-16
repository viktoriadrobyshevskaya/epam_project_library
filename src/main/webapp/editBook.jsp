<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditBook</title>
</head>
<body>
<c:set var="modifiedBook" value="${requestScope.get('edit-book')}" />

<p>Please, edit book: ${modifiedBook.getTitle()}</p>


<form action="editBook" method="POST">


    <input type="hidden" name="book_id" value="${modifiedBook.getId()}">

    <p>Please enter your updated title</p>
    <input type="text" name="book_title" value="${modifiedBook.getTitle()}">
    <br>
    <p>Please enter your updated author</p>
    <input type="text" name="book_author" value="${modifiedBook.getAuthor()}">

    <br>
    <button name="save" value="save">save</button>
</form>

</body>
</html>
