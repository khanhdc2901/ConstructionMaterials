/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Tieu Gia Huy - CE191594
 */
public class Account {
    private int id;
    private String userName;
    private String passWord;
    private int isUser;
    private int isAdmin;
    private String email;

    public Account() {
    }

    public Account(int id, String userName, String passWord, int isUser, int isAdmin) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.isUser = isUser;
        this.isAdmin = isAdmin;
    }

    public Account(int id, String userName, String passWord, int isUser, int isAdmin, String email) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.isUser = isUser;
        this.isAdmin = isAdmin;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getIsUser() {
        return isUser;
    }

    public void setIsUser(int isUser) {
        this.isUser = isUser;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", isUser=" + isUser + ", isAdmin=" + isAdmin + '}';
    }
    
}
