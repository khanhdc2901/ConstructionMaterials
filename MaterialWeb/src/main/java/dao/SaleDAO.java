/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static constant.Constants.MAX_ELEMENTS_PER_PAGE;
import db.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Sale;

/**
 *
 * @author Dai Minh Nhu - CE190213
 */
public class SaleDAO extends DBContext {

    int idTop = -1;

    public static void main(String[] args) {
        SaleDAO dao = new SaleDAO();

//        for (int i = 1; i < 10; i++) {
//            System.out.println(getVNDString(String.valueOf((int) Math.pow(10, i))));
////            System.out.println(String.valueOf((int)Math.pow(10, i)));
//        }

            System.out.println(dao.countItem());

//        System.out.println(getVNDString("10000"));
//        System.out.println("2025-07-24 19:45:34.0".compareTo("2025-07-24 19:45:00.0"));
//        List<Sale> salesList = dao.getAll();
//
//        for (Sale sale : salesList) {
//            System.out.println(sale);
//        }
//        System.out.println(dao.stringConvertDateTime("2025-06-21T20:00"));
//        System.out.println(dao.create("Minh Nhu", 100, 0, 10, true, "2025-06-21T20:00", "2025-06-21T09:00"));
//        Sale{id=0, name=, discount=, typeOfDiscount=0, amount=10, coHanSuDung=false, dateStart=, dateEnd=}
    }

    public List<Sale> getAll() {
        List<Sale> list = new ArrayList<>();

        try {
            String query = "SELECT id, name, discount, typeOfDiscount, soLuong, coHanSuDung, dateStart, dateEnd\n"
                    + "FROM Sale\n"
                    + "order by id";

            ResultSet rs = this.executeSelectionQuery(query, null);

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int discount = rs.getInt(3);
                int typeOfDiscount = rs.getInt(4);
                int soLuong = rs.getInt(5);
                boolean soHanSuDung = rs.getBoolean(6);
                String dateStart = rs.getString(7);
                String dateEnd = rs.getString(8);

                Sale sale = new Sale(id, name, discount, typeOfDiscount, soLuong, soHanSuDung, dateStart, dateEnd);

                list.add(sale);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<Sale> getAll(int page) {
        List<Sale> list = new ArrayList<>();

        try {
            String query = "SELECT id, name, discount, typeOfDiscount, soLuong, coHanSuDung, dateStart, dateEnd\n"
                    + "FROM Sale\n"
                    + "order by id\n"
                    + "OFFSET ? ROWS \n"
                    + "FETCH NEXT ? ROWS ONLY;";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{(page - 1) * MAX_ELEMENTS_PER_PAGE, MAX_ELEMENTS_PER_PAGE});

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int discount = rs.getInt(3);
                int typeOfDiscount = rs.getInt(4);
                int soLuong = rs.getInt(5);
                boolean soHanSuDung = rs.getBoolean(6);
                String dateStart = rs.getString(7);
                String dateEnd = rs.getString(8);

                Sale sale = new Sale(id, name, discount, typeOfDiscount, soLuong, soHanSuDung, dateStart, dateEnd);

                list.add(sale);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public Sale getElementByID(int id) {

        try {
            String query = "SELECT name, discount, typeOfDiscount, soLuong, coHanSuDung, dateStart, dateEnd\n"
                    + "FROM Sale where id = ?\n"
                    + "order by id";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{id});

            while (rs.next()) {
                String name = rs.getString(1);
                int discount = rs.getInt(2);
                int typeOfDiscount = rs.getInt(3);
                int soLuong = rs.getInt(4);
                boolean soHanSuDung = rs.getBoolean(5);
                String dateStart = rs.getString(6);
                String dateEnd = rs.getString(7);

                return new Sale(id, name, discount, typeOfDiscount, soLuong, soHanSuDung, dateStart, dateEnd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public int create(String name, int discount, int typeOfDiscount, int soLuong, boolean coHanSuDung, String dateStart, String dateEnd) {
        try {

//            dateStart = stringConvertDateTime(dateStart);
//            dateEnd = stringConvertDateTime(dateEnd);
            String query = "INSERT INTO sale (id, name, discount, typeOfDiscount, soLuong, coHanSuDung, dateStart, dateEnd)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            if (idTop == -1) {
                idTop = getNextIDTop();
            }
            idTop++;

            Sale sale = new Sale(idTop, name, discount, typeOfDiscount, soLuong, coHanSuDung, dateStart, dateEnd);
            System.out.println(sale);

            return this.executeQuery(query, new Object[]{idTop, name, discount, typeOfDiscount, soLuong, coHanSuDung, dateStart, dateEnd});

        } catch (SQLException ex) {
            Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void update(int id, String name, int discount, int typeOfDiscount, int soLuong, boolean coHanSuDung, String dateStart, String dateEnd) {
        try {
            String query = "UPDATE sale\n"
                    + "SET name = ?, discount = ?, typeOfDiscount = ?, soLuong = ?, coHanSuDung = ?, dateStart = ?, dateEnd = ?\n"
                    + "WHERE id = ?;";

//            Sale sale = new Sale(id, name, discount, typeOfDiscount, soLuong, coHanSuDung, dateStart, dateEnd);
//            System.out.println(sale);
            int result = this.executeQuery(query, new Object[]{name, discount, typeOfDiscount, soLuong, coHanSuDung, dateStart, dateEnd, id});

            if (result == 1) {
                System.out.println("Updated");
            } else {
                System.out.println("The ID is not existed!!!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(int id) {
        try {
            String query = "DELETE FROM sale WHERE id = ?;";
            PreparedStatement pstatement = this.getConnection().prepareStatement(query);

            pstatement.setInt(1, id);

            if (this.executeQuery(query, new Object[]{id}) >= 1) {
                System.out.println("Removed a sale");
            } else {
                System.out.println("Removing is failure!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getNextIDTop() {
        try {
            String query = "SELECT TOP 1 id FROM sale ORDER BY id DESC";
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

    public int getNextIDInside() {
        try {
            String query = "select id from sale";
            PreparedStatement pstatement = this.getConnection().prepareStatement(query);
            ResultSet rs = pstatement.executeQuery();

            int pre = -1;

            while (rs.next()) {
                int id = rs.getInt(1);

                if (!(pre == -1 || pre + 1 == id)) {
                    return pre + 1;
                }

                pre = id;
            }

            if (pre == -1) {
                return 0;
            } else {
                return pre + 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
    
    public int countItem() {
        try {
            String query = "select count(id) as numrow from sale";
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
