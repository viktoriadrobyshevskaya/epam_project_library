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

<div class="album py-5 bg-light">
    <div class="container">
        <form action="userOperation" method="POST">
            <button class="btn btn-outline-primary" name="addUser" value="addUser"><i class="bi bi-plus-circle"></i> Добавить нового пользователя
            </button>
        </form>
        <br>
        <h1 class="display-6">Список пользователей: </h1>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Логин</th>
                <th scope="col">Пароль</th>
                <th scope="col">Роль</th>
                <th scope="col">Операции</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${requestScope.get('users')}">
                <form action="userOperation" method="POST">
                    <tr>
                        <input type="hidden" name="user_id" value="${user.getId()}">
                        <td><c:out value="${user.getLogin()}"/></td>
                        <td><c:out value="${user.getPassword()}"/></td>
                        <td><c:out value="${user.getRole().getTitle()}"/></td>
                        <td>
                            <button class="btn btn-outline-primary" name="edit" value="edit">редактировать</button>
                            <button class="btn btn-outline-danger" name="remove" value="remove">удалить</button>
                            <button class="btn btn-outline-info" name="showDetails" value="showDetails">доп.информация
                            </button>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            </tbody>
        </table>
        <br>
        <a href="/library/books"><i class="bi bi-arrow-left-circle"></i> На главную</a>
        <br><br>
    </div>
</div>
</body>
</html>