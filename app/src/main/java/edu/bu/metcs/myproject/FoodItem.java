package edu.bu.metcs.myproject;

import java.util.Date;

public class FoodItem {

    private int id;
    private int spaceId;
    private String name;
    private String type;
    private String expirationDate;
    private String quantity;
    private double cost;

    public FoodItem(int spaceId, String name, String type, String expirationDate, String quantity, double cost) {
        this.spaceId = spaceId;
        this.name = name;
        this.type = type;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.cost = cost;
    }

    public FoodItem(int id, int spaceId, String name, String type, String expirationDate, String quantity, double cost) {
        this.id = id;
        this.spaceId = spaceId;
        this.name = name;
        this.type = type;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
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
