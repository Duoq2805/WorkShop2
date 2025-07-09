<%-- 
    Document   : home
    Created on : Jul 8, 2025, 8:46:20 AM
    Author     : ThaiDuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/jsp/include/header.jspf"%>


<h2 class="text-center mb-4">📁 Danh mục sản phẩm</h2>
        
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

<%@include file="/jsp/include/footer.jspf"%>

