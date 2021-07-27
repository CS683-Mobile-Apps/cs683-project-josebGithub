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

public class AddFridgeFoodItemsActivity extends AppCompatActivity {

    private final static String TAG = AddFridgeFoodItemsActivity.class.getSimpleName ();
    AddFridgeFoodItemsFragment addFridgeFoodItemsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fridge_food_items);

        //add fragments dynamically
        //create a fragment object
        addFridgeFoodItemsFragment = new AddFridgeFoodItemsFragment();
        addFridgeFoodItemsFragment.setArguments(getIntent().getExtras());
        // get the reference to the FragmentManger object
        FragmentManager fragManager = getSupportFragmentManager();
        // get the reference to the FragmentTransaction object
        FragmentTransaction transaction = fragManager.beginTransaction();
        // add the fragment into the transaction
        transaction.add(R.id.addFridgeFoodItemsContainer, addFridgeFoodItemsFragment);
        // commit the transaction.
        transaction.commit();
    }


    public void onClickSave(View view) throws ParseException {


         EditText foodNameView = findViewById(R.id.foodNameId);
         String foodName = foodNameView.getText().toString();

         Spinner spinner = (Spinner) findViewById(R.id.foodTypeSpinnerId);
         String foodType = spinner.getSelectedItem().toString();

         //EditText foodTypeView =  view.findViewById(R.id.foodTypeId);
        // String foodType = foodTypeView.getText().toString();

         EditText expiryDateView = findViewById(R.id.expiryDateId);
         String cExpiryDate = expiryDateView.getText().toString();
         Date expiryDate = new SimpleDateFormat("MM/dd/yyyy").parse(cExpiryDate);

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

        FoodItem foodItems = new FoodItem(foodName, foodType, expiryDate, quantity, cost);
        for (int i=0; i<FoodSpace.foodSpaces.length;i++) {
            if (FoodSpace.foodSpaces[i].getTitle().equals("REFRIGERATOR")) {
                Log.d(TAG, "Add Food Item");
                FoodSpace.foodSpaces[i].foodItems.add(foodItems);
                break;
            }
        }

        Log.d(TAG, "Call RefrigeratorFoodListActivity.class");
        Intent intent = new Intent(this, RefrigeratorFoodListActivity.class);
        startActivity(intent);
    }

}