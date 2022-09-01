<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
        <nav class="mr-md-auto display-6">
           <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'admin' || sessionScope.get('user').getRole().getTitle() eq 'librarian'}">
                <i class="bi bi-book"></i><a class="p-2 text-dark" href="/library/books">КНИГИ</a>
                <i class="bi bi-person"></i><a class="p-2 text-dark" href="/library/users">ПОЛЬЗОВАТЕЛИ</a>
                <i class="bi bi-people"></i><a class="p-2 text-dark" href="/library/authors">АВТОРЫ</a>
                <i class="bi bi-cart-plus"></i><a class="p-2 text-dark" href="/library/orders">ЗАКАЗЫ</a>
            </c:if>
            <form action="orderOperation" method="POST">
                <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'user'}">
                    <i class="bi bi-cart-plus"></i>
                    <button type="submit" name="orders" value="orders" style="color: black" class="btn btn-link">МОИ
                        ЗАКАЗЫ
                    </button>
                </c:if>
                <i class="bi bi-person"></i>
                <button name="showDetails" type="submit" value="showDetails" style="color: black" class="btn btn-link">
                    ПРОФИЛЬ
                </button>
            </form>
        </nav>

    <a class="btn btn-outline-danger my-2 my-md-0 mr-md-3" href="/library/logOut"><i
            class="bi bi-door-open"></i>Выйти</a>
</div>
<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">Добро пожаловать в библиотеку, <c:out value="${sessionScope.get('user').getLogin()}"/>!</h1>
    <p class="lead">Свобода существует затем, чтобы ходить в библиотеку. <i class="bi bi-chat-quote"></i> И.Бродский</p>
</div>