package edu.bu.metcs.myproject.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import edu.bu.metcs.myproject.FoodItem;

public class MyFoodManagerDBHelper extends SQLiteOpenHelper {
    private final static String TAG = MyFoodManagerDBHelper.class.getSimpleName ();
    SQLiteDatabase sqLiteDatabase;

    public MyFoodManagerDBHelper(Context context) {
        super(context, MyFoodManagerDBContract.DBName, null, MyFoodManagerDBContract.DB_VERION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "Create Tables...");

        String insertFridgeStmt = "INSERT INTO "+MyFoodManagerDBContract.MyFoodManagerContract.FOOD_SPACE_TABLE_NAME+
                " VALUES "+"(0,'REFRIGERATOR')";

        String insertFreezerStmt = "INSERT INTO "+MyFoodManagerDBContract.MyFoodManagerContract.FOOD_SPACE_TABLE_NAME+
                " VALUES "+"(1,'FREEZER')";

        String insertPantryStmt = "INSERT INTO "+MyFoodManagerDBContract.MyFoodManagerContract.FOOD_SPACE_TABLE_NAME+
                " VALUES "+"(2,'PANTRY')";

        String insertCabinetStmt = "INSERT INTO "+MyFoodManagerDBContract.MyFoodManagerContract.FOOD_SPACE_TABLE_NAME+
                " VALUES "+"(3,'KITCHEN CABINET')";

        sqLiteDatabase.execSQL(MyFoodManagerDBContract.CREATE_FOOD_SPACE_TABLE);
        sqLiteDatabase.execSQL(insertFridgeStmt);
        sqLiteDatabase.execSQL(insertFreezerStmt);
        sqLiteDatabase.execSQL(insertPantryStmt);
        sqLiteDatabase.execSQL(insertCabinetStmt);

        String insertCheeseItemStmt = "INSERT INTO "+MyFoodManagerDBContract.MyFoodManagerContract.FOOD_ITEM_TABLE_NAME+
                " VALUES "+"(0, 0, 'Cheddar Cheese', 'dairy', '08/15/2021', '12 pieces', '5.60')";

        String insertSalmonItemStmt = "INSERT INTO "+MyFoodManagerDBContract.MyFoodManagerContract.FOOD_ITEM_TABLE_NAME+
                " VALUES "+"(1, 0, 'Salmon', 'fish', '08/10/2021', '1', '10.80')";

        sqLiteDatabase.execSQL(MyFoodManagerDBContract.CREATE_FOOD_ITEM_TABLE);
        sqLiteDatabase.execSQL(insertCheeseItemStmt);
        sqLiteDatabase.execSQL(insertSalmonItemStmt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }


}


