package edu.bu.metcs.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.bu.metcs.myproject.Database.MyFoodManagerDao;


/**
 * This class is to a list fragment for the Refrigerator food list page
 */
public class RefrigeratorFoodListFragment extends Fragment {

    private final static String TAG = RefrigeratorFoodListFragment.class.getSimpleName ();
    MyFoodManagerDao myFoodManagerDao;
    ListView listview;
    View view;

    public RefrigeratorFoodListFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        myFoodManagerDao = myFoodManagerDao.getInstance(getContext());
        myFoodManagerDao.openDB();

        int foodspaceId = getArguments().getInt("FOODSPACE_ID");
        Log.d(TAG, "FOODSPACE_ID : "+foodspaceId);

        ArrayList<FoodItem> foodItems=myFoodManagerDao.getAllFoodItems(foodspaceId);

        view = inflater.inflate(R.layout.fridge_food_list_fragment, container, false);

        RecyclerView fridgeFoodListRecyclerView = (RecyclerView) (view.findViewById(R.id.fridge_foodlist_recyclerview));

        FridgeFoodListAdapter fridgeFoodListAdapter = new FridgeFoodListAdapter((foodItems));
        fridgeFoodListRecyclerView.setAdapter(fridgeFoodListAdapter);

        fridgeFoodListAdapter.setListener((FridgeFoodListAdapter.Listener)getActivity());

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        fridgeFoodListRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

}
