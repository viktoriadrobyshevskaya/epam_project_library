<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="book" value="${requestScope.get('edit-user')}"/>

<p>Please, edit user: ${book.getLogin()}</p>

<form action="editUser" method="POST">

    <input type="hidden" name="user_id" value="${book.getId()}">

    Введите новый логин: <input type="text" name="user_login" value="${book.getLogin()}">
    <br>
    Введите новый пароль: <input type="text" name="user_password" value="${book.getPassword()}">
    <br>
    Роль:
    <select name="role">
    <c:forEach var="role" items="${sessionScope.get('roles')}">
            <option value="${role.getId()}">${role.getTitle()}</option>
    </c:forEach>
    </select>
    <br>
    <button name="save" value="save">save</button>
</form>
<br>
<br>
<a href="/library/users"> Back</a>
