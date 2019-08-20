<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>

<h1>There are item you choose: </h1>

<div class="col-xl-2 col-md-4 col-sm-6 col-12">
    <div class="card mb-4 shadow-sm">
        <div class="card-body">

            <h3 class="card-text"><c:import url="items.jsp"  /> <c:out value="${items.name}" /></h3>
            <p class="card-text"><b>Price: </b><c:import url="items.jsp" /> <c:out value="${items.price}" />
                <span  class="float-right"><b>code: </b><c:import url="items.jsp" /> <c:out value="${items.item_code}" />
            </p>

            <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                </div>
                <small class="text-muted d-none">9 mins</small>
            </div>
        </div>
    </div>
</div>

<a href=<c:url value="FinalPage.jsp" />> Buy it!</a>

</body>
</html>