package controller.product;

import static constant.CommonFunction.getTotalPages;
import static constant.CommonFunction.isEmptyString;
import dao.CategoriesDao;
import dao.ProductDao;
import dao.TheReviewDAO;
import dao.UserDAO;
import model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.TheReview;
import model.User;

@WebServlet(name = "DisplayServlet", urlPatterns = {"/display"})
public class DisplayServlet extends HttpServlet {

    TheReviewDAO theReviewDAO = new TheReviewDAO();
    ProductDao productDao = new ProductDao();
    UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductDao dao = new ProductDao();
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
            String cidRaw = request.getParameter("cid");
            List<Product> products;
            int totalProducts;

            if (keyword != null && !keyword.trim().isEmpty()) {
                products = dao.searchByName(keyword, page, pageSize);
                totalProducts = dao.countByKeyword(keyword);
                request.setAttribute("keyword", keyword); // để hiển thị lại trong ô tìm kiếm
            } else if (cidRaw != null && !cidRaw.isEmpty()) {
                try {
                    int cid = Integer.parseInt(cidRaw);
                    products = dao.getProductsByCategory(cid, page, pageSize);
                    totalProducts = dao.countByCategory(cid);
                    request.setAttribute("cid", cid);
                } catch (NumberFormatException e) {
                    products = dao.getProductsByPage(page, pageSize);
                    totalProducts = dao.countProducts();
                }
            } else {
                products = dao.getProductsByPage(page, pageSize);
                totalProducts = dao.countProducts();
            }
//            List<Product> products = dao.getProductsByPage(page, pageSize);            
//            int totalProducts = dao.countProducts();

            int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

            // Truyền dữ liệu sang JSP
            request.setAttribute("list", products);
            request.setAttribute("page", page);
            request.setAttribute("totalPages", totalPages);

            CategoriesDao cdao = new CategoriesDao();
            List<Category> categories = cdao.getAllCategories();
            request.setAttribute("categories", categories);
            // Forward đến trang JSP hiển thị sản phẩm
            request.getRequestDispatcher("/WEB-INF/product/forUser/list.jsp").forward(request, response);
        } else if (view.equals("detail")) {
            // Bắt buộc đăng nhập
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                String url = request.getContextPath() + "/display?view=detail&id=" + request.getParameter("id");
                session.setAttribute("redirectAfterLogin", url);
                response.sendRedirect(request.getContextPath() + "/auth?view=login");
                return;
            }

            ///
            ///
            /// cho comment
            int page = 1;
            int totalPages = 0;

            String namePage = "";

            String productId_str = request.getParameter("id");
            String userId_str = request.getParameter("uId");

            String pageParam = request.getParameter("page");
            if (pageParam != null && Integer.parseInt(pageParam) > 1) {
                page = Integer.parseInt(pageParam);
            }

            List<TheReview> reviewList = new ArrayList<>();
            int countItems;

            int productId = Integer.parseInt(productId_str);
            countItems = theReviewDAO.countItem_toProduct(productId);

            Product productComment = productDao.getById(productId);
            request.setAttribute("productComment", productComment);

//                HttpSession session = request.getSession(false);
            if (!(session == null || session.getAttribute("user") == null)) {
                User user = (User) session.getAttribute("user");
                request.setAttribute("canComment", !theReviewDAO.haveComment(user.getUserid(), productId));

                TheReview tmpTR = theReviewDAO.getby2ID(user.getUserid(), productId);
                if (tmpTR != null) {
                    reviewList.add(tmpTR);
                }
                reviewList.addAll(theReviewDAO.getAll_toProduct_exceptionUser(productId, page, user.getUserid()));
            } else {
                reviewList = theReviewDAO.getAll_toProduct(productId, page);
            }

//            reviewList = theReviewDAO.getAll_toProduct(productId, page);
            totalPages = getTotalPages(countItems);

            if (page > totalPages) {
                page = totalPages;
            }

            request.setAttribute("totalPages", totalPages);
            request.setAttribute("reviewList", reviewList);

            /// end
            ///
            ///
            String idRaw = request.getParameter("id");

            try {
                int id = Integer.parseInt(idRaw);
                Product product = dao.getById(id);

                if (product != null) {
                    request.setAttribute("product", product);
                    CategoriesDao cdao = new CategoriesDao();
                    List<Category> categories = cdao.getAllCategories();
                    request.setAttribute("categories", categories);
                    request.getRequestDispatcher("/WEB-INF/product/forUser/detail.jsp").forward(request, response);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("detail".equals(action)) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            int price = Integer.parseInt(request.getParameter("price"));
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            String unit = request.getParameter("unit");
            String brandName = request.getParameter("brandName");
            String image = request.getParameter("image");

            Product product = new Product(0, name, description, categoryId, price, stockQuantity, unit, brandName, image);
            ProductDao dao = new ProductDao();
            dao.insert(product); // bạn phải có phương thức insert phù hợp

            response.sendRedirect(request.getContextPath() + "/display?view=list");

        } else {
            if (action.equalsIgnoreCase("create")) {
                int userId = Integer.parseInt(request.getParameter("uId"));
                int productId = Integer.parseInt(request.getParameter("id"));
                int rating = Integer.parseInt(request.getParameter("rating"));
                String message = request.getParameter("message");
                if (theReviewDAO.create(userId, productId, rating, message) >= 1) {
                    System.out.println("Create successfull");
                } else {
                    System.out.println("Create failure!");
                }

            } else if (action.equalsIgnoreCase("remove")) {
                int userId = Integer.parseInt(request.getParameter("uId"));
                int productId = Integer.parseInt(request.getParameter("id"));
                if (theReviewDAO.remove(userId, productId) >= 1) {
                    System.out.println("Remove successfull");
                } else {
                    System.out.println("Remove failure!");
                }
            }

            response.sendRedirect(request.getContextPath() + "/display?view=detail&id=" + request.getParameter("id"));
        }
    }
}
