<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>

<body>

<jsp:include page="welcomeMenu.jsp"/>

<div class="container">

    <c:if test="${not empty problem}">
        <div class="alert alert-danger text-center" role="alert">
            <h4 class="alert-heading">Ошибка</h4>
            <hr>
            <p><c:out value="${problem}"/></p>
        </div>
    </c:if>

    <c:choose>
        <c:when test="${not empty requestScope.get('orders')}">
            <div class="container">
                <h2>Заказы:</h2>
                <table class="table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Пользователь</th>
                        <th scope="col">Книга</th>
                        <th scope="col">Автор</th>
                        <th scope="col">Статус заказа</th>
                        <th scope="col">Операции</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="order" items="${requestScope.get('orders')}">
                        <form action="orders" method="POST">
                            <tr>
                                <input type="hidden" name="id_order" value="${order.getId()}">
                                <input type="hidden" name="id_book" value="${order.getBookId()}">
                                <td><c:out value="${order.getUser().getLogin()}"/></td>
                                <td><c:out value="${order.getBook().getTitle()}"/></td>
                                <td><c:out value="${order.getBook().getAuthor().getName()}"/>
                                    <c:out value="${order.getBook().getAuthor().getSurname()}"/></td>
                                <td><c:out value="${order.getStatus()}"/></td>
                                <td>
                                    <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">
                                        <c:choose>
                                            <c:when test="${order.getStatus() eq 'APPROVED'}">
                                                status
                                            </c:when>
                                            <c:when test="${order.getStatus() eq 'IN_PROGRESS'}">
                                                <c:if test="${order.getBook().getNumberOfCopies() > 0}">
                                                    <button class="btn btn-outline-success" name="approve" value="approve">
                                                        одобрить
                                                    </button>
                                                </c:if>
                                                <button class="btn btn-outline-danger" name="cancel" value="cancel">
                                                    отклонить
                                                </button>
                                            </c:when>
                                        </c:choose>

                                    </c:if>
                                    <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'user' && order.getStatus() eq 'IN_PROGRESS'}">
                                        <button class="btn btn-outline-danger" name="delete" value="delete">отменить
                                        </button>
                                    </c:if>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:when>
        <c:otherwise>
            <div class="container">
                <h4>Нет действующих заказов.</h4>
            </div>
        </c:otherwise>
    </c:choose>
    <br><br>
    <a href="/library/books"><i class="bi bi-arrow-left-circle"></i> Назад</a>
    <br><br>
</div>
</body>
</html>
