<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en"  class="mdl-js">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="<c:url value = '/static/img/favicon.ico'/>">

        <title>Our web-store application</title>

        <link href="<c:url value = '/static/css/bootstrap.min.css'/>" rel="stylesheet">
        <link href="<c:url value = '/static/css/index.css'/>" rel="stylesheet">
    </head>

    <body class="text-center">
        <form class="form-login" action="user" method="post">
            <img class="mb-4" src="<c:url value = '/static/img/login.svg'/>" alt="" width="72" height="72">
            <h1 class="h3 mb-3 font-weight-normal">Please, log in</h1>
            <input type="text" name="action" value="login" hidden>
            <label for="inputlogin" class="sr-only">Email address</label>
            <input type="text" name="login" id="inputlogin" class="form-control" placeholder="Input login" required autofocus>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Input password" required>
            <button class="btn btn-lg btn-dark btn-block" type="submit">Log in</button>
            <p class="mt-5 mb-3 text-muted">&copy; 2019</p>
        </form>
    </body>
</html>
