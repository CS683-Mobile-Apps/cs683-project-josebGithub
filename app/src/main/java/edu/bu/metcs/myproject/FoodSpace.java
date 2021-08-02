package edu.bu.metcs.myproject;

import java.util.ArrayList;

public class FoodSpace {

    private int id;
    private String title;
    public ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();

    //public final static Project[] projects = {
    public static FoodSpace[] foodSpaces = {
            new FoodSpace("REFRIGERATOR"), new FoodSpace("FREEZER"),
            new FoodSpace("PANTRY"), new FoodSpace("KITCHEN CABINET")
    };


    public FoodSpace(String title) {
        this.title = title;
    }

    public FoodSpace(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    @Override
    public String toString() {
        return "FoodSpaces{" +
                "title='" + title + '\'' +
                ", FoodItems=" + foodItems +
                '}';
    }
}
