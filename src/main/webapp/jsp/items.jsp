<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Store</title>
</head>
<body>


<c:set var="user" value="${user}" scope="session" />
<h3>Hello, <c:out value="${user.firstName}" /> <c:out value="${user.lastName}" /> </h3>

<br>
<a href="<c:url value="/jsp/MakeOrder.jsp"/>"> Make Order !!!!</a>

<br>
<br>

<a href="<c:url value="/index.jsp"/>">Exit from account</a>

</body>
</html>
