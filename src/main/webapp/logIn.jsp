<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:if test="${not empty error}">
    <label style="color: red"> <c:out value="${error}"></c:out> </label>
</c:if>

<form action="login" method="POST">

    Enter the login: <input type="text" name="loginInput" placeholder="Username">
    <br>
    Enter the password: <input type="password" name="passwordInput" placeholder="Password">
    <br>
    <br>
    <button type="submit" name="buttonSubmit">Log In</button>

</form>


