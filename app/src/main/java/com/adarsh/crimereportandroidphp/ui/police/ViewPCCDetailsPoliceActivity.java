package com.adarsh.crimereportandroidphp.ui.police;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.retrofit.models.VerifyPccModel;
import com.adarsh.crimereportandroidphp.retrofit.network.Api;
import com.adarsh.crimereportandroidphp.retrofit.network.ApiClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPCCDetailsPoliceActivity extends AppCompatActivity {

    private TextInputEditText viewPccDistrict;
    private TextInputEditText viewPccPs;
    private TextInputEditText viewNameAndAliases;
    private TextInputEditText viewParentName;
    private TextInputEditText viewPccDob;
    private TextInputEditText viewPsprtNo;
    private TextInputEditText viewPermAddress;
    private TextInputEditText viewPresentAddres;
    private TextInputEditText viewAddressOfFive;
    private TextInputEditText viewPccPhone;
    private TextInputEditText viewPccEmail;
    private TextInputEditText viewPurposeOfVisit;
    private TextInputEditText viewNameAddress;
    private TextInputEditText viewCriminalCases;
    String pccid, userid, district, ps, name, parentname, dob, psportNo,
            permAddress, presentAddress, addressOfFive, pccPhone,
            email, purpose, referencee, criminalcases, verify;
    private MaterialButton verifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_p_c_c_details_police);
        initView();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("pcc", Context.MODE_PRIVATE);
        pccid = sharedPreferences.getString("pccid", null);
        userid = sharedPreferences.getString("userid", null);
        district = sharedPreferences.getString("district", null);
        ps = sharedPreferences.getString("police_station", null);
        name = sharedPreferences.getString("name", null);
        parentname = sharedPreferences.getString("father_name", null);
        dob = sharedPreferences.getString("dob", null);
        psportNo = sharedPreferences.getString("passport", null);
        permAddress = sharedPreferences.getString("perm_address", null);
        presentAddress = sharedPreferences.getString("present_address", null);
        addressOfFive = sharedPreferences.getString("residence_address", null);
        pccPhone = sharedPreferences.getString("phone", null);
        email = sharedPreferences.getString("email", null);
        purpose = sharedPreferences.getString("purpose", null);
        referencee = sharedPreferences.getString("reference_person", null);
        criminalcases = sharedPreferences.getString("criminal_case", null);
        verify = sharedPreferences.getString("verify", null);
        if (verify.equals("0")) {
            verifyBtn.setVisibility(View.VISIBLE);
        } else {
            verifyBtn.setVisibility(View.GONE);
        }

        viewPccDistrict.setText(district);
        viewPccPs.setText(ps);
        viewNameAndAliases.setText(name);
        viewParentName.setText(parentname);
        viewPccDob.setText(dob);
        viewPsprtNo.setText(psportNo);
        viewPermAddress.setText(permAddress);
        viewPresentAddres.setText(presentAddress);
        viewAddressOfFive.setText(addressOfFive);
        viewPccPhone.setText(pccPhone);
        viewPccEmail.setText(email);
        viewPurposeOfVisit.setText(purpose);
        viewNameAddress.setText(purpose);
        viewCriminalCases.setText(criminalcases);
    }

    private void initView() {
        viewPccDistrict = findViewById(R.id.view_pcc_district);
        viewPccPs = findViewById(R.id.view_pcc_ps);
        viewNameAndAliases = findViewById(R.id.view_name_and_aliases);
        viewParentName = findViewById(R.id.view_parent_name);
        viewPccDob = findViewById(R.id.view_pcc_dob);
        viewPsprtNo = findViewById(R.id.view_psprt_no);
        viewPermAddress = findViewById(R.id.view_perm_address);
        viewPresentAddres = findViewById(R.id.view_present_addres);
        viewAddressOfFive = findViewById(R.id.view_address_of_five);
        viewPccPhone = findViewById(R.id.view_pcc_phone);
        viewPccEmail = findViewById(R.id.view_pcc_email);
        viewPurposeOfVisit = findViewById(R.id.view_purpose_of_visit);
        viewNameAddress = findViewById(R.id.view_name_address);
        viewCriminalCases = findViewById(R.id.view_criminal_cases);
        verifyBtn = findViewById(R.id.verifyBtn);
    }

    public void verifyPcc(View view) {
        Api api = ApiClient.Citizen().create(Api.class);
        api.VERIFY_PCC_MODEL_CALL(pccid).enqueue(new Callback<VerifyPccModel>() {
            @Override
            public void onResponse(Call<VerifyPccModel> call, Response<VerifyPccModel> response) {
                VerifyPccModel verifyPccModel = response.body();
                if (verifyPccModel.getStatus().equalsIgnoreCase("Success")) {
                    Toast.makeText(ViewPCCDetailsPoliceActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), PoliceHome.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ViewPCCDetailsPoliceActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<VerifyPccModel> call, Throwable t) {
                Toast.makeText(ViewPCCDetailsPoliceActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}