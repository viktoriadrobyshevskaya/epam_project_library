<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<a href="/library/logOut">Log Out</a>
<br><br>
<c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">
    <jsp:include page="menuList.jsp"/>
    <br>
</c:if>
<br>
<jsp:include page="additionalButtons.jsp"/>

<form action="bookOperation" method="POST">
    <input type="text" name="search" value=""/>
    <button name="search" value="search">Поиск</button>
</form>
<br>
<h2>Каталог книг: </h2>
<c:if test="${not empty error}">
    <label style="color: red"> <c:out value="${error}"></c:out> </label>
</c:if>

<table border="2">
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
                    <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">
                        <button name="remove" value="remove">remove</button>
                        <button name="edit" value="edit">edit</button>
                    </c:if>
                    <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'user' &&
                    book.getYearOfPublication() > 0}">
                        <button name="request" value="request">request</button>
                    </c:if>
                </td>

            </tr>

        </form>

    </c:forEach>

</table>
<br>
<br>
<c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">
    <form action="bookOperation" method="POST">
        <button name="addBook" value="addBook"> + добавить новую книгу</button>
    </form>
</c:if>
<br>
<br>
<a href="/library/books">Главная страница</a>

