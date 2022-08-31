<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h4>Пожалуйста, введите новую книгу: </h4>
<c:set var="book" value="${requestScope.get('add-book')}"/>

<form action="addBook" method="POST">

    <input type="hidden" name="book_id" value="${book.getId()}">

    <p>Пожалуйста, заполните поля:</p>
    Название: <input type="text" name="book_title" value="${book.getTitle()}">
    <br>
    Автор:
    <select name="author">
        <c:forEach var="author" items="${requestScope.get('authors')}">
            <option value="${author.getId_author()}">${author.getName()} ${author.getSurname()}</option>
        </c:forEach>
    </select>
    <br>
    Год публикации: <input type="text" name="book_year" value="${book.getYearOfPublication()}">
    <br>
    Количество экземпляров: <input type="text" name="book_number" value="${book.getNumberOfCopies()}">
    <br>
    <button name="save" value="save">save</button>
</form>

<br>
<br>
<a href="/library/books">Back</a>
