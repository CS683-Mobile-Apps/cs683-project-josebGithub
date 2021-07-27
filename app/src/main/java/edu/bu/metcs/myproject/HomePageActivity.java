package edu.bu.metcs.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.util.Log;

import static edu.bu.metcs.myproject.R.id.frame_layout;


public class HomePageActivity extends AppCompatActivity {

    private final static String TAG = HomePageActivity.class.getSimpleName ();
    HomePageListFragment homePageListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_homepage);

        if (savedInstanceState != null) {
            Log.d(TAG, "Saved instance state is null");
            return;
        } else {
            //add fragments dynamically
            //create a fragment object
            Log.d(TAG, "Saved instance state is not null");
            homePageListFragment = new HomePageListFragment();
            homePageListFragment.setArguments(getIntent().getExtras());
            // get the reference to the FragmentManger object
            FragmentManager fragManager = getSupportFragmentManager();
            // get the reference to the FragmentTransaction object
            FragmentTransaction transaction = fragManager.beginTransaction();
            // add the fragment into the transaction
            transaction.add(R.id.frame_layout, homePageListFragment);
            // commit the transaction.
            transaction.commit();
        }

    }
}
