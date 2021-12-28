package com.adarsh.crimereportandroidphp.ui.police;

import static com.adarsh.crimereportandroidphp.util.UtilClass.showToast;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.retrofit.models.Police_Login_Model;
import com.adarsh.crimereportandroidphp.retrofit.network.Api;
import com.adarsh.crimereportandroidphp.retrofit.network.ApiClient;
import com.adarsh.crimereportandroidphp.ui.LoginHome;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoliceLoginFragment extends Fragment {


    TextInputEditText username,pswd;
    Button loginn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_police_login, container, false);
        username=root.findViewById(R.id.user_email);
        pswd=root.findViewById(R.id.user_pswd);
        loginn=root.findViewById(R.id.login);
        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = username.getText().toString();
                String pass = pswd.getText().toString();

                if (TextUtils.isEmpty(username.getText())) {
                    showToast(getContext(),"All fields are required");
                }
                else if (TextUtils.isEmpty(pswd.getText())) {
                    showToast(getContext(),"All fields are required");
                }
                else {
                    Api api = ApiClient.Citizen().create(Api.class);
                    api.POLICE_LOGIN_MODEL_CALL(uid, pass).enqueue(new Callback<Police_Login_Model>() {
                        @Override
                        public void onResponse(Call<Police_Login_Model> call, Response<Police_Login_Model> response) {
                            Police_Login_Model search_police_login_model = response.body();
                            String s = search_police_login_model.getStatus();

                            if (s.equals("Success")) {
                                Toast.makeText(getContext(), "Login Success", Toast.LENGTH_SHORT).show();

                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("pmykey", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("dist", search_police_login_model.getStation_details().getDistrict());
                                editor.putString("stationn", search_police_login_model.getStation_details().getName());
                                editor.putString("station_id",search_police_login_model.getStation_details().getPolice_id());
                                editor.putString("code",search_police_login_model.getStation_details().getCode());
                                editor.commit();

                                SharedPreferences sp = getContext().getSharedPreferences("session_manager", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putBoolean("session", true);
                                edit.putString("user", "police");
                                edit.commit();
                                Intent i=new Intent(getActivity(), PoliceHome.class);
                                startActivity(i);

                            } else {
                                Toast.makeText(getActivity(), search_police_login_model.getStatus(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Police_Login_Model> call, Throwable t) {
                            Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return root;
    }
}