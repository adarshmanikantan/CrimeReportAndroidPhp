package com.adarsh.crimereportandroidphp.ui.police.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.adapters.ViewComplaintByPoliceAdapter;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewComplaintByStationModel;
import com.adarsh.crimereportandroidphp.retrofit.network.Api;
import com.adarsh.crimereportandroidphp.retrofit.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewComplaintsByPolice extends AppCompatActivity {

    private RecyclerView complaintsRecyclerviewPs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaints_by_police);
        initView();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("pmykey", Context.MODE_PRIVATE);
        String district = sharedPreferences.getString("dist", null);
        String name = sharedPreferences.getString("stationn", null);
        String police_id = sharedPreferences.getString("station_id", null);

        Api api = ApiClient.Citizen().create(Api.class);
        api.VIEW_COMPLAINT_BY_STATION_MODEL_CALL(district, name).enqueue(new Callback<ViewComplaintByStationModel>() {
            @Override
            public void onResponse(Call<ViewComplaintByStationModel> call, Response<ViewComplaintByStationModel> response) {
                ViewComplaintByStationModel viewComplaintByStationModel = response.body();
                if (viewComplaintByStationModel.getStatus().equalsIgnoreCase("Success")) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                    complaintsRecyclerviewPs.setLayoutManager(layoutManager);
                    ViewComplaintByPoliceAdapter viewComplaintByPoliceAdapter = new ViewComplaintByPoliceAdapter(viewComplaintByStationModel, getApplicationContext());
                    complaintsRecyclerviewPs.setAdapter(viewComplaintByPoliceAdapter);

                } else {
                    Toast.makeText(ViewComplaintsByPolice.this, "No complaints", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ViewComplaintByStationModel> call, Throwable t) {
                Toast.makeText(ViewComplaintsByPolice.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        complaintsRecyclerviewPs = findViewById(R.id.complaints_recyclerview_ps);
    }
}