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
    <button name="search" value="search">search</button>
</form>
<br>
<h2>Books catalog: </h2>
<c:if test="${not empty error}">
    <label style="color: red"> <c:out value="${error}"></c:out> </label>
</c:if>


<div class="album py-5 bg-light">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <c:forEach var="book" items="${requestScope.get('books')}">
                <form action="bookOperation" method="POST">

                    <div class="col">
                        <div class="card shadow-sm">
                            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">${book.getTitle()}</text></svg>
                            <input type="hidden" name="book_id" value="${book.getId()}">
                            <div class="card-body">
                                <p class="card-text">кол-во: ${book.getNumberOfCopies()}</p>
                                <p class="card-text">${book.getYearOfPublication()}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">
                                            <button type="button" class="btn btn-sm btn-outline-secondary" name="remove" value="remove">remove</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary" name="edit" value="edit">edit</button>
                                        </c:if>
                                        <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'user' &&
                                           book.getYearOfPublication() > 0}">
                                            <button type="button" class="btn btn-sm btn-outline-secondary" name="request" value="request">request</button>
                                        </c:if>
                                    </div>
                                    <small class="text-muted">${book.getAuthor().getName()} ${book.getAuthor().getMiddleName()} ${book.getAuthor().getSurname()}</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </c:forEach>
        </div>
    </div>
</div>




<%--<table border="2">--%>
<%--    <tr>--%>
<%--        <th>Title</th>--%>
<%--        <th>Author</th>--%>
<%--        <th>Year of Publication</th>--%>
<%--        <th>Number of copies</th>--%>
<%--        <th>Operations</th>--%>

<%--    </tr>--%>

<%--    <c:forEach var="book" items="${requestScope.get('books')}">--%>
<%--        <form action="bookOperation" method="POST">--%>
<%--            <tr>--%>
<%--                <input type="hidden" name="book_id" value="${book.getId()}">--%>

<%--                <td><c:out value="${book.getTitle()}"/></td>--%>

<%--                <td><c:out value="${book.getAuthor().getName()}"/>--%>
<%--                    <c:out value="${book.getAuthor().getMiddleName()}"/>--%>
<%--                    <c:out value="${book.getAuthor().getSurname()}"/>--%>
<%--                </td>--%>

<%--                <td><c:out value="${book.getYearOfPublication()}"/></td>--%>
<%--                <td><c:out value="${book.getNumberOfCopies()}"/></td>--%>
<%--                <td>--%>
<%--                    <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">--%>
<%--                        <button name="remove" value="remove">remove</button>--%>
<%--                        <button name="edit" value="edit">edit</button>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'user' &&--%>
<%--                    book.getYearOfPublication() > 0}">--%>
<%--                        <button name="request" value="request">request</button>--%>
<%--                    </c:if>--%>
<%--                </td>--%>

<%--            </tr>--%>

<%--        </form>--%>

<%--    </c:forEach>--%>

<%--</table>--%>
<br>
<br>
<c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">
    <form action="bookOperation" method="POST">
        <button name="addBook" value="addBook">+add book</button>
    </form>
</c:if>
<br>
<br>
<a href="/library/books">Back to main page</a>

