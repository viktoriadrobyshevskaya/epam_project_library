<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<html>
<head>
    <title>Books</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>

<body>

<jsp:include page="welcomeMenu.jsp"/>

<div class="album py-5 bg-light">

    <c:if test="${not empty problem}">
        <div class="alert alert-danger text-center" role="alert">
            <h4 class="alert-heading">Ошибка</h4>
            <hr>
            <p><c:out value="${problem}"/></p>
        </div>
    </c:if>

    <div class="container">
        <form action="bookOperation" method="POST" class="form-inline">
            <div class="form-group mx-sm-3 mb-2">
                <input type="text" class="form-control" name="search" placeholder="Введите автора..."/>
            </div>
            <button type="submit" class="btn btn-outline-primary mb-2" name="search" value="search">Поиск</button>
        </form>
        <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">
            <form action="bookOperation" method="POST">
                <div class="form-group mx-sm-3 mb-2">
                    <button class="btn btn-outline-primary" name="addBook" value="addBook"> <i class="bi bi-plus-circle"></i> Добавить новую книгу</button>
                </div>
            </form>
        </c:if>
        <h2>Каталог книг: </h2>
        <br>
        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center" role="alert">
                <h4 class="alert-heading">Ошибка</h4>
                <hr>
                <p><c:out value="${error}"/></p>
            </div>
        </c:if>
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <c:forEach var="book" items="${requestScope.get('books')}">
                <form action="bookOperation" method="POST">
                    <div class="col">
                        <div class="card shadow-sm">
                            <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                                 xmlns="http://www.w3.org/2000/svg" role="img" aria-label=""
                                 preserveAspectRatio="xMidYMid slice" focusable="false">
                                <rect width="100%" height="100%" fill="#55595c"></rect>
                                <text x="10%" y="50%" fill="#eceeef" dy=".3em">${book.getTitle()}</text>
                            </svg>
                            <input type="hidden" name="book_id" value="${book.getId()}">
                            <div class="card-body">
                                <p class="card-text">${book.getAuthor().getName()} ${book.getAuthor().getMiddleName()} ${book.getAuthor().getSurname()}</p>
                                <p class="card-text">${book.getYearOfPublication()}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">
                                            <button type="submit" class="btn btn-sm btn-outline-secondary" name="edit"
                                                    value="edit">редактировать
                                            </button>
                                            <button type="submit" class="btn btn-sm btn-outline-secondary" name="remove"
                                                    value="remove">удалить
                                            </button>
                                        </c:if>
                                        <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'user' && book.getYearOfPublication() > 0}">
                                            <button type="submit" class="btn btn-sm btn-outline-secondary"
                                                    name="request" value="request">заказать
                                            </button>
                                        </c:if>
                                    </div>
                                    <small class="text-muted">кол-во: ${book.getNumberOfCopies()}</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </c:forEach>
        </div>
        <br><br>
        <a href="/library/books">Главная страница</a>
        <br><br>
    </div>
</div>
</body>
</html>

