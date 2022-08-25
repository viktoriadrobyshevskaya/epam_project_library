<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:if test="${not empty error}">
    <label style="color: red"> <c:out value="${error}"></c:out> </label>
</c:if>

<form action="login" method="POST">

    Введите логин: <input type="text" name="loginInput" placeholder="Username">
    <br>
    Введите пароль: <input type="password" name="passwordInput" placeholder="Password">
    <br>
    <button type="submit" name="buttonSubmit">submit</button>

</form>


