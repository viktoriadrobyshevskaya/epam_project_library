<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<c:forEach var="author" items="${requestScope.get('authors')}">
    <form action="authorOperation" method="POST">

        <input type="hidden" name="author_id" value="${author.getId_author()}">

        <p><c:out value="${author.getName()}"/>
            <c:out value="${author.getMiddleName()}"/>
            <c:out value="${author.getSurname()}"/>
            <c:out value="${author.getYearOfBirth()}"/>
            <button name="remove" value="remove">remove</button>
            <button name="edit" value="edit">edit</button>
        </p>
    </form>

</c:forEach>

<form action="authorOperation" method="POST">
    <button name="addAuthor" value="addAuthor">+add author</button>
</form>