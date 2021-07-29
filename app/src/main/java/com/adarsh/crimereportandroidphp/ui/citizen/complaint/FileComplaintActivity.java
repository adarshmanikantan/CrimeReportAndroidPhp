package com.adarsh.crimereportandroidphp.ui.citizen.complaint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.adapters.ViewPoliceStationAdapter;
import com.adarsh.crimereportandroidphp.retrofit.models.FileComplaintModel;
import com.adarsh.crimereportandroidphp.retrofit.models.Station_details;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewPoliceStationModel;
import com.adarsh.crimereportandroidphp.retrofit.network.Api;
import com.adarsh.crimereportandroidphp.retrofit.network.ApiClient;
import com.adarsh.crimereportandroidphp.ui.citizen.CitizenHome;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.adarsh.crimereportandroidphp.util.Util.showDatePickerDialog;
import static com.adarsh.crimereportandroidphp.util.Util.showTimePickerDialog;
import static com.adarsh.crimereportandroidphp.util.UtilClass.intent;

public class FileComplaintActivity extends AppCompatActivity {

    private MaterialAutoCompleteTextView complaintDistrict;
    private MaterialAutoCompleteTextView complaintPs;
    private TextInputEditText complaintType;
    private TextInputEditText placeOfOccurence;
    private TextInputEditText date;
    private TextInputEditText time;
    private TextInputEditText complaintDetails;
    String selection="Thiruvananthapuram";
    String[] policeStations;
    List<Station_details> viewPoliceStationModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_complaint);
        initView();


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, R.layout.dropdownlayout, getResources()
                .getStringArray(R.array.district_arrays));
       // districtsArray=loadPoliceStations("Thiruvananthapuram");
       // Toast.makeText(this,districtsArray[0], Toast.LENGTH_SHORT).show();
        complaintDistrict.setAdapter(arrayAdapter);
        complaintDistrict.setCursorVisible(false);
        complaintDistrict.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                complaintDistrict.showDropDown();
                selection = (String) parent.getItemAtPosition(position);
               loadPoliceStations(selection);

            }
        });

    complaintPs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            complaintPs.showDropDown();
        }
    });

    }

    private void loadPoliceStations(String selection) {
        Api api=ApiClient.Citizen().create(Api.class);
        api.VIEW_POLICE_STATION_MODEL_CALL(selection).enqueue(new Callback<ViewPoliceStationModel>() {
            @Override
            public void onResponse(Call<ViewPoliceStationModel> call, Response<ViewPoliceStationModel> response) {
                ViewPoliceStationModel viewPoliceStationModel=response.body();
                if(viewPoliceStationModel.getStatus().equalsIgnoreCase("success"))
                {
                    viewPoliceStationModels=new ArrayList<>();
                    for(int i=0;i<viewPoliceStationModel.getStation_details().length;i++)
                    {
                        viewPoliceStationModels.add(viewPoliceStationModel.getStation_details()[i]);
                    }

                    ViewPoliceStationAdapter stationAdapter=new ViewPoliceStationAdapter(getApplicationContext(),R.id.file_complaint_layout,R.id.pstv,viewPoliceStationModels);
                    complaintPs.setAdapter(stationAdapter);
                    complaintPs.setCursorVisible(false);
                }
                else
                {
                    Toast.makeText(FileComplaintActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ViewPoliceStationModel> call, Throwable t) {
                Toast.makeText(FileComplaintActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void initView() {
        complaintDistrict = findViewById(R.id.complaint_district);
        complaintPs = findViewById(R.id.complaint_ps);
        complaintType = findViewById(R.id.complaint_type);
        placeOfOccurence = findViewById(R.id.place_of_occurence);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        complaintDetails = findViewById(R.id.complaint_details);
    }

    public void complaintSubmitClick(View view) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String userId=sharedPreferences.getString("loginid",null);
        String district=complaintDistrict.getText().toString();
       String policestation=complaintPs.getText().toString();
       String strComplaintType=complaintType.getText().toString();
       String strPlaceOfOccurence=placeOfOccurence.getText().toString();
       String strDateTime=date.getText().toString()+" , "+time.getText().toString();
       String details=complaintDetails.getText().toString();
       if(validate())
       {
           Api api = ApiClient.Citizen().create(Api.class);
           api.FILE_COMPLAINT_MODEL_CALL(policestation,strComplaintType,district,strPlaceOfOccurence,strDateTime,details,userId).enqueue(new Callback<FileComplaintModel>() {
               @Override
               public void onResponse(Call<FileComplaintModel> call, Response<FileComplaintModel> response) {
                   FileComplaintModel fileComplaintModel=response.body();
                   if(fileComplaintModel.getStatus().equalsIgnoreCase("Success"))
                   {
                      startActivity(new Intent(FileComplaintActivity.this,CitizenHome.class));
                   }
               }

               @Override
               public void onFailure(Call<FileComplaintModel> call, Throwable t) {

               }
           });

       }
    }

    private boolean validate() {
        if(complaintDistrict.getText().toString().isEmpty())
        {
            complaintDistrict.setError("Select District");
            return false;
        }
        if(complaintPs.getText().toString().isEmpty())
        {
            complaintPs.setError("Select Police Station");
            return false;
        }
        if(complaintType.getText().toString().isEmpty())
        {
            complaintType.setError("This field is empty");
            return false;
        }
        if(placeOfOccurence.getText().toString().isEmpty())
        {
            placeOfOccurence.setError("This field is empty");
            return false;
        }
         if(date.getText().toString().isEmpty())
        {
            date.setError("This field is empty");
            return false;
        }
         if(time.getText().toString().isEmpty())
        {
            time.setError("This field is empty");
            return false;
        }
         if(complaintDetails.getText().toString().isEmpty())
        {
            complaintDetails.setError("This field is empty");
            return false;
        }
        else
        {
            return true;
        }
    }


    public void selectDate(View view) {
        showDatePickerDialog(FileComplaintActivity.this,date);
    }

    public void selectTime(View view) {
      showTimePickerDialog(FileComplaintActivity.this,time);
    }


}