package com.adarsh.crimereportandroidphp.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.ui.LoginHome;
import com.adarsh.crimereportandroidphp.ui.citizen.CitizenHome;
import com.adarsh.crimereportandroidphp.ui.police.PoliceHome;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("session_manager", MODE_PRIVATE);

        Boolean session = sharedPreferences.getBoolean("session", false);
        String user = sharedPreferences.getString("user", "none");

//        Toast.makeText(getApplicationContext(),session+""+user,Toast.LENGTH_LONG).show();
        if (session && user.equals("citizen")) {
            startActivity(new Intent(getApplicationContext(), CitizenHome.class));
        }
        else if(session && user.equals("police"))
        {
            startActivity(new Intent(getApplicationContext(), PoliceHome.class));

        }
        else {

            new Handler().postDelayed(new Runnable() {

                /*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 */

                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(MainActivity.this, LoginHome.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);


        }
    }
}
