package edu.bu.metcs.myproject.Services;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.JobIntentService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.bu.metcs.myproject.Database.MyFoodManagerDao;
import edu.bu.metcs.myproject.FoodItem;
import edu.bu.metcs.myproject.Notifications.ExpiryDateNotification;

public class ExpiryDateJobIntentService extends JobIntentService {

    private final static String TAG = ExpiryDateJobIntentService.class.getSimpleName ();
    final Handler mHandler = new Handler();
    ArrayList<String> expiryFoodItems = new ArrayList<String>();

    //Unique job ID for this service.
    private static final int JOB_ID = 10002;

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, ExpiryDateJobIntentService.class, JOB_ID, intent);
    }

    //This method is called whenever an intent is enqueued and is executed in a background worker thread.
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        MyFoodManagerDao myFoodManagerDao = MyFoodManagerDao.getInstance(getApplicationContext());
        //postToastMsg("ExpiryDateJobIntentService:onHandleWork...");

        // Retrieve food items that are going to expire in 5 days at the background
        //postToastMsg("Retrieve fooditem...");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Using today's date
        c.add(Calendar.DATE, 5); // Adding 5 days
        String output = sdf.format(c.getTime());
        //postToastMsg("DAYS : "+output);
        expiryFoodItems = myFoodManagerDao.getFridgeFoodItemByExpiryDate(output);
        if (expiryFoodItems.size()!=0) {
            ExpiryDateNotification expiryDateNotification = new ExpiryDateNotification(getApplicationContext());
            expiryDateNotification.createNotification(expiryFoodItems);
            //postToastMsg("ExpirySize: "+expiryFoodItems.size());
           // postToastMsg("Job is finished");
        }

    }

    public void postToastMsg(final String msg) {
        mHandler.post(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
