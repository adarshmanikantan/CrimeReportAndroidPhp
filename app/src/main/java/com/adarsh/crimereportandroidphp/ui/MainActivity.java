package com.adarsh.crimereportandroidphp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.ui.LoginHome;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread background = new Thread()
        {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(3000);

                    // After 5 seconds redirect to another intent
                    Intent i=new Intent(getApplicationContext(), LoginHome.class);
                    startActivity(i);

                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();

    }


}