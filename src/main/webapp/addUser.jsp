<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="book" value="${requestScope.get('add-user')}"/>

<h4>Пожалуйста, введите нового пользователя: </h4>

<form action="addUser" method="POST">

    <input type="hidden" name="user_id" value="${book.getId()}">

    <p>Пожалуйста, заполните поля:</p>
    Логин: <input type="text" name="user_login" value="${book.getLogin()}">
    <br>
    Пароль: <input type="text" name="user_password" value="${book.getPassword()}">
    <br>

    Роль:
    <c:forEach var="role" items="${sessionScope.get('roles')}">
        <input type="radio" name="role" value="${role.getId()}">
        <label>${role.getTitle()}</label>
    </c:forEach>

<%--        Роль: <input type="text" name="user_idRole" value="${editedDetails.getRoleId()}">--%>

<%--    Роль: <input type="radio" name="user_idRole" value="${editedDetails.getRoleId()}" checked> admin--%>
<%--    <input type="radio" name="user_idRole" value="${editedDetails.getRoleId()}" checked> user--%>
<%--    <input type="radio" name="user_idRole" value="${editedDetails.getRoleId()}" checked> librarian--%>

    <br>
    <button name="save" value="save">save</button>
</form>
<br>
<br>
<a href="/library/users">Back</a>
