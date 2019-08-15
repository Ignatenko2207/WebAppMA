<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
    <title>MAKING ORDER</title>
</head>
<body>

<h2>There are our products : </h2>

<!-- PROBLEMATIC CODE  -->
<sql:setDataSource
        var="shop_db_ma"
        driver="org.postgresql.Driver"
        url="jdbc:postgresql://localhost:5432/shop_db_ma"
        user="postgres" password="Max05012004"
/>

<sql:query var="list_items" dataSource="${shop_db_ma}">
    SELECT * FROM items;
</sql:query>

<br>
<br>
<c:set var="/item" value="item" scope="session"/>
<h3><c:out value="item.id"/> <c:out value="item.item_code"/> <c:out value="item.name"/> <c:out value="item.price"/></h3>

<%--<div align="center">--%>
<%--    <table border="1" cellpadding="5">--%>
<%--        <caption><h2>List of items</h2></caption>--%>
<%--        <tr>--%>
<%--            <th>ID</th>--%>
<%--            <th>Item_Code</th>--%>
<%--            <th>Name</th>--%>
<%--            <th>Price</th>--%>
<%--        </tr>--%>
<%--        <c:forEach var="items" items="${list_items.raws}">--%>
<%--            <tr>--%>
<%--                <td><c:out value="${items.id}" /></td>--%>
<%--                <td><c:out value="${items.item_code}" /></td>--%>
<%--                <td><c:out value="${items.name}" /></td>--%>
<%--                <td><c:out value="${items.price}" /></td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--    </table>--%>
<%--</div>--%>



</body>
</html>
