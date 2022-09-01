<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
        <c:choose>
            <c:when test="${userDetails != null}">
                <h3>Дополнительные данные об <span style="color: blue"><c:out
                        value="${userDetails.getUser().getLogin()}"/></span>: </h3>
                <table class="table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Имя</th>
                        <th scope="col">Фамилия</th>
                        <th scope="col">Телефон</th>
                        <th scope="col">Адрес</th>
                        <th scope="col">Операции</th>
                    </tr>
                    </thead>
                    <tbody>
                    <form action="userDetailsOperation" method="POST">
                        <tr>
                            <input type="hidden" name="id" value="${userDetails.getId()}">
                            <input type="hidden" name="user_id" value="${userDetails.getUserId()}">
                            <td><c:out value="${userDetails.getName()}"/></td>
                            <td><c:out value="${userDetails.getSurname()}"/></td>
                            <td><c:out value="${userDetails.getPhone()}"/></td>
                            <td><c:out value="${userDetails.getAddress()}"/></td>
                            <td>
                                <button class="btn btn-outline-primary" name="editDetails" value="editDetails">
                                    редактировать
                                </button>
                            </td>
                        </tr>
                    </form>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger text-center" role="alert">
                        <h4 class="alert-heading">Внимание</h4>
                        <hr>
                        <p><c:out value="${error}"/></p>
                    </div>
                </c:if>
                <c:set var="modifiedUserDetails" value="${requestScope.get('modifiedUserDetails')}"/>
                <c:choose>
                    <c:when test="${modifiedUserDetails != null}">
                        <h4 class="display-6 text-left">Пожалуйста, введите ваши данные:</h4>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="card">
                                    <div class="card-body">
                                        <form action="addUserDetails" method="POST">

                                            <input type="hidden" name="user_id"
                                                   value="${modifiedUserDetails.getUserId()}">

                                            <div class="form-group">
                                                <label>Имя</label>
                                                <input type="text" name="user_name"
                                                       value="${modifiedUserDetails.getName()}" class="form-control"
                                                       placeholder="Введите имя">
                                            </div>

                                            <div class="form-group">
                                                <label>Фамилия</label>
                                                <input type="text" name="user_surname"
                                                       value="${modifiedUserDetails.getSurname()}" class="form-control"
                                                       placeholder="Введите фамилию">
                                            </div>

                                            <div class="form-group">
                                                <label>Телефон</label>
                                                <input type="text" name="user_phone"
                                                       value="${modifiedUserDetails.getPhone()}"
                                                       placeholder="+375(44)********" class="form-control">
                                            </div>

                                            <div class="form-group">
                                                <label>Адрес</label>
                                                <input name="user_address" value="${modifiedUserDetails.getAddress()}"
                                                       placeholder="Минск, Минская 45-25" type="text"
                                                       class="form-control">
                                            </div>

                                            <button class="btn btn-outline-primary" name="save" value="save">Сохранить
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-warning" role="alert">
                            Нет данных о пользователе
                        </div>
                        <h4 class="display-6 text-left">Пожалуйста, введите ваши данные:</h4>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="card">
                                    <div class="card-body">
                                        <form action="addUserDetails" method="POST">
                                            <input type="hidden" name="user_id" value="${requestScope.get('user_ID')}">
                                            <div class="form-group">
                                                <label>Имя</label>
                                                <input type="text" name="user_name" value="" class="form-control"
                                                       placeholder="Введите имя">
                                            </div>
                                            <div class="form-group">
                                                <label>Фамилия</label>
                                                <input type="text" name="user_surname" value="" class="form-control"
                                                       placeholder="Введите фамилию">
                                            </div>
                                            <div class="form-group">
                                                <label>Телефон</label>
                                                <input type="text" name="user_phone" value=""
                                                       placeholder="+375(44)********" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label>Адрес</label>
                                                <input name="user_address" value="" placeholder="Минск, Минская 45-25"
                                                       type="text" class="form-control">
                                            </div>
                                            <button class="btn btn-outline-primary" name="save" value="save">Сохранить
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
        <br><br>
        <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">
            <a href="/library/users"><i class="bi bi-arrow-left-circle"></i> Вернуться ко всем
                пользователям </a><br>
            <a href="/library/books"><i class="bi bi-arrow-left-circle"></i> Вернуться на главную
                страницу </a><br>
        </c:if>
        <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'user'}">
            <a href="/library/books"><i class="bi bi-arrow-left-circle"></i> Назад</a><br>
        </c:if>
    </div>
</div>
</body>
</html>