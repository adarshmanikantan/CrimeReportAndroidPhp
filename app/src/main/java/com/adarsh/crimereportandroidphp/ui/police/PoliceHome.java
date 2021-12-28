package com.adarsh.crimereportandroidphp.ui.police;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.ui.LoginHome;
import com.adarsh.crimereportandroidphp.ui.police.ui.ViewComplaintsByPolice;
import com.adarsh.crimereportandroidphp.ui.police.ui.ViewPccByPolice;

public class PoliceHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_home);
    }

    public void viewComplaintClick(View view) {
        Intent i = new Intent(getApplicationContext(), ViewComplaintsByPolice.class);
        startActivity(i);
    }

    public void viewPccClick(View view) {
        Intent i = new Intent(getApplicationContext(), ViewPccByPolice.class);
        startActivity(i);
    }


    public void logoutClick(View view) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("session_manager", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("session", false);
        editor.putString("user", "none");
        editor.commit();
        Intent i=new Intent(getApplicationContext(), LoginHome.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}