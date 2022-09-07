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
        <h4 class="display-6 text-left">Пожалуйста, введите новые данные:</h4>
        <div class="row">
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body">
                        <c:set var="userDetails" value="${requestScope.get('edit-userDetail')}"/>
                        <form action="editUserDetails" method="POST">
                            <input type="hidden" name="id" value="${userDetails.getId()}">
                            <input type="hidden" name="user_id" value="${userDetails.getUserId()}">
                            <div class="form-group">
                                <label>Имя:</label>
                                <input type="text" name="user_name" value="${userDetails.getName()}" class="form-control" placeholder="Введите имя">
                            </div>
                            <div class="form-group">
                                <label>Фамилия:</label>
                                <input  type="text" name="user_surname" value="${userDetails.getSurname()}" class="form-control" placeholder="Введите фамилию">
                            </div>
                            <div class="form-group">
                                <label>Телефон:</label>
                                <input type="text" name="user_phone" value="${userDetails.getPhone()}" placeholder="+375(44)********" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Адрес:</label>
                                <input name="user_address" value="${userDetails.getAddress()}" placeholder="Минск, Минская 45-25" type="text" class="form-control">
                            </div>
                            <button class="btn btn-outline-primary" name="save" value="save">Сохранить</button>
                            <form action="orderOperation" method="POST">
                                <button class="btn btn-outline-danger" name="showDetails" value="showDetails"> Отмена</button>
                            </form>
                        </form>
                    </div>
                    <a href="/library/books"><i class="bi bi-arrow-left-circle"></i> На главную</a> <br>
                    <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">
                        <a href="/library/users"><i class="bi bi-arrow-left-circle"></i> Вернуться на Пользователи</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>