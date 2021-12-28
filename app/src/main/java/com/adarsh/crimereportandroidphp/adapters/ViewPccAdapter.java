package com.adarsh.crimereportandroidphp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewPccByCitizenModel;
import com.adarsh.crimereportandroidphp.ui.police.ViewPCCDetailsPoliceActivity;

public class ViewPccAdapter extends RecyclerView.Adapter<ViewPccAdapter.MyViewHolder>{
    ViewPccByCitizenModel viewPccByCitizenModel;
    Context context;

    public ViewPccAdapter(ViewPccByCitizenModel viewPccByCitizenModel, Context context) {
        this.viewPccByCitizenModel = viewPccByCitizenModel;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_pcc_layout, parent, false);
        return new ViewPccAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.policestation.setText(viewPccByCitizenModel.getPcc_details()[position].getPolice_station()+" , "+viewPccByCitizenModel.getPcc_details()[position].getDistrict());
      holder.purpose.setText("PCC Application for "+viewPccByCitizenModel.getPcc_details()[position].getPurpose());
      if(viewPccByCitizenModel.getPcc_details()[position].getVerify_status().equals("1"))
      {
          holder.status.setText("Your Request is Verified");
      }
      else
      {
          holder.status.setText("Your Request will be Verified soon");
      }

    }

    @Override
    public int getItemCount() {
        return viewPccByCitizenModel.getPcc_details().length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView purpose,policestation,status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            policestation=itemView.findViewById(R.id.policestation);
            purpose=itemView.findViewById(R.id.purpose);
            status=itemView.findViewById(R.id.pcc_status);
        }
    }
}
