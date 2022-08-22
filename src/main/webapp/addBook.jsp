<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="editedAuthor" value="${requestScope.get('add-book')}" />

<h4>Пожалуйста, введите новую книгу: </h4>

<form action="addBook" method="POST">

    <input type="hidden" name="book_id" value="${editedAuthor.getId()}">

    <p>Пожалуйста, заполните поля:</p>
    Название: <input type="text" name="book_title" value="${editedAuthor.getTitle()}">
    <br>
    Автор: <input type="text" name="book_author" value="${editedAuthor.getId_author()}">
    <br>
    Год публикации: <input type="text" name="book_year" value="${editedAuthor.getYearOfPublication()}">
    <br>
    Количество экземпляров: <input type="text" name="book_number" value="${editedAuthor.getNumberOfCopies()}">
    <br>
    <button name="save" value="save">save</button>
</form>

