<%-- 
    Document   : list
    Created on : Jul 18, 2025, 12:29:15 PM
    Author     : Tieu Gia Huy - CE191594
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.Order" %>
<%@ include file="/WEB-INF/include/header.jsp" %>

<div class="container mt-4">
  <h1>Danh sách tất cả đơn hàng</h1>
  <table class="table table-bordered table-striped">
    <thead class="table-dark">
      <tr>
        <th>ID</th>
        <th>User ID</th>
        <th>Ngày đặt</th>
        <th>Tổng tiền</th>
        <th>Trạng thái</th>
        <th>Thao tác</th>
      </tr>
    </thead>
    <tbody>
    <%
      List<Order> orders = (List<Order>) request.getAttribute("orders");
      if (orders != null) {
        for (Order o : orders) {
    %>
      <tr>
        <td><%= o.getId() %></td>
        <td><%= o.getUserId() %></td>
        <td><%= o.getOrderDate() %></td>
        <td><%= String.format("%,d VND", o.getTotalAmount()) %></td>
        <td><%= o.getStatus() %></td>
        <td>
          <form action="<%=request.getContextPath()%>/orders" method="post" class="d-inline">
            <input type="hidden" name="action" value="updateStatus"/>
            <input type="hidden" name="id"     value="<%= o.getId() %>"/>
            <select name="status"
                    class="form-select form-select-sm d-inline w-auto"
                    onchange="this.form.submit()">
              <option value="Đang xử lý"      <%= "Đang xử lý".equals(o.getStatus())     ? "selected" : "" %>>
                Đang xử lý
              </option>
              <option value="Đang vận chuyển" <%= "Đang vận chuyển".equals(o.getStatus())? "selected" : "" %>>
                Đang vận chuyển
              </option>
              <option value="Hoàn thành"      <%= "Hoàn thành".equals(o.getStatus())     ? "selected" : "" %>>
                Hoàn thành
              </option>
              <option value="Đã hủy"          <%= "Đã hủy".equals(o.getStatus())         ? "selected" : "" %>>
                Đã hủy
              </option>
            </select>
          </form>
        </td>
      </tr>
    <%
        }
      }
    %>
    </tbody>
  </table>
</div>

<%@ include file="/WEB-INF/include/footer.jsp" %>


