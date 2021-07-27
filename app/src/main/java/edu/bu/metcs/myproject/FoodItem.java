package edu.bu.metcs.myproject;

import java.util.Date;

public class FoodItem {

    private String name;
    private String type;
    private Date expirationDate;
    private String quantity;
    private double cost;

    public FoodItem(String name, String type, Date expirationDate, String quantity, double cost) {
        this.name = name;
        this.type = type;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "FoodItems{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", expirationDate=" + expirationDate +
                ", quantity='" + quantity + '\'' +
                ", cost=" + cost +
                '}';
    }
}
