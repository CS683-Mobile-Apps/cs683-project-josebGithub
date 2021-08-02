package edu.bu.metcs.myproject.Database;

import java.util.Date;

public class MyFoodManagerDBContract {
    public static final String DBName = "myFoodManager.DB";
    public static final int DB_VERION = 1;

    public static final class MyFoodManagerContract {

        //Table for food spaces
        public static final String FOOD_SPACE_TABLE_NAME = "food_spaces";
        public static final String COLUMN_FOOD_SPACE_ID = "food_space_id";
        public static final String COLUMN_FOOD_SPACE_TITLE = "food_space_title";

        //Table for food items
        public static final String FOOD_ITEM_TABLE_NAME = "food_items";
        public static final String COLUMN_FOOD_ITEM_ID = "food_item_id";
        public static final String COLUMN_FOOD_SPACE_ITEM_ID = "food_space_item_id";
        public static final String COLUMN_FOOD_NAME = "food_name";
        public static final String COLUMN_FOOD_TYPE = "food_type";
        public static final String COLUMN_FOOD_EXPIRY_DATE = "food_expiry_date";
        public static final String COLUMN_FOOD_QUANTITY = "food_quantity";
        public static final String COLUMN_FOOD_COST = "food_cost";
    }

    public static final String CREATE_FOOD_SPACE_TABLE = "CREATE TABLE " +
            MyFoodManagerContract.FOOD_SPACE_TABLE_NAME + "(" + MyFoodManagerContract.COLUMN_FOOD_SPACE_ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            MyFoodManagerContract.COLUMN_FOOD_SPACE_TITLE + " TEXT);";

    public static final String CREATE_FOOD_ITEM_TABLE = "CREATE TABLE " +
            MyFoodManagerContract.FOOD_ITEM_TABLE_NAME + "(" + MyFoodManagerContract.COLUMN_FOOD_ITEM_ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            MyFoodManagerContract.COLUMN_FOOD_SPACE_ITEM_ID + " INTEGER NOT NULL," +
            MyFoodManagerContract.COLUMN_FOOD_NAME + " TEXT NOT NULL," +
            MyFoodManagerContract.COLUMN_FOOD_TYPE + " TEXT NOT NULL," +
            MyFoodManagerContract.COLUMN_FOOD_EXPIRY_DATE + " TEXT NOT NULL," +
            MyFoodManagerContract.COLUMN_FOOD_QUANTITY+ " TEXT NOT NULL," +
            MyFoodManagerContract.COLUMN_FOOD_COST + " TEXT NOT NULL);";
}
