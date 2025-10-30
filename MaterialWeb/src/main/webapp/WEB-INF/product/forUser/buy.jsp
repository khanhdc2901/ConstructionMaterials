<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<main class="container mt-4">
    <h2 class="mb-3">Xác nhận đơn hàng</h2>

    <form method="post" action="${pageContext.request.contextPath}/confirmBuy">
        <c:if test="${not empty selectedSale}">
            <input type="hidden" name="selectedSaleId" value="${selectedSale.id}" />
        </c:if>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Tên sản phẩm</th>
                    <th>Số lượng</th>
                    <th>Đơn giá</th>
                    <th>Thành tiền</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${selectedItems}">
                    <tr>
                        <td>${item.product.name}</td>
                        <td>${item.quantity}</td>
                        <td>${item.product.price}</td>
                        <td>${item.product.price * item.quantity} VND</td>
                    </tr>
                    <input type="hidden" name="productIds" value="${item.product.id}" />
                    <input type="hidden" name="quantities" value="${item.quantity}" />
                </c:forEach>

                <c:if test="${not empty selectedSale}">
                    <tr>
                        <td colspan="1" class="alert-danger"><strong>Khuyến mãi:</strong></td>
                        <td colspan="3" class="alert-danger text-center">
                            <strong>${selectedSale.name} (Giảm giá: ${selectedSale.currentDiscount})</strong>
                        </td>
                    </tr>
                </c:if>

                <tr>
                    <td colspan="1" style="text-align:left"><strong>Tổng tiền:</strong></td>
                    <td colspan="3" style="text-align:center"><strong>${calculatedTotal} VND</strong></td>
                </tr>
            </tbody>
        </table>

        <div class="d-flex gap-2 mb-3 ms-auto justify-content-center">
            <button class="btn btn-success" type="submit"><i class="fas fa-check"></i></button>
            <a href="${pageContext.request.contextPath}/carts" class="btn btn-danger"><i class="fa fa-x"></i></a>
        </div>
    </form>
</main>

<%@include file="/WEB-INF/include/footer.jsp" %>
