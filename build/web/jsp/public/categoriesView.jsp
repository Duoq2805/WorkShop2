<%-- 
    Document   : categories-view
    Created on : Jun 24, 2025, 1:55:42 AM
    Author     : ThaiDuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh mục</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/categories.css">
    </head>
    <body class="container mt-5">
        <h2 class="text-center mb-4">📁 Danh mục sản phẩm</h2>
        
        <div class="text-start mb-4">
            <a href="index.jsp" class="btn btn-secondary">
                <i class="glyphicon glyphicon-arrow-left"></i> Quay lại trang chủ
            </a>
        </div>
        
        <div class="row">
            <c:forEach var="c" items="${listCategory}">
                <div class="col-sm-6 col-md-4 mb-3">
                    <div class="category-card">
                        <a class="category-link" href="productPortfolio?typeId=${c.typeId}">
                            ${c.categoryName}
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
