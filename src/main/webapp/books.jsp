<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<c:forEach var="book" items="${requestScope.get('books')}">
    <form action="bookOperation" method="POST">

        <input type="hidden" name="book_id" value="${book.getId()}">

        <p><c:out value="${book.getTitle()}"/>
        <c:out value="${book.getId_author()}"/>
        <c:out value="${book.getYearOfPublication()}"/>
        <c:out value="${book.getNumberOfCopies()}"/>
        <button name="remove" value="remove">remove</button>
        <button name="edit" value="edit">edit</button></p>
    </form>

</c:forEach>