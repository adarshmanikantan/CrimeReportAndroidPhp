package com.adarsh.crimereportandroidphp.ui.citizen.complaint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.retrofit.models.RootLoginModel;
import com.adarsh.crimereportandroidphp.retrofit.network.Api;
import com.adarsh.crimereportandroidphp.retrofit.network.ApiClient;
import com.adarsh.crimereportandroidphp.util.AESUtils;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.adarsh.crimereportandroidphp.util.UtilClass.showToast;

public class EncryptedComplaintActivity extends AppCompatActivity {

    private TextInputEditText viewComplaintDistrict;
    private TextInputEditText viewComplaintPs;
    private TextInputEditText viewComplaintType;
    private TextInputEditText viewPlaceOfOccurence;
    private TextInputEditText viewDate;
    private TextInputEditText viewTime;
    private TextInputEditText viewComplaintDetails;

    String[] dateTime;
    String complaintId, userId, policeStation, complaintType, district, placeOfOccurence, date, time, details, verify, firId,
            encpoliceStation, enccomplaintType, encdistrict, encplaceOfOccurence, encdate, encTime, encdetails;
    private Button viewFirBtn;
    private ScrollView fileComplaintLayout;
    private Button decryptComplaintBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypted_complaint);
        initView();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("complaint", Context.MODE_PRIVATE);
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

        try {
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
        viewFirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewFirActivity.class);
                i.putExtra("firIdIntent", firId);
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
        viewFirBtn = findViewById(R.id.view_fir_btn);
        fileComplaintLayout = findViewById(R.id.file_complaint_layout);
        decryptComplaintBtn = findViewById(R.id.decrypt_complaint_btn);
    }

    public void decryptComplaint(View view) {
        LayoutInflater inflater = LayoutInflater.from(EncryptedComplaintActivity.this);
        final View cuslay = inflater.inflate(R.layout.askpin, null);

        final TextInputEditText pin = cuslay.findViewById(R.id.epin);
        Button ok = cuslay.findViewById(R.id.eok);
        AlertDialog.Builder AB = new AlertDialog.Builder(EncryptedComplaintActivity.this);
        AB.setView(cuslay);
        final AlertDialog A = AB.create();
        A.show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);

                Api api = ApiClient.Citizen().create(Api.class);
                api.ROOT_LOGIN_MODEL_CALL(sharedPreferences.getString("email", null), pin.getText().toString()).enqueue(new Callback<RootLoginModel>() {
                    @Override
                    public void onResponse(Call<RootLoginModel> call, Response<RootLoginModel> response) {

                        RootLoginModel rootLoginModel = response.body();
                        if (rootLoginModel.getStatus().equalsIgnoreCase("success")) {
                            try {
                                A.dismiss();
                                decryptComplaintBtn.setVisibility(View.INVISIBLE);
                                viewComplaintPs.setText(AESUtils.decrypt(encpoliceStation));
                                viewComplaintType.setText(AESUtils.decrypt(enccomplaintType));
                                viewComplaintDistrict.setText(AESUtils.decrypt(encdistrict));
                                viewPlaceOfOccurence.setText(AESUtils.decrypt(encplaceOfOccurence));
                                viewDate.setText(AESUtils.decrypt(encdate));
                                viewTime.setText(AESUtils.decrypt(encTime));
                                viewComplaintDetails.setText(AESUtils.decrypt(encdetails));
                                if (firId.equals("")) {

                                } else {
                                    Toast.makeText(EncryptedComplaintActivity.this, firId, Toast.LENGTH_SHORT).show();
                                    viewFirBtn.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception e) {
                                Toast.makeText(EncryptedComplaintActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {

                            showToast(getApplicationContext(), "Incorrect Password");
                        }
                    }


                    @Override
                    public void onFailure(Call<RootLoginModel> call, Throwable t) {

                        showToast(getApplicationContext(), t.getMessage());
                    }
                });

            }

        });
    }
}