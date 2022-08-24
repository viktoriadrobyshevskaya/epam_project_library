<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>


<table>
    <tr>
        <th>Login</th>
        <th>Password</th>
        <th>Role ID</th>
        <th>Operations</th>

    </tr>

    <c:forEach var="user" items="${requestScope.get('users')}">
        <form action="userOperation" method="POST">


            <tr>

                <input type="hidden" name="user_id" value="${user.getId()}">

                <td><c:out value="${user.getLogin()}"/></td>
                <td><c:out value="${user.getPassword()}"/></td>
                <td><c:out value="${user.getRoleId()}"/></td>
                <td>
                    <button name="remove" value="remove">remove</button>
                    <button name="edit" value="edit">edit</button>
                    <button name="showDetails" value="showDetails">showDetails</button>
                </td>

            </tr>
        </form>

    </c:forEach>
</table>
<br>
<br>
<form action="userOperation" method="POST">
    <button name="addUser" value="addUser">+add user</button>
</form>