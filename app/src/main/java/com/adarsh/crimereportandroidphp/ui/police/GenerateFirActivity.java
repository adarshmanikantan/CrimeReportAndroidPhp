package com.adarsh.crimereportandroidphp.ui.police;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.retrofit.models.GenerateFirModel;
import com.adarsh.crimereportandroidphp.retrofit.network.Api;
import com.adarsh.crimereportandroidphp.retrofit.network.ApiClient;
import com.adarsh.crimereportandroidphp.ui.citizen.complaint.FileComplaintActivity;
import com.adarsh.crimereportandroidphp.ui.citizen.complaint.ViewFirActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.adarsh.crimereportandroidphp.util.Util.showDatePickerDialog;
import static com.adarsh.crimereportandroidphp.util.Util.showTimePickerDialog;

public class GenerateFirActivity extends AppCompatActivity {

    private TextInputEditText firNumberField;
    private TextInputEditText firDateField;
    private TextInputEditText firTimeField;
    private TextInputEditText firIpcactField;
    private TextInputEditText ipcSessionField;
    private TextInputEditText dayField;
    private TextInputEditText timeFromField;
    private TextInputEditText timeToField;
    private TextInputEditText dateFromField;
    private TextInputEditText dateToField;
    private TextInputEditText diaryentryField;
    private TextInputEditText entrytimeField;
    private TextInputEditText typeField;
    private TextInputEditText distanceField;
    private TextInputEditText beatNoField;
    private TextInputEditText addressEdt;
    private TextInputEditText complaintdetailsField;
    String firNumber,firDate, firTime, firIpcAct, firIpcSection, days, timeFrom, timeTo, dateFrom, dateTo, diaryEntry, diaryEntryTime, type, distance, beatNo, address, complaintDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_fir);
        initView();
    }

    public void nextClick(View view) {
        if(validate())
        {
            firNumber = firNumberField.getText().toString();
            firDate = firDateField.getText().toString();
            firTime = firTimeField.getText().toString();
            firIpcAct = firIpcactField.getText().toString();
            firIpcSection = ipcSessionField.getText().toString();
            days = dayField.getText().toString();
            timeFrom = timeFromField.getText().toString();
            timeTo = timeToField.getText().toString();
            dateFrom = dateFromField.getText().toString();
            dateTo = dateToField.getText().toString();
            diaryEntry = diaryentryField.getText().toString();
            diaryEntryTime = entrytimeField.getText().toString();
            type = typeField.getText().toString();
            distance = distanceField.getText().toString();
            beatNo = beatNoField.getText().toString();
            address = addressEdt.getText().toString();
            complaintDetails = complaintdetailsField.getText().toString();
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("policecomplaint", Context.MODE_PRIVATE);
           String complaintId= sharedPreferences.getString("complaintId", null);
           String policeStationName= sharedPreferences.getString("policeStation", null);
          String district=  sharedPreferences.getString("district", null);
            SharedPreferences sp = getApplicationContext().getSharedPreferences("pmykey", Context.MODE_PRIVATE);
            String stationId=sp.getString("station_id",null);

            Api api=ApiClient.Citizen().create(Api.class);
            api.GENERATE_FIR_MODEL_CALL(stationId,district,policeStationName,firNumber,"2021",firDate,firTime,firIpcAct,firIpcSection,days,dateFrom,dateTo,timeFrom,timeTo,diaryEntry,diaryEntryTime,type,distance,complaintDetails,beatNo,address,complaintId).enqueue(new Callback<GenerateFirModel>() {
                @Override
                public void onResponse(Call<GenerateFirModel> call, Response<GenerateFirModel> response) {
                    GenerateFirModel generateFirModel=response.body();
                    if(generateFirModel.getStatus().equalsIgnoreCase("success"))
                    {
                        String firId=generateFirModel.getFir_id();


                        Toast.makeText(GenerateFirActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("firpref", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("firno", firNumber);
//                        editor.putString("firdate", firDate);
//                        editor.putString("firtime", firTime);
//                        editor.putString("ipcAct", firIpcAct);
//                        editor.putString("ipcsession", firIpcSection);
//                        editor.putString("day", days);
//                        editor.putString("timefrom", timeFrom);
//                        editor.putString("timeto", timeTo);
//                        editor.putString("datefrom", dateFrom);
//                        editor.putString("dateto", dateTo);
//                        editor.putString("diaryentry", diaryEntry);
//                        editor.putString("diaryentrytime", diaryEntryTime);
//                        editor.putString("type", type);
//                        editor.putString("distance", distance);
//                        editor.putString("beatno", beatNo);
//                        editor.putString("address", address);
//                        editor.putString("complaintdetails", complaintDetails);
//                        editor.apply();
                        Intent i = new Intent(GenerateFirActivity.this, ViewFirActivity.class);
                        i.putExtra("firIdIntent",firId);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(GenerateFirActivity.this, "FIR Generation Failed,Try Again", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GenerateFirModel> call, Throwable t) {
                    Toast.makeText(GenerateFirActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    private boolean validate() {
        if(firNumberField.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(firDateField.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(firTimeField.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(firIpcactField.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(ipcSessionField.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(dayField.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(dateFromField.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(dateToField.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(timeFromField.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(timeToField.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(diaryentryField.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(entrytimeField.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(typeField.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(distanceField.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(beatNoField.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(addressEdt.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(complaintdetailsField.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {

            return true;
        }
    }

    private void initView() {
        firNumberField = findViewById(R.id.fir_number_field);
        firDateField = findViewById(R.id.fir_date_field);
        firTimeField = findViewById(R.id.fir_time_field);
        firIpcactField = findViewById(R.id.fir_ipcact_field);
        ipcSessionField = findViewById(R.id.ipc_session_field);
        dayField = findViewById(R.id.day_field);
        timeFromField = findViewById(R.id.time_from_field);
        timeToField = findViewById(R.id.time_to_field);
        dateFromField = findViewById(R.id.date_from_field);
        dateToField = findViewById(R.id.date_to_field);
        diaryentryField = findViewById(R.id.diaryentry_field);
        entrytimeField = findViewById(R.id.entrytime_field);
        typeField = findViewById(R.id.type_field);
        distanceField = findViewById(R.id.distance_field);
        beatNoField = findViewById(R.id.beat_no_field);
        addressEdt = findViewById(R.id.address_edt);
        complaintdetailsField = findViewById(R.id.complaintdetails_field);
    }

    public void setFirDate(View view) {
        showDatePickerDialog(GenerateFirActivity.this,firDateField);

    }

    public void setFirTime(View view) {
        showTimePickerDialog(GenerateFirActivity.this,firTimeField);
    }

    public void setTimeFrom(View view) {
        showTimePickerDialog(GenerateFirActivity.this,timeFromField);

    }


    public void setDateFrom(View view) {
        showDatePickerDialog(GenerateFirActivity.this,dateFromField);

    }

    public void setDateTo(View view) {
        showDatePickerDialog(GenerateFirActivity.this,dateToField);

    }

    public void setTimeTo(View view) {
        showTimePickerDialog(GenerateFirActivity.this,timeToField);

    }

    public void setEntryTime(View view) {
        showTimePickerDialog(GenerateFirActivity.this,entrytimeField);

    }
}
