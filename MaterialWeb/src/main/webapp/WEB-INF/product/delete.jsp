<%-- 
    Document   : delete
    Created on : Jun 21, 2025, 12:24:53 PM
    Author     : Le Duy Khanh - CE190235
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/include/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css"/>

<main>
    <div class="container">
        <h1 class="mt-5 text-danger">Xóa Sản Phẩm</h1>

        <form method="post" action="${pageContext.request.contextPath}/product">
            <input type="hidden" name="action" value="delete" />
            <input type="hidden" name="id" value="${product.id}" />

            <p>Bạn có chắc chắn muốn xóa sản phẩm này không?</p>

            <table class="table">
                <tr>
                    <th>ID</th>
                    <td>${product.id}</td>
                </tr>
                <tr>
                    <th>Tên sản phẩm</th>
                    <td>${product.name}</td>
                </tr>
                <tr>
                    <th>Mô tả</th>
                    <td>${product.description}</td>
                </tr>
                <tr>
                    <th>Giá</th>
                    <td>${product.price}</td>
                </tr>
                <tr>
                    <th>Số lượng</th>
                    <td>${product.stockQuantity}</td>
                </tr>
                <tr>
                    <th>Đơn vị</th>
                    <td>${product.unit}</td>
                </tr>
                <tr>
                    <th>Thương hiệu</th>
                    <td>${product.brandName}</td>
                </tr>
            </table>

            <button class="btn btn-danger" type="submit">Delete</button>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/product?view=list">Cancel</a>
        </form>
    </div>
</main>

<%@include file="/WEB-INF/include/footer.jsp" %>
