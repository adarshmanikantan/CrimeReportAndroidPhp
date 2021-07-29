package com.adarsh.crimereportandroidphp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.retrofit.models.Station_details;

import java.util.ArrayList;
import java.util.List;

public class ViewPoliceStationAdapter extends ArrayAdapter<Station_details> {

    Context context;
    int resource, textViewResourceId;
    List<Station_details> items, tempItems, suggestions;

    public ViewPoliceStationAdapter(Context context, int resource, int textViewResourceId, List<Station_details> items) {
        super(context, resource, textViewResourceId, items);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<Station_details>(items); // this makes the difference.
        suggestions = new ArrayList<Station_details>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.singlepslayout, parent, false);
        }
        Station_details station = items.get(position);
        if (station != null) {
            TextView lblName = (TextView) view.findViewById(R.id.pstv);
            if (lblName != null)
                lblName.setText(station.getName());
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((Station_details) resultValue).getName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Station_details station : tempItems) {
                    if (station.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(station);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Station_details> filterList = (ArrayList<Station_details>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Station_details station : filterList) {
                    add(station);
                    notifyDataSetChanged();
                }
            }
        }
    };
}