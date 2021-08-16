package edu.bu.metcs.myproject.Services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import edu.bu.metcs.myproject.Notifications.ExpiryDateNotification;

public class AlarmReceiver extends BroadcastReceiver{

    private final static String TAG = AlarmReceiver.class.getSimpleName ();

        //the method will be fired when the alarm is triggered
        @Override
        public void onReceive(Context context, Intent intent) {
            Intent mIntent = new Intent(context, ExpiryDateJobIntentService.class);
            ExpiryDateJobIntentService.enqueueWork(context, mIntent);
        }
    }

