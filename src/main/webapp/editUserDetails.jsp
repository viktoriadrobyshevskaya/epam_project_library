<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="userDetails" value="${requestScope.get('edit-userDetail')}"/>

<p>Пожалуйста, введите новые данные:</p>

<form action="editUserDetails" method="POST">

    <input type="hidden" name="id" value="${userDetails.getId()}">
    <input type="hidden" name="user_id" value="${userDetails.getUserId()}">

    Имя: <input type="text" name="user_name" value="${userDetails.getName()}">
    <br>
    Фамилия: <input type="text" name="user_surname" value="${userDetails.getSurname()}">
    <br>
    Телефон: <input type="text" name="user_phone" value="${userDetails.getPhone()}">
    <br>
    Адрес: <input type="text" name="user_address" value="${userDetails.getAddress()}">
    <br>
    <button name="save" value="save">save</button>
</form>
<br>
<br>
<c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">
    <h4><a href="/library/users">Back to users</a></h4>
</c:if>
<h4>
    <form action="orderOperation" method="POST">
        <button name="showDetails" value="showDetails">Back</button>
    </form>
    <a href="/library/books">Back to the main page</a>
</h4>
