package edu.bu.metcs.myproject;

import java.util.ArrayList;

public class FoodSpace {

    private String title;
    public ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();

    //public final static Project[] projects = {
    public static FoodSpace[] foodSpaces = {
            new FoodSpace("REFRIGERATOR"), new FoodSpace("FREEZER"),
            new FoodSpace("PANTRY"), new FoodSpace("KITCHEN CABINET")
    };


    public FoodSpace(String title) {
        this.title = title;
        //this.foodItems = new ArrayList<>();
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
