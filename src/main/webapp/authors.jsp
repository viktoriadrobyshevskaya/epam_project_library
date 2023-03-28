<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<html>
<head>
    <title>Authors</title>
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
        <form action="authorOperation" method="POST">
            <button class="btn btn-outline-primary" name="addAuthor" value="addAuthor"><i class="bi bi-plus-circle"></i> Добавить нового автора</button>
        </form>
        <br>
        <h2>Список авторов: </h2>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Имя</th>
                <th scope="col">Отчество</th>
                <th scope="col">Фамилия</th>
                <th scope="col">Год рождения</th>
                <th scope="col">Операции</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="author" items="${requestScope.get('authors')}">
                <form action="authorOperation" method="POST">
                    <tr>
                        <input type="hidden" name="author_id" value="${author.getId_author()}"/>
                        <td><c:out value="${author.getName()}"/></td>
                        <td><c:out value="${author.getMiddleName()}"/></td>
                        <td><c:out value="${author.getSurname()}"/></td>
                        <td><c:out value="${author.getYearOfBirth()}"/></td>
                        <td>
                            <button class="btn btn-outline-primary" name="edit" value="edit">редактировать</button>
                            <button class="btn btn-outline-danger" name="remove" value="remove">удалить</button>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            </tbody>
        </table>
        <br><br>
        <a href="/library/books"><i class="bi bi-arrow-left-circle"></i> Назад</a>
        <br><br>
    </div>
</div>
</body>
</html>