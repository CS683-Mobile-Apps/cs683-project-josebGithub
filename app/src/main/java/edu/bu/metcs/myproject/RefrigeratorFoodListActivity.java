package edu.bu.metcs.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;

public class RefrigeratorFoodListActivity extends AppCompatActivity implements FridgeFoodListAdapter.Listener {

    private final static String TAG = RefrigeratorFoodListActivity.class.getSimpleName ();
   // RefrigeratorFoodListFragment fridgeFoodListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge_food_list);

        FloatingActionButton foodItemAddButton = findViewById(R.id.add_float_button);

        foodItemAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddFridgeFoodItemsActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onClick(int position) {

    }

    @Override
    public void onEditClick(int position) throws ParseException {
        EditFridgeFoodItemFragment fridgeFoodItemFragment = (EditFridgeFoodItemFragment) getSupportFragmentManager().findFragmentById(R.id.editFridgeFoodItemFragment);

        if (fridgeFoodItemFragment !=null) {
            Log.d(TAG, "Fridge Food Item Fragment is not null");
            fridgeFoodItemFragment.setFridgeFoodItemView(0, position);
        } else {
            Log.d(TAG, "Fridge Food Item Fragment is NULL");
           // Toast toast=Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_LONG);
            //toast.show();
            Intent intent = new Intent(this, EditFridgeFoodItemActivity.class);
            intent.putExtra("itemId", position);
            intent.putExtra("foodspaceId", 0);
            startActivity(intent);
        }
    }
}
