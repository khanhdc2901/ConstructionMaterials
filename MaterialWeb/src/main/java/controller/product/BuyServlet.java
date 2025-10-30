package controller.product;

import constant.Constants;
import dao.CartDao;
import dao.ProductDao;
import dao.SaleDAO;
import java.io.IOException;
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
import model.Product;
import model.Sale;
import model.User;

@WebServlet(name = "BuyServlet", urlPatterns = {"/buy"})
public class BuyServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String[] selectedIds = request.getParameterValues("selectedProductIds");
        
        if (selectedIds == null || selectedIds.length == 0) {
            request.setAttribute("error", "Vui lòng chọn ít nhất một sản phẩm để thanh toán.");
            request.getRequestDispatcher("/WEB-INF/cart/view.jsp").forward(request, response);
            return;
        }
        
        ProductDao productDao = new ProductDao();
        CartDao cartDao = new CartDao();
        List<CartItem> selectedItems = new ArrayList<>();
        double calculatedTotal = 0;
        
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        
        try {
            int userId = user.getUserid();
            
            for (String idStr : selectedIds) {
                int productId = Integer.parseInt(idStr);
                int quantity = Integer.parseInt(request.getParameter("quantity_" + productId));
                
                Product product = productDao.getById(productId);
                
                if (product != null) {
                    selectedItems.add(new CartItem(product, quantity));
                    calculatedTotal += (long) product.getPrice() * quantity;

                    // Remove from DB
                }
            }

            // Update session cart
            session.setAttribute("cart", cart);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi trong quá trình thanh toán.");
            request.getRequestDispatcher("/carts").forward(request, response);
            return;
        }

        // Handle sale
        String saleIdParam = request.getParameter("selectedSaleId");
        double totalAfterDiscount = calculatedTotal;
        Sale selectedSale = null;
        
        if (saleIdParam != null && !saleIdParam.isEmpty()) {
            try {
                int saleId = Integer.parseInt(saleIdParam);
                SaleDAO saleDao = new SaleDAO();
                selectedSale = saleDao.getElementByID(saleId);
                
                if (selectedSale != null && selectedSale.isAvailableSale()) {
                    String discountStr = selectedSale.getCurrentDiscount();
                    int discountType = selectedSale.getTypeOfDiscount();
                    
                    try {
                        if (discountType == Constants.PERCENT) {
                            if (discountStr.endsWith("%")) {
                                discountStr = discountStr.replace("%", "").trim();
                            }
                            double percent = Double.parseDouble(discountStr);
                            totalAfterDiscount = calculatedTotal * (1 - percent / 100.0);
                        } else if (discountType == Constants.DIRECT) {
                            discountStr = discountStr.replaceAll("[^\\d]", "");
                            double amount = Double.parseDouble(discountStr);
                            totalAfterDiscount = calculatedTotal - amount;
                            if (totalAfterDiscount < 0) {
                                totalAfterDiscount = 0;
                            }
                        }
                    } catch (NumberFormatException e) {
                        totalAfterDiscount = calculatedTotal;
                    }
                }
            } catch (NumberFormatException e) {
                // Ignore invalid sale
            }
        }

//        System.out.println(selectedItems.size());
//        if (selectedItems == null || selectedItems.isEmpty()) {
//            
////            request.getRequestDispatcher("/WEB-INF/cart/view.jsp").forward(request, response);
//            response.sendRedirect(request.getContextPath() + "/carts");
//        }
        // Send to JSP
        request.setAttribute("selectedItems", selectedItems);
        request.setAttribute("selectedSale", selectedSale);
        request.setAttribute("calculatedTotal", Math.ceil(totalAfterDiscount));
        request.getRequestDispatcher("/WEB-INF/product/forUser/buy.jsp").forward(request, response);
    }
}
