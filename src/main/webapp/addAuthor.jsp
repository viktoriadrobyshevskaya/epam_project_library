<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="addedAuthor" value="${requestScope.get('add-author')}" />

<h4>Пожалуйста, введите нового автора:</h4>

<form action="addAuthor" method="POST">

    <input type="hidden" name="author_id" value="${addedAuthor.getId_author()}">

    <p>Пожалуйста, заполните поля:</p>
    Имя: <input type="text" name="author_name" value="${addedAuthor.getName()}">
    <br>
    Отчество: <input type="text" name="author_middleName" value="${addedAuthor.getMiddleName()}">
    <br>
    Фамилия: <input type="text" name="author_surname" value="${addedAuthor.getSurname()}">
    <br>
    Год рождения: <input type="text" name="author_year" value="${addedAuthor.getYearOfBirth()}">
    <br>
    <button name="save" value="save">save</button>
</form>

