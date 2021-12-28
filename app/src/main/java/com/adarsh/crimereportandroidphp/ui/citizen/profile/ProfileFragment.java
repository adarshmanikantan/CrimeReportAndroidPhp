package com.adarsh.crimereportandroidphp.ui.citizen.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.adarsh.crimereportandroidphp.R;


public class ProfileFragment extends Fragment {


    private ImageView profile;
    private TextView proEmailTv;
    private TextView proNameTv;
    private TextView proPhoneTv;
    private TextView proYobTv;
    private TextView proCoTv;
    private TextView proHnameTv;
    private TextView proStreetTv;
    private TextView proVillageTv;
    private TextView proPostcodeTv;
    private TextView proDistrictTv;
    private TextView proStateTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(root);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        // Inflate the layout for this fragment

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        proNameTv.setText(sharedPreferences.getString("firstname", null));
        proEmailTv.setText(sharedPreferences.getString("email", null));
        proPhoneTv.setText(sharedPreferences.getString("phone", null));
        proYobTv.setText(sharedPreferences.getString("year", null));
        proCoTv.setText(sharedPreferences.getString("careof", null));
      //  Toast.makeText(getContext(),sharedPreferences.getString("hname", null), Toast.LENGTH_SHORT).show();
        proHnameTv.setText(sharedPreferences.getString("hname", null));
        proStreetTv.setText(sharedPreferences.getString("street", null));
        proVillageTv.setText(sharedPreferences.getString("village", null));
        proPostcodeTv.setText(sharedPreferences.getString("postcode", null));
        proDistrictTv.setText(sharedPreferences.getString("district", null));
        proStateTv.setText(sharedPreferences.getString("state", null));
        return root;
    }

    private void initView(View root) {
        profile = root.findViewById(R.id.profile);
        proEmailTv = root.findViewById(R.id.pro_email_tv);
        proNameTv = root.findViewById(R.id.pro_name_tv);
        proPhoneTv = root.findViewById(R.id.pro_phone_tv);
        proYobTv = root.findViewById(R.id.pro_yob_tv);
        proCoTv = root.findViewById(R.id.pro_co_tv);
        proHnameTv = root.findViewById(R.id.pro_hname_tv);
        proStreetTv = root.findViewById(R.id.pro_street_tv);
        proVillageTv = root.findViewById(R.id.pro_village_tv);
        proPostcodeTv = root.findViewById(R.id.pro_postcode_tv);
        proDistrictTv = root.findViewById(R.id.pro_district_tv);
        proStateTv = root.findViewById(R.id.pro_state_tv);
    }
}