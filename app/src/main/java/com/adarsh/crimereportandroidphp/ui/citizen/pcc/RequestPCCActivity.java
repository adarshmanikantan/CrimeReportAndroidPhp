package com.adarsh.crimereportandroidphp.ui.citizen.pcc;

import static com.adarsh.crimereportandroidphp.util.Util.showDatePickerDialog;
import static com.adarsh.crimereportandroidphp.util.UtilClass.showToast;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.adapters.ViewPoliceStationAdapter;
import com.adarsh.crimereportandroidphp.retrofit.models.AddPoliceModel;
import com.adarsh.crimereportandroidphp.retrofit.models.Station_details;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewPoliceStationModel;
import com.adarsh.crimereportandroidphp.retrofit.network.Api;
import com.adarsh.crimereportandroidphp.retrofit.network.ApiClient;
import com.adarsh.crimereportandroidphp.ui.citizen.CitizenHome;
import com.adarsh.crimereportandroidphp.ui.citizen.complaint.FileComplaintActivity;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestPCCActivity extends AppCompatActivity {
//else if(!firstName.matches("^[A-Za-z]+$")){
//    setError(etFirstName, AppConstants.WARN_FIELD_REQUIRED);
//}

    private TextInputEditText nameAndAliases;
    private TextInputEditText parentName;
    private TextInputEditText pccDob;
    private TextInputEditText psprtNo;
    private TextInputEditText permAddress;
    private TextInputEditText presentAddres;
    private TextInputEditText addressOfFive;
    private TextInputEditText pccPhone;
    private TextInputEditText pccEmail;
    private TextInputEditText purposeOfVisit;
    private TextInputEditText nameAddress;
    private TextInputEditText criminalCases;
    private MaterialAutoCompleteTextView pccDistrict;
    private MaterialAutoCompleteTextView pccPs;
    String selection = "Thiruvananthapuram";
    List<Station_details> viewPoliceStationModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_p_c_c);
        initView();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, R.layout.dropdownlayout, getResources()
                .getStringArray(R.array.district_arrays));
        // districtsArray=loadPoliceStations("Thiruvananthapuram");
        // Toast.makeText(this,districtsArray[0], Toast.LENGTH_SHORT).show();
        pccDistrict.setAdapter(arrayAdapter);
        pccDistrict.setCursorVisible(false);

        pccDistrict.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pccDistrict.showDropDown();
                selection = (String) parent.getItemAtPosition(position);
                loadPoliceStations(selection);

            }
        });

        pccPs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pccPs.showDropDown();
            }
        });

    }

    private void loadPoliceStations(String selection) {
        Api api = ApiClient.Citizen().create(Api.class);
        api.VIEW_POLICE_STATION_MODEL_CALL(selection).enqueue(new Callback<ViewPoliceStationModel>() {
            @Override
            public void onResponse(Call<ViewPoliceStationModel> call, Response<ViewPoliceStationModel> response) {
                ViewPoliceStationModel viewPoliceStationModel = response.body();
                if (viewPoliceStationModel.getStatus().equalsIgnoreCase("success")) {
                    viewPoliceStationModels = new ArrayList<>();
                    for (int i = 0; i < viewPoliceStationModel.getStation_details().length; i++) {
                        viewPoliceStationModels.add(viewPoliceStationModel.getStation_details()[i]);
                    }

                    ViewPoliceStationAdapter stationAdapter = new ViewPoliceStationAdapter(getApplicationContext(), R.id.file_complaint_layout, R.id.pstv, viewPoliceStationModels);
                    pccPs.setAdapter(stationAdapter);
                    pccPs.setCursorVisible(false);
                } else {
                    Toast.makeText(RequestPCCActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ViewPoliceStationModel> call, Throwable t) {
                Toast.makeText(RequestPCCActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }


    public void pccSubmitClick(View view) {
        if (validate()) {
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
            String userId=sharedPreferences.getString("loginid",null);
            Api api = ApiClient.Citizen().create(Api.class);
            api.ADD_PCC_MODEL_CALL(userId, pccDistrict.getText().toString(), pccPs.getText().toString(), nameAndAliases.getText().toString(), parentName.getText().toString(), pccDob.getText().toString(), psprtNo.getText().toString(), permAddress.getText().toString(), presentAddres.getText().toString(), addressOfFive.getText().toString(), pccPhone.getText().toString(), pccEmail.getText().toString(), purposeOfVisit.getText().toString(), nameAddress.getText().toString(), criminalCases.getText().toString()).enqueue(new Callback<AddPoliceModel>() {
                @Override
                public void onResponse(Call<AddPoliceModel> call, Response<AddPoliceModel> response) {
                   AddPoliceModel addPoliceModel=response.body();
                   if(addPoliceModel.getStatus().equalsIgnoreCase("Success"))
                   {
                       Toast.makeText(RequestPCCActivity.this, "Applied Successfully", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(RequestPCCActivity.this, CitizenHome.class));

                   }
                   else
                   {
                       Toast.makeText(RequestPCCActivity.this, "Failed.Try Again", Toast.LENGTH_SHORT).show();
                   }
                }

                @Override
                public void onFailure(Call<AddPoliceModel> call, Throwable t) {
                    Toast.makeText(RequestPCCActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        } else {

        }
    }

    private boolean validate() {
        if (pccDistrict.getText().toString().isEmpty()) {
          showToast(getApplicationContext(),"Select District");
            pccDistrict.setError("All fields are required");
            return false;
        }
        else if (pccPs.getText().toString().isEmpty()) {
        showToast(getApplicationContext(),"Select Police Station");
            pccPs.setError("All fields are required");
            return false;

        }
        else if (nameAndAliases.getText().toString().isEmpty()) {
        showToast(getApplicationContext(),"Enter your name");
            nameAndAliases.setError("All fields are required");

            return false;

        }
        else if(containsDigit(nameAndAliases.getText().toString())){
            Toast.makeText(getApplicationContext(),"Numbers are not allowed in name", Toast.LENGTH_SHORT).show();

            return false;
        }
        else if (parentName.getText().toString().isEmpty()) {
           showToast(getApplicationContext(),"Enter father's/mother's name");
            parentName.setError("All fields are required");

            return false;


        }
        else if(containsDigit(parentName.getText().toString())){
            Toast.makeText(getApplicationContext(),"Numbers are not allowed in father's/mother's name", Toast.LENGTH_SHORT).show();

            return false;
        }
        else if (pccDob.getText().toString().isEmpty()) {
           showToast(getApplicationContext(),"Enter Date of Birth");

            pccDistrict.setError("All fields are required");

            return false;


        }
        else if (psprtNo.getText().toString().isEmpty()) {
         showToast(getApplicationContext(),"Enter Passport number");
            psprtNo.setError("All fields are required");

            return false;

        }
        else if (permAddress.getText().toString().isEmpty()) {
           showToast(getApplicationContext(),"Enter permanent address");
            permAddress.setError("All fields are required");

            return false;

        }
        else if (presentAddres.getText().toString().isEmpty()) {
           showToast(getApplicationContext(),"Enter present address");
            presentAddres.setError("All fields are required");

            return false;


        }
        else if (addressOfFive.getText().toString().isEmpty()) {
            showToast(getApplicationContext(),"Enter address of residence in last 5 years");
            addressOfFive.setError("All fields are required");

            return false;

        }
        else if (!(pccPhone.getText().toString().length() == 10)){
            showToast(getApplicationContext(),"Enter valid phone number");
            return false;


        }
        else if (!(isValidEmail(pccEmail.getText().toString()))) {
           showToast(getApplicationContext(),"Enter valid email");
            return false;

        }
        else if (purposeOfVisit.getText().toString().isEmpty()) {
            purposeOfVisit.setError("All fields are required");
      showToast(getApplicationContext(),"Enter purpose of visit");
            return false;

        }
        else if (nameAddress.getText().toString().isEmpty()) {
           showToast(getApplicationContext(),"Enter details of referencee");
            nameAddress.setError("All fields are required");

            return false;
        }
        else if(criminalCases.getText().toString().isEmpty())
        {
            showToast(getApplicationContext(),"Enter about the criminal cases involved");
            criminalCases.setError("All fields are required");
            return false;
        }

         else {
            return true;
        }
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public final boolean containsDigit(String s) {
        boolean containsDigit = false;

        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (containsDigit = Character.isDigit(c)) {
                    break;
                }
            }
        }

        return containsDigit;
    }
    private void initView() {
        nameAndAliases = findViewById(R.id.name_and_aliases);
        parentName = findViewById(R.id.parent_name);
        pccDob = findViewById(R.id.pcc_dob);
        psprtNo = findViewById(R.id.psprt_no);
        permAddress = findViewById(R.id.perm_address);
        presentAddres = findViewById(R.id.present_addres);
        addressOfFive = findViewById(R.id.address_of_five);
        pccPhone = findViewById(R.id.pcc_phone);
        pccEmail = findViewById(R.id.pcc_email);
        purposeOfVisit = findViewById(R.id.purpose_of_visit);
        nameAddress = findViewById(R.id.name_address);
        criminalCases = findViewById(R.id.criminal_cases);
        pccDistrict = findViewById(R.id.pcc_district);
        pccPs = findViewById(R.id.pcc_ps);
    }

    public void selectDate(View view) {
        showDatePickerDialog(RequestPCCActivity.this,pccDob);
    }

}