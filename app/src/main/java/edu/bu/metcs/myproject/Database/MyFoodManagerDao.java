package edu.bu.metcs.myproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import edu.bu.metcs.myproject.FoodItem;
import edu.bu.metcs.myproject.FoodSpace;

public class MyFoodManagerDao {

    private final static String TAG = MyFoodManagerDao.class.getSimpleName();
    public static MyFoodManagerDao instance;

    public MyFoodManagerDBHelper myFoodManagerDBHelper;
    public SQLiteDatabase mReadableDB, mWriteableDB;

    public MyFoodManagerDao(Context context) {
        myFoodManagerDBHelper = new MyFoodManagerDBHelper(context);
    }

    public void openDB() {
        mReadableDB = myFoodManagerDBHelper.getReadableDatabase();
        mWriteableDB = myFoodManagerDBHelper.getWritableDatabase();
    }

    public void closeDB() {
        mReadableDB.close();
        mWriteableDB.close();
    }

    public static MyFoodManagerDao getInstance(Context context) {
        if (instance == null)
            instance = new MyFoodManagerDao(context);
        return instance;
    }


    public long addFoodSpace(FoodSpace foodspace) {
        ContentValues foodSpaceValue = new ContentValues();

        foodSpaceValue.put(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_TITLE, foodspace.getTitle());

        return mWriteableDB.insert(MyFoodManagerDBContract.MyFoodManagerContract.FOOD_SPACE_TABLE_NAME, null, foodSpaceValue);
    }

    public long addFoodItem(int foodspaceId, FoodItem fooditem) {
        ContentValues foodItemValue = new ContentValues();

        foodItemValue.put(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_ITEM_ID, foodspaceId);
        foodItemValue.put(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_NAME, fooditem.getName());
        foodItemValue.put(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_TYPE, fooditem.getType());
        foodItemValue.put(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_EXPIRY_DATE, fooditem.getExpirationDate().toString());
        foodItemValue.put(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_QUANTITY, fooditem.getQuantity());
        foodItemValue.put(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_COST, fooditem.getCost());

        return mWriteableDB.insert(MyFoodManagerDBContract.MyFoodManagerContract.FOOD_ITEM_TABLE_NAME, null, foodItemValue);
    }

    public ArrayList<FoodSpace> getAllFoodSpaces() {

        String[] foodspaceValues = {MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_ID,
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_TITLE};


        Cursor foodspaceCursor = mReadableDB.query(MyFoodManagerDBContract.MyFoodManagerContract.FOOD_SPACE_TABLE_NAME,
                foodspaceValues, null, null, null, null, null);


        ArrayList<FoodSpace> foodspaces = new ArrayList<FoodSpace>();

        while (foodspaceCursor.moveToNext()) {
            int foodspaceId = foodspaceCursor.getInt(foodspaceCursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_ID));
            String foodspaceTitle = foodspaceCursor.getString(foodspaceCursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_TITLE));

            foodspaces.add(new FoodSpace(foodspaceTitle));
        } // while

        foodspaceCursor.close();
        return foodspaces;
    }

    public ArrayList<FoodItem> getAllFoodItems(int foodspaceId) {

        String[] fooditemValues = {MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_ITEM_ID,
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_NAME,
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_TYPE,
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_EXPIRY_DATE,
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_QUANTITY,
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_COST};

        String selection = MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_ITEM_ID + "=?";
        String[] selectionArgs = {Integer.toString(foodspaceId)};

        Cursor fooditemCursor = mReadableDB.query(MyFoodManagerDBContract.MyFoodManagerContract.FOOD_ITEM_TABLE_NAME,
                fooditemValues, selection, selectionArgs, null, null, null);


        ArrayList<FoodItem> fooditems = new ArrayList<FoodItem>();

        while (fooditemCursor.moveToNext()) {
            int fooditemId = fooditemCursor.getInt(fooditemCursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_ITEM_ID));
            String fooditemName = fooditemCursor.getString(fooditemCursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_NAME));
            String fooditemType = fooditemCursor.getString(fooditemCursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_TYPE));
            String fooditemExpirydate = fooditemCursor.getString(fooditemCursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_EXPIRY_DATE));
            String fooditemQuantity = fooditemCursor.getString(fooditemCursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_QUANTITY));
            String fooditemCost = fooditemCursor.getString(fooditemCursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_COST));


            fooditems.add(new FoodItem(fooditemId, foodspaceId, fooditemName, fooditemType, fooditemExpirydate, fooditemQuantity, Double.parseDouble(fooditemCost)));
        } // while

        fooditemCursor.close();
        return fooditems;
    }

    public FoodSpace getFoodSpaceById(int foodspaceId) {
        String[] foodspaceValues = {MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_ID,
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_TITLE};

        String selection = MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_ID + "=?";
        String[] selectionArgs = {Integer.toString(foodspaceId)};

        Cursor cursor = mReadableDB.query(MyFoodManagerDBContract.MyFoodManagerContract.FOOD_SPACE_TABLE_NAME,
                foodspaceValues, selection, selectionArgs, null, null, null);

        cursor.moveToFirst();

        String foodspaceTitle = cursor.getString(cursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_TITLE));

        FoodSpace foodspace = new FoodSpace(foodspaceId, foodspaceTitle);

        cursor.close();
        return foodspace;
    }

    public FoodItem getFoodItemById(int fooditemId, int foodspaceId) {
        String[] fooditemValues = {MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_ITEM_ID,
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_ITEM_ID,
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_NAME,
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_TYPE,
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_EXPIRY_DATE,
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_QUANTITY,
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_COST};

        String selection = MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_ITEM_ID + "=? AND " +
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_ITEM_ID + "=?";

        String[] selectionArgs = {Integer.toString(fooditemId), Integer.toString(foodspaceId)};

        Cursor cursor = mReadableDB.query(MyFoodManagerDBContract.MyFoodManagerContract.FOOD_ITEM_TABLE_NAME,
                fooditemValues, selection, selectionArgs, null, null, null);

        cursor.moveToFirst();

        String fooditemName = cursor.getString(cursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_NAME));
        String fooditemType = cursor.getString(cursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_TYPE));
        String fooditemExpirydate = cursor.getString(cursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_EXPIRY_DATE));
        String fooditemQuantity = cursor.getString(cursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_QUANTITY));
        String fooditemCost = cursor.getString(cursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_COST));

        Log.d("TAG", "fooditemId : " + fooditemId);
        Log.d("TAG", "fooditemId : " + fooditemName);
        Log.d("TAG", "fooditemId : " + fooditemType);
        Log.d("TAG", "fooditemId : " + fooditemExpirydate);

        FoodItem fooditem = new FoodItem(fooditemId, foodspaceId, fooditemName, fooditemType, fooditemExpirydate, fooditemQuantity, Double.parseDouble(fooditemCost));

        cursor.close();
        return fooditem;
    }

    public String[] getAllFoodSpaceTitles() {

        String[] foodspaceTitleValues = {MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_ID,
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_TITLE};


        Cursor foodspaceTitleCursor = mReadableDB.query(MyFoodManagerDBContract.MyFoodManagerContract.FOOD_SPACE_TABLE_NAME,
                foodspaceTitleValues, null, null, null, null, null);


        String[] foodspaceTitles = new String[foodspaceTitleCursor.getCount()];

        while (foodspaceTitleCursor.moveToNext()) {
            String foodspaceId = foodspaceTitleCursor.getString(foodspaceTitleCursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_ID));
            String foodspaceTitle = foodspaceTitleCursor.getString(foodspaceTitleCursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_TITLE));

            foodspaceTitles[Integer.parseInt(foodspaceId)] = foodspaceTitle;
        } // while

        foodspaceTitleCursor.close();
        return foodspaceTitles;
    }

    public long updateFoodItemById(FoodItem fooditem, int fooditemId, int foodspaceId) {

        String selection = MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_ITEM_ID + "=? AND " +
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_ITEM_ID + "=?";
        String[] selectionArgs = {Integer.toString(fooditemId), Integer.toString(foodspaceId)};


        ContentValues fooditemValue = new ContentValues();
        fooditemValue.put(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_NAME, fooditem.getName());
        fooditemValue.put(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_TYPE, fooditem.getType());
        fooditemValue.put(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_EXPIRY_DATE, fooditem.getExpirationDate().toString());
        fooditemValue.put(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_QUANTITY, fooditem.getQuantity());
        fooditemValue.put(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_COST, fooditem.getCost());


        long numberOfRows = mWriteableDB.update(MyFoodManagerDBContract.MyFoodManagerContract.FOOD_ITEM_TABLE_NAME,
                fooditemValue, selection, selectionArgs);

        return numberOfRows;
    }


    public long delectFoodItemById(int itemId) {
        String selection = MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_ITEM_ID + "=?";
        String[] selectionArgs = {itemId + ""};

        return mWriteableDB.delete(MyFoodManagerDBContract.MyFoodManagerContract.FOOD_ITEM_TABLE_NAME,
                selection, selectionArgs);

    }

    public int getFoodSpaceId(int fooditemId) {
        String[] fooditemValues = {MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_ITEM_ID,
                MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_ITEM_ID};

        String selection = MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_ITEM_ID + "=?";

        String[] selectionArgs = {Integer.toString(fooditemId)};

        Cursor cursor = mReadableDB.query(MyFoodManagerDBContract.MyFoodManagerContract.FOOD_ITEM_TABLE_NAME,
                fooditemValues, selection, selectionArgs, null, null, null);

        cursor.moveToFirst();

        int foodspaceId = cursor.getInt(cursor.getColumnIndex(MyFoodManagerDBContract.MyFoodManagerContract.COLUMN_FOOD_SPACE_ITEM_ID));


        cursor.close();
        return foodspaceId;
    }

}
