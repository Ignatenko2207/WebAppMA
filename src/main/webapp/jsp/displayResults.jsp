<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.*"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.mainacad.service.ItemService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>


<h1>You have selected </h1>

<%
String[] itemsSelected = request.getParameterValues("CheckBoxGroup");

    for (String temp : itemsSelected) {
        out.println("<li>" + temp + "<li>");
    }
%>

</body>
</html>
