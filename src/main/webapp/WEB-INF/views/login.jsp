<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value='/template/login/styles.css'/>" rel="stylesheet"/>
    <title>Login</title>
</head>
<body>
<div class="container">
    <!-- <h1 class="form-heading">login Form</h1> -->
    <div class="login-form">
        <div class="main-div">
            <c:if test="${param.incorrectAccount != null}">
                <div class="alert alert-danger">
                    Username or password incorrect
                </div>
            </c:if>
            <c:if test="${param.accessDenied != null}">
                <div class="alert alert-danger">
                    You do not have permission to access this page!
                </div>
            </c:if>
            <form class="box" action="j_spring_security_check" id="formLogin" method="post">
                <h1>Login</h1>
                <input type="text" name="j_username" placeholder="Username">
                <input type="password" name="j_password" placeholder="Password">
                <input type="submit" value="Login">

                <%--<input type="text" id="userName" name="j_username" placeholder="Username">
                <input type="password" id="password" name="j_password" placeholder="Password">
                <button type="submit" class="btn btn-primary">Login</button>--%>
            </form>
        </div>
    </div>
</div>
</body>
</html>