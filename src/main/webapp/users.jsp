<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<c:forEach var="user" items="${requestScope.get('users')}">
    <form action="userOperation" method="POST">

        <input type="hidden" name="user_id" value="${user.getId()}">

        <p><c:out value="${user.getTitle()}"/>
            <c:out value="${user.getId_author()}"/>
            <c:out value="${user.getYearOfPublication()}"/>
            <c:out value="${user.getNumberOfCopies()}"/>
            <button name="remove" value="remove">remove</button>
            <button name="edit" value="edit">edit</button>
        </p>
    </form>

</c:forEach>

<form action="userOperation" method="POST">
    <button name="addUser" value="addUser">+add user</button>
</form>
