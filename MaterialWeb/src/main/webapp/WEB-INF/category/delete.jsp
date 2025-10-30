<%-- 
    Document   : delete
    Created on : Jul 20, 2025
    Author     : Nguyen Thanh Nhan - CE190122
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/include/header.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css"/>

<main>
    <div class="container">
        <h1 class="mt-5 text-danger">Xác nhận xóa danh mục</h1>

        <form method="post" action="${pageContext.request.contextPath}/category">
            <!-- Hidden fields to send action and id -->
            <input type="hidden" name="action" value="delete" />
            <input type="hidden" name="id" value="${category.id}" />

            <p>Bạn có chắc chắn muốn xóa danh mục sau không?</p>

            <table class="table table-bordered">
                <tr>
                    <th>ID</th>
                    <td>${category.id}</td>
                </tr>
                <tr>
                    <th>Tên danh mục</th>
                    <td>${category.name}</td>
                </tr>
                <tr>
                    <th>Số sản phẩm thuộc danh mục này</th>
                    <td>
                        <c:choose>
                            <c:when test="${productCount > 0}">
                                <span class="text-danger"><strong>${productCount}</strong> sản phẩm</span>
                                    </c:when>
                                    <c:otherwise>
                                <span class="text-success">0 sản phẩm</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>

                <tr>
                    <th><label for="name">Nhập đúng mật khẩu để xóa</label></th>
                    <td><input type="password" name="passworddelete" id="passworddelete" value="" required class="form-control"/></td>
                </tr>
                <c:if test="${not empty msg}">
                    <div class="alert alert-danger">${msg}</div>
                </c:if>
            </table>

            <c:choose>
                <c:when test="${productCount > 0}">
                    <div class="alert alert-warning">
                        <strong>Chú ý:</strong> Danh mục này chứa <strong>${productCount}</strong> sản phẩm.
                        Nếu bạn tiếp tục, tất cả sản phẩm sẽ bị xóa trước khi xóa danh mục này.
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-success">
                        Danh mục này không chứa sản phẩm nào. Bạn có thể xóa an toàn.
                    </div>
                </c:otherwise>
            </c:choose>


            <div class="mt-4">
                <button class="btn btn-danger" type="submit" name="action" value="delete">DELETE</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/category?view=list">Huỷ</a>
            </div>
        </form>
    </div>
</main>

<%@include file="/WEB-INF/include/footer.jsp" %>
