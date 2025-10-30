<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
    <head>
        <title>Sản phẩm</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <%@include file="/WEB-INF/include/header.jsp" %>
    <body class="bg-light">

        <div class="container py-5">
            <h2 class="mb-4 text-center text-primary">Danh sách sản phẩm</h2>

            <div class="d-flex  d-flex justify-content-end gap-2 align-items-center mb-3">
                <form class="d-flex align-items-center gap-2" method="get" action="${pageContext.request.contextPath}/product">
                    <input type="hidden" name="view" value="list" />
                    <input type="text" name="keyword" class="form-control form-control-sm" style="width: 250px;" 
                           placeholder="Tìm sản phẩm..." value="${param.keyword}" />
                    <button class="btn btn-outline-primary btn-sm" type="submit">🔍 Tìm</button>
                </form>

                <a href="${pageContext.request.contextPath}/product?view=create" class="btn btn-success btn-sm">
                    + Thêm sản phẩm
                </a>
            </div>



            <div class="row">
                <c:choose>
                    <c:when test="${not empty list}">
                        <c:forEach var="p" items="${list}">
                            <div class="col-md-4 mb-4">
                                <div class="card h-100 shadow-sm">
                                      <c:if test="${not empty p.image}">
        <img src="${pageContext.request.contextPath}/assets/images/${p.image}" class="card-img-top" alt="${p.name}" style="height: 200px; object-fit: contain;">
    </c:if>
                                    <div class="card-body">
                                        <h5 class="card-title text-success">${p.name}</h5>
                                        <p class="card-text">${p.description}</p>
                                        <ul class="list-group list-group-flush small">
                                            <li class="list-group-item">Danh mục: ${p.categoryId}</li>
                                            <li class="list-group-item">
                                                Giá:
                                                <strong>
                                                    <fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/> VNĐ
                                                </strong>
                                            </li>
                                            <li class="list-group-item">Số lượng: ${p.stockQuantity} ${p.unit}</li>
                                            <li class="list-group-item">Thương hiệu: ${p.brandName}</li>
                                        </ul>
                                        <div class="mt-3 d-flex justify-content-start gap-2">
                                            <a href="${pageContext.request.contextPath}/product?view=update&id=${p.id}" class="btn btn-primary btn-sm">✏️ EDIT</a>
                                            <a href="${pageContext.request.contextPath}/product?view=delete&id=${p.id}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xoá sản phẩm này?');">🗑️ DELETE</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-warning text-center">Không có sản phẩm nào.</div>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- PHÂN TRANG -->
            <c:if test="${totalPages > 1}">
                <nav>
                    <ul class="pagination justify-content-center">
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${i == page ? 'active' : ''}">
                                <a class="page-link" href="${pageContext.request.contextPath}/product?view=list&page=${i}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </c:if>
        </div>

        <%@include file="/WEB-INF/include/footer.jsp" %>
    </body>
</html>
