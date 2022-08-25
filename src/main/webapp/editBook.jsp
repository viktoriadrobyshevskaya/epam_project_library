<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="editedVariable" value="${requestScope.get('edit-book')}"/>

<p>Please, edit book: ${editedVariable.getTitle()}</p>

<form action="editBook" method="POST">

    <input type="hidden" name="book_id" value="${editedVariable.getId()}">

    Название: <input type="text" name="book_title" value="${editedVariable.getTitle()}">
    <br>
    Автор: <input type="text" name="book_author" value="${editedVariable.getId_author()}">
    <br>
    Год выпуска: <input type="text" name="book_year" value="${editedVariable.getYearOfPublication()}">
    <br>
    Количество экземпляров: <input type="text" name="book_number" value="${editedVariable.getNumberOfCopies()}">
    <br>
    <button name="save" value="save">save</button>
</form>
<br>
<br>
<a href="/library/books">Back</a>
