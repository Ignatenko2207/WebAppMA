<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="com.mainacad.service.ItemService" %>
<html>
<head>
    <title>MAKING ORDER</title>
</head>
<body>

<h2>There are our products : </h2>

<sql:setDataSource
        var="shop_db_ma"
        driver="org.postgresql.Driver"
        url="jdbc:postgresql://localhost:5432/shop_db_ma"
        user="postgres" password="Max05012004"
/>

<sql:query var="list_items" sql="SELECT * FROM items" dataSource="${shop_db_ma}"/>

<br>
<br>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of items</h2></caption>
        <tr>
            <th>ID</th>
            <th>Item_Code</th>
            <th>Name</th>
            <th>Price</th>
        </tr>
        <c:forEach var="rows" items="${list_items.rows}">
            <tr>
                <td><c:out value="${rows.id}" /></td>
                <td><c:out value="${rows.item_code}" /></td>
                <td><c:out value="${rows.name}" /></td>
                <td><c:out value="${rows.price}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>



</body>
</html>
