<%@ page contentType='text/html;charset=UTF-8' language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="creationTime" class="java.util.Date"/>
<jsp:setProperty name="creationTime" property="time" value="${cart.creationTime}"/>
<html>
<head>
    <%@include file="styles.jsp" %>
    <title>Cart</title>
</head>
<body>

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
            <li class="nav-item active">
                <a class="nav-link disabled" href="./cart">Cart</a>
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
    <div class="jumbotron pt-4 pb-4 shadow-sm">
        <c:if test="${cart==null}">
            <div class="alert alert-danger">
                <p class="display-4 d-inline">You don't have open cart!</p>
                <hr/>
                <p class="subTitle font-italic">It will be created automatically by adding items to it.</p>
                <a href="../items" class="btn btn-danger">Back to items</a>
            </div>
        </c:if>
        <c:if test="${ok}">
            <div class="alert alert-success alert-dismissible">
                <p class="display-4 d-inline">Your cart is confirmed!</p>
                <hr/>
                <p class="subTitle font-italic">Thank you for staying with us!</p>
                <a href="../items" class="btn btn-success">Back to items</a>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <c:if test="${cart!=null}">
        <div class="mr-1 ml-1">
            <div class="display-4 position-relative">
                <p class="d-inline-block">Cart</p>
                <c:if test="${cart.closed}">
                    <p class="text-secondary d-inline-block font-weight-light font-italic">(closed)</p>
                </c:if>
            </div>
            <p class="font-weight-light font-italic text-secondary position-absolute"
               style="margin-top: -20px; margin-left: 105px">
                id: ${cart.id},
                created at: <fmt:formatDate value="${creationTime}" pattern="MM/dd/yyyy HH:mm:ss"/>
            </p>
        </div>
        <div class="table-responsive table-striped">
            <table class="table col-sm-12">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Item name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Amount</th>
                    <th scope="col">Cost</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="orderItemList" items="${orderItemList}">
                    <c:set var="order" value="${orderItemList.get(0)}"/>
                    <c:set var="item" value="${orderItemList.get(1)}"/>
                    <tr>
                        <th scope="row">${order.id}</th>
                        <td>${item.name}</td>
                        <td>$${item.price}</td>
                        <td>
                            <c:if test="${!cart.closed}">
                                <form class="form-inline input-group" action="../order" method="post"
                                      style="max-width: 150px; min-width: 150px">
                                    <input class="form-control border-success" type="number" name="amount"
                                           value="${order.amount}">
                                    <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="oid" value="${order.id}">
                                    <input class="form-control input-group-append btn-outline-success" type="submit"
                                           value="upd">
                                </form>
                            </c:if>
                            <c:if test="${cart.closed}">
                                ${order.amount}
                            </c:if>
                        </td>
                        <td>$${item.price * order.amount}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <hr/>
        <div class="row justify-content-center">
            <div class="ml-2 mr-2 col-sm-12 rounded shadow bg-light">
                <p class="col-form-label font-italic">Total :&nbsp;<b>${orderItemList.size()}</b>&nbsp;
                    orders
                    for&nbsp;<b>$&nbsp;${total}</b></p>
                <c:if test="${!cart.closed}">
                    <form class="form-group" action="../cart" method="post">
                        <input class="form-control" type="hidden" name="action" value="confirm">
                        <input class="form-control" type="hidden" name="cid" value="${cart.id}">
                        <input class="btn-success form-control" type="submit"
                        <c:if test="${orderItemList.size()<=0}"> disabled </c:if> value="Confirm">
                    </form>
                </c:if>
            </div>
        </div>
    </div>
    </c:if>
</div>
</div>
</body>
</html>
