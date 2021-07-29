package com.adarsh.crimereportandroidphp.ui.police.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.adapters.ViewPccByPoliceAdapter;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewPccByPoliceModel;
import com.adarsh.crimereportandroidphp.retrofit.network.Api;
import com.adarsh.crimereportandroidphp.retrofit.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPccByPolice extends AppCompatActivity {

    private RecyclerView pccPoliceRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pcc_by_police);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("pmykey", Context.MODE_PRIVATE);
        String district = sharedPreferences.getString("dist", null);
        String name = sharedPreferences.getString("stationn", null);
        String police_id = sharedPreferences.getString("station_id", null);
        initView();

        Api api= ApiClient.Citizen().create(Api.class);
        api.VIEW_PCC_BY_POLICE_MODEL_CALL(district,name).enqueue(new Callback<ViewPccByPoliceModel>() {
            @Override
            public void onResponse(Call<ViewPccByPoliceModel> call, Response<ViewPccByPoliceModel> response) {
                ViewPccByPoliceModel viewPccByPoliceModel=response.body();
                if(viewPccByPoliceModel.getStatus().equalsIgnoreCase("Success"))
                {
                    LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
                    pccPoliceRecycler.setLayoutManager(layoutManager);
                    ViewPccByPoliceAdapter viewPccByPoliceAdapter=new ViewPccByPoliceAdapter(viewPccByPoliceModel,getApplicationContext());
                    pccPoliceRecycler.setAdapter(viewPccByPoliceAdapter);
                }
                else
                {
                    Toast.makeText(ViewPccByPolice.this, "No Requests for Police Clearance Certificate", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ViewPccByPoliceModel> call, Throwable t) {
                Toast.makeText(ViewPccByPolice.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        pccPoliceRecycler = findViewById(R.id.pcc_police_recycler);
    }
}