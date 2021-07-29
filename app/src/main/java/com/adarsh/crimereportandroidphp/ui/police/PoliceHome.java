package com.adarsh.crimereportandroidphp.ui.police;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.ui.police.ui.ViewComplaintsByPolice;
import com.adarsh.crimereportandroidphp.ui.police.ui.ViewPccByPolice;

public class PoliceHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_home);
    }

    public void viewComplaintClick(View view) {
  Intent i=new Intent(getApplicationContext(), ViewComplaintsByPolice.class);
  startActivity(i);
    }

    public void viewPccClick(View view) {
        Intent i=new Intent(getApplicationContext(), ViewPccByPolice.class);
        startActivity(i);
    }
}