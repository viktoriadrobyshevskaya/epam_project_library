<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="editedVariable" value="${requestScope.get('add-user')}" />

<h4>Пожалуйста, введите нового пользователя: </h4>

<form action="addUser" method="POST">

    <input type="hidden" name="user_id" value="${editedVariable.getId()}">

    <p>Пожалуйста, заполните поля:</p>
    Логин: <input type="text" name="user_login" value="${editedVariable.getLogin()}">
    <br>
    Пароль: <input type="text" name="user_password" value="${editedVariable.getPassword()}">
    <br>
    Роль: <input type="text" name="user_idRole" value="${editedVariable.getRoleId()}">
    <br>
    <button name="save" value="save">save</button>
</form>
<br>
<br>
<a href="/library/users">Back</a>
