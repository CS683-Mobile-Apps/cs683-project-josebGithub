package edu.bu.metcs.myproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.text.ParseException;

import edu.bu.metcs.myproject.Database.MyFoodManagerDao;

public class RefrigeratorFoodListActivity extends AppCompatActivity implements FridgeFoodListAdapter.Listener {

    private final static String TAG = RefrigeratorFoodListActivity.class.getSimpleName ();
    RefrigeratorFoodListFragment fridgeFoodListFragment;
    MyFoodManagerDao myFoodManagerDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge_food_list);

        final int foodspaceId = getIntent().getExtras().getInt("FOODSPACE_ID");
        //add fragments dynamically
        //create a fragment object
      //  fridgeFoodListFragment = new RefrigeratorFoodListFragment();
     //   fridgeFoodListFragment.setArguments(getIntent().getExtras());

        final FloatingActionButton foodItemAddButton = findViewById(R.id.add_float_button);

        foodItemAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddFridgeFoodItemsActivity.class);
                intent.putExtra("addfoodspaceId", foodspaceId);
                startActivity(intent);
            }
        });


        BottomNavigationView bottomNav = findViewById(R.id.backtomainNavigationView);

        openFragment(new RefrigeratorFoodListFragment());


        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.fridgeIcon:
                        openFragment(new RefrigeratorFoodListFragment());
                        foodItemAddButton.show();
                        return true;

                    case R.id.food_spaces_icon:
                        openFragment(new HomePageListFragment());
                        foodItemAddButton.hide();
                        return true;

                }

                return false;
            }
        });
    }

    void openFragment(Fragment fragment){
        fragment.setArguments(getIntent().getExtras());
        // get the reference to the FragmentManger object
        FragmentManager fragManager = getSupportFragmentManager();
        // get the reference to the FragmentTransaction object
        FragmentTransaction transaction = fragManager.beginTransaction();
        // add the fragment into the transaction
        transaction.replace(R.id.fridgeFoodListContainer, fragment);
        transaction.addToBackStack(null);
      //  transaction.add(R.id.fridgeFoodListContainer,fridgeFoodListFragment);
        // commit the transaction.
        transaction.commit();

        /**
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
         **/

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // executed this when hardware back button is pressed
        openFragment(new HomePageListFragment());
    }


    @Override
    public void onEditClick(int itemId,  int foodspaceId) throws ParseException {
        EditFridgeFoodItemFragment fridgeFoodItemFragment = (EditFridgeFoodItemFragment) getSupportFragmentManager().
                findFragmentById(R.id.editFridgeFoodItemFragment);

        if (fridgeFoodItemFragment !=null) {
            Log.d(TAG, "Fridge Food Item Fragment is not null");
            fridgeFoodItemFragment.setFridgeFoodItemView(foodspaceId, itemId);
        } else {
            Log.d(TAG, "Fridge Food Item Fragment is NULL");
           // Toast toast=Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_LONG);
            //toast.show();
            Intent intent = new Intent(this, EditFridgeFoodItemActivity.class);
            intent.putExtra("itemId", itemId);
            intent.putExtra("foodspaceId", foodspaceId);
            startActivity(intent);
        }
    }

    @Override
    public void onDeleteClick(final int itemId, final int foodspaceId, String itemName) throws ParseException {
        final int fooditemId = itemId;
        AlertDialog.Builder builder = new AlertDialog.Builder(RefrigeratorFoodListActivity.this);
        builder.setTitle("Delete Food Item");
        builder.setMessage("Are you sure you want to delete the \'"+itemName+ "\' ?");

        //Yes Button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myFoodManagerDao.getInstance (getApplicationContext()).delectFoodItemById(fooditemId);
               // Intent intent = new Intent (this, RefrigeratorFoodListActivity.class);
                Intent intent = new Intent (getApplicationContext(), RefrigeratorFoodListActivity.class);
                intent.putExtra("FOODSPACE_ID", foodspaceId);
                startActivity (intent);
            }
        });

        //No Button
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
