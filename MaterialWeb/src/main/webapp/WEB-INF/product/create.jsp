<%-- 
    Document   : create
    Created on : Jun 20, 2025, 9:10:54 PM
    Author     : Le Duy Khanh - CE190235
--%>

<%@page import="dao.ProductDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/include/header.jsp" %>

<main>
    <div class="container">
        <h1 class="mt-5">Add New Product</h1>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/product" enctype="multipart/form-data">
            <table class="table">

                <tr>
                    <td><label for="name">Tên sản phẩm</label></td>
                    <td><input class="form-control" type="text" name="name" id="name" value="${product.name}" required></td>
                </tr>

                <tr>
                    <td><label for="description">Mô tả</label></td>
                    <td><textarea class="form-control" name="description" id="description" rows="3">${product.description}</textarea></td>
                </tr>

                <tr>
                    <td><label for="categoryId">Danh mục</label></td>
                    <td>
                        <select class="form-select" name="categoryId" id="categoryId" required>
                            <c:forEach var="cat" items="${categoryList}">
                                <option value="${cat.id}">${cat.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>


                <tr>
                    <td><label for="price">Giá</label></td>
                    <td><input min="1" class="form-control" type="number" name="price" id="price" value="${product.price}" required></td>
                </tr>

                <tr>
                    <td><label for="stockQuantity">Số lượng</label></td>
                    <td><input min="1" class="form-control" type="number" name="stockQuantity" id="stockQuantity" value="${product.stockQuantity}" required></td>
                </tr>

                <tr>
                    <td><label for="unit">Đơn vị</label></td>
                    <td><input class="form-control" type="text" name="unit" id="unit" value="${product.unit}" required></td>
                </tr>

                <tr>
                    <td><label for="brandName">Thương hiệu</label></td>
                    <td><input class="form-control" type="text" name="brandName" id="brandName" value="${product.brandName}"></td>
                </tr>

                <tr>
                    <td><label for="cover">Ảnh</label></td>
                    <td><input type="file" name="img" accept=".png, .jpg, .jpeg" 
                               class="form-control" id="cover"/></td>
                </tr>

                <tr>
                    <td></td>
                    <td>
                        <button class="btn btn-outline-dark" type="submit" name="action" value="create">Save</button>
                        <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/product">Cancel</a>
                    </td>
                </tr>

            </table>
        </form>
    </div>
</main>

<%@include file="/WEB-INF/include/footer.jsp" %>

