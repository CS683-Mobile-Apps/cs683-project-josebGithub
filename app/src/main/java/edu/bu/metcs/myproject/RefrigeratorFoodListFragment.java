package edu.bu.metcs.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * This class is to a list fragment for the Refrigerator food list page
 */
public class RefrigeratorFoodListFragment extends Fragment {

    private final static String TAG = HomePageListFragment.class.getSimpleName ();
    ListView listview;
    View view;

    public RefrigeratorFoodListFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fridge_food_list_fragment, container, false);

        RecyclerView fridgeFoodListRecyclerView = (RecyclerView) (view.findViewById(R.id.fridge_foodlist_recyclerview));

        FridgeFoodListAdapter fridgeFoodListAdapter = new FridgeFoodListAdapter((FoodSpace.foodSpaces[0].foodItems));
        fridgeFoodListRecyclerView.setAdapter(fridgeFoodListAdapter);

        fridgeFoodListAdapter.setListener((FridgeFoodListAdapter.Listener)getActivity());

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        fridgeFoodListRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

}
