<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="addedUser" value="${requestScope.get('edit-user')}" />

<p>Please, edit user: ${addedUser.getLogin()}</p>

<form action="editUser" method="POST">

    <input type="hidden" name="user_id" value="${addedUser.getId()}">

    Введите новый логин: <input type="text" name="user_login" value="${addedUser.getLogin()}">
    <br>
    Введите новый пароль: <input type="text" name="user_password" value="${addedUser.getPassword()}">
    <br>
    Роль: <input type="text" name="user_idRole" value="${addedUser.getRoleId()}">
    <br>
    <button name="save" value="save">save</button>
</form>

