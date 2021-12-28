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
import com.adarsh.crimereportandroidphp.retrofit.models.ViewComplaintByStationModel;
import com.adarsh.crimereportandroidphp.ui.police.EncryptedPoliceComplaintActivity;

public class ViewComplaintByPoliceAdapter extends RecyclerView.Adapter<ViewComplaintByPoliceAdapter.ComplaintViewHolder> {
    ViewComplaintByStationModel viewComplaintModel;
    Context context;
    String firId;
    public ViewComplaintByPoliceAdapter(ViewComplaintByStationModel viewComplaintModel, Context context) {
        this.viewComplaintModel = viewComplaintModel;
        this.context = context;
    }

    @NonNull
    @Override
    public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complaint_list_item_shimmer_layout, parent, false);
        return new ViewComplaintByPoliceAdapter.ComplaintViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position) {
        holder.type.setText(viewComplaintModel.getComplaint_details()[position].getComplaint_type());
        String date=viewComplaintModel.getComplaint_details()[position].getDate_time();
        holder.date.setText(date);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String complaintId = viewComplaintModel.getComplaint_details()[position].getComplaint_id();
                String userId = viewComplaintModel.getComplaint_details()[position].getUser_id();
                String policeStation = viewComplaintModel.getComplaint_details()[position].getPolice_station();
                String complaintType = viewComplaintModel.getComplaint_details()[position].getComplaint_type();
                String district = viewComplaintModel.getComplaint_details()[position].getDistrict();
                String placeOfOccurence = viewComplaintModel.getComplaint_details()[position].getPlace_of_occurance();
                String date_time = viewComplaintModel.getComplaint_details()[position].getDate_time();
                String details = viewComplaintModel.getComplaint_details()[position].getDetails();
                String verify = viewComplaintModel.getComplaint_details()[position].getVerify();
                String username=viewComplaintModel.getComplaint_details()[position].getUser_name();
                String email=viewComplaintModel.getComplaint_details()[position].getEmail();
                String phone=viewComplaintModel.getComplaint_details()[position].getPhone();
                if (!(null == viewComplaintModel.getComplaint_details()[position].getFir_id())) {
                    firId = viewComplaintModel.getComplaint_details()[position].getFir_id();
                }
                SharedPreferences sharedPreferences = context.getSharedPreferences("policecomplaint", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("complaintId", complaintId);
                editor.putString("userId", userId);
                editor.putString("policeStation", policeStation);
                editor.putString("complaintType", complaintType);
                editor.putString("district", district);
                editor.putString("placeOfOccurence", placeOfOccurence);
                editor.putString("date_time", date_time);
                editor.putString("details", details);
                editor.putString("verify", verify);
                editor.putString("firId",firId);
                editor.putString("username",username);
                editor.putString("email",email);
                editor.putString("phone",phone);
                editor.apply();
                editor.commit();
                Intent i = new Intent(context, EncryptedPoliceComplaintActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        }
        );
    }

    @Override
    public int getItemCount() {
        return viewComplaintModel.getComplaint_details().length;
    }

    public class ComplaintViewHolder extends RecyclerView.ViewHolder{
      TextView type,date;
        public ComplaintViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.complaint_type_tv);
            date = itemView.findViewById(R.id.complaint_date_tv);
        }
    }
}
