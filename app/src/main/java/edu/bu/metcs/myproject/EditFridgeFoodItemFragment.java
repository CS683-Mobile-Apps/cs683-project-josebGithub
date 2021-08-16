package edu.bu.metcs.myproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.bu.metcs.myproject.Database.MyFoodManagerDao;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditFridgeFoodItemFragment#} factory method to
 * create an instance of this fragment.
 */
public class EditFridgeFoodItemFragment extends Fragment {

    private final static String TAG = EditFridgeFoodItemFragment.class.getSimpleName();

    private String foodSpace;
    private TextView foodNameTextView, foodTypeTextView, expiryDateTextView, quantityTextView, costTextView;
    private String foodType;
    private int foodspaceId;
    private int itemId;
    MyFoodManagerDao myFoodManagerDao;
    private FoodItem foodItem;


    public EditFridgeFoodItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final EditText expiryDate;

        View view = inflater.inflate(R.layout.fragment_edit_fridge_food_item, container, false);


        foodNameTextView = view.findViewById(R.id.foodNameId);
        foodTypeTextView = view.findViewById(R.id.foodTypeId);
        expiryDateTextView = view.findViewById(R.id.expiryDateId);
        quantityTextView = view.findViewById(R.id.quantityId);
        costTextView = view.findViewById(R.id.costId);


        Spinner spinner = (Spinner) view.findViewById(R.id.foodTypeSpinnerId);
        int initialSelectedPosition = spinner.getSelectedItemPosition();
        spinner.setSelection(initialSelectedPosition, false);
        String[] versions = getResources().getStringArray(R.array.food_type_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_2, versions);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                foodType = item.toString();
                foodTypeTextView.setText(foodType);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // initiate the date picker and a button
        expiryDate = (EditText) view.findViewById(R.id.expiryDateId);
        // perform click event on edit text
        expiryDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                String monthString = String.valueOf(monthOfYear+1);
                                if (monthString.length() == 1) {
                                    monthString = "0"+ monthString;
                                }
                                expiryDate.setText(monthString + "/"
                                        + dayOfMonth + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        if (getArguments() != null) {
            foodspaceId = (int) getArguments().getInt("foodspaceId");
            itemId = (int) getArguments().getInt("itemId");
            Log.d(TAG, "foodspaceId : " + foodspaceId + "   ItemId : " + itemId);
        } else {
            foodspaceId = 0;
            itemId = 0;
        }

        Log.d(TAG, "SetFridgeFoodItemView ->   foodspaceId : " + foodspaceId + "   ItemId : " + itemId);
        myFoodManagerDao = myFoodManagerDao.getInstance(getContext());
        foodItem = myFoodManagerDao.getFoodItemById(itemId, foodspaceId);

        try {
            setFridgeFoodItemView(foodspaceId, itemId);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return view;
    }


    public void setFridgeFoodItemView(int foodspaceId, int itemId) throws ParseException {
        foodNameTextView.setText(foodItem.getName());
        foodTypeTextView.setText(foodItem.getType());
        String eDate = foodItem.getExpirationDate().toString();
        Log.d(TAG, "eDate : " + eDate);
        expiryDateTextView.setText(foodItem.getExpirationDate());
        quantityTextView.setText(foodItem.getQuantity());
        costTextView.setText(Double.toString(foodItem.getCost()));
    }

}