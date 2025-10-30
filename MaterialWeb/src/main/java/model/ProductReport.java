/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Tieu Gia Huy - CE191594
 */
public class ProductReport {

    public int productId;
    public int quantity;
    public int revenue;

    public ProductReport(int productId, int quantity, int revenue) {
        this.productId = productId;
        this.quantity = quantity;
        this.revenue = revenue;
    }

    // Getters

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRevenue() {
        return revenue;
    }
}
