/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import static constant.Constants.*;
import static constant.CommonFunction.getVNDString;
import java.time.LocalDateTime;

/**
 *
 * @author Dai Minh Nhu - CE190213
 */
public class Sale {

    private int id;     // bat dau tu 1, 2, 3; id chi cho 1 san pham duy nhat, co tao moi
    private String name;
    private int discount;
    private int typeOfDiscount;
    private int amount;        //neu khong gioi han so luon thi la INFINITY cua constant
    private boolean coHanSuDung;
    private String dateStart;      //seconds => max 68year 24days
    private String dateEnd;

    public Sale(int id, String name, int discount, int typeOfDiscount, int soLuong, boolean coHanSuDung, String dateStart, String dateEnd) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.typeOfDiscount = typeOfDiscount;
        this.amount = soLuong;
        this.coHanSuDung = coHanSuDung;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTypeOfDiscount() {
        return typeOfDiscount;
    }

    public void setTypeOfDiscount(int typeOfDiscount) {
        this.typeOfDiscount = typeOfDiscount;
    }

    public String getCurrentDiscount() {
        String result = "" + discount;
        
        if (typeOfDiscount == constant.Constants.DIRECT) {
            result = "-" + getVNDString(result);
        } else if (typeOfDiscount == constant.Constants.PERCENT) {
            result = result + "%";
        }
        
        return result;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrentAmount() {
        return (amount < INFINITY) ? ("" + amount) : "Non-limit";
    }

    public boolean isCoHanSuDung() {
        return coHanSuDung;
    }

    public void setCoHanSuDung(boolean coHanSuDung) {
        this.coHanSuDung = coHanSuDung;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isAvailableSale() {
        String[] now = LocalDateTime.now().toString().split("T");

        String date = now[0];
        String time = now[1].substring(0, 10);

        String newNow = date + " " + time;

//        System.out.println(date + " " + time);
//        System.out.println(newNow.compareTo(dateEnd));
        boolean outOfDate;
        boolean notYet;
        boolean notRemaing = amount <= 0;

        if (!coHanSuDung) {
            outOfDate = false;
            notYet = false;
        } else {
            outOfDate = newNow.compareTo(dateEnd) > 0;
            notYet = newNow.compareTo(dateStart) < 0;
        }
        
//        System.out.println(!outOfDate);
//        System.out.println(!notYet);
//        System.out.println(!notRemaing);
//        System.out.println(id);

        return !outOfDate && !notYet && !notRemaing;
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + id + ", name=" + name + ", discount=" + discount + ", typeOfDiscount=" + typeOfDiscount + ", amount=" + amount + ", coHanSuDung=" + coHanSuDung + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + '}';
    }

}
