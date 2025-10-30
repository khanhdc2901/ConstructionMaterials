/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Tieu Gia Huy - CE191594
 */
public class OrderItem {
    
    private int orderId; 
    private int productId;
    private String productName;
    private int price;
    private String unit;
    private int quantity;
    private String status;

    public OrderItem(int productId, String productName, int price, String unit, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.unit = unit;
        this.quantity = quantity;
    }

    public OrderItem(int orderId, String productName, int price, String unit, int quantity, String status) {
        this.orderId = orderId;
        this.productName = productName;
        this.price = price;
        this.unit = unit;
        this.quantity = quantity;
        this.status = status;
    }
    
    // Getters & Setters...
    
    public int getOrderId() {
        return orderId;
    }
   
    public void setOrderId(int orderId) {    
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
