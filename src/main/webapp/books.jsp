<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<a href="/library/logOut">Log Out</a>
<br>
<br>
<br>
<table>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Year of Publication</th>
        <th>Number of copies</th>
        <th>Operations</th>

    </tr>

    <c:forEach var="book" items="${requestScope.get('books')}">
        <form action="bookOperation" method="POST">

            <tr>
                <input type="hidden" name="book_id" value="${book.getId()}">

                <td><c:out value="${book.getTitle()}"/></td>

                <td><c:out value="${book.getAuthor().getName()}"/>
                    <c:out value="${book.getAuthor().getMiddleName()}"/>
                    <c:out value="${book.getAuthor().getSurname()}"/>
                </td>

                <td><c:out value="${book.getYearOfPublication()}"/></td>
                <td><c:out value="${book.getNumberOfCopies()}"/></td>
                <td>
                    <button name="remove" value="remove">remove</button>
                    <button name="edit" value="edit">edit</button>
                    <button name="read" value="read">read</button>
                    <button name="order" value="order">order</button>
                </td>

            </tr>

        </form>

    </c:forEach>

</table>
<br>
<br>
<form action="bookOperation" method="POST">
    <button name="addBook" value="addBook">+add book</button>
</form>
<br>
<jsp:include page="menuList.jsp"></jsp:include>
