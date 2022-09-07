<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <div class="container">
        <h1 class="display-4 text-left">Пожалуйста, войдите в систему</h1>
    </div>
</div>

<div class="container">

    <c:if test="${not empty problem}">
        <div class="alert alert-danger text-center" role="alert">
            <h4 class="alert-heading">Ошибка</h4>
            <hr>
            <p><c:out value="${problem}"/></p>
        </div>
    </c:if>

    <h4 class="display-6 text-left">Пожалуйста, введите нового пользователя:</h4>
    <div class="row">
        <div class="col-sm-6">
            <div class="card">
                <div class="card-body">

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger text-center" role="alert">
                            <h4 class="alert-heading">Ошибка</h4>
                            <hr>
                            <p><c:out value="${error}"/></p>
                        </div>
                    </c:if>

                    <form action="login" method="POST">
                        <div class="form-group">
                            <i class="bi bi-person"></i>
                            <label for="formGroupExampleInput">Логин пользователя:</label>
                            <input required name="loginInput" type="text" class="form-control" id="formGroupExampleInput"
                                   placeholder="введите логин">
                        </div>
                        <div class="form-group">
                            <i class="bi bi-key"></i>
                            <label for="formGroupExampleInput2">Пароль пользователя:</label>
                            <input required name="passwordInput" type="password" class="form-control" id="formGroupExampleInput2"
                                   placeholder="введите пароль">
                        </div>
                        <div class="form-group">
                            <button class="btn btn-outline-primary" name="buttonSubmit" type="submit">Войти</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>