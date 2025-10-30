/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBContext;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DBContext {

    public List<User> getUsersByPage(int page, int pageSize) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user_login ORDER BY userID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, (page - 1) * pageSize);
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("status"),
                        rs.getString("fullName"),
                        rs.getString("phoneNumber"),
                        rs.getInt("userID"),
                        rs.getString("role")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countUsers() {
        String sql = "SELECT COUNT(*) FROM user_login";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<User> searchByKeyword(String keyword, int page, int pageSize) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user_login WHERE fullName LIKE ? ORDER BY userID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setInt(2, (page - 1) * pageSize);
            ps.setInt(3, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("status"),
                        rs.getString("fullName"),
                        rs.getString("phoneNumber"),
                        rs.getInt("userID"),
                        rs.getString("role")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countByKeyword(String keyword) {
        String sql = "SELECT COUNT(*) FROM user_login WHERE fullName LIKE ?";
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

    public User getById(int id) {
        try {
            String query = "SELECT UserID, FullName, PhoneNumber, email, password, Status, Role\n"
                    + "FROM     user_login\n"
                    + "WHERE  (UserID = ?)";
            ResultSet rs = this.executeSelectionQuery(query, new Object[]{id});
            if (rs.next()) {
                return new User(
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("status"),
                        rs.getString("fullName"),
                        rs.getString("phoneNumber"),
                        rs.getInt("userID"),
                        rs.getString("role")
                );
            }
        } catch (Exception e) {
            System.out.println("Khong the lay user trong db");
        }
        return null;
    }

    public void insert(User u) {
        String sql = "INSERT INTO user_login (email, password, status, fullName, phoneNumber, role) VALUES (?, ?, ?, ?, ?, ?)";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getStatus());
            ps.setString(4, u.getFullName());
            ps.setString(5, u.getPhonenumbers());
            ps.setString(6, u.getRole());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRoleStatusOnly(int userId, String role, String status) {
        String sql = "UPDATE user_login SET role = ?, status = ? WHERE userID = ?";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, role);
            ps.setString(2, status);
            ps.setInt(3, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int delete(int id) {
        String sql ="DELETE FROM user_login WHERE userID = ?";
        try {
            return this.executeQuery(sql, new Object[]{id});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateUser(User user) {
        String sql = "UPDATE user_login SET FullName = ?, email = ?, password = ?, phoneNumber = ? WHERE UserID = ?";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword()); // nếu có mã hóa MD5 thì truyền mã hóa
            ps.setString(4, user.getPhonenumbers());
            ps.setInt(5, user.getUserid());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAvatar(int userId, String avatarFilename) {
        String sql = "UPDATE user_login SET avatar = ? WHERE userID = ?";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, avatarFilename);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
