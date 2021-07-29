package com.adarsh.crimereportandroidphp.adapters;

import android.annotation.SuppressLint;
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
import com.adarsh.crimereportandroidphp.retrofit.models.ViewComplaintModel;
import com.adarsh.crimereportandroidphp.ui.citizen.complaint.EncryptedComplaintActivity;

public class ViewComplaintAdapter extends RecyclerView.Adapter<ViewComplaintAdapter.MyViewHolder> {
    Context context;
    ViewComplaintModel viewComplaintModel;
    String firId;
    public ViewComplaintAdapter(Context context, ViewComplaintModel viewComplaintModel) {
        this.context = context;
        this.viewComplaintModel = viewComplaintModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_shimmer_layout, parent, false);
        return new MyViewHolder(itemView);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

       holder.type.setText(viewComplaintModel.getComplaint_details()[position].getComplaint_type());
       String date=viewComplaintModel.getComplaint_details()[position].getDate_time();
       holder.date.setText(date);
       if(viewComplaintModel.getComplaint_details()[position].getVerify().equals("0"))
       {
           holder.status.setText("Your complaint will be verified soon.");
           holder.status.setTextColor(R.color.red);
       }else
       {
           if(viewComplaintModel.getComplaint_details()[position].getFir_id().equals(""))
           {
               holder.status.setText("Your complaint is verified.");


           }else {
               holder.status.setText("FIR is generated");

           }
           holder.status.setTextColor(R.color.green);
       }
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String complaintId=viewComplaintModel.getComplaint_details()[position].getComplaint_id();
               String userId=viewComplaintModel.getComplaint_details()[position].getUser_id();
               String policeStation=viewComplaintModel.getComplaint_details()[position].getPolice_station();
               String complaintType=viewComplaintModel.getComplaint_details()[position].getComplaint_type();
               String district=viewComplaintModel.getComplaint_details()[position].getDistrict();
               String placeOfOccurence=viewComplaintModel.getComplaint_details()[position].getPlace_of_occurance();
               String date_time=viewComplaintModel.getComplaint_details()[position].getDate_time();
               String details=viewComplaintModel.getComplaint_details()[position].getDetails();
               String verify=viewComplaintModel.getComplaint_details()[position].getVerify();
               if(!(viewComplaintModel.getComplaint_details()[position].getFir_id().equals("")))
               {
                    firId=viewComplaintModel.getComplaint_details()[position].getFir_id();
               }
               SharedPreferences sharedPreferences=context.getSharedPreferences("complaint",Context.MODE_PRIVATE);
               SharedPreferences.Editor editor=sharedPreferences.edit();
               editor.putString("complaintId",complaintId);
               editor.putString("userId",userId);
               editor.putString("policeStation",policeStation);
               editor.putString("complaintType",complaintType);
               editor.putString("district",district);
               editor.putString("placeOfOccurence",placeOfOccurence);
               editor.putString("date_time",date_time);
               editor.putString("details",details);
               editor.putString("verify",verify);
               editor.putString("firId",firId);
               editor.apply();
               editor.commit();
               Intent i=new Intent(context, EncryptedComplaintActivity.class);
               i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i);
           }
       });
    }

    @Override
    public int getItemCount() {
        return viewComplaintModel.getComplaint_details().length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView type, date,status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.complaint_type_tv);
            date = itemView.findViewById(R.id.complaint_date_tv);
            status=itemView.findViewById(R.id.verify_status_tv);
        }
    }
}
