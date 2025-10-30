/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Category;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import dao.CategoriesDao;
import dao.ProductDao;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Nguyen Thanh Nhan - CE190122
 */
@WebServlet(name = "CategoryServlet", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

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
            out.println("<title>Servlet CategoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryServlet at " + request.getContextPath() + "</h1>");
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

        CategoriesDao dao = new CategoriesDao();
        String view = request.getParameter("view");
        // Xử lý phân trang
        if (view == null || view.isBlank() || view.equals("list")) {
            int page = 1;
            int pageSize = 9;

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
            List<Category> category;
            int totalCategory;

            if (keyword != null && !keyword.trim().isEmpty()) {
                category = dao.searchByName(keyword, page, pageSize);
                totalCategory = dao.countByKeyword(keyword);
                request.setAttribute("keyword", keyword); // để hiển thị lại trong ô tìm kiếm
            } else {
                category = dao.getCategoriesByPage(page, pageSize);
                totalCategory = dao.countCategories();
            }
//            List<Product> category = dao.getProductsByPage(page, pageSize);            
//            int totalCategory = dao.countProducts();

            int totalPages = (int) Math.ceil((double) totalCategory / pageSize);

            // Truyền dữ liệu sang JSP
            request.setAttribute("list", category);
            request.setAttribute("page", page);
            request.setAttribute("totalPages", totalPages);

            // Forward đến trang JSP hiển thị sản phẩm
            request.getRequestDispatcher("/WEB-INF/category/list.jsp").forward(request, response);
        } else if (view.equals("create")) {
            request.getRequestDispatcher("/WEB-INF/category/create.jsp").forward(request, response);

        } else if (view.equals("update")) {
            String idRaw = request.getParameter("id");
            try {
                int id = Integer.parseInt(idRaw);
                Category category = dao.getById(id);
                if (category != null) {
                    request.setAttribute("category", category);
                    request.getRequestDispatcher("/WEB-INF/category/update.jsp").forward(request, response);
                } else {
                    response.getWriter().println("Không tìm thấy sản phẩm.");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("ID không hợp lệ.");
            }

        } else if (view.equals("delete")) {
            String idRaw = request.getParameter("id");
            try {
                int id = Integer.parseInt(idRaw);
                Category category = dao.getById(id);
                if (category != null) {
                    // Đếm số sản phẩm trong danh mục này
                    ProductDao productDao = new ProductDao();
                    int productCount = productDao.countByCategory(id);

                    request.setAttribute("category", category);
                    request.setAttribute("productCount", productCount);
                    request.getRequestDispatcher("/WEB-INF/category/delete.jsp").forward(request, response);
                } else {
                    response.getWriter().println("Không tìm thấy sản phẩm.");
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
        ProductDao pDao = new ProductDao();
        String action = request.getParameter("action");
        CategoriesDao dao = new CategoriesDao();
        if ("create".equals(action)) {
            String name = request.getParameter("name");
            dao.insert(new Category(0, name));
            response.sendRedirect(request.getContextPath() + "/category?view=list");

        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            dao.update(new Category(id, name));
            response.sendRedirect(request.getContextPath() + "/category?view=list");

        } else if ("delete".equalsIgnoreCase(action)) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            String passcheck = request.getParameter("passworddelete");
            if (user.checkpassdelete(passcheck)) {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));

                    // Xóa sản phẩm trong danh mục
                    pDao.deleteByCategoryId(id);

                    // Xóa danh mục
                    dao.delete(id);

                    response.sendRedirect(request.getContextPath() + "/category?view=list");
                } catch (NumberFormatException e) {
                    response.getWriter().println("ID không hợp lệ.");
                }

            } else {
                String msg = "Vui lòng nhập đúng mật khẩu của tài khoản để xóa.";
                int id = Integer.parseInt(request.getParameter("id"));

                request.setAttribute("msg", msg);
                request.setAttribute("category", dao.getById(id));
                request.setAttribute("productCount", pDao.countByCategory(id));

                request.getRequestDispatcher("/WEB-INF/category/delete.jsp").forward(request, response);
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
