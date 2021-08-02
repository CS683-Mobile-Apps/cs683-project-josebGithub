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

import edu.bu.metcs.myproject.Database.MyFoodManagerDBContract;
import edu.bu.metcs.myproject.Database.MyFoodManagerDao;

/**
 * This class is a list fragment for the home page
 */
public class HomePageListFragment extends ListFragment {

    private final static String TAG = HomePageListFragment.class.getSimpleName ();
    private static MyFoodManagerDao myFoodManagerDao;
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

            // ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,android.R.id.text1, foodSpacesArray);
            //  setListAdapter(adapter);
            myFoodManagerDao = MyFoodManagerDao.getInstance(getContext());
            myFoodManagerDao.openDB();
            String[] foodSpaceArray = myFoodManagerDao.getAllFoodSpaceTitles();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, foodSpaceArray);
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
                          intent.putExtra("FOODSPACE_ID", position);
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
