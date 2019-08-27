<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="styles.jsp" %>
    <script src="/js/holder.js"></script>
    <title>Items</title>
</head>
<body class="p0">
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top pt-md-0 pb-md-0">
    <a class="navbar-brand" href="#">
        <span class="text-uppercase">MY</span>
        <span class="text-uppercase font-weight-light">SHOP</span>
        <span class="small">@${sessionScope.get("user-name")}</span>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-end mt-2" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link disabled" href="">Items<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item mr-2">
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

<div class="container" style="margin-top: 63px">

    <div class="row">
        <c:forEach items="${items}" var="item">
            <div class="p-1 col-sm-12 col-md-6 col-lg-4">
                <div id="item${item.id}" class="card card p-0 shadow-sm">
                    <a href="?item_id=${item.id}"><img class="card-img-top" src="holder.js/100px300"
                                                       alt="Card image cap"></a>
                    <div class="card-body">
                        <h4 class="card-title"><a href="?item_id=${item.id}" class="text-dark">${item.name}</a></h4>
                        <p class="card-text">$${item.price}</p>
                        <div class="row justify-content-end mr-1">
                            <form class="input-group small ml-auto" action="../order" method="post"
                                  style="width: 16rem">
                                <div class="input-group-prepend input-group-text">Amount</div>
                                <input type="hidden" class="form-control" name="action" value="add_to_cart"/>
                                <input type="hidden" class="form-control" name="item_id" value="${item.id}"/>
                                <input type="number" name="amount" class="form-control" aria-label="Items amount"
                                       aria-describedby="button-addon2" value="1">
                                <div class="input-group-append">
                                    <input class="btn btn-success" type="submit" value="Add to Cart" id="button-addon2">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</div>
</body>
</html>
