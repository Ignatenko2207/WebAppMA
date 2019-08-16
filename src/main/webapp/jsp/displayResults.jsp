<%--
  Created by IntelliJ IDEA.
  User: 38098
  Date: 16.08.2019
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>

<h1>You have selected </h1>

<%
    String message="";
    String values[] = request.getParameterValues("CheckBoxGroup");

    if(values!=null) {

        for (int i=0;i<values.length;i++) {
        message += "<BR/>" + values[i];
    }
}
    response.setContentType("text/html");
%>
<%= message %>

</body>
</html>
