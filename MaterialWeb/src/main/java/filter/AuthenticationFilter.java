/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

/**
 *
 * @author Nguyen Thanh Nhan - CE190122
 */
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/category", "/product", "/theReview", "/user", "/sale"})
public class AuthenticationFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        System.out.println("doFilter called");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        HttpSession session = req.getSession(false); 
        
        
        if(session == null || session.getAttribute("user") == null) { 
            resp.sendRedirect(req.getContextPath() + "/home");
            return; 
        }
        
        
         User user = (User) session.getAttribute("user");
        if (!user.isAdmin()) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }
        chain.doFilter(request, response);
    }
}