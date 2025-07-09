<%-- 
    Document   : product-detail
    Created on : Jun 21, 2025, 3:39:01 PM
    Author     : ThaiDuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Product, model.dao.ProductDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String id = request.getParameter("id");
    ProductDAO dao = new ProductDAO();
    Product product = dao.getObjectById(id);
    request.setAttribute("product", product);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chi tiết sản phẩm - ${product.productName}</title>

        <!-- Bootstrap 5 CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-detail.css">
    </head>
    <body>
        <div class="container">
            <c:choose>
                <c:when test="${product != null}">
                    <c:set var="discountedPrice" value="${product.price * (1 - product.discount / 100.0)}"/>

                    <div class="product-header">
                        <h1 class="product-title">${product.productName}</h1>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item active" aria-current="page">${product.productName}</li>
                            </ol>
                        </nav>
                    </div>

                    <div class="row">
                        <div class="col-lg-6">
                            <div class="product-image-container">
                                <img src="${pageContext.request.contextPath}${product.productImage}" 
                                     class="product-image img-fluid" 
                                     alt="${product.productName}"
                                     onerror="this.src='${pageContext.request.contextPath}/images/default-product.png'">
                            </div>
                        </div>

                        <div class="col-lg-6">
                            <div class="product-info">
                                <div class="price-section">
                                    <span class="current-price">
                                        <fmt:formatNumber value="${discountedPrice}" type="number" maxFractionDigits="0"/> VNĐ
                                    </span>

                                    <c:if test="${product.discount > 0}">
                                        <span class="original-price">
                                            <fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0"/> VNĐ
                                        </span>
                                        <span class="discount-badge">-${product.discount}%</span>
                                    </c:if>
                                </div>

                                <div class="product-meta">
                                    <div class="meta-item">
                                        <span class="meta-label"><i class="fas fa-box-open me-2"></i>Đơn vị:</span>
                                        <span class="meta-value">${product.unit}</span>
                                    </div>

                                    <div class="meta-item">
                                        <span class="meta-label"><i class="far fa-calendar-alt me-2"></i>Ngày đăng:</span>
                                        <span class="meta-value">
                                            <fmt:formatDate value="${product.postedDate}" pattern="dd/MM/yyyy"/>
                                        </span>
                                    </div>

                                    <div class="meta-item">
                                        <span class="meta-label"><i class="fas fa-user me-2"></i>Người đăng:</span>
                                        <span class="meta-value">${product.account}</span>
                                    </div>
                                </div>

                                <a href="productPortfolio?typeId=${product.typeId}" class="btn btn-back">
                                    <i class="fas fa-arrow-left me-2"></i>Quay lại danh sách
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-4">
                        <div class="col-12">
                            <div class="product-description">
                                <h3><i class="fas fa-info-circle me-2"></i>Mô tả sản phẩm</h3>
                                <p>${product.brief}</p>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-danger mt-5">
                        <i class="fas fa-exclamation-triangle me-2"></i>Sản phẩm không tồn tại hoặc đã bị xóa!
                    </div>
                    <a href="productPortfolio?typeId=${product.typeId}" class="btn btn-back mt-3">
                        <i class="fas fa-arrow-left me-2"></i>Quay lại danh sách
                    </a>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Bootstrap 5 JS Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
