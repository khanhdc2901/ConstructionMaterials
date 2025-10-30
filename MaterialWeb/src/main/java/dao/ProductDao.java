package dao;

import db.DBContext;
import model.Product;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import model.ProductReport;
import java.sql.Date;


public class ProductDao extends DBContext {
    // Trả về danh sách sản phẩm theo từng trang (phân trang)

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM products ORDER BY id";
            ResultSet rs = this.executeSelectionQuery(sql, null);
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getInt("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("unit"),
                        rs.getString("brand_name"),
                        rs.getString("image")
                ));
            }
        } catch (SQLException e) {
            System.out.println("bi loi roi phan get all product");
        }
        return list;
    }

    public List<Product> getProductsByPage(int page, int pageSize) {
        List<Product> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM products ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            // Tính OFFSET: số dòng cần bỏ qua
            ps.setInt(1, (page - 1) * pageSize);
            // Số dòng cần lấy
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getInt("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("unit"),
                        rs.getString("brand_name"),
                        rs.getString("image")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
// Đếm tổng số sản phẩm trong bảng (dùng để tính tổng số trang)

    public int countProducts() {
        try {
            String sql = "SELECT COUNT(*) FROM products";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void insert(Product p) {
        try {
            String sql = "INSERT INTO products (name, description, category_id, price, stock_quantity, unit, brand_name, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setInt(3, p.getCategoryId());
            ps.setInt(4, p.getPrice());
            ps.setInt(5, p.getStockQuantity());
            ps.setString(6, p.getUnit());
            ps.setString(7, p.getBrandName());
            ps.setString(8, p.getImage());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cập nhật sản phẩm
    public void update(Product p) {
        try {
            String sql = "UPDATE products SET name = ?, description = ?, category_id = ?, price = ?, stock_quantity = ?, unit = ?, brand_name = ?, image = ? WHERE id = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setInt(3, p.getCategoryId());
            ps.setInt(4, p.getPrice());
            ps.setInt(5, p.getStockQuantity());
            ps.setString(6, p.getUnit());
            ps.setString(7, p.getBrandName());
            ps.setString(8, p.getImage()); // thêm dòng này
            ps.setInt(9, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xoá sản phẩm theo ID
    public void delete(int id) {
        try {
            String sql = "DELETE FROM products WHERE id = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy sản phẩm theo ID
    public Product getById(int id) {
        try {
            String sql = "SELECT * FROM products WHERE id = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getInt("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("unit"),
                        rs.getString("brand_name"),
                        rs.getString("image")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Product> searchByName(String keyword, int page, int pageSize) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setInt(2, (page - 1) * pageSize);
            ps.setInt(3, pageSize);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getInt("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("unit"),
                        rs.getString("brand_name"),
                        rs.getString("image") // thêm dòng này
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int countByKeyword(String keyword) {
        String sql = "SELECT COUNT(*) FROM products WHERE name LIKE ?";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
// Lấy sản phẩm theo category với phân trang

    public List<Product> getProductsByCategory(int categoryId, int page, int pageSize) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category_id = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, (page - 1) * pageSize);
            ps.setInt(3, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getInt("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("unit"),
                        rs.getString("brand_name"),
                        rs.getString("image") // thêm dòng này
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

// Đếm số sản phẩm theo category
    public int countByCategory(int categoryId) {
        String sql = "SELECT COUNT(*) FROM products WHERE category_id = ?";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteByCategoryId(int categoryId) {
        String sql = "DELETE FROM products WHERE category_id = ?";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<ProductReport> getTopSellingProducts(int year, int month) {
        List<ProductReport> list = new ArrayList<>();

        String sql = "SELECT product_id, SUM(quantity) AS totalQty, SUM(quantity * price_at_time) AS revenue "
                + "FROM order_items oi JOIN orders o ON oi.order_id = o.id "
                + "WHERE o.status = 'Hoàn thành' AND YEAR(o.order_date) = ?"
                + (month > 0 ? " AND MONTH(o.order_date) = ?" : "")
                + " GROUP BY product_id ORDER BY totalQty DESC";

        try {
            DBContext db = new DBContext();
            PreparedStatement ps;
            if (month > 0) {
                ps = db.getConnection().prepareStatement(sql);
                ps.setInt(1, year);
                ps.setInt(2, month);
            } else {
                ps = db.getConnection().prepareStatement(sql);
                ps.setInt(1, year);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductReport(
                        rs.getInt("product_id"),
                        rs.getInt("totalQty"),
                        rs.getInt("revenue")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void decreaseStockQuantity(int productId, int quantity) {
    String sql = "UPDATE products SET stock_quantity = stock_quantity - ? WHERE id = ?";
    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
        ps.setInt(1, quantity);
        ps.setInt(2, productId);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}



    public static List<ProductReport> getTopSellingProductsByDay(LocalDate date) throws SQLException {
        List<ProductReport> list = new ArrayList<>();
        String sql
                = "SELECT oi.product_id AS period, SUM(oi.quantity) AS qty, SUM(oi.quantity*oi.price_at_time) AS rev "
                + "FROM order_items oi JOIN orders o ON oi.order_id=o.id "
                + "WHERE o.status='Hoàn thành' AND CAST(o.order_date AS DATE)=? "
                + "GROUP BY oi.product_id ORDER BY qty DESC";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(date));
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ProductReport(
                            rs.getInt("period"),
                            rs.getInt("qty"),
                            rs.getInt("rev")
                    ));
                }
            }
        }
        return list;
    }

    public static List<ProductReport> getTopSellingProductsByMonth(int year, int month) throws SQLException {
        List<ProductReport> list = new ArrayList<>();
        String sql
                = "SELECT oi.product_id AS period, SUM(oi.quantity) AS qty, SUM(oi.quantity*oi.price_at_time) AS rev "
                + "FROM order_items oi JOIN orders o ON oi.order_id=o.id "
                + "WHERE o.status='Hoàn thành' AND YEAR(o.order_date)=? AND MONTH(o.order_date)=? "
                + "GROUP BY oi.product_id ORDER BY qty DESC";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, year);
            ps.setInt(2, month);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ProductReport(
                            rs.getInt("period"),
                            rs.getInt("qty"),
                            rs.getInt("rev")
                    ));
                }
            }
        }
        return list;
    }

    public static List<ProductReport> getTopSellingProductsByQuarter(int year, int quarter) throws SQLException {
        List<ProductReport> list = new ArrayList<>();
        String sql
                = "SELECT oi.product_id AS period, SUM(oi.quantity) AS qty, SUM(oi.quantity*oi.price_at_time) AS rev "
                + "FROM order_items oi JOIN orders o ON oi.order_id=o.id "
                + "WHERE o.status='Hoàn thành' AND YEAR(o.order_date)=? AND DATEPART(QUARTER, o.order_date)=? "
                + "GROUP BY oi.product_id ORDER BY qty DESC";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, year);
            ps.setInt(2, quarter);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ProductReport(
                            rs.getInt("period"),
                            rs.getInt("qty"),
                            rs.getInt("rev")
                    ));
                }
            }
        }
        return list;
    }

}
