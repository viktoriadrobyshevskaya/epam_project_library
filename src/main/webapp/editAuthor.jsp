<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="addedUser" value="${requestScope.get('edit-author')}" />

<p>Пожалуйста, введите новые данные:</p>

<form action="editAuthor" method="POST">

    <input type="hidden" name="author_id" value="${addedUser.getId_author()}">

    Имя: <input type="text" name="author_name" value="${addedUser.getName()}">
    <br>
    Отчество: <input type="text" name="author_middleName" value="${addedUser.getMiddleName()}">
    <br>
    Фамилия: <input type="text" name="author_surname" value="${addedUser.getSurname()}">
    <br>
    Год рождения: <input type="text" name="author_year" value="${addedUser.getYearOfBirth()}">
    <br>
    <button name="save" value="save">save</button>
</form>

