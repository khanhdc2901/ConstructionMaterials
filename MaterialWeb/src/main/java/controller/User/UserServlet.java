/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.User;

import dao.TheReviewDAO;
import dao.TokenForgetDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.UserDAO;
import java.util.List;
import model.User;

/**
 *
 * @author Nguyen Thanh Nhan - CE190122
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {

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
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        UserDAO dao = new UserDAO();
        String view = request.getParameter("view");

        if (view == null || view.isBlank() || view.equals("list")) {
            int page = 1;
            int pageSize = 10;

            try {
                String pageParam = request.getParameter("page");
                if (pageParam != null) {
                    page = Integer.parseInt(pageParam);
                    if (page < 1) {
                        page = 1;
                    }
                }
            } catch (NumberFormatException e) {
                page = 1;
            }

            String keyword = request.getParameter("keyword");
            List<User> users;
            int totalUsers;

            if (keyword != null && !keyword.trim().isEmpty()) {
                users = dao.searchByKeyword(keyword, page, pageSize);
                totalUsers = dao.countByKeyword(keyword);
                request.setAttribute("keyword", keyword);
            } else {
                users = dao.getUsersByPage(page, pageSize);
                totalUsers = dao.countUsers();
            }

            int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

            request.setAttribute("list", users);
            request.setAttribute("page", page);
            request.setAttribute("totalPages", totalPages);
            request.getRequestDispatcher("/WEB-INF/user/list.jsp").forward(request, response);

        } else if (view.equals("create")) {
            request.getRequestDispatcher("/WEB-INF/user/create.jsp").forward(request, response);

        } else if (view.equals("update")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = dao.getById(id);
                if (user != null) {
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/WEB-INF/user/update.jsp").forward(request, response);
                } else {
                    response.getWriter().println("Không tìm thấy người dùng.");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("ID không hợp lệ.");
            }

        } else if (view.equals("delete")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = dao.getById(id);
                if (user != null) {
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/WEB-INF/user/delete.jsp").forward(request, response);
                } else {
                    response.getWriter().println("Không tìm thấy người dùng.");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("ID không hợp lệ.");
            }

        } else {
            response.getWriter().println("Tham số 'view' không hợp lệ.");
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
        //processRequest(request, response);
        String action = request.getParameter("action");
        UserDAO dao = new UserDAO();

        if ("create".equals(action)) {
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phoneNumber");
            String status = request.getParameter("status");
            String role = request.getParameter("role");

            User user = new User(email, password, status, fullName, phoneNumber, 0);
            user.setRole(role);
            dao.insert(user);
            response.sendRedirect(request.getContextPath() + "/user?view=list");

        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String role = request.getParameter("role");
            String status = request.getParameter("status");

            dao.updateRoleStatusOnly(id, role, status);
            response.sendRedirect("user?view=list");

        } else if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                
                TheReviewDAO theReviewDAO = new TheReviewDAO();
                TokenForgetDAO tokenForgetDAO = new TokenForgetDAO();

                theReviewDAO.remove(id);
                tokenForgetDAO.remove(id);

                
                dao.delete(id);
                response.sendRedirect(request.getContextPath() + "/user?view=list");
            } catch (NumberFormatException e) {
                response.getWriter().println("ID không hợp lệ khi xoá!");
            }
        }
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
