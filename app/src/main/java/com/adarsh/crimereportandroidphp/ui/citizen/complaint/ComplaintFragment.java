package com.adarsh.crimereportandroidphp.ui.citizen.complaint;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.adapters.ViewComplaintAdapter;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewComplaintModel;
import com.adarsh.crimereportandroidphp.retrofit.network.Api;
import com.adarsh.crimereportandroidphp.retrofit.network.ApiClient;
import com.adarsh.crimereportandroidphp.util.UtilClass;
import com.google.android.material.card.MaterialCardView;
import com.todkars.shimmer.ShimmerRecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.adarsh.crimereportandroidphp.util.Util.showToast;

public class ComplaintFragment extends Fragment {


    private MaterialCardView fileComplaintCv;
    private MaterialCardView viewComplaintStatus;
    private ShimmerRecyclerView recyclerview;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_complaint, container, false);

        initView(root);
        recyclerview.showShimmer();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);

        fileComplaintCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UtilClass.intent(getContext(), FileComplaintActivity.class,0);
               
            }
        });
        Api api= ApiClient.Citizen().create(Api.class);
        api.VIEW_COMPLAINT_MODEL_CALL(sharedPreferences.getString("loginid",null)).enqueue(new Callback<ViewComplaintModel>() {
            @Override
            public void onResponse(Call<ViewComplaintModel> call, Response<ViewComplaintModel> response) {
                recyclerview.hideShimmer();
                ViewComplaintModel viewComplaintModel=response.body();
                if (viewComplaintModel.getStatus().equalsIgnoreCase("success")) {
                    ViewComplaintAdapter viewComplaintAdapter=new ViewComplaintAdapter(getContext(),viewComplaintModel);
                    recyclerview.setAdapter(viewComplaintAdapter);
                }
               else
                {
                    Toast.makeText(getContext(), "No complaints are added yet", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ViewComplaintModel> call, Throwable t) {
                recyclerview.hideShimmer();
                showToast(getActivity(),t.getMessage());

            }
        });

        return root;
    }

    private void initView(View root) {
        fileComplaintCv = root.findViewById(R.id.file_complaint_cv);
        recyclerview=root.findViewById(R.id.complaint_recycler);
    }
}