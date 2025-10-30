<%-- 
    Document   : update
    Created on : Jun 21, 2025, 11:31:44 AM
    Author     : Le Duy Khanh - CE190235
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/include/header.jsp" %>


<main>
    <div class="container">
        <h1 class="mt-5">UPDATE PRODUCT</h1>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/product" enctype="multipart/form-data">

            <table class="table">

                <tr>
                    <th><label for="id">ID</label></th>
                    <td><input type="text" name="id" id="id" value="${product.id}" readonly class="form-control"/></td>
                </tr>

                <tr>
                    <th><label for="name">Tên sản phẩm</label></th>
                    <td><input type="text" name="name" id="name" value="${product.name}" required class="form-control"/></td>
                </tr>

                <tr>
                    <th><label for="description">Mô tả</label></th>
                    <td><textarea name="description" id="description" rows="3" class="form-control" required>${product.description}</textarea></td>
                </tr>

                <tr>
                    <th><label for="categoryId">Danh mục</label></th>
                    <td>
                        <select name="categoryId" id="categoryId" class="form-control" required>
                            <c:forEach var="cat" items="${categoryList}">
                                <option value="${cat.id}" ${cat.id == product.categoryId ? "selected" : ""}>${cat.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>


                <tr>
                    <th><label for="price">Giá</label></th>
                    <td><input type="number" name="price" id="price" value="${product.price}" required class="form-control" min="0"/></td>
                </tr>

                <tr>
                    <th><label for="stockQuantity">Số lượng</label></th>
                    <td><input type="number" name="stockQuantity" id="stockQuantity" value="${product.stockQuantity}" required class="form-control" min="0"/></td>
                </tr>

                <tr>
                    <th><label for="unit">Đơn vị</label></th>
                    <td><input type="text" name="unit" id="unit" value="${product.unit}" required class="form-control"/></td>
                </tr>

                <tr>
                    <th><label for="brandName">Thương hiệu</label></th>
                    <td><input type="text" name="brandName" id="brandName" value="${product.brandName}" class="form-control"/></td>
                </tr>
                <tr>
                    <th><label for="images">Ảnh mới (tuỳ chọn)</label></th>
                    <td>
                        <input type="file" name="img" accept=".png, .jpg, .jpeg" class="form-control" id="img"/>
                        <p>Ảnh hiện tại: 
                            <img src="${pageContext.request.contextPath}/assets/images/${product.image}" alt="Current Image" width="100"/>
                        </p>

                    </td>
                </tr>

                <tr>
                    <td></td>
                    <td>
                        <button class="btn btn-outline-dark" type="submit" name="action" value="update"
                                onclick="return confirm('Bạn có chắc muốn lưu thay đổi không?')">Save</button>

                        <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/product?view=list">Cancel</a>
                    </td>
                </tr>

            </table>
        </form>
    </div>
</main>

<%@include file="/WEB-INF/include/footer.jsp" %>
