<%-- 
    Document   : detail
    Created on : Jun 21, 2025, 11:31:44 AM
    Author     : Le Duy Khanh - CE190235
--%>

<%@page import="constant.CommonFunction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vnd" uri="http://example.com/common" %>

<c:set var="theReviewAddition" value="It is"/>

<%@include file="/WEB-INF/include/header.jsp" %>

<main>
  <div class="container mt-4">
    <div class="row">
      <!-- Hình ảnh bên trái -->
      <div class="col-md-6">
        <c:if test="${not empty product.image}">
          <img
            src="${pageContext.request.contextPath}/assets/images/${product.image}"
            alt="${product.name}"
            class="img-fluid rounded shadow"
            style="width: 100%; max-height: 500px; object-fit: cover;"
          />
        </c:if>
      </div>

      <!-- Thông tin bên phải -->
      <div class="col-md-6">
        <h2 class=" text-danger">${product.name}</h2>
        <form method="post" action="${pageContext.request.contextPath}/carts">
          <input type="hidden" name="id" value="${product.id}" />
          <input type="hidden" name="action" value="addToCart" />

          <table class="table table-sm">
            <tr>
              <th>ID</th>
              <td>${product.id}</td>
            </tr>
            <tr>
              <th>Mô tả</th>
              <td>${product.description}</td>
            </tr>
            <tr>
              <th>Danh mục</th>
              <td>
                <c:forEach var="c" items="${categories}">
                  <c:if test="${c.id == product.categoryId}">${c.name}</c:if>
                </c:forEach>
              </td>
            </tr>
            <tr>
              <th>Giá</th>
              <td>${vnd:toVND(product.price)}</td>
            </tr>
            <tr>
              <th>Số lượng hiện có</th>
              <td>
                <c:choose>
                  <c:when test="${product.stockQuantity <= 0}">
                    <strong class="text-danger">Hết hàng</strong>
                  </c:when>
                  <c:otherwise>${product.stockQuantity}</c:otherwise>
                </c:choose>
              </td>
            </tr>
            <tr>
              <th>Đơn vị</th>
              <td>${product.unit}</td>
            </tr>
            <tr>
              <th>Thương hiệu</th>
              <td>${product.brandName}</td>
            </tr>

            <c:if test="${product.stockQuantity > 0}">
              <tr>
                <th>Số lượng mua</th>
                <td>
                  <input
                    class="form-control border-info"
                    name="orderNumbers"
                    id="orderNumbers"
                    type="number"
                    required
                  />
                  <c:if test="${not empty error}">
                    <div class="alert alert-danger mt-2">${error}</div>
                  </c:if>
                </td>
              </tr>
            </c:if>

            <tr>
              <td colspan="2">
                <div>
                  <c:if test="${product.stockQuantity > 0}">
                    <button class="btn btn-warning" type="submit" name="action" value="detail">Add to Cart</button>
                  </c:if>
                  <a class="btn btn-dark" href="${pageContext.request.contextPath}/display?view=list"><<< Back</a>
                </div>
              </td>
            </tr>
          </table>
        </form>
      </div>
    </div>
  </div>
</main>


<%@include file="/WEB-INF/theReview/listProductDetail.jsp" %>             

<%@include file="/WEB-INF/include/footer.jsp" %>
