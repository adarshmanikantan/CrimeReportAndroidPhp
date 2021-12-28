package com.adarsh.crimereportandroidphp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.retrofit.models.SearchIpcModel;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewIpcModel;

public class SearchIpcAdapter extends RecyclerView.Adapter<SearchIpcAdapter.IpcViewHolder> {
    SearchIpcModel viewIpcModel;
    Context context;

    public SearchIpcAdapter(SearchIpcModel viewIpcModel, Context context) {
        this.viewIpcModel = viewIpcModel;
        this.context = context;
    }

    @NonNull
    @Override
    public IpcViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ipc_single_item, parent, false);
        return new SearchIpcAdapter.IpcViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IpcViewHolder holder, int position) {
        holder.sectionNo.setText(viewIpcModel.getSectiont_details()[position].getSectiono());
        holder.title.setText(viewIpcModel.getSectiont_details()[position].getTitle());
        holder.description.setText(viewIpcModel.getSectiont_details()[position].getDescription());
    }

    @Override
    public int getItemCount() {
        return viewIpcModel.getSectiont_details().length;
    }

    public class IpcViewHolder extends RecyclerView.ViewHolder {
        TextView sectionNo,title,description;
        public IpcViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionNo=itemView.findViewById(R.id.section_no);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
        }
    }
}
