package controller.product;

import dao.OrderDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.OrderItem;
import model.User;

@WebServlet(name = "OrderServlet", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String view = request.getParameter("view");
        String namePage;

        if (view == null || view.isEmpty() || view.equalsIgnoreCase("order")) {
            namePage = "orderList";

            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("user");

            if (currentUser != null) {
                OrderDAO dao = new OrderDAO();
                List<OrderItem> orderList = dao.getOrderItemsByUserId(currentUser.getUserid());
                request.setAttribute("orderList", orderList);
            } else {
                response.sendRedirect(request.getContextPath() + "/auth?view=login");
                return;
            }
        } else if (view.equalsIgnoreCase("orderDetail")) {
            namePage = "orderDetail";
        } else {
            namePage = "orderList";
        }

        request.getRequestDispatcher("/WEB-INF/order/" + namePage + ".jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("updateStatus".equals(action)) {
            int orderId = Integer.parseInt(request.getParameter("id"));
            String status = request.getParameter("status");
            new OrderDAO().updateStatus(orderId, status);
            response.sendRedirect("orders"); // hoặc "order?view=orders"
            return;
        }
        // Xử lý hủy đơn trước
        if ("cancel".equals(action)) {
            String sOrderId = request.getParameter("orderId");
            if (sOrderId != null) {
                try {
                    int orderId = Integer.parseInt(sOrderId);
                    new OrderDAO().updateStatus(orderId, "Đã hủy");
                } catch (NumberFormatException e) {
                    // Log lỗi hoặc xử lý
                }
            }
            response.sendRedirect("order");
            return;
        }

        // Xử lý thêm OrderItem
        String sProductId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String sPrice = request.getParameter("price");
        String unit = request.getParameter("unit");
        String sQuantity = request.getParameter("quantity");

        if (sProductId != null && productName != null && sPrice != null && unit != null && sQuantity != null) {
            try {
                int productId = Integer.parseInt(sProductId);
                int price = Integer.parseInt(sPrice);
                int quantity = Integer.parseInt(sQuantity);

                OrderItem item = new OrderItem(productId, productName, price, unit, quantity);
                HttpSession session = request.getSession();
                List<OrderItem> orderList = (List<OrderItem>) session.getAttribute("orderList");
                if (orderList == null) {
                    orderList = new ArrayList<>();
                }
                orderList.add(item);
                session.setAttribute("orderList", orderList);
            } catch (NumberFormatException e) {
                // Log hoặc xử lý lỗi
            }
        }
        if ("return".equals(action)) {
            String sOrderId = request.getParameter("orderId");
            if (sOrderId != null) {
                try {
                    int orderId = Integer.parseInt(sOrderId);
                    HttpSession session = request.getSession();
                    User currentUser = (User) session.getAttribute("user");

                    if (currentUser != null) {
                        OrderDAO dao = new OrderDAO();
                        // Kiểm tra trạng thái hiện tại của đơn hàng (đảm bảo là Hoàn thành)
                        List<OrderItem> userOrders = dao.getOrderItemsByUserId(currentUser.getUserid());
                        for (OrderItem item : userOrders) {
                            if (item.getOrderId() == orderId && "Hoàn thành".equals(item.getStatus())) {
                                dao.updateStatus(orderId, "Đã trả hàng");
                                break;
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    // Log lỗi nếu cần
                }
            }
            response.sendRedirect("order");
            return;
        }

        response.sendRedirect("order");
    }

    @Override
    public String getServletInfo() {
        return "OrderServlet handles viewing, storing, and canceling orders.";
    }
}
