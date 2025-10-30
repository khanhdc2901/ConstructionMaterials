/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Huynh Thai Duy Phuong - CE190603
 */
public class Category {

    private int id;
    private String name;
    private String iconClass;
    private String textColor;

    // getters and setters
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(int id, String name, String iconClass, String textColor) {
        this.id = id;
        this.name = name;
        this.iconClass = iconClass;
        this.textColor = textColor;
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

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public Category() {
    }

}
