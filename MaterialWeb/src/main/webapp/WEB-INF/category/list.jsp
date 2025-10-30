<%-- 
    Document   : list
    Created on : Jul 11, 2025, 10:37:07 AM
    Author     : Nguyen Thanh Nhan - CE190122
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<!DOCTYPE html>
<html>
<head>
    <title>Danh mục</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <%@include file="/WEB-INF/include/header.jsp" %>

    <div class="container py-5">
        <h2 class="mb-4 text-center text-primary">LIST CATEGORY</h2>

        <!-- Tìm kiếm và thêm -->
        <div class="d-flex justify-content-end gap-2 align-items-center mb-3">
            <form class="d-flex align-items-center gap-2" method="get" action="${pageContext.request.contextPath}/category">
                <input type="hidden" name="view" value="list" />
                <input type="text" name="keyword" class="form-control form-control-sm" style="width: 250px;"
                       placeholder="Tìm danh mục..." value="${param.keyword}" />
                <button class="btn btn-outline-primary btn-sm" type="submit">🔍 SEARCH</button>
            </form>

            <a href="${pageContext.request.contextPath}/category?view=create" class="btn btn-success btn-sm">
                + ADD CATEGORY
            </a>
        </div>

        <!-- Danh sách danh mục -->
        <div class="row">
            <c:choose>
                <c:when test="${not empty list}">
                    <table class="table table-bordered table-hover table-striped">
                        <thead class="table-primary">
                            <tr>
                                <th>ID</th>
                                <th>Category</th>
                                <th>Acction</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="c" items="${list}">
                                <tr>
                                    <td>${c.id}</td>
                                    <td>${c.name}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/category?view=update&id=${c.id}" class="btn btn-primary btn-sm">✏️ EDIT</a>
                                        <a href="${pageContext.request.contextPath}/category?view=delete&id=${c.id}" class="btn btn-danger btn-sm"
                                           onclick="return confirm('Bạn có chắc muốn xoá danh mục này?');">🗑️ DELETE</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-warning text-center">EMPTY.</div>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Phân trang -->
        <c:if test="${totalPages > 1}">
                <nav>
                    <ul class="pagination justify-content-center">
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${i == page ? 'active' : ''}">
                                <a class="page-link" href="${pageContext.request.contextPath}/category?view=list&page=${i}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </c:if>
    </div>

    <%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>

