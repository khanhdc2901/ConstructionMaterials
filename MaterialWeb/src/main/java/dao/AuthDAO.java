/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Nguyen Thanh Nhan - CE190122
 */
public class AuthDAO extends DBContext {

    private final String CHECK_EMAIL = "select * from user_login where email = ?";
    private final String GET_ALL_MEMBER = "select * from user_login";
    private final String LOGIN_MEMBERS = "SELECT * FROM user_login WHERE email = ? AND password = ?";
    private final String REGISTER_MEMBER = "INSERT INTO user_login ([FullName], [PhoneNumber], [email], [password], [Status], [Role])\n"
            + "VALUES (?, ?, ?, ?, ?, ?);";

    public List<User> getAllUser() {
        List<User> listUser = new ArrayList<>();
        try {
            PreparedStatement stm = this.getConnection().prepareStatement(GET_ALL_MEMBER);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String Email = rs.getString("email").trim();
                String Password = rs.getString("password").trim();
                String FullName = rs.getString("FullName").trim();
                String PhoneNumber = rs.getString("PhoneNumber").trim();
                String Status = rs.getString("Status").trim();
                int UserID = rs.getInt("UserID");

                User us = new User(Email, Password, Status, FullName, PhoneNumber, UserID);
                listUser.add(us);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUser;

    }

    public User login(String email, String password) {

        try {
            String hashedPassword = hashMd5(password);
            PreparedStatement stm = this.getConnection().prepareStatement(LOGIN_MEMBERS);
            stm.setString(1, email);
            stm.setString(2, hashedPassword);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                String Email = rs.getString("email").trim();
                String Password = rs.getString("password").trim();
                String FullName = rs.getString("FullName").trim();
                String PhoneNumber = rs.getString("PhoneNumber").trim();
                String Status = rs.getString("Status").trim();
                int UserID = rs.getInt("UserID");
                String Role = rs.getString("Role");
                User user = new User(Email, Password, Status, FullName, PhoneNumber, UserID, Role);
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private boolean checkemail(String email) {
        try {
            PreparedStatement stm = this.getConnection().prepareStatement(CHECK_EMAIL);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean register(String email, String password, String fullname, String phonenumber) {
        if (checkemail(email)) {
            return false;
        }
        try {
//            String getidquery = "select Max(UserID) + 1 from user_login";
//            PreparedStatement stm = this.getConnection().prepareStatement(getidquery);
//
//            ResultSet rs = stm.executeQuery();
//            int getid = 1;
//            if (rs.next()) {
//                getid = rs.getInt(1);
//            }
            String hashedPassword = hashMd5(password);
            PreparedStatement stm2 = this.getConnection().prepareStatement(REGISTER_MEMBER);
//            stm2.setInt(1, getid);
//            stm2.setString(2, fullname);
//            stm2.setString(3, phonenumber);
//            stm2.setString(4, email);
//            stm2.setString(5, password);
//            stm2.setString(6, "active");
            stm2.setString(1, fullname);
            stm2.setString(2, phonenumber);
            stm2.setString(3, email);
            stm2.setString(4, hashedPassword);
            stm2.setString(5, "active");
            stm2.setString(6, "User");

            int check = stm2.executeUpdate();

            if (check == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<User>getALlUser(){
        String sql = "Select * from user_login";
        
        ArrayList<User> listuser = new ArrayList<>();
        
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("Status"),
                        rs.getString("FullName"),
                        rs.getString("PhoneNumber"),
                        rs.getInt("UserID"),
                        rs.getString("Role"));
                listuser.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listuser;
    }
    

    //check email ton tai
    public User getUserByEmail(String email) {
        String sql = "Select * from [user_login] where email = ?";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("Status"),
                        rs.getString("FullName"),
                        rs.getString("PhoneNumber"),
                        rs.getInt("UserID"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User getUserById(int userId) {
        String sql = "Select * from [user_login] where UserID = ?";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("Status"),
                        rs.getString("FullName"),
                        rs.getString("PhoneNumber"),
                        rs.getInt("UserID"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updatePassword(String email, String password) {
        String sql = "UPDATE [dbo].[user_login]\n"
                + "   SET [password] = ?\n"
                + " WHERE [email] = ?";
        try {
            String hashedPassword = hashMd5(password);
            PreparedStatement st = this.getConnection().prepareStatement(sql);
            st.setString(1, hashedPassword);
            st.setString(2, email);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public String hashMd5(String raw) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] mess = md.digest(raw.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : mess) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

}
