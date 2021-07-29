package com.adarsh.crimereportandroidphp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewPccByPoliceModel;
import com.adarsh.crimereportandroidphp.ui.police.ViewPCCDetailsPoliceActivity;

public class ViewPccByPoliceAdapter extends RecyclerView.Adapter<ViewPccByPoliceAdapter.PccViewHolder> {
    ViewPccByPoliceModel viewPccByPoliceModel;
    Context context;

    public ViewPccByPoliceAdapter(ViewPccByPoliceModel viewPccByPoliceModel, Context context) {
        this.viewPccByPoliceModel = viewPccByPoliceModel;
        this.context = context;
    }

    @NonNull
    @Override
    public PccViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pcc_layout, parent, false);
        return new ViewPccByPoliceAdapter.PccViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PccViewHolder holder, int position) {
      holder.name.setText(viewPccByPoliceModel.getPcc_details()[position].getName());
      holder.address.setText(viewPccByPoliceModel.getPcc_details()[position].getPresent_address());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              SharedPreferences sharedPreferences=context.getSharedPreferences("pcc",Context.MODE_PRIVATE);
              SharedPreferences.Editor editor=sharedPreferences.edit();
              editor.putString("pccid",viewPccByPoliceModel.getPcc_details()[position].getPcc_id());
              editor.putString("userid",viewPccByPoliceModel.getPcc_details()[position].getUser_id());
              editor.putString("district",viewPccByPoliceModel.getPcc_details()[position].getDistrict());
              editor.putString("police_station",viewPccByPoliceModel.getPcc_details()[position].getPolice_station());
              editor.putString("name",viewPccByPoliceModel.getPcc_details()[position].getName());
              editor.putString("father_name",viewPccByPoliceModel.getPcc_details()[position].getFather_name());
              editor.putString("dob",viewPccByPoliceModel.getPcc_details()[position].getDob());
              editor.putString("passport",viewPccByPoliceModel.getPcc_details()[position].getPassport());
              editor.putString("perm_address",viewPccByPoliceModel.getPcc_details()[position].getPermanent_address());
              editor.putString("present_address",viewPccByPoliceModel.getPcc_details()[position].getPresent_address());
              editor.putString("residence_address",viewPccByPoliceModel.getPcc_details()[position].getResidence_address());
              editor.putString("phone",viewPccByPoliceModel.getPcc_details()[position].getPhone());
              editor.putString("email",viewPccByPoliceModel.getPcc_details()[position].getEmail());
              editor.putString("purpose",viewPccByPoliceModel.getPcc_details()[position].getPurpose());
              editor.putString("reference_person",viewPccByPoliceModel.getPcc_details()[position].getReference_person());
              editor.putString("criminal_case",viewPccByPoliceModel.getPcc_details()[position].getCriminal_case());
              editor.putString("verify",viewPccByPoliceModel.getPcc_details()[position].getVerify_status());
              editor.apply();
           Intent i=new Intent(context, ViewPCCDetailsPoliceActivity.class);
           i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           context.startActivity(i);
          }
      });
    }

    @Override
    public int getItemCount() {
        return viewPccByPoliceModel.getPcc_details().length;
    }

    public class PccViewHolder extends RecyclerView.ViewHolder
    {
         TextView name,address;
        public PccViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_of_applicant);
            address=itemView.findViewById(R.id.address_of_applicant);
        }
    }
}
