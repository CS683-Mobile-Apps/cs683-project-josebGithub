package edu.bu.metcs.myproject.Notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

import edu.bu.metcs.myproject.Database.MyFoodManagerDBHelper;
import edu.bu.metcs.myproject.R;
import edu.bu.metcs.myproject.RefrigeratorFoodListActivity;

public class ExpiryDateNotification {

    private final static String TAG = ExpiryDateNotification.class.getSimpleName ();
    private Context context;

    public ExpiryDateNotification(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotification(ArrayList<String> expiryFoodItems) {
        Notification mNotification;
        NotificationManager mNotificationManager;
        String epFoodItems = String.join("\n", expiryFoodItems);

        Log.d(TAG, "Creating foodItemIntent...");
        Intent foodItemIntent = new Intent(this.context, RefrigeratorFoodListActivity.class);
        // notifyIntent.setAction()
        foodItemIntent.putExtra("FOODSPACE_ID", 0);
        foodItemIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        Log.d(TAG, "PendingIntent.getActivity...");
        PendingIntent foodItemPendingIntent = PendingIntent.getActivity(this.context, 0,
                foodItemIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mNotificationManager = (NotificationManager)
                this.context.getSystemService(Context.NOTIFICATION_SERVICE);

        Spannable sb = new SpannableString("Food items in Refrigerator will be expired in 5 days:");
        sb.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String channelId = "10001";

            CharSequence name = this.context.getString(R.string.channel_name);

            String description = this.context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(channelId, name, importance);

            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.setShowBadge(true);

            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mNotificationManager.createNotificationChannel(mChannel);

           // Bitmap largeIcon = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_fridge_notification_icon);


            mNotification = new Notification.Builder(this.context, "10001")
                    .setContentTitle("Food Items Expiration Notification")
                    .setTicker("MyFoodManager")
                    .setContentText("Your food items are going to expire!")
                    .setSmallIcon(R.drawable.ic_fridge_notification_icon)
                    .setContentIntent(foodItemPendingIntent)
                    .setStyle(new Notification.BigTextStyle()
                            .bigText(epFoodItems)
                    .setBigContentTitle(sb))
                    .setColor(Color.GREEN)
                    .setAutoCancel(true)
                    .build();
        } else {
            mNotification = new Notification.Builder(this.context)
                    .setContentTitle("Food items Expiration Notification")
                    .setTicker("MyFoodManager")
                    .setContentText("Your food items are going to expire!")
                    .setSmallIcon(R.drawable.ic_fridge_notification_icon)
                    .setContentIntent(foodItemPendingIntent)
                    .setStyle(new Notification.BigTextStyle()
                            .bigText(epFoodItems)
                    .setBigContentTitle(sb))
                    .setAutoCancel(true)
                    .setColor(Color.BLUE)
                    .build();
        }

        Log.d(TAG, "Notify the user...");
        mNotificationManager.notify(0, mNotification);
    }
}
