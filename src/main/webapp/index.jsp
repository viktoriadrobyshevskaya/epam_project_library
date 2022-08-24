<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>

<h3> Welcome to Victory Library </h3>

<jsp:include page="users.jsp"></jsp:include>
<jsp:include page="books.jsp"></jsp:include>
<jsp:include page="authors.jsp"></jsp:include>

</body>
</html>
