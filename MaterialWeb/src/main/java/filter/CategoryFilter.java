/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

/**
 *
 * @author Huynh Thai Duy Phuong - CE190603
 */


import dao.CategoriesDao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*") // áp dụng cho tất cả request
public class CategoryFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        // Chỉ set 1 lần nếu chưa có
        if (req.getAttribute("categories") == null) {
            CategoriesDao dao = new CategoriesDao();
            request.setAttribute("categories", dao.getAllCategories());
        }

        chain.doFilter(request, response);
    }
}
