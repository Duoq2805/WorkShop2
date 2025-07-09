<%-- 
    Document   : products
    Created on : Jun 20, 2025, 8:15:08 PM
    Author     : ThaiDuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.dao.ProductDAO, model.Product, java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String typeId = request.getParameter("typeId");
    ProductDAO dao = new ProductDAO();
    List<Product> products;

    if (typeId != null && !typeId.isEmpty()) {
        products = dao.listByCategory(typeId); // lọc theo danh mục
    } else {
        products = dao.listAll(); // tất cả
    }

    request.setAttribute("products", products);
%>
<%@include file="/jsp/include/header.jspf"%>
<link rel="stylesheet" href="css/products.css">
<h1 class="text-center">Danh sách sản phẩm</h1>

<div class="text-start mb-4">
    <a href="home" class="btn btn-secondary">
        <i class="glyphicon glyphicon-arrow-left"></i> Quay lại trang chu
    </a>
</div>

<div class="row">
    <c:forEach var="p" items="${products}">
        <div class="col-sm-6 col-md-4 mb-4">
            <div class="product-card">
                <c:set var="img" value="${fn:replace(p.productImage, '.pnj', '.png')}" />
                <img src="${pageContext.request.contextPath}${img}" class="product-image" alt="${p.productName}">
                <div class="product-body">
                    <h5 class="product-title">${p.productName}</h5>
                    <p class="product-brief">
                        <c:choose>
                            <c:when test="${p.brief != null and not empty p.brief}">
                                ${fn:substring(p.brief, 0, 100)}...
                            </c:when>
                            <c:otherwise>
                                Không có mô tả
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p class="product-price">
                        <fmt:formatNumber value="${p.price - (p.price * p.discount / 100)}" type="number"/> VNĐ
                        <span class="text-muted small"> (Giảm ${p.discount}%)</span>
                    </p>
                    <a href="jsp/public/product-detail.jsp?id=${p.productId}" class="btn btn-primary btn-block">Xem chi tiết</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<%@include file="/jsp/include/footer.jspf"%>

