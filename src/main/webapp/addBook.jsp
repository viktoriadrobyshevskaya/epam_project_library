<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<html>
<head>
    <title>Welcome Page</title>
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
        <h4 class="display-6 text-left">Пожалуйста, введите новую книгу:</h4>
        <div class="row">
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body">

                        <c:set var="book" value="${requestScope.get('add-book')}"/>
                        <form action="addBook" method="POST">

                            <input type="hidden" name="book_id" value="${book.getId()}">

                            <div class="form-group">
                                <label>Название:</label>
                                <input type="text" name="book_title" value="${book.getTitle()}" class="form-control"
                                       placeholder="Введите название">
                            </div>

                            <div class="form-group">
                                <label>Автор:</label>
                                <select name="author" class="form-control">
                                    <c:forEach var="author" items="${requestScope.get('authors')}">
                                        <option value="${author.getId_author()}">${author.getName()} ${author.getSurname()}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Год публикации:</label>
                                <input type="text" name="book_year" value="${book.getYearOfPublication()}"
                                       placeholder="Введите год публикации" class="form-control">
                            </div>

                            <div class="form-group">
                                <label>Количество экземпляров:</label>
                                <input name="book_number" value="${book.getNumberOfCopies()}"
                                       placeholder="Введите количество экземпляров" type="text" class="form-control">
                            </div>

                            <button class="btn btn-outline-primary" name="save" value="save">Сохранить</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <br><br>
        <a href="/library/books"><i class="bi bi-arrow-left-circle"></i> Назад</a>
        <br><br>
    </div>
</div>
</body>
</html>