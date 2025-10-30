/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.User;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import constant.HashUtil;
import dao.UserDAO;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;

/**
 *
 * @author Tieu Gia Huy - CE191594
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 5 * 1024 * 1024, // 5MB
        maxRequestSize = 10 * 1024 * 1024 // 10MB
)
@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaleServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

//        if (user == null) {
//            // Dữ liệu mẫu
//            user = new User("huy123", "Huy Tiêu", "huy@example.com", "123456");
//            session.setAttribute("user", user);
//        }
        String view = request.getParameter("view");

        String namePage = "";

        if (view == null || view.equals("") || view.equalsIgnoreCase("profile")) {
            namePage = "profile";
        } else if (view.equalsIgnoreCase("editProfile")) {
            namePage = "editProfile";
        }

        request.getRequestDispatcher("/WEB-INF/profile/" + namePage + ".jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/auth?view=login");
            return;
        }

        if (user != null) {
            user.setFullName(request.getParameter("fullName"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));

            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String rawPassword = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String phone = request.getParameter("phone");

            System.out.println(rawPassword);

            if (!rawPassword.equals(confirmPassword)) {
                request.setAttribute("error", "Passwords do not match!");
                request.setAttribute("user", user); // giữ lại dữ liệu đã nhập
                request.getRequestDispatcher("/WEB-INF/profile/editProfile.jsp").forward(request, response);
                return;
            }

            String hashedPassword = HashUtil.toMD5(rawPassword);
            user.setPassword(hashedPassword);

            user.setFullName(fullName);
            user.setEmail(email);
            user.setPhonenumbers(phone);

            UserDAO dao = new UserDAO();
            dao.updateUser(user);

            Part avatarPart = request.getPart("avatarFile");
            if (avatarPart != null && avatarPart.getSize() > 0) {
                // Tách tên file gốc và phần mở rộng
                String submitted = Paths.get(avatarPart.getSubmittedFileName())
                        .getFileName().toString();
                String ext = submitted.contains(".")
                        ? submitted.substring(submitted.lastIndexOf('.'))
                        : "";
                // Tạo tên file duy nhất
                String fileName = "u" + user.getUserid() + "_"
                        + System.currentTimeMillis() + ext;

                // Đường dẫn tuyệt đối tới webapp/assets/img
                String imagesDir = getServletContext().getRealPath("/assets/img");
                File dir = new File(imagesDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Lưu file
                avatarPart.write(new File(dir, fileName).getAbsolutePath());

                // Cập nhật DB
                dao.updateAvatar(user.getUserid(), fileName);
                user.setAvatar(fileName);

            }

            session.setAttribute("user", user);
        }

        request.getSession()
                .setAttribute("message", "Profile updated successfully!");
        response.sendRedirect(
                "profile");
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
