<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<table>
    <tr>
        <th>Name</th>
        <th>Middle Name</th>
        <th>Surname</th>
        <th>Year of birth</th>
        <th>Operations</th>

    </tr>

    <c:forEach var="author" items="${requestScope.get('authors')}">
        <form action="authorOperation" method="POST">


            <tr>

                <input type="hidden" name="author_id" value="${author.getId_author()}">

                <td><c:out value="${author.getName()}"/></td>
                <td><c:out value="${author.getMiddleName()}"/></td>
                <td><c:out value="${author.getSurname()}"/></td>
                <td><c:out value="${author.getYearOfBirth()}"/></td>
                <td>
                    <button name="remove" value="remove">remove</button>
                    <button name="edit" value="edit">edit</button>
                </td>

            </tr>
        </form>

    </c:forEach>
</table>
<br>
<br>
<form action="authorOperation" method="POST">
    <button name="addAuthor" value="addAuthor">+add author</button>
</form>
<br>
<jsp:include page="menuList.jsp"></jsp:include>