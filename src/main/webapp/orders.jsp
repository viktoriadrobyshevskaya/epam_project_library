<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<a href="/library/logOut">Log Out</a>
<br>
<c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">
    <jsp:include page="menuList.jsp"/>
</c:if>
<br>
<h2>Orders:</h2>
<table border="2">
    <tr>
        <th>User</th>
        <th>Book</th>
        <th>Book Author</th>
        <th>Order Status</th>
        <th>Operations</th>

    </tr>

    <c:forEach var="order" items="${requestScope.get('orders')}">
        <form action="orders" method="POST">
            <tr>
                <input type="hidden" name="id_order" value="${order.getId()}">
                <input type="hidden" name="id_book" value="${order.getBookId()}">
                <td><c:out value="${order.getUser().getLogin()}"/></td>
                <td><c:out value="${order.getBook().getTitle()}"/></td>
                <td><c:out value="${order.getBook().getAuthor().getName()}"/>
                    <c:out value="${order.getBook().getAuthor().getSurname()}"/>
                </td>
                <td><c:out value="${order.getStatus()}"/></td>
                <td>
                    <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">
                    <c:choose>
                        <c:when test="${order.getStatus() eq 'APPROVED'}">
                            <button name="accept" value="accept">return book</button>
                        </c:when>
                        <c:when test="${order.getStatus() eq 'IN_PROGRESS'}">
                            <button name="approve" value="approve">approve order</button>
                            <button name="cancel" value="cancel">cancel order</button>
                        </c:when>
                    </c:choose>

                    </c:if>
                    <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'user' &&
                    order.getStatus() eq 'IN_PROGRESS'}">
                        <button name="delete" value="delete">delete order</button>
                    </c:if>
                </td>
            </tr>
        </form>
    </c:forEach>

</table>
<br>
<br>
<a href="/library/books"> Back </a>
