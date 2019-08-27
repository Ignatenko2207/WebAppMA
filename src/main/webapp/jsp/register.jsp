<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="styles.jsp" %>
    <title>Register</title>
</head>
<body>
<div class="container mt-2">
    <div class="jumbotron shadow col-sm">
        <form action="../auth" method="post" role="form">
            <legend>Registration</legend>
            <c:if test="${err}">
                <div class="alert alert-danger" role="alert">
                        ${errMsg}
                </div>
            </c:if>
            <input type="text" name="action" value="register" hidden>
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
            <div class="form-group">
                <label for="first-name">First name</label>
                <input type="text" name="first-name" id="first-name" class="form-control" placeholder="first name"
                       aria-describedby="fNameHelp" required>
                <small id="fNameHelp" class="text-muted">*required</small>
            </div>
            <div class="form-group">
                <label for="last-name">Last name</label>
                <input type="text" name="last-name" id="last-name" class="form-control" placeholder="last name"
                       aria-describedby="latsNameHelp" required>
                <small id="latsNameHelp" class="text-muted">*required</small>
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
            <a id="register" class="btn btn-secondary" href="../"
               role="button">Log in</a>
        </form>
    </div>
</div>
</body>
</html>
