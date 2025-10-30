/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.CartItem;
import model.Product;

/**
 *
 * @author Le Duy Khanh - CE190235
 */
public class CartDao {

    private final Connection conn;

    public CartDao() {
        DBContext db = new DBContext();
        this.conn = db.getConnection();
    }

    public int getOrCreateCartId(int userId) throws Exception {
        // 1. Kiểm tra giỏ hàng đã tồn tại chưa
        String checkSql = "SELECT id FROM carts WHERE user_id = ?";
        try ( PreparedStatement ps = conn.prepareStatement(checkSql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }

        // 2. Nếu chưa có thì tạo mới
        String insertSql = "INSERT INTO carts (user_id) VALUES (?)";
        try ( PreparedStatement ps = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        throw new Exception("Không thể tạo giỏ hàng mới.");
    }

    public void addToCart(int userId, int productId, int quantity) throws Exception {
        int cartId = getOrCreateCartId(userId);

        // 1. Kiểm tra sản phẩm đã tồn tại trong giỏ chưa
        String checkSql = "SELECT id, quantity FROM cart_items WHERE cart_id = ? AND product_id = ?";
        try ( PreparedStatement ps = conn.prepareStatement(checkSql)) {
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                long currentQty = rs.getInt("quantity");
                int itemId = rs.getInt("id");

                // 2. Nếu có thì update số lượng
                String updateSql = "UPDATE cart_items SET quantity = ? WHERE id = ?";
                try ( PreparedStatement ups = conn.prepareStatement(updateSql)) {
                    ups.setLong(1, currentQty + quantity);
                    ups.setInt(2, itemId);
                    ups.executeUpdate();
                    return;
                }
            }
        }

        // 3. Nếu chưa có thì thêm mới
        String insertSql = "INSERT INTO cart_items (cart_id, product_id, quantity) VALUES (?, ?, ?)";
        try ( PreparedStatement ps = conn.prepareStatement(insertSql)) {
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ps.setLong(3, quantity);
            ps.executeUpdate();
        }
    }

    public List<CartItem> getCartItems(int userId) throws Exception {
        List<CartItem> items = new ArrayList<>();
        int cartId = getOrCreateCartId(userId);

        String sql = "SELECT p.id, p.name, p.price, ci.quantity "
                + "FROM cart_items ci "
                + "JOIN products p ON ci.product_id = p.id "
                + "WHERE ci.cart_id = ?";

        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getInt("price"));

                CartItem item = new CartItem(p, rs.getLong("quantity"));
                items.add(item);
            }
        }

        return items;
    }

    public void removeFromCart(int userId, int productId) throws Exception {
        int cartId = getOrCreateCartId(userId);

        String sql = "DELETE FROM cart_items WHERE cart_id = ? AND product_id = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ps.executeUpdate();
        }
    }

    public void decreaseQuantity(int userId, int productId, int amount) throws Exception {
        int cartId = getOrCreateCartId(userId);

        String selectSql = "SELECT id, quantity FROM cart_items WHERE cart_id = ? AND product_id = ?";
        try ( PreparedStatement ps = conn.prepareStatement(selectSql)) {
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int currentQty = rs.getInt("quantity");
                int itemId = rs.getInt("id");

                if (amount >= currentQty) {
                    removeFromCart(userId, productId);
                } else {
                    String updateSql = "UPDATE cart_items SET quantity = ? WHERE id = ?";
                    try ( PreparedStatement ups = conn.prepareStatement(updateSql)) {
                        ups.setInt(1, currentQty - amount);
                        ups.setInt(2, itemId);
                        ups.executeUpdate();
                    }
                }
            }
        }
    }
}