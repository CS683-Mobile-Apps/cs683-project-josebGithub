package edu.bu.metcs.myproject.Services;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import edu.bu.metcs.myproject.Database.MyFoodManagerDao;
import edu.bu.metcs.myproject.FoodItem;

public class UpdateDBJobIntentService extends JobIntentService {

        private final static String TAG = UpdateDBJobIntentService.class.getSimpleName ();
        final Handler mHandler = new Handler();

        //Unique job ID for this service.
        private static final int JOB_ID = 1000;

        public static void enqueueWork(Context context, Intent intent) {
            enqueueWork(context, UpdateDBJobIntentService.class, JOB_ID, intent);
        }

        //This method is called whenever an intent is enqueued and is executed in a background worker thread.
        @Override
        protected void onHandleWork(@NonNull Intent intent) {

            MyFoodManagerDao myFoodManagerDao = MyFoodManagerDao.getInstance(getApplicationContext());
            Log.d(TAG, "JobIntentService onHandleWork");

            // update food item in the database at the background
            FoodItem foodItem = (FoodItem) intent.getSerializableExtra("foodItem");
            int itemId = intent.getIntExtra("itemId", -1);
            int foodspaceId = intent.getIntExtra("foodspaceId", -1);
            postToastMsg("Update fooditem...");
            myFoodManagerDao.updateFoodItemById(foodItem, itemId, foodspaceId);

            postToastMsg("Update for "+foodItem.getName()+" is finished");
        }

        public void postToastMsg(final String msg) {
            mHandler.post(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }
            });
        }
}



