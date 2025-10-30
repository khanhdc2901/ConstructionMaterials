/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.AuthController;

import dao.TokenForgetDAO;
import model.TokenForgetPassword;
import model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import dao.AuthDAO;
import java.net.URLEncoder;

/**
 *
 * @author HP
 */
@WebServlet(name = "requestPassword", urlPatterns = {"/requestPassword"})
public class requestPassword extends HttpServlet {

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
            out.println("<title>Servlet requestPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet requestPassword at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/WEB-INF/login/requestPassword.jsp").forward(request, response);
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
        AuthDAO dao = new AuthDAO();
        String email = request.getParameter("email");
        //email co ton tai trong db
        User user = dao.getUserByEmail(email);
        if (user == null) {
            request.setAttribute("mess", "email khong ton tai");
            request.getRequestDispatcher("/WEB-INF/login/requestPassword.jsp").forward(request, response);
            return;
        }
        resetService service = new resetService();
        String token = service.generateToken();

        String domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String encodedToken = URLEncoder.encode(token, "UTF-8");
        String linkReset = domain + request.getContextPath() + "/resetPassword?token=" + encodedToken;

        TokenForgetPassword newTokenForget = new TokenForgetPassword(
                user.getUserid(), false, token, service.expireDateTime());

        //send link to this email
        TokenForgetDAO daoToken = new TokenForgetDAO();
        boolean isInsert = daoToken.insertTokenForget(newTokenForget);
        if (!isInsert) {
            request.setAttribute("mess", "have error in server");
            request.getRequestDispatcher("/WEB-INF/login/requestPassword.jsp").forward(request, response);
            return;
        }
        boolean isSend = service.sendEmail(email, linkReset, user.getFullName());
        if (!isSend) {
            request.setAttribute("mess", "can not send request");
            request.getRequestDispatcher("/WEB-INF/login/requestPassword.jsp").forward(request, response);
            return;
        }
        request.setAttribute("mess", "send request success");
        request.getRequestDispatcher("/WEB-INF/login/requestPassword.jsp").forward(request, response);
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
