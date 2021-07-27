package edu.bu.metcs.myproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import androidx.fragment.app.ListFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class is a list fragment for the home page
 */
public class HomePageListFragment extends ListFragment {

    private final static String TAG = HomePageListFragment.class.getSimpleName ();
    ListView listview;
    View view;

    public HomePageListFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.foodspace_list_fragment, container, false);

        Log.d(TAG, "onCreateView: " + savedInstanceState);

            //ArrayList<FoodSpaces> foodSpaces = new ArrayList<>();

            Log.d(TAG, "Create Food Space: " + savedInstanceState);
            //Create Food Spaces
            String[] foodSpacesArray = {FoodSpace.foodSpaces[0].getTitle().toString(), FoodSpace.foodSpaces[1].getTitle().toString(),
                    FoodSpace.foodSpaces[2].getTitle().toString(), FoodSpace.foodSpaces[3].getTitle().toString()};

            //Create Food Items
            Date expiryDate = null;
            SimpleDateFormat sDate = new SimpleDateFormat("MM/dd/yyyy");
            try {
                expiryDate = sDate.parse("07/30/2021");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            FoodItem foodItem1 = new FoodItem("Cheddar Cheese", "Dairy", expiryDate, "12 pieces", 5.60);
            FoodItem foodItem2 = new FoodItem("Salmon", "Fish", expiryDate, "1", 10.80);

            //Add Item to Refrigerator
            Log.d(TAG, "SavedInstanceState : " + savedInstanceState);
            Log.d(TAG, "Add FOOD ITEM TO FRIDGE");
            FoodSpace.foodSpaces[0].foodItems.add(foodItem1);
            FoodSpace.foodSpaces[0].foodItems.add(foodItem2);

            /**
             FoodSpaces[] foodSpaces = { new FoodSpaces( "REFRIGERATOR"), new FoodSpaces("FREEZER"),
             new FoodSpaces("PANTRY"), new FoodSpaces("KITCHEN CABINET")};
             **/
            // ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,android.R.id.text1, foodSpacesArray);
            //  setListAdapter(adapter);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, foodSpacesArray);
            setListAdapter(adapter);


            return super.onCreateView(inflater,container,savedInstanceState);
    }


    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        listview=getListView();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                 // ItemClicked item = adapterView.getItemAtPosition(position);
                  Intent intent=null;

                  //if user clicks the REFRIGERATOR list, then the app will bring the user to the other activity - Refrigerator food list page
                  switch(position) {
                      case 0:
                          intent=new Intent(getContext(), RefrigeratorFoodListActivity.class);
                          break;
                      default:
                          Toast.makeText(getActivity(), (CharSequence) listview.getItemAtPosition(position),Toast.LENGTH_LONG).show();
                  }

                  Log.d(TAG, "Get intent");

                  if (intent !=null) {
                      Log.d(TAG, "Intent not null");
                      startActivity(intent);
                  }
            }
        });
    }

}
