/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Statistic;

import dao.OrderDAO;
import dao.ProductDao;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import model.OrderReport;
import model.ProductReport;

/**
 *
 * @author Tieu Gia Huy - CE191594
 */
@WebServlet(name = "StatisticServlet", urlPatterns = {"/StatisticServlet"})
public class StatisticServlet extends HttpServlet {

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
            out.println("<title>Servlet StatisticServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticServlet at " + request.getContextPath() + "</h1>");
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
        String type = request.getParameter("type");    // "order" hoặc "product"
        String period = request.getParameter("period");  // "day", "month", "quarter"

        // Lần đầu chưa chọn gì thì show form
        if (type == null || period == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/report/statistic.jsp");
            rd.forward(request, response);
            return;
        }

        try {
            if ("order".equals(type)) {
                List<OrderReport> reports;
                switch (period) {
                    case "day": {
                        LocalDate date = LocalDate.parse(request.getParameter("date"));
                        reports = OrderDAO.getReportByDay(date);
                        break;
                    }
                    case "month": {
                        int year = Integer.parseInt(request.getParameter("year"));
                        int month = Integer.parseInt(request.getParameter("month"));
                        reports = OrderDAO.getReportByMonth(year, month);
                        break;
                    }
                    default: /* quarter */ {
                        int year = Integer.parseInt(request.getParameter("year"));
                        int quarter = Integer.parseInt(request.getParameter("quarter"));
                        reports = OrderDAO.getReportByQuarter(year, quarter);
                        break;
                    }
                }
                request.setAttribute("orderReports", reports);

            } else { // product
                List<ProductReport> reports;
                switch (period) {
                    case "day": {
                        LocalDate date = LocalDate.parse(request.getParameter("date"));
                        reports = ProductDao.getTopSellingProductsByDay(date);
                        break;
                    }
                    case "month": {
                        int year = Integer.parseInt(request.getParameter("year"));
                        int month = Integer.parseInt(request.getParameter("month"));
                        reports = ProductDao.getTopSellingProductsByMonth(year, month);
                        break;
                    }
                    default: /* quarter */ {
                        int year = Integer.parseInt(request.getParameter("year"));
                        int quarter = Integer.parseInt(request.getParameter("quarter"));
                        reports = ProductDao.getTopSellingProductsByQuarter(year, quarter);
                        break;
                    }
                }
                request.setAttribute("productReports", reports);
            }
        } catch (Exception e) {
            throw new ServletException("Lỗi khi lấy dữ liệu thống kê", e);
        }

        // Giữ lại period để JSP hiện nhãn đúng
        request.setAttribute("period", period);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/report/report-result.jsp");
        rd.forward(request, response);
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
        processRequest(request, response);
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
