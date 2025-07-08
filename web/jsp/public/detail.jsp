<%-- 
    Document   : detail
    Created on : Jul 8, 2025, 8:52:49 AM
    Author     : ThaiDuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/jsp/include/header.jspf"%>

<h2>Chi tiết sản phẩm</h2>

<div class="product-detail">
    <img src="${product.image}" alt="${product.name}">
    <h3>${product.name}</h3>
    <p>Giá: ${product.price} VNĐ</p>
    <c:if test="${product.salePrice < product.price}">
        <p style="color:red;">Giá KM: ${product.salePrice} VNĐ</p>
    </c:if>
    <p>Mô tả: ${product.description}</p>
</div>

<hr>
<h4>Phân khúc của bạn: ${segment}</h4>

<hr>
<h3>Sản phẩm đã xem</h3>
<c:forEach var="p" items="${viewedProducts}">
    <div class="product-item">
        <a href="detail?pid=${p.id}">
            <img src="${p.image}" alt="${p.name}" />
            <p>${p.name}</p>
        </a>
    </div>
</c:forEach>

<%@include file="/jsp/include/footer.jspf"%>

