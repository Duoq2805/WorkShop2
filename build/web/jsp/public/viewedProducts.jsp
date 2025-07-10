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
    <p class="text-muted">Bạn chưa xem sản phẩm nào hoặc chưa đăng nhập.</p>
</c:if>

<div class="row viewed-products">
    <c:forEach var="product" items="${viewedProducts}">
        <div class="col-md-4 product">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h3>${product.productName}</h3>
                    <p>Giá: ${product.price} VNĐ</p>
                    <p>Giảm giá: 
                        <c:choose>
                            <c:when test="${product.discount > 0}">
                                ${product.discount}%
                            </c:when>
                            <c:otherwise>Không có</c:otherwise>
                        </c:choose>
                    </p>
                    <a href="${pageContext.request.contextPath}/product-detail?id=${product.productId}" class="btn btn-primary">Xem chi tiết</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<a href="${pageContext.request.contextPath}/home" class="btn btn-default">Quay lại trang chủ</a>
<%@include file="/jsp/include/footer.jspf"%>
