<%-- 
    Document   : list
    Created on : Jul 8, 2025, 8:51:47 AM
    Author     : ThaiDuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/jsp/include/header.jspf"%>

<h2>Sản phẩm</h2>

<form method="get" action="list">
    Min Price: <input type="number" name="min" value="${param.min}">
    Max Price: <input type="number" name="max" value="${param.max}">
    <input type="checkbox" name="sale" value="true" <c:if test="${param.sale eq 'true'}">checked</c:if>> Có giảm giá
    Sắp xếp:
    <select name="sort">
        <option value="">--Chọn--</option>
        <option value="asc" <c:if test="${param.sort eq 'asc'}">selected</c:if>>Giá tăng</option>
        <option value="desc" <c:if test="${param.sort eq 'desc'}">selected</c:if>>Giá giảm</option>
    </select>
    <input type="submit" value="Lọc">
</form>

<div class="product-list">
    <c:forEach var="p" items="${productList}">
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

