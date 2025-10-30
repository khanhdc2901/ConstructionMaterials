<%-- 
    Document   : orderList  
    Created on : Jul 18, 2025, 12:29:15 PM
    Author     : Tieu Gia Huy - CE191594
--%>

<%!
    public String progressClass(String status) {
        switch (status) {
            case "Đang xử lý":
                return "bg-info";
            case "Đang vận chuyển":
                return "bg-primary";
            case "Hoàn thành":
                return "bg-success";
            case "Đã hủy":
                return "bg-danger";
            case "Đã trả hàng":
                return "bg-warning";
            default:
                return "bg-secondary";
        }
    }

    public int progressWidth(String status) {
        switch (status) {
            case "Đang xử lý":
                return 50;
            case "Đang vận chuyển":
                return 75;
            case "Hoàn thành":
                return 100;
            case "Đã hủy":
                return 0;
            case "Đã trả hàng":
                return 100;
            default:
                return 0;
        }
    }
%>


<%@ page import="java.util.*, model.OrderItem" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/include/header.jsp" %>

<div class="container mt-4">
    <h2 class="mb-3">Danh sách đơn hàng</h2>
    <%
        List<OrderItem> orderList = (List<OrderItem>) request.getAttribute("orderList");
        if (orderList == null || orderList.isEmpty()) {
    %>
    <div class="alert alert-info">Chưa có đơn hàng nào.</div>
    <%
    } else {
    %>
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th scope="col">Tên sản phẩm</th>
                    <th scope="col">Đơn giá</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Thành tiền</th>
                    <th scope="col">Trạng thái</th> <!-- Thêm cột trạng thái -->
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (OrderItem item : orderList) {
                %>
                <tr>
                    <td><%= item.getProductName()%></td>
                    <td><%= String.format("%,d VND", item.getPrice())%></td>
                    <td><%= item.getQuantity()%></td>
                    <td><%= String.format("%,d VND", item.getPrice() * item.getQuantity())%></td>
                    <td>
                        <div class="progress w-100" style="height: 24px;">
                            <div
                                class="progress-bar <%= progressClass(item.getStatus())%>"
                                role="progressbar"
                                style="width: <%= progressWidth(item.getStatus())%>%"
                                aria-valuenow="<%= progressWidth(item.getStatus())%>"
                                aria-valuemin="0"
                                aria-valuemax="100">
                            </div>
                        </div>
                        <div class="text-center mt-1"><%= item.getStatus()%></div>
                    </td>
                    <td class="align-middle">
                        <% if ("Đang xử lý".equals(item.getStatus())
                                    || "Đang vận chuyển".equals(item.getStatus())) {%>
                        <form action="order" method="post" onsubmit="return confirm('Bạn có chắc muốn hủy đơn này?');">
                            <input type="hidden" name="action" value="cancel"/>
                            <input type="hidden" name="orderId" value="<%=item.getOrderId()%>"/>
                            <button type="submit" class="btn btn-sm btn-danger mt-1">Hủy đơn</button>
                        </form>
                        <% } %>
                        <% if ("Hoàn thành".equals(item.getStatus())) {%>
                        <form action="order" method="post" onsubmit="return confirm('Xác nhận trả hàng?');">
                            <input type="hidden" name="action" value="return"/>
                            <input type="hidden" name="orderId" value="<%=item.getOrderId()%>"/>
                            <button type="submit" class="btn btn-sm btn-warning mt-1">Trả hàng</button>
                        </form>
                        <% } %>

                    </td>



                </tr>
                <%
                    }
                %>


            </tbody>
        </table>
    </div>
    <%
        }
    %>
</div>

<%@include file="/WEB-INF/include/footer.jsp" %>
