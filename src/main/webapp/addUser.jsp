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
    <div class="container">
        <h4 class="display-6 text-left">Пожалуйста, введите нового пользователя:</h4>
        <div class="row">
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body">
                        <c:set var="book" value="${requestScope.get('add-user')}"/>
                        <form action="addUser" method="POST">
                            <input type="hidden" name="user_id" value="${book.getId()}">
                            <div class="form-group">
                                <label>Логин:</label>
                                <input type="text" name="user_login" value="${book.getLogin()}" class="form-control" placeholder="Введите имя">
                            </div>
                            <div class="form-group">
                                <label>Пароль:</label>
                                <input type="password" name="user_password" value="${book.getPassword()}" class="form-control" placeholder="Введите фамилию">
                            </div>
                            <div class="form-group">
                                <label>Роль:</label>
                                <c:forEach var="role" items="${sessionScope.get('roles')}">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="role" value="${role.getId()}">
                                        <label class="form-check-label">${role.getTitle()}</label>
                                    </div>
                                </c:forEach>
                            </div>
                            <button class="btn btn-outline-primary" name="save" value="save">Сохранить</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <br><br>
        <a href="/library/users"><i class="bi bi-arrow-left-circle"></i> Назад</a>
        <br><br>
    </div>
</div>
</body>
</html>