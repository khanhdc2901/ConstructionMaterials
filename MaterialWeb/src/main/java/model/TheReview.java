/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.ProductDao;
import dao.UserDAO;

/**
 *
 * @author Dai Minh Nhu - CE190213
 */
public class TheReview {

    private int id;
    private User user;
    private Product product;
    private int rating;
    private String review;
    

    public TheReview(int id, int user_id, int product_id, int rating, String review) {
        ProductDao productDao = new ProductDao();
        UserDAO userDAO = new UserDAO();

        this.id = id;
        this.user = userDAO.getById(user_id);
        this.product = productDao.getById(product_id);
        this.rating = rating;
        this.review = review;
    }

//    @Override
//    public String toString() {
//        return "TheReview{" + "id=" + id + ", user_id=" + user_id + ", product_id=" + product_id + ", rating=" + rating + ", review=" + review + '}';
//    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getRating() {
        this.rating = realRating(this.rating);

        return rating;
    }

    public void setRating(int rating) {

        this.rating = realRating(rating);
    }

    public int realRating(int rate) {
        if (rate > 5) {
            rate = 5;
        }
        if (rate < 1) {
            rate = 1;
        }
        return rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

}
