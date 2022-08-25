<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>


<table>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Phone</th>
        <th>Address</th>

    </tr>

    <tr>
        <input type="hidden" name="user_id" value="${userDetails.getUserId()}">
        <td><c:out value="${userDetails.getName()}"/></td>
        <td><c:out value="${userDetails.getSurname()}"/></td>
        <td><c:out value="${userDetails.getPhone()}"/></td>
        <td><c:out value="${userDetails.getAddress()}"/></td>
    </tr>

</table>

<a href="/library/users">Back</a>