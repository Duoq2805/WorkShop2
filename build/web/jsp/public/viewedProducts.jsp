<%-- 
    Document   : viewedProducts
    Created on : Jul 10, 2025, 5:28:37 PM
    Author     : ThaiDuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/jsp/include/header.jspf"%>
<h1>Sản Phẩm Bạn Đã Xem</h1>
<c:if test="${empty viewedProducts}">
    <p>Bạn chưa xem sản phẩm nào hoặc chưa đăng nhập.</p>
</c:if>
<div class="viewed-products">
    <c:forEach var="product" items="${viewedProducts}">
        <div class="product">
            <h3>${product.name}</h3>
            <p>Giá: ${product.price} VNĐ</p>
            <p>Giảm giá: ${product.discount > 0 ? product.discount : 'Không có'}</p>
            <a href="productDetails?id=${product.id}">Xem chi tiết</a>
        </div>
    </c:forEach>
</div>
<a href="home">Quay lại trang chủ</a>
<%@include file="/jsp/include/footer.jspf"%>
