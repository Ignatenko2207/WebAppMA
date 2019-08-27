<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <%@ include file="styles.jsp" %>
    <title>Login</title>
</head>
<body>

<div class="container  mt-2">
    <div class="jumbotron  shadow">
        <form action="/auth" method="post">
            <legend>Authorization</legend>
            <c:if test="${err}">
                <div class="alert alert-danger" role="alert">
                        ${errMsg}
                </div>
            </c:if>
            <input type="text" name="action" value="login" hidden>
            <div class="form-group">
                <label for="login">Login</label>
                <input type="text" name="login" id="login" class="form-control" placeholder="login"
                       aria-describedby="helpLogin" required>
                <small id="helpLogin" class="text-muted">*required</small>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" name="password" id="password" placeholder="password"
                       required>
                <small id="helpPass" class="text-muted">*required</small>
            </div>
            <button type="submit" class="btn btn-primary">Log in</button>
            <a id="register" class="btn btn-secondary" href="../register"
               role="button">Register</a>
        </form>

    </div>
</div>
</body>
</html>
