<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="addedUser" value="${requestScope.get('edit-book')}" />

<p>Please, edit book: ${addedUser.getTitle()}</p>

<form action="editBook" method="POST">

    <input type="hidden" name="book_id" value="${addedUser.getId()}">

    Название: <input type="text" name="book_title" value="${addedUser.getTitle()}">
    <br>
    Автор: <input type="text" name="book_author" value="${addedUser.getId_author()}">
    <br>
    Год выпуска: <input type="text" name="book_year" value="${addedUser.getYearOfPublication()}">
    <br>
    Количество экземпляров: <input type="text" name="book_number" value="${addedUser.getNumberOfCopies()}">
    <br>
    <button name="save" value="save">save</button>
</form>

