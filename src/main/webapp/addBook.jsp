<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="editedVariable" value="${requestScope.get('add-book')}" />

<h4>Пожалуйста, введите новую книгу: </h4>

<form action="addBook" method="POST">

    <input type="hidden" name="book_id" value="${editedVariable.getId()}">

    <p>Пожалуйста, заполните поля:</p>
    Название: <input type="text" name="book_title" value="${editedVariable.getTitle()}">
    <br>
    Автор: <input type="text" name="book_author" value="${editedVariable.getId_author()}">
    <br>
    Год публикации: <input type="text" name="book_year" value="${editedVariable.getYearOfPublication()}">
    <br>
    Количество экземпляров: <input type="text" name="book_number" value="${editedVariable.getNumberOfCopies()}">
    <br>
    <button name="save" value="save">save</button>
</form>
<br>
<br>
<a href="/library/books">Back</a>
