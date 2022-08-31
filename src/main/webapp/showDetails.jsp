<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<c:choose>
    <c:when test="${userDetails != null}">
        <h3>Additional information about <span style="color: orange"><c:out value="${userDetails.getUser().getLogin()}"/></span>: </h3>
        <table border="1">
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Operations</th>

            </tr>
            <form action="userDetailsOperation" method="POST">
                <tr>
                    <input type="hidden" name="id" value="${userDetails.getId()}">
                    <input type="hidden" name="user_id" value="${userDetails.getUserId()}">
                    <td><c:out value="${userDetails.getName()}"/></td>
                    <td><c:out value="${userDetails.getSurname()}"/></td>
                    <td><c:out value="${userDetails.getPhone()}"/></td>
                    <td><c:out value="${userDetails.getAddress()}"/></td>
                    <td>
                        <button name="editDetails" value="editDetails">edit</button>
                    </td>
                </tr>

            </form>
        </table>
    </c:when>
    <c:otherwise>
        <h3> <span style="color: red">There is no data about the user!</span></h3>
        <h3> Please, fill in your details:</h3>

        <form action="addUserDetails" method="POST">
            Имя: <input type="text" name="user_name" value="">
            <br>
            Фамилия: <input type="text" name="user_surname" value="">
            <br>
            Phone: <input type="text" name="user_phone" value="">
            <br>
            Address: <input type="text" name="user_address" value="">
            <br>
            <button name="save" value="save">save</button>
        </form>

    </c:otherwise>
</c:choose>



<br>
<br>
<c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">
    <h3><a href="/library/users">Back to users</a></h3>
    <h3><a href="/library/books">Back to the main page</a></h3>
</c:if>
<c:if test="${sessionScope.get('user').getRole().getTitle() eq 'user'}">
    <a href="/library/books">Back</a>
</c:if>