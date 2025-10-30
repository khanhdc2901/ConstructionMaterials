/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.AuthDAO;

/**
 *
 * @author Nguyen Thanh Nhan - CE190122
 */
public class User {

    private String username;
    private String email;
    private String password;
    private String status;
    private String fullName;
    private String phonenumbers;
    private int userid;
    private String role;
    private String avatar;

    public User(String email, String password, String status, String fullName, String phonenumbers, int userid, String role) {
        this.email = email;
        this.password = password;
        this.status = status;
        this.fullName = fullName;
        this.phonenumbers = phonenumbers;
        this.userid = userid;
        this.role = role;
        
    }

    public User(String email, String password, String status, String fullName, String phonenumbers, int userid) {
        this.email = email;
        this.password = password;
        this.status = status;
        this.fullName = fullName;
        this.phonenumbers = phonenumbers;
        this.userid = userid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhonenumbers() {
        return phonenumbers;
    }

    public void setPhonenumbers(String phonenumbers) {
        this.phonenumbers = phonenumbers;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User() {
    }

    public User(String username, String fullName, String email, String password, String avatar) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public boolean isAdmin() {
        return role != null && role.equalsIgnoreCase("Admin");
    }

    public boolean checkpassdelete(String passwordcheck) {

        AuthDAO dao = new AuthDAO();

        String passhashcheck = dao.hashMd5(passwordcheck);
        if (passhashcheck == null || this.password == null) {
            return false;
        }
        return passhashcheck.equals(this.password);
    }
}
