<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<form action="orderOperation" method="POST">
    <c:if test="${sessionScope.get('user').getRole().getTitle() eq 'user'}">
        <button name="orders" value="orders">Мои заказы</button>
        <br> <br>
    </c:if>

    <button name="showDetails" value="showDetails">Дополнительные данные</button>
    <br> <br>
</form>