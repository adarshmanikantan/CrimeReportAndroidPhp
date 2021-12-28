package com.adarsh.crimereportandroidphp.ui.police;

import static com.adarsh.crimereportandroidphp.util.UtilClass.showToast;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.retrofit.models.Police_Login_Model;
import com.adarsh.crimereportandroidphp.retrofit.models.VerifyComplaintModel;
import com.adarsh.crimereportandroidphp.retrofit.network.Api;
import com.adarsh.crimereportandroidphp.retrofit.network.ApiClient;
import com.adarsh.crimereportandroidphp.ui.citizen.complaint.ViewFirActivity;
import com.adarsh.crimereportandroidphp.util.AESUtils;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EncryptedPoliceComplaintActivity extends AppCompatActivity {
    private TextInputEditText viewComplaintDistrict;
    private TextInputEditText viewComplaintPs;
    private TextInputEditText viewComplaintType;
    private TextInputEditText viewPlaceOfOccurence;
    private TextInputEditText viewDate;
    private TextInputEditText viewTime;
    private TextInputEditText viewComplaintDetails;
    int flag = 0;
    String[] dateTime;
    String complaintId, userId, policeStation, complaintType, district, placeOfOccurence, date, time, details, verify, firId,
            encpoliceStation, enccomplaintType, encdistrict, encplaceOfOccurence, encdate, encTime, encdetails,complainantName,userEmail,userPhone;
    private Button generateFirBtn;
    private ScrollView fileComplaintLayout;
    private Button verifyComplaintBtn;
    private Button viewFirBtn;
    private Button decryptBtn;
    private TextView complainantname;
    private TextView email;
    private TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypted_police_complaint);
        initView();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("policecomplaint", Context.MODE_PRIVATE);
        complaintId = sharedPreferences.getString("complaintId", null);
        userId = sharedPreferences.getString("userId", null);
        policeStation = sharedPreferences.getString("policeStation", null);
        complaintType = sharedPreferences.getString("complaintType", null);
        district = sharedPreferences.getString("district", null);
        placeOfOccurence = sharedPreferences.getString("placeOfOccurence", null);
        dateTime = sharedPreferences.getString("date_time", null).split(",");
        date = dateTime[0];
        time = dateTime[1];
        details = sharedPreferences.getString("details", null);
        verify = sharedPreferences.getString("verify", null);
        firId = sharedPreferences.getString("firId", null);
        complainantName=sharedPreferences.getString("username",null);
        userEmail=sharedPreferences.getString("email",null);
        userPhone= sharedPreferences.getString("phone",null);
//        Toast.makeText(getApplicationContext(),verify,Toast.LENGTH_LONG).show();
//        if(null != firId)
//        {
//            viewFirBtn.setVisibility(View.VISIBLE);
//        }

        if (verify.equals("0")) {
            verifyComplaintBtn.setVisibility(View.VISIBLE);
        }
        if (verify.equals("1")) {
            verifyComplaintBtn.setVisibility(View.GONE);
            if ("".equals(firId)) {
                // Toast.makeText(getApplicationContext(), "No Fir", Toast.LENGTH_SHORT).show();
                generateFirBtn.setVisibility(View.VISIBLE);
            } else {
                // Toast.makeText(getApplicationContext(), firId, Toast.LENGTH_SHORT).show();
                generateFirBtn.setVisibility(View.GONE);
            }

        }

        try {
            complainantname.setText("Complainant Name : "+complainantName);
            email.setText("Email : "+userEmail);
            phone.setText("Phone : "+userPhone);
            encpoliceStation = AESUtils.encrypt(policeStation);
            enccomplaintType = AESUtils.encrypt(complaintType);
            encdistrict = AESUtils.encrypt(district);
            encplaceOfOccurence = AESUtils.encrypt(placeOfOccurence);
            encdate = AESUtils.encrypt(date);
            encTime = AESUtils.encrypt(time);
            encdetails = AESUtils.encrypt(details);
            viewComplaintPs.setText(encpoliceStation);
            viewComplaintType.setText(enccomplaintType);
            viewComplaintDistrict.setText(encdistrict);
            viewPlaceOfOccurence.setText(encplaceOfOccurence);
            viewDate.setText(encdate);
            viewTime.setText(encTime);
            viewComplaintDetails.setText(encdetails);



        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        generateFirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), GenerateFirActivity.class);
                startActivity(i);
            }
        });
    }

    private void initView() {
        viewComplaintDistrict = findViewById(R.id.view_complaint_district);
        viewComplaintPs = findViewById(R.id.view_complaint_ps);
        viewComplaintType = findViewById(R.id.view_complaint_type);
        viewPlaceOfOccurence = findViewById(R.id.view_place_of_occurence);
        viewDate = findViewById(R.id.view_date);
        viewTime = findViewById(R.id.view_time);
        viewComplaintDetails = findViewById(R.id.view_complaint_details);
        generateFirBtn = findViewById(R.id.view_fir_btn);
        fileComplaintLayout = findViewById(R.id.file_complaint_layout);
        verifyComplaintBtn = findViewById(R.id.verify_complaint_btn);
        viewFirBtn = findViewById(R.id.view_fir_btn);
        decryptBtn = findViewById(R.id.decrypt_btn);
        complainantname = findViewById(R.id.complainantname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
    }

    public void decryptComplaint(View view) {
        if (flag == 0) {
            LayoutInflater inflater = LayoutInflater.from(EncryptedPoliceComplaintActivity.this);
            final View cuslay = inflater.inflate(R.layout.askpin, null);

            final TextInputEditText pin = cuslay.findViewById(R.id.epin);
            Button ok = cuslay.findViewById(R.id.eok);
            AlertDialog.Builder AB = new AlertDialog.Builder(EncryptedPoliceComplaintActivity.this);
            AB.setView(cuslay);
            final AlertDialog A = AB.create();
            A.show();

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("pmykey", Context.MODE_PRIVATE);
                    Api api = ApiClient.Citizen().create(Api.class);
                    api.POLICE_LOGIN_MODEL_CALL(sharedPreferences.getString("code", null), pin.getText().toString()).enqueue(new Callback<Police_Login_Model>() {
                        @Override
                        public void onResponse(Call<Police_Login_Model> call, Response<Police_Login_Model> response) {

                            Police_Login_Model rootLoginModel = response.body();
                            if (rootLoginModel.getStatus().equalsIgnoreCase("success")) {
                                try {
                                    A.dismiss();
                                    viewComplaintPs.setText(AESUtils.decrypt(encpoliceStation));
                                    viewComplaintType.setText(AESUtils.decrypt(enccomplaintType));
                                    viewComplaintDistrict.setText(AESUtils.decrypt(encdistrict));
                                    viewPlaceOfOccurence.setText(AESUtils.decrypt(encplaceOfOccurence));
                                    viewDate.setText(AESUtils.decrypt(encdate));
                                    viewTime.setText(AESUtils.decrypt(encTime));
                                    viewComplaintDetails.setText(AESUtils.decrypt(encdetails));
                                    //   generateFirBtn.setVisibility(View.VISIBLE);
                                    flag = 1;
                                    decryptBtn.setVisibility(View.GONE);

                                } catch (Exception e) {
                                    Toast.makeText(EncryptedPoliceComplaintActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            } else {

                                showToast(getApplicationContext(), "Incorrect Password");
                            }
                        }


                        @Override
                        public void onFailure(Call<Police_Login_Model> call, Throwable t) {

                            showToast(getApplicationContext(), t.getMessage());
                        }
                    });

                }

            });
        } else {
            if (firId.equals("")) {

                // Toast.makeText(this, "FIRID NULL", Toast.LENGTH_SHORT).show();
            } else {
                // generateFirBtn.setVisibility(View.INVISIBLE);
                Intent i = new Intent(getApplicationContext(), ViewFirActivity.class);
                i.putExtra("firIdIntent", firId);
                startActivity(i);
            }
        }
    }

    public void verifyComplaint(View view) {
        Api api = ApiClient.Citizen().create(Api.class);
        api.VERIFY_COMPLAINT_MODEL_CALL(complaintId).enqueue(new Callback<VerifyComplaintModel>() {
            @Override
            public void onResponse(Call<VerifyComplaintModel> call, Response<VerifyComplaintModel> response) {
                VerifyComplaintModel verifyComplaintModel = response.body();
                if (verifyComplaintModel.getStatus().equalsIgnoreCase("success")) {
                    verifyComplaintBtn.setVisibility(View.GONE);
                    generateFirBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(EncryptedPoliceComplaintActivity.this, "Complaint Verified Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EncryptedPoliceComplaintActivity.this, "Something went wrong! Try Again", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<VerifyComplaintModel> call, Throwable t) {
                Toast.makeText(EncryptedPoliceComplaintActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}