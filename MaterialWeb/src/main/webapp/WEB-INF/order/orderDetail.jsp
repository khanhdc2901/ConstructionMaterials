<%-- 
    Document   : orderDetail
    Created on : Jul 11, 2025, 4:52:49 PM
    Author     : Tieu Gia Huy - CE191594
--%>
<%@ page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html>
    <head><title>Chi tiết đơn hàng</title></head>
    <body>
        <h2>📦 Chi tiết đơn hàng</h2>

        <%
            String orderIdStr = request.getParameter("id");
            int orderId = Integer.parseInt(orderIdStr);
        %>

        <table border="1" cellpadding="8">
            <tr>
                <th>Tên sản phẩm</th>
                <th>Số lượng</th>
                <th>Đơn giá</th>
                <th>Thành tiền</th>
            </tr>

            <%
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdb", "user", "password");

                    String sql = "SELECT p.name, oi.quantity, oi.price_at_time "
                            + "FROM order_items oi JOIN products p ON oi.product_id = p.id "
                            + "WHERE oi.order_id = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, orderId);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        int qty = rs.getInt("quantity");
                        int price = rs.getInt("price_at_time");
            %>
            <tr>
                <td><%= rs.getString("name")%></td>
                <td><%= qty%></td>
                <td><%= price%></td>
                <td><%= qty * price%></td>
            </tr>
            <%
                    }

                    conn.close();
                } catch (Exception e) {
                    out.println("Lỗi: " + e.getMessage());
                }
            %>
        </table>

    </body>
</html>

<%@include file="/WEB-INF/include/footer.jsp" %>