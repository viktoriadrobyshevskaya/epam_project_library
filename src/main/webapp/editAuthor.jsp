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

                        <c:set var="author" value="${requestScope.get('edit-author')}"/>
                        <form action="editAuthor" method="POST">

                            <input type="hidden" name="author_id" value="${author.getId_author()}">

                            <div class="form-group">
                                <label>Имя:</label>
                                <input type="text" name="author_name" value="${author.getName()}" class="form-control"
                                       placeholder="">
                            </div>

                            <div class="form-group">
                                <label>Отчество:</label>
                                <input type="text" name="author_middleName" value="${author.getMiddleName()}"
                                       placeholder="" class="form-control">
                            </div>

                            <div class="form-group">
                                <label>Фамилия:</label>
                                <input type="text" name="author_surname" value="${author.getSurname()}" placeholder=""
                                       class="form-control">
                            </div>

                            <div class="form-group">
                                <label>Год рождения:</label>
                                <input name="author_year" value="${author.getYearOfBirth()}" placeholder="" type="text"
                                       class="form-control">
                            </div>

                            <button class="btn btn-outline-primary" name="save" value="save">Сохранить</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <br><br>
        <a href="/library/authors"><i class="bi bi-arrow-left-circle"></i> Назад</a>
        <br><br>
    </div>
</div>
</body>
</html>