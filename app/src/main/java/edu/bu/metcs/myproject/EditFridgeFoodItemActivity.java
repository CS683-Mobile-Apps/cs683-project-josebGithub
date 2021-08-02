package edu.bu.metcs.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.bu.metcs.myproject.Database.MyFoodManagerDao;

public class EditFridgeFoodItemActivity extends AppCompatActivity {

    private final static String TAG = HomePageActivity.class.getSimpleName ();
    MyFoodManagerDao myFoodManagerDao;
    EditFridgeFoodItemFragment editFridgeFoodItemFragment;
    private int foodspaceId, itemId;
    private FoodItem foodItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fridge_food_item);

        myFoodManagerDao = myFoodManagerDao.getInstance(getApplicationContext());
      //  foodItem = myFoodManagerDao.getFoodItemById(foodspaceId, itemId);

        //add fragments dynamically
        //create a fragment object
        editFridgeFoodItemFragment = new EditFridgeFoodItemFragment();
        editFridgeFoodItemFragment.setArguments(getIntent().getExtras());
        // get the reference to the FragmentManger object
        FragmentManager fragManager = getSupportFragmentManager();
        // get the reference to the FragmentTransaction object
        FragmentTransaction transaction = fragManager.beginTransaction();
        // add the fragment into the transaction
        transaction.add(R.id.editFridgeFoodItemContainer, editFridgeFoodItemFragment);
        // commit the transaction.
        transaction.commit();
    }


    public void onClickSave(View view) throws ParseException {
        Intent intent = getIntent();
        foodspaceId = intent.getIntExtra("foodspaceId", -1);
        itemId = intent.getIntExtra("itemId", -1);
        Log.d(TAG, "Edit - foodspaceId : "+foodspaceId);
        Log.d(TAG, "Edit - itemId : "+itemId);

        EditText foodNameView = findViewById(R.id.foodNameId);
        String foodName = foodNameView.getText().toString();

       // Spinner spinner = (Spinner) findViewById(R.id.foodTypeSpinnerId);
        EditText foodTypeView = findViewById(R.id.foodTypeId);
        String foodType = foodTypeView.getText().toString();

        EditText expiryDateView = findViewById(R.id.expiryDateId);
        String cExpiryDate = expiryDateView.getText().toString();
        //Date expiryDate = new SimpleDateFormat("MM/dd/yyyy").parse(cExpiryDate);

        EditText quantityEditView = findViewById(R.id.quantityId);
        String quantity = quantityEditView.getText().toString();

        EditText costEditView= findViewById(R.id.costId);
        String dCost = costEditView.getText().toString();
        Double cost=0.0;
        if(!dCost.isEmpty()) {
            try {
                cost = Double.parseDouble(dCost);
                // it means it is double
            } catch (Exception e) {
                // this means it is not double
                e.printStackTrace();
            }
        }

        Log.d("TAG", "itemId : "+itemId);
        Log.d("TAG", "foodspaceId : "+foodspaceId);
        FoodItem updatedFoodItem = new FoodItem(itemId, foodspaceId, foodName, foodType, cExpiryDate, quantity, cost);
        myFoodManagerDao.updateFoodItemById(updatedFoodItem, itemId, foodspaceId);
        //updateFoodItem(foodspaceId, itemId, foodName, foodType, expiryDate.toString(), quantity, cost);
        Log.d(TAG, "Call RefrigeratorFoodListActivity.class");
        Intent intent1 = new Intent(this, RefrigeratorFoodListActivity.class);
        intent1.putExtra("FOODSPACE_ID", foodspaceId);
        startActivity(intent1);
    }

    public void updateFoodItem(int foodspaceId, int itemId, String foodName, String foodType, String expiryDate, String quantity, Double cost) {
        FoodSpace.foodSpaces[foodspaceId].foodItems.get(itemId).setName(foodName);
        FoodSpace.foodSpaces[foodspaceId].foodItems.get(itemId).setType(foodType);
        FoodSpace.foodSpaces[foodspaceId].foodItems.get(itemId).setExpirationDate(expiryDate);
        FoodSpace.foodSpaces[foodspaceId].foodItems.get(itemId).setQuantity(quantity);
        FoodSpace.foodSpaces[foodspaceId].foodItems.get(itemId).setCost(cost);
    }

}