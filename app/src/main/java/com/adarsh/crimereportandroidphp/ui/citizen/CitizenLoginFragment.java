package com.adarsh.crimereportandroidphp.ui.citizen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.retrofit.models.RootLoginModel;
import com.adarsh.crimereportandroidphp.retrofit.network.Api;
import com.adarsh.crimereportandroidphp.retrofit.network.ApiClient;
import com.adarsh.crimereportandroidphp.util.AadharScannerActivity;
import com.adarsh.crimereportandroidphp.util.Util;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.adarsh.crimereportandroidphp.util.Util.dismissProgressDialog;
import static com.adarsh.crimereportandroidphp.util.Util.showProgressDialog;
import static com.adarsh.crimereportandroidphp.util.UtilClass.intent;
import static com.adarsh.crimereportandroidphp.util.UtilClass.isEmpty;
import static com.adarsh.crimereportandroidphp.util.UtilClass.showToast;


public class CitizenLoginFragment extends Fragment {


    private TextInputLayout textInputLatyot;
    private TextInputEditText loginEmailEdt;
    private TextInputLayout passwordInputLayout;
    private TextInputEditText loginPasswordEdt;
    private Button loginBtn;
    private Button registerbtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_citizen_login, container, false);
        initView(root);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(loginEmailEdt) && isEmpty(loginPasswordEdt)) {
                    showToast(getContext(), "Enter values");
                } else {
                    showProgressDialog(getContext(),"Loading...","Please wait!",getResources().getDrawable(R.drawable.clock),false);
                    Api api = ApiClient.Citizen().create(Api.class);
                    api.ROOT_LOGIN_MODEL_CALL(loginEmailEdt.getText().toString(), loginPasswordEdt.getText().toString()).enqueue(new Callback<RootLoginModel>() {
                        @Override
                        public void onResponse(Call<RootLoginModel> call, Response<RootLoginModel> response) {

                            RootLoginModel rootLoginModel = response.body();
                            if (rootLoginModel.getStatus().equalsIgnoreCase("success")) {
                                dismissProgressDialog();
                                showToast(getContext(), "Login Success");
                                intent(getContext(), CitizenHome.class,0);
                                shareData(rootLoginModel);
                            }
                            else
                            {
                                dismissProgressDialog();
                                showToast(getContext(),"Incorrect Username or password");
                            }
                        }


                        @Override
                        public void onFailure(Call<RootLoginModel> call, Throwable t) {
                            dismissProgressDialog();
                            showToast(getContext(), t.getMessage());
                        }
                    });

                }

            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent(getContext(), AadharScannerActivity.class,1);
            }
        });
        return root;
    }

    private void shareData(RootLoginModel rootLoginModel) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Toast.makeText(getContext(),rootLoginModel.getUser_data().getUser_id(), Toast.LENGTH_SHORT).show();
        editor.putString("loginid", rootLoginModel.getUser_data().getUser_id());
        editor.putString("firstname", rootLoginModel.getUser_data().getFirstname());
        editor.putString("lastname", rootLoginModel.getUser_data().getLastname());
        editor.putString("email", rootLoginModel.getUser_data().getEmail());
        editor.putString("password", rootLoginModel.getUser_data().getPassword());
        editor.putString("adhar", rootLoginModel.getUser_data().getAdhar());
        editor.putString("phone", rootLoginModel.getUser_data().getPhone());
        editor.putString("gender", rootLoginModel.getUser_data().getGender());
        editor.putString("year", rootLoginModel.getUser_data().getYear());
        editor.putString("careof", rootLoginModel.getUser_data().getCare_of());
        editor.putString("hname", rootLoginModel.getUser_data().getHname());
        editor.putString("street", rootLoginModel.getUser_data().getStreet());
        editor.putString("village", rootLoginModel.getUser_data().getVillage());
        editor.putString("postcode", rootLoginModel.getUser_data().getPostcode());
        editor.putString("district", rootLoginModel.getUser_data().getDistrict());
        editor.putString("state", rootLoginModel.getUser_data().getState());
        editor.apply();


    }

    private void initView(View root) {
        textInputLatyot = root.findViewById(R.id.text_input_Latyot);
        loginEmailEdt = root.findViewById(R.id.login_email_edt);
        passwordInputLayout = root.findViewById(R.id.password_inputLayout);
        loginPasswordEdt = root.findViewById(R.id.login_password_edt);
        loginBtn = root.findViewById(R.id.login_btn);
        registerbtn = root.findViewById(R.id.registerbtn);
    }
}