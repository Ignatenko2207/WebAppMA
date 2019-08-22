<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="styles.jsp"></c:import>
    <title></title>
</head>
<body>
<c:set var="item" value="${requestScope.get('item')}"/>

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top pt-md-0 pb-md-0">
    <a class="navbar-brand" href="#">
        <span class="text-uppercase">MY</span>
        <span class="text-uppercase font-weight-light">SHOP &caret;</span>
        <span class="small">@${sessionScope.get("user-name")}</span>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-end mt-2" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="./items">Items<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="./cart">Cart</a>
            </li>
            <li class="nav-item">
                <form class="form-inline" action="../auth" method="post">
                    <input class=" form-control mr-sm-2" type="hidden" name="action" value="logout">
                    <button class="btn btn-outline-light" type="submit">Log Out</button>
                </form>
            </li>
        </ul>
    </div>
</nav>

<div class="container" style="margin-top: 65px">
    <div class="jumbotron ml-0 mr-0 shadow-sm">
        <h1 class="display-3">${item.name}</h1>
        <p class="lead">price: $${item.price}</p>
        <hr class="my-2">
        <p>Item id:${item.id}</p>
        <p>Item code:${item.itemCode}</p>
        <div class="row">
            <form class="input-group ml-auto col-lg-5 col-md-7 " action="../order" method="post">
                <div class="input-group-text input-group-prepend">Amount</div>
                <input type="hidden" class="form-control" name="action" value="add_to_cart"/>
                <input type="hidden" class="form-control" name="item_id" value="${item.id}"/>
                <input type="number" name="amount" class="form-control" aria-label="Items amount"
                       aria-describedby="button-addon2" value="1">
                <div class="input-group-append">
                    <input class="btn btn-success" type="submit" value="Add to Cart" id="button-addon2">
                    <a class="btn btn-primary input-group-append" href="./items#<c:out value="item${item.id}"/>"
                       role="button">Back</a>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
