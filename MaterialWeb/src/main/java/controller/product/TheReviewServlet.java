/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.product;

import static constant.CommonFunction.getTotalPages;
import static constant.CommonFunction.isEmptyString;
import dao.ProductDao;
import dao.TheReviewDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Product;
import model.TheReview;
import model.User;

/**
 *
 * @author Dai Minh Nhu - CE190213
 */
@WebServlet(name = "TheReviewServlet", urlPatterns = {"/theReview"})
public class TheReviewServlet extends HttpServlet {

    TheReviewDAO theReviewDAO = new TheReviewDAO();
    ProductDao productDao = new ProductDao();
    UserDAO userDAO = new UserDAO();

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
            out.println("<title>Servlet RatingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RatingServlet at " + request.getContextPath() + "</h1>");
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

        String view = request.getParameter("view");
//        List<Product> productList = productDao.getAll();
//        List<User> userList = 
//        
//        request.setAttribute("productList", productList);

        int page = 1;
        int totalPages = 0;

        String namePage = "";

        String productId_str = request.getParameter("pId");
        String userId_str = request.getParameter("uId");

        if (view == null || view.equals("") || view.equalsIgnoreCase("list")) {
            namePage = "listReview";

            String pageParam = request.getParameter("page");
            if (pageParam != null && Integer.parseInt(pageParam) > 1) {
                page = Integer.parseInt(pageParam);
            }

            List<TheReview> reviewList;
            int countItems;

            if (!isEmptyString(productId_str) && !isEmptyString(userId_str)) {  // khi nguoi dung mua xem san pham
                int userId = Integer.parseInt(userId_str);
                int productId = Integer.parseInt(productId_str);
                countItems = theReviewDAO.countItem_toProduct(productId);

                User user = userDAO.getById(userId);
                request.setAttribute("user", user);
                Product productComment = productDao.getById(productId);
                request.setAttribute("productComment", productComment);

                reviewList = theReviewDAO.getAll_toProduct(productId, page);
            } else if (!isEmptyString(productId_str)) {                         // khi quan li xem san pham

                int productId = Integer.parseInt(productId_str);
                countItems = theReviewDAO.countItem_toProduct(productId);

                Product productComment = productDao.getById(productId);
                request.setAttribute("productComment", productComment);

                HttpSession session = request.getSession(false);

                if (!(session == null || session.getAttribute("user") == null)) {
                    User user = (User) session.getAttribute("user");
                    request.setAttribute("canComment", !theReviewDAO.haveComment(user.getUserid(), productId));
                }

                reviewList = theReviewDAO.getAll_toProduct(productId, page);
            } else if (!isEmptyString(userId_str)) {                            // khi nguoi dung xem tat ca comment cua minh
                int userId = Integer.parseInt(userId_str);
                countItems = theReviewDAO.countItem_toUser(userId);

                User user = userDAO.getById(userId);
                request.setAttribute("user", user);

                reviewList = theReviewDAO.getAll_toUser(userId, page);
            } else {                                                            // khi quan li xem tat ca comment
                countItems = theReviewDAO.countItem();
                reviewList = theReviewDAO.getAll(page);
            }

            totalPages = getTotalPages(countItems);

            if (page > totalPages) {
                page = totalPages;
            }

            request.setAttribute("totalPages", totalPages);

//            List<TheReview> reviewList = theReviewDAO.getAll(page);
            request.setAttribute("reviewList", reviewList);

        }
        
        request.getRequestDispatcher("/WEB-INF/theReview/" + namePage + ".jsp").forward(request, response);
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
        if (action != null && !action.isEmpty()) {

            if (action.equalsIgnoreCase("create")) {
                int userId = Integer.parseInt(request.getParameter("uId"));
                int productId = Integer.parseInt(request.getParameter("pId"));
                int rating = Integer.parseInt(request.getParameter("rating"));
                String message = request.getParameter("message");
                if (theReviewDAO.create(userId, productId, rating, message) >= 1) {
                    System.out.println("Create successfull");
                } else {
                    System.out.println("Create failure!");
                }

            } else if (action.equalsIgnoreCase("remove")) {
                int userId = Integer.parseInt(request.getParameter("uId"));
                int productId = Integer.parseInt(request.getParameter("pId"));
                if (theReviewDAO.remove(userId, productId) >= 1) {
                    System.out.println("Remove successfull");
                } else {
                    System.out.println("Remove failure!");
                }
            }
        }
        
        String str_back = "";
//        if (!isEmptyString((String) request.getParameter("comeback"))){
//            str_back = "?pId=" + request.getParameter("pId");
//            
//            System.out.println(request.getParameter("comeback"));
//        }

        response.sendRedirect(request.getContextPath() + "/theReview" + str_back);
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
