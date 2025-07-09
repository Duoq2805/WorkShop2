<%-- 
    Document   : login
    Created on : Jun 20, 2025, 8:19:25 PM
    Author     : ThaiDuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <title>Đăng nhập</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body class="container">
        <h1 class="mt-5 text-center">Đăng nhập</h1>
        <form action="LoginServlet" method="post" class="w-50 mx-auto">
            <div class="mb-3">
                <label class="form-label">Tài khoản:</label>
                <input type="text" name="username" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Mật khẩu:</label>
                <input type="password" name="password" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Đăng nhập</button>
        </form>

        <c:if test="${not empty sessionScope.loginError}">
            <p class="text-danger text-center">Sai tài khoản hoặc mật khẩu, thử lại đi!</p>
            <c:remove var="loginError" scope="session"/>
        </c:if>
    </body>
</html>
