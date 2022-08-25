<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="editedVariable" value="${requestScope.get('edit-author')}" />

<p>Пожалуйста, введите новые данные:</p>

<form action="editAuthor" method="POST">

    <input type="hidden" name="author_id" value="${editedVariable.getId_author()}">

    Имя: <input type="text" name="author_name" value="${editedVariable.getName()}">
    <br>
    Отчество: <input type="text" name="author_middleName" value="${editedVariable.getMiddleName()}">
    <br>
    Фамилия: <input type="text" name="author_surname" value="${editedVariable.getSurname()}">
    <br>
    Год рождения: <input type="text" name="author_year" value="${editedVariable.getYearOfBirth()}">
    <br>
    <button name="save" value="save">save</button>
</form>
<br>
<br>
<a href="/library/authors">Back</a>