/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static constant.Constants.MAX_ELEMENTS_PER_PAGE;
import db.DBContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TheReview;

/**
 *
 * @author Dai Minh Nhu - CE190213
 */
public class TheReviewDAO extends DBContext {

    public static void main(String[] args) {
        TheReviewDAO dao = new TheReviewDAO();

        dao.create(1, 10, 2, "No money");
//        System.out.println(dao.getAll(1));
    }

    public TheReview getby2ID(int userId, int productId) {

        try {
            String query = "SELECT id, productId, rating, review\n"
                    + "FROM     theReview\n"
                    + "WHERE  (userId = ? and productId = ?)\n";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{userId, productId});

            while (rs.next()) {
                int id = rs.getInt(1);
                int product_Id = rs.getInt(2);
                int rating = rs.getInt(3);
                String review = rs.getString(4);

                return new TheReview(id, userId, product_Id, rating, review);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SaleDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<TheReview> getAll_toProduct_exceptionUser(int productId, int page, int user_id) {
        List<TheReview> list = new ArrayList<>();

        try {
            String query = "SELECT id, userId, rating, review\n"
                    + "FROM theReview\n"
                    + "WHERE (productId = ? AND userId != ?)\n"
                    + "ORDER BY id\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY;";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{productId, user_id, (page - 1) * MAX_ELEMENTS_PER_PAGE, MAX_ELEMENTS_PER_PAGE});

            while (rs.next()) {
                int id = rs.getInt(1);
                int userId = rs.getInt(2);
                int rating = rs.getInt(3);
                String review = rs.getString(4);

                TheReview theReview = new TheReview(id, userId, productId, rating, review);
                list.add(theReview);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SaleDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<TheReview> getAll_toUser(int userId, int page) {
        List<TheReview> list = new ArrayList<>();

        try {
            String query = "SELECT id, productId, rating, review\n"
                    + "FROM     theReview\n"
                    + "WHERE  (userId = ?)\n"
                    + "ORDER BY id\n"
                    + "OFFSET ? ROWS \n"
                    + "FETCH NEXT ? ROWS ONLY;";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{userId, (page - 1) * MAX_ELEMENTS_PER_PAGE, MAX_ELEMENTS_PER_PAGE});

            while (rs.next()) {
                int id = rs.getInt(1);
                int productId = rs.getInt(2);
                int rating = rs.getInt(3);
                String review = rs.getString(4);

                TheReview theReview = new TheReview(id, userId, productId, rating, review);
                list.add(theReview);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SaleDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<TheReview> getAll_toProduct(int productId, int page) {
        List<TheReview> list = new ArrayList<>();

        try {
            String query = "SELECT id, userId, rating, review\n"
                    + "FROM     theReview\n"
                    + "WHERE  (productId = ?)\n"
                    + "ORDER BY id\n"
                    + "OFFSET ? ROWS \n"
                    + "FETCH NEXT ? ROWS ONLY;";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{productId, (page - 1) * MAX_ELEMENTS_PER_PAGE, MAX_ELEMENTS_PER_PAGE});

            while (rs.next()) {
                int id = rs.getInt(1);
                int userId = rs.getInt(2);
                int rating = rs.getInt(3);
                String review = rs.getString(4);

                TheReview theReview = new TheReview(id, userId, productId, rating, review);
                list.add(theReview);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SaleDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<TheReview> getAll(int page) {
        List<TheReview> list = new ArrayList<>();

        try {
            String query = "SELECT id, userId, productId, rating, review\n"
                    + "FROM theReview\n"
                    + "ORDER BY id\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY;";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{(page - 1) * MAX_ELEMENTS_PER_PAGE, MAX_ELEMENTS_PER_PAGE});

            while (rs.next()) {
                int id = rs.getInt(1);
                int userId = rs.getInt(2);
                int productId = rs.getInt(3);
                int rating = rs.getInt(4);
                String review = rs.getString(5);

                TheReview theReview = new TheReview(id, userId, productId, rating, review);
                list.add(theReview);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SaleDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public boolean haveComment(int userId, int product_id) {

        try {
            String query = "SELECT id\n"
                    + "FROM     theReview\n"
                    + "WHERE  (userId = ?) AND (productID = ?)";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{userId, product_id});

            while (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SaleDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public int create(int userId, int productId, int rating, String message) {
        try {
            String query = "INSERT INTO [dbo].[theReview]\n"
                    + "           ([id]\n"
                    + "           ,[userId]\n"
                    + "           ,[productID]\n"
                    + "           ,[rating]\n"
                    + "           ,[review])\n"
                    + "     VALUES (?, ?, ?, ?, ?)";
            return this.executeQuery(query, new Object[]{getNextIDTop() + 1, userId, productId, rating, message});
        } catch (SQLException ex) {
            System.out.println("Error creating");
        }

        return 0;
    }
    
    public int remove(int userId) {
        try {
            String query = "DELETE FROM [dbo].[theReview]\n"
                    + "WHERE userId = ?";
            return this.executeQuery(query, new Object[]{userId});
        } catch (SQLException ex) {
            System.out.println("Error creating");
        }

        return 0;
    }

    public int remove(int userId, int productId) {
        try {
            String query = "DELETE FROM [dbo].[theReview]\n"
                    + "WHERE userId = ? and productID = ?";
            return this.executeQuery(query, new Object[]{userId, productId});
        } catch (SQLException ex) {
            System.out.println("Error creating");
        }

        return 0;
    }

//
//    public Sale getElementByID(int id) {
//
//        try {
//            String query = "SELECT name, discount, typeOfDiscount, soLuong, coHanSuDung, dateStart, dateEnd\n"
//                    + "FROM Sale where id = ?\n"
//                    + "order by id";
//
//            ResultSet rs = this.executeSelectionQuery(query, new Object[]{id});
//
//            while (rs.next()) {
//                String name = rs.getString(1);
//                int  discount = rs.getInt(2);
//                int typeOfDiscount = rs.getInt(3);
//                int soLuong = rs.getInt(4);
//                boolean soHanSuDung = rs.getBoolean(5);
//                String dateStart = rs.getString(6);
//                String dateEnd = rs.getString(7);
//
//                return new Sale(id, name, discount, typeOfDiscount, soLuong, soHanSuDung, dateStart, dateEnd);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return null;
//    }
//
//    public int create(String name, int discount, int typeOfDiscount, int soLuong, boolean coHanSuDung, String dateStart, String dateEnd) {
//        try {
//
////            dateStart = stringConvertDateTime(dateStart);
////            dateEnd = stringConvertDateTime(dateEnd);
//            String query = "INSERT INTO sale (id, name, discount, typeOfDiscount, soLuong, coHanSuDung, dateStart, dateEnd)\n"
//                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
//            if (idTop == -1) {
//                idTop = getNextIDTop();
//            }
//            idTop++;
//
//            Sale sale = new Sale(idTop, name, discount, typeOfDiscount, soLuong, coHanSuDung, dateStart, dateEnd);
//            System.out.println(sale);
//
//            return this.executeQuery(query, new Object[]{idTop, name, discount, typeOfDiscount, soLuong, coHanSuDung, dateStart, dateEnd});
//
//        } catch (SQLException ex) {
//            Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return -1;
//    }
//
//    public void update(int id, String name, int discount, int typeOfDiscount, int soLuong, boolean coHanSuDung, String dateStart, String dateEnd) {
//        try {
//            String query = "UPDATE sale\n"
//                    + "SET name = ?, discount = ?, typeOfDiscount = ?, soLuong = ?, coHanSuDung = ?, dateStart = ?, dateEnd = ?\n"
//                    + "WHERE id = ?;";
//
////            Sale sale = new Sale(id, name, discount, typeOfDiscount, soLuong, coHanSuDung, dateStart, dateEnd);
////            System.out.println(sale);
//            int result = this.executeQuery(query, new Object[]{name, discount, typeOfDiscount, soLuong, coHanSuDung, dateStart, dateEnd, id});
//
//            if (result == 1) {
//                System.out.println("Updated");
//            } else {
//                System.out.println("The ID is not existed!!!");
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void remove(int id) {
//        try {
//            String query = "DELETE FROM sale WHERE id = ?;";
//            PreparedStatement pstatement = this.getConnection().prepareStatement(query);
//
//            pstatement.setInt(1, id);
//
//            if (this.executeQuery(query, new Object[]{id}) >= 1) {
//                System.out.println("Removed a sale");
//            } else {
//                System.out.println("Removing is failure!");
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
    public int getNextIDTop() {
        try {
            String query = "SELECT TOP (1) id\n"
                    + "FROM     theReview\n"
                    + "ORDER BY id DESC";
            ResultSet rs = this.executeSelectionQuery(query, null);

            int id = -1;
            while (rs.next()) {
                id = rs.getInt(1);
            }

            return id;

        } catch (SQLException ex) {
            Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }
//
//    public int getNextIDInside() {
//        try {
//            String query = "select ArtistId from Artist";
//            PreparedStatement pstatement = this.getConnection().prepareStatement(query);
//            ResultSet rs = pstatement.executeQuery();
//
//            int pre = -1;
//
//            while (rs.next()) {
//                int id = rs.getInt(1);
//
//                if (!(pre == -1 || pre + 1 == id)) {
//                    return pre + 1;
//                }
//
//                pre = id;
//            }
//
//            if (pre == -1) {
//                return 0;
//            } else {
//                return pre + 1;
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return 0;
//    }

    public int countItem_toUser(int userId) {
        try {
            String query = "select count(id) as numrow from theReview where UserID = ?";
            ResultSet rs = this.executeSelectionQuery(query, new Object[]{userId});
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error");
        }

        return 0;
    }

    public int countItem_toProduct(int productId) {
        try {
            String query = "select count(id) as numrow from theReview where productID = ?";
            ResultSet rs = this.executeSelectionQuery(query, new Object[]{productId});
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error");
        }

        return 0;
    }

    public int countItem() {
        try {
            String query = "select count(id) as numrow from theReview";
            ResultSet rs = this.executeSelectionQuery(query, null);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error");
        }

        return 0;
    }
}
