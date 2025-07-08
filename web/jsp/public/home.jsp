<%-- 
    Document   : home
    Created on : Jul 8, 2025, 8:46:20 AM
    Author     : ThaiDuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/jsp/include/header.jspf"%>

<h2>Trang chủ - Sản phẩm nổi bật</h2>
<div class="product-list">
    <c:forEach var="p" items="${featuredProducts}">
        <div class="product-item">
            <a href="detail?pid=${p.id}">
                <img src="${p.image}" alt="${p.name}" />
                <h3>${p.name}</h3>
                <p>Giá: ${p.price} VNĐ</p>
                <c:if test="${p.salePrice < p.price}">
                    <p style="color:red;">Giá KM: ${p.salePrice} VNĐ</p>
                </c:if>
            </a>
        </div>
    </c:forEach>
</div>

<%@include file="/jsp/include/footer.jspf"%>

