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
        <h4 class="display-6 text-left">Введите новые данные:</h4>
        <div class="row">
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body">
                        <c:set var="user" value="${requestScope.get('edit-user')}"/>
                        <form action="editUser" method="POST">
                            <input type="hidden" name="user_id" value="${user.getId()}">
                            <div class="form-group">
                                <label>Введите новый логин:</label>
                                <input type="text" name="user_login" value="${user.getLogin()}" class="form-control" placeholder="Введите логин">
                            </div>
                            <div class="form-group">
                                <label>Введите новый пароль:</label>
                                <input type="password" name="user_password" value="${user.getPassword()}" class="form-control" placeholder="Введите пароль">
                            </div>
                            <div class="form-group">
                                <label>Роль:</label>
                                <select name="role" class="form-control">
                                <c:forEach var="role" items="${sessionScope.get('roles')}">
                                        <option value="${role.getId()}">${role.getTitle()}</option>
                                </c:forEach>
                                </select>
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