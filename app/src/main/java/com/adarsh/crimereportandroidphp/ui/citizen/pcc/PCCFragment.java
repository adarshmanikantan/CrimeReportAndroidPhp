package com.adarsh.crimereportandroidphp.ui.citizen.pcc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.adapters.ViewPccAdapter;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewPccByCitizenModel;
import com.adarsh.crimereportandroidphp.retrofit.network.Api;
import com.adarsh.crimereportandroidphp.retrofit.network.ApiClient;
import com.adarsh.crimereportandroidphp.util.UtilClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PCCFragment extends Fragment {

    LinearLayout requestpcc;
    RecyclerView pccRecyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pcc, container, false);
        requestpcc=root.findViewById(R.id.requestpcclayout);
        pccRecyclerView=root.findViewById(R.id.pcc_recyclerview);

        requestpcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              UtilClass.intent(getContext(),RequestPCCActivity.class,0);
            }
        });
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

        Api api=ApiClient.Citizen().create(Api.class);
        api.VIEW_PCC_BY_CITIZEN_MODEL_CALL(sharedPreferences.getString("loginid",null)).enqueue(new Callback<ViewPccByCitizenModel>() {
            @Override
            public void onResponse(Call<ViewPccByCitizenModel> call, Response<ViewPccByCitizenModel> response) {
                ViewPccByCitizenModel viewPccByCitizenModel=response.body();
                if(viewPccByCitizenModel.getStatus().equalsIgnoreCase("success"))
                {
                    LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                    pccRecyclerView.setLayoutManager(layoutManager);
                    ViewPccAdapter viewPccAdapter=new ViewPccAdapter(viewPccByCitizenModel,getContext());
                    pccRecyclerView.setAdapter(viewPccAdapter);
                }
                else
                {
                    Toast.makeText(getActivity(), "No PCC Requests Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ViewPccByCitizenModel> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}