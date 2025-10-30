/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.product;

import dao.CartDao;
import dao.CategoriesDao;
import dao.ProductDao;
import dao.SaleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.CartItem;
import model.Sale;
import model.User;

/**
 *
 * @author Le Duy Khanh - CE190235
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/carts"})
public class CartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/auth?view=login");
                return;
            }

            int userId = user.getUserid(); // test user ID (nếu chưa có login)
String action = request.getParameter("action");
            if ("remove".equals(action)) {

                int productId = Integer.parseInt(request.getParameter("id"));
                CartDao dao = new CartDao();
                dao.removeFromCart(userId, productId);

                // redirect lại để tránh lỗi reload
                response.sendRedirect(request.getContextPath() + "/carts");
                return;
            }

            CartDao dao = new CartDao();
            List<CartItem> items = dao.getCartItems(userId);

            Cart cart = new Cart();
            for (CartItem item : items) {
                cart.addItem(item.getProduct(), item.getQuantity());
            }

            request.setAttribute("cart", cart); // gán vào request
            SaleDAO saleDao = new SaleDAO();
            List<Sale> allSales = saleDao.getAll();
            List<Sale> availableSales = new ArrayList<>();

            for (Sale sale : allSales) {
                if (sale.isAvailableSale()) {
                    availableSales.add(sale);
                }
            }

            request.setAttribute("availableSales", availableSales);

            request.getRequestDispatcher("/WEB-INF/cart/view.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addToCart".equals(action)) {
            try {
                HttpSession session = request.getSession(false);
                User user = (User) session.getAttribute("user");
                if (user == null) {
                    response.sendRedirect(request.getContextPath() + "/auth?view=login");
                    return;
                }

                int userId = user.getUserid();
                String quantityStr = request.getParameter("orderNumbers");

                // Validate số lượng
                if (quantityStr == null || quantityStr.trim().isEmpty()) {
                    request.setAttribute("error", "Vui lòng nhập lại số lượng hợp lệ.");
                    forwardToDetailPage(request, response);
                    return;
                }

                int quantity;
                try {
                    double test = Double.parseDouble(quantityStr);
                    if (test != Math.floor(test)) {
                        request.setAttribute("error", "Vui lòng nhập lại số lượng hợp lệ.");
                        forwardToDetailPage(request, response);
return;
                    }

                    quantity = Integer.parseInt(quantityStr);

                    if (quantity <= 0) {
                        request.setAttribute("error", "Vui lòng nhập lại số lượng hợp lệ.");
                        forwardToDetailPage(request, response);
                        return;
                    }
                    int productId = Integer.parseInt(request.getParameter("id"));
                    ProductDao productDao = new ProductDao();
                    int stockQuantity = productDao.getById(productId).getStockQuantity();

                    if (quantity > stockQuantity) {
                        request.setAttribute("error", "Số lượng mua vượt quá số lượng hiện có.");
                        forwardToDetailPage(request, response);
                        return;
                    }

                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Vui lòng nhập lại số lượng hợp lệ.");
                    forwardToDetailPage(request, response);
                    return;
                }

                int productId = Integer.parseInt(request.getParameter("id"));
                CartDao dao = new CartDao();
                dao.addToCart(userId, productId, quantity);

                response.sendRedirect(request.getContextPath() + "/carts");

            } catch (Exception e) {
                e.printStackTrace(response.getWriter());
            }
        } else if ("decreaseQuantity".equals(action)) {
            try {
                HttpSession session = request.getSession(false);
                User user = (User) session.getAttribute("user");
                if (user == null) {
                    response.sendRedirect(request.getContextPath() + "/auth?view=login");
                    return;
                }

                int userId = user.getUserid();
                int productId = Integer.parseInt(request.getParameter("id"));
                int decreaseAmount = Integer.parseInt(request.getParameter("decreaseAmount"));

                if (decreaseAmount <= 0) {
                    // Nếu người dùng nhập giảm <= 0
                    response.sendRedirect(request.getContextPath() + "/carts");
                    return;
                }

                CartDao dao = new CartDao();
                dao.decreaseQuantity(userId, productId, decreaseAmount);

                response.sendRedirect(request.getContextPath() + "/carts");

            } catch (Exception e) {
                e.printStackTrace(response.getWriter());
            }
        }

    }

    private void forwardToDetailPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        ProductDao productDao = new ProductDao();
        CategoriesDao catDao = new CategoriesDao();
request.setAttribute("product", productDao.getById(productId));
        request.setAttribute("categories", catDao.getAllCategories());

        request.getRequestDispatcher("/WEB-INF/product/forUser/detail.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}