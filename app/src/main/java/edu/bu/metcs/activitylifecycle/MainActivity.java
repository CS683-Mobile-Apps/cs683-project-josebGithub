package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.bu.metcs.myproject.R;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
    }
}
