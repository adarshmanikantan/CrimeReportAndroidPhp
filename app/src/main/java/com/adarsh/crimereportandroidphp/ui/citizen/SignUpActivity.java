package com.adarsh.crimereportandroidphp.ui.citizen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.retrofit.models.RegistrationModel;
import com.adarsh.crimereportandroidphp.retrofit.network.Api;
import com.adarsh.crimereportandroidphp.retrofit.network.ApiClient;
import com.adarsh.crimereportandroidphp.ui.LoginHome;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.adarsh.crimereportandroidphp.util.Util.dismissProgressDialog;
import static com.adarsh.crimereportandroidphp.util.Util.showProgressDialog;
import static com.adarsh.crimereportandroidphp.util.UtilClass.intent;
import static com.adarsh.crimereportandroidphp.util.UtilClass.showToast;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText regEmail;
    private TextInputEditText regPhn;
    private TextInputEditText regPass;
    private TextInputEditText regConfirmpass;
    private TextInputEditText regName;
    private TextInputEditText regGender;
    private TextInputEditText regYob;
    private TextInputEditText regCareOf;
    private TextInputEditText regHouseName;
    private TextInputEditText regStreet;
    private TextInputEditText regVillageTehsil;
    private TextInputEditText regPostcode;
    private TextInputEditText regDistrict;
    private TextInputEditText regState;

    String nameString, genderString, yobString, careOfString, houseString, villageTehsilString, streetString, districtString, stateString, postCodeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        sharedPreferences.getString("key1", null);
        nameString = sharedPreferences.getString("key2", null);
        genderString = sharedPreferences.getString("key3", null);
        yobString = sharedPreferences.getString("key4", null);
        careOfString = sharedPreferences.getString("key5", null);
        houseString = sharedPreferences.getString("key6", null);
        villageTehsilString = sharedPreferences.getString("key7", null);
        streetString = sharedPreferences.getString("key8", null);
        districtString = sharedPreferences.getString("key9", null);
        stateString = sharedPreferences.getString("key10", null);
        postCodeString = sharedPreferences.getString("key11", null);
        regName.setText(nameString);
        regGender.setText(genderString);
        regYob.setText(yobString);
        regCareOf.setText(careOfString);
        regHouseName.setText(houseString);
        regVillageTehsil.setText(villageTehsilString);
        regStreet.setText(streetString);
        regPostcode.setText(postCodeString);
        regDistrict.setText(districtString);
        regState.setText(stateString);
        regPostcode.setText(postCodeString);

        TextInputEditText[] fields = {regName, regGender, regYob, regCareOf, regHouseName, regStreet, regVillageTehsil, regPostcode, regDistrict, regState};
        validate(fields);


    }

    private boolean validate(TextInputEditText[] fields) {
        for (int i = 0; i < fields.length; i++) {
            TextInputEditText currentField = fields[i];
            if (currentField.getText().toString().length() <= 0) {
                currentField.setError("This field is required");
                return false;
            }
        }
        return true;
    }

    private void initView() {
        regEmail = findViewById(R.id.reg_email);
        regPhn = findViewById(R.id.reg_phn);
        regPass = findViewById(R.id.reg_pass);
        regConfirmpass = findViewById(R.id.reg_confirmpass);
        regName = findViewById(R.id.reg_name);
        regGender = findViewById(R.id.reg_gender);
        regYob = findViewById(R.id.reg_yob);
        regCareOf = findViewById(R.id.reg_care_of);
        regHouseName = findViewById(R.id.reg_house_name);
        regStreet = findViewById(R.id.reg_street);
        regVillageTehsil = findViewById(R.id.reg_village_tehsil);
        //   regPostoffice = findViewById(R.id.reg_postoffice);
        regPostcode = findViewById(R.id.reg_postcode);
        regDistrict = findViewById(R.id.reg_district);
        //  regSubdistrict = findViewById(R.id.reg_subdistrict);
        regState = findViewById(R.id.reg_state);
    }

    public void signupClick(View view) {
        TextInputEditText[] fields = {regEmail, regPhn, regPass, regConfirmpass, regName, regGender, regYob, regCareOf, regHouseName, regStreet, regVillageTehsil, regPostcode, regDistrict, regState};
        if (validate(fields)) {
            showProgressDialog(getApplicationContext(),"Loading...","Please wait!",getResources().getDrawable(R.drawable.clock),false);

            Api api = ApiClient.Citizen().create(Api.class);
            api.REGISTRATION_MODEL_CALL(nameString, regPhn.getText().toString(), regEmail.getText().toString(), regPass.getText().toString(), "123", genderString, yobString, careOfString, houseString, streetString, villageTehsilString, postCodeString, districtString, stateString).enqueue(new Callback<RegistrationModel>() {
                @Override
                public void onResponse(Call<RegistrationModel> call, Response<RegistrationModel> response) {
                    RegistrationModel registrationModel = response.body();
                    if (registrationModel.getStatus().equalsIgnoreCase("success")) {
                        dismissProgressDialog();
                        showToast(getApplicationContext(), "Registration Success");
                        startActivity(new Intent(getApplicationContext(), LoginHome.class));
                    } else {
                        dismissProgressDialog();
                        showToast(getApplicationContext(), "Registration failed");
                    }
                }

                @Override
                public void onFailure(Call<RegistrationModel> call, Throwable t) {
                    dismissProgressDialog();
                    showToast(getApplicationContext(), t.getMessage());
                }
            });
        } else {
            Toast.makeText(this, "Enter all values", Toast.LENGTH_SHORT).show();
        }
    }
}