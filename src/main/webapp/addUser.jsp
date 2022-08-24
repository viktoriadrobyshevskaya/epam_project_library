<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="addedUser" value="${requestScope.get('add-user')}" />

<h4>Пожалуйста, введите нового пользователя: </h4>

<form action="addUser" method="POST">

    <input type="hidden" name="user_id" value="${addedUser.getId()}">

    <p>Пожалуйста, заполните поля:</p>
    Логин: <input type="text" name="user_login" value="${addedUser.getLogin()}">
    <br>
    Пароль: <input type="text" name="user_password" value="${addedUser.getPassword()}">
    <br>
    Роль: <input type="text" name="user_idRole" value="${addedUser.getRoleId()}">
    <br>
    <button name="save" value="save">save</button>
</form>

