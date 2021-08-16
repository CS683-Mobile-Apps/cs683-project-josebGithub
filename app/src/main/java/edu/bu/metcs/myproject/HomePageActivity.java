package edu.bu.metcs.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import edu.bu.metcs.myproject.Services.AlarmReceiver;
import edu.bu.metcs.myproject.Services.ExpiryDateJobIntentService;


public class HomePageActivity extends AppCompatActivity {

    private final static String TAG = HomePageActivity.class.getSimpleName ();
    HomePageListFragment homePageListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_homepage);
        setAlarm(getApplicationContext());


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


    private void setAlarm(Context context) {

        Log.d(TAG, "Setting up the alarm manager...");
        //getting the alarm manager
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Log.d(TAG, "Creating a new intent specifying the broadcast receiver...");
        //creating a new intent specifying the broadcast receiver
        Intent serviceIntent = new Intent(context, AlarmReceiver.class);

        //creating a pending intent using the intent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, serviceIntent, 0);

        am.cancel(pendingIntent);

         //Set the alarm at 11:00am everyday
        /**
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getTime().compareTo(new Date()) < 0)
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        **/


            //Start alarm every 10000ms (i.e 10 sec)
            int time = 10000;
            Log.d(TAG, "Setting the repeating alarm that will be fired every day...");
            //setting the repeating alarm that will be fired every day
            am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), time, pendingIntent);
            //Toast.makeText(context, "Alarm is set", Toast.LENGTH_SHORT).show();
    }
}
