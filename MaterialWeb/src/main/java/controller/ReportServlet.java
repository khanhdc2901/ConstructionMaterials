/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import dao.ProductDao;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import model.OrderReport;
import model.ProductReport;

/**
 *
 * @author Tieu Gia Huy - CE191594
 */
@WebServlet(name = "ViewStatisticPageServlet", urlPatterns = {"/report"})
public class ReportServlet extends HttpServlet {

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
            out.println("<title>Servlet ViewStatisticPageServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewStatisticPageServlet at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String type = req.getParameter("type");
        String period = req.getParameter("period");
        String yearStr = req.getParameter("year");
        int year = (yearStr != null && !yearStr.isEmpty())
                ? Integer.parseInt(yearStr)
                : LocalDate.now().getYear();

        // lần đầu mở form
        if (type == null || period == null) {
            req.getRequestDispatcher("/WEB-INF/report/statistic.jsp").forward(req, resp);
            return;
        }

        // Bắt lỗi thiếu ngày/tháng
        String error = null;
        if ("day".equals(period)) {
            String d = req.getParameter("date");
            if (d == null || d.isEmpty()) {
                error = "Bạn chưa chọn ngày";
            }
        } else if ("month".equals(period)) {
            String m = req.getParameter("month");
            if (m == null || m.isEmpty()) {
                error = "Bạn chưa chọn tháng";
            }
        }
        if (error != null) {
            req.setAttribute("errorMessage", error);
            req.getRequestDispatcher("/WEB-INF/report/statistic.jsp")
                    .forward(req, resp);
            return;
        }

        LocalDate date = null;
        YearMonth ym = null;
        int quarter = 0;
        if ("day".equals(period)) {
            date = LocalDate.parse(req.getParameter("date"));
        } else if ("month".equals(period)) {
            ym = YearMonth.parse(req.getParameter("month"));
        } else {
            quarter = Integer.parseInt(req.getParameter("quarter"));
        }

        try {
            if ("order".equals(type)) {
                List<OrderReport> data;
                if ("day".equals(period)) {
                    data = OrderDAO.getReportByDay(date);
                } else if ("month".equals(period)) {
                    data = OrderDAO.getReportByMonth(year, ym.getMonthValue());
                } else {
                    data = OrderDAO.getReportByQuarter(year, quarter);
                }
                req.setAttribute("orderReports", data);

            } else {
                List<ProductReport> data;
                if ("day".equals(period)) {
                    data = ProductDao.getTopSellingProductsByDay(date);
                } else if ("month".equals(period)) {
                    data = ProductDao.getTopSellingProductsByMonth(year, ym.getMonthValue());
                } else {
                    data = ProductDao.getTopSellingProductsByQuarter(year, quarter);
                }
                req.setAttribute("productReports", data);
            }

        } catch (SQLException e) {
            throw new ServletException("Lỗi thống kê", e);
        }

        req.setAttribute("period", period);
        req.getRequestDispatcher("/WEB-INF/report/report-result.jsp").forward(req, resp);
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
