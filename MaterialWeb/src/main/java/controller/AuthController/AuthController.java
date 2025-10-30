/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.AuthController;

import dao.AuthDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Nguyen Thanh Nhan - CE190122
 */
@WebServlet(name = "AuthController", urlPatterns = {"/auth"})
public class AuthController extends HttpServlet {

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
            out.println("<title>Servlet AuthController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthController at " + request.getContextPath() + "</h1>");
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

        String type = request.getParameter("view");

        String namePage = "";

        if (type.equalsIgnoreCase("login")) {
            namePage = "login";
        } else if (type.equalsIgnoreCase("register")) {
            namePage = "register";
        }

        request.getRequestDispatcher("/WEB-INF/login/" + namePage + ".jsp").forward(request, response);
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

        String type = request.getParameter("action");
        String remember = request.getParameter("rememberme");
        String email = request.getParameter("email");
        if (remember != null) {
            rememberMe(true, email, response);
        } else {
            rememberMe(false, email, response);
        }
        if (type.equalsIgnoreCase("LOGIN")) {
            String pass = request.getParameter("password");

            PostLogin(request, response, email, pass);

        } else if (type.equalsIgnoreCase("REGISTER")) {
            String pass = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            String phonenumber = request.getParameter("phone");
            String cofirmpassword = request.getParameter("confirmpassword");

            PostRegister(request, response, email, pass, fullname, phonenumber, cofirmpassword);
        } else if (type.equalsIgnoreCase("LOGOUT")) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/home");
        }

    }

    protected void PostLogin(HttpServletRequest request, HttpServletResponse response,
            String email, String pass)
            throws ServletException, IOException {

        AuthDAO dao = new AuthDAO();
        User user = dao.login(email, pass);

        if (user != null) {
            if (user.getStatus().equalsIgnoreCase("active")) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/home");
                return; 
            } else {
                request.setAttribute("msg", "Your account is not active.");
                request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "Login failed. Incorrect username or password");
            request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
        }
    }

    private void rememberMe(boolean isRemember, String email, HttpServletResponse response) {
        if (isRemember) {
            Cookie cookie = new Cookie("EMAIL", email);
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("EMAIL", email);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    protected void PostRegister(HttpServletRequest request, HttpServletResponse response,
            String email, String pass, String fullname, String phonenumber, String conpassword)
            throws ServletException, IOException {
        //processRequest(request, response);

        AuthDAO dao = new AuthDAO();
        if (fullname == null || fullname.trim().isEmpty()
                || phonenumber == null || phonenumber.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || pass == null || pass.trim().isEmpty()
                || conpassword == null || conpassword.trim().isEmpty()) {

            request.setAttribute("msg", "Please fill in all required fields.");
            request.getRequestDispatcher("/WEB-INF/login/register.jsp").forward(request, response);
            return;
        }
        if (pass.equals(conpassword)) {
            if (dao.register(email, pass, fullname, phonenumber)) {
                request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Email already exists.");
                request.getRequestDispatcher("/WEB-INF/login/register.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "Passwords do not match.");
            request.getRequestDispatcher("/WEB-INF/login/register.jsp").forward(request, response);
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
