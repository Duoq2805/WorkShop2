<%-- 
    Document   : search
    Created on : Jul 8, 2025, 8:52:15 AM
    Author     : ThaiDuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/jsp/include/header.jspf"%>

<h2>Kết quả tìm kiếm: '${param.keyword}'</h2>

<form action="search" method="get">
    <input type="text" name="keyword" value="${param.keyword}">
    <input type="submit" value="Tìm kiếm">
</form>

<c:if test="${empty searchResult}">
    <p>Không tìm thấy sản phẩm phù hợp.</p>
</c:if>

<div class="product-list">
    <c:forEach var="p" items="${searchResult}">
        <div class="product-item">
            <a href="jsp/public/product-detail.jsp?id=${p.productId}">
                <img src="${p.productImage}" alt="${p.productName}" />
                <h3>${p.productName}</h3>
                <p>Giá: ${p.price} VNĐ</p>
                <c:if test="${p.discount > 0}">
                    <p style="color:red;">Giá KM: ${p.price - (p.price * p.discount / 100)} VNĐ</p>
                </c:if>
            </a>
        </div>
    </c:forEach>
</div>

<%@include file="/jsp/include/footer.jspf"%>

