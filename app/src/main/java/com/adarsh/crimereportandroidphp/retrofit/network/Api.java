package com.adarsh.crimereportandroidphp.retrofit.network;

import com.adarsh.crimereportandroidphp.retrofit.models.AddPCCModel;
import com.adarsh.crimereportandroidphp.retrofit.models.AddPoliceModel;
import com.adarsh.crimereportandroidphp.retrofit.models.FileComplaintModel;
import com.adarsh.crimereportandroidphp.retrofit.models.GenerateFirModel;
import com.adarsh.crimereportandroidphp.retrofit.models.Police_Login_Model;
import com.adarsh.crimereportandroidphp.retrofit.models.RegistrationModel;
import com.adarsh.crimereportandroidphp.retrofit.models.RootLoginModel;
import com.adarsh.crimereportandroidphp.retrofit.models.VerifyComplaintModel;
import com.adarsh.crimereportandroidphp.retrofit.models.VerifyPccModel;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewComplaintByStationModel;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewComplaintModel;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewFirModel;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewIpcModel;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewPccByCitizenModel;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewPccByPoliceModel;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewPoliceStationModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("user_login.php?")
    Call<RootLoginModel> ROOT_LOGIN_MODEL_CALL(@Query("email") String email, @Query("password") String password);

    @GET("police_login.php?")
    Call<Police_Login_Model> POLICE_LOGIN_MODEL_CALL(@Query("code") String code, @Query("password") String pswd);

    @GET("user_regs.php?")
    Call<RegistrationModel> REGISTRATION_MODEL_CALL(@Query("name") String username, @Query("phone") String phone, @Query("email") String email, @Query("password") String password
            , @Query("adhar") String aadhar, @Query("gender") String gender, @Query("year") String year, @Query("care_of") String careOf,
                                                    @Query("house_name") String housename, @Query("street") String street,
                                                    @Query("village") String village, @Query("post_code") String postCode, @Query("district") String district, @Query("state") String state);

    @GET("complaint.php?")
    Call<FileComplaintModel> FILE_COMPLAINT_MODEL_CALL(@Query("police_station") String ps, @Query("complaint_type") String complaintType,
                                                       @Query("district") String district, @Query("place_of_occurance") String place, @Query("date_time") String dateTime, @Query("details") String details, @Query("user_id") String userId);

    @GET("filter_complaint.php?")
    Call<ViewComplaintModel> VIEW_COMPLAINT_MODEL_CALL(@Query("user_id") String userId);

    @GET("view_particular_station.php?")
    Call<ViewPoliceStationModel> VIEW_POLICE_STATION_MODEL_CALL(@Query("district") String district);

    @GET("add_pcc.php?")
    Call<AddPoliceModel> ADD_PCC_MODEL_CALL(@Query("user_id") String user_id, @Query("district") String district, @Query("station") String station
            , @Query("name") String name, @Query("father_name") String father_name, @Query("dob") String dob, @Query("passport") String passport, @Query("per_address") String per_address,
                                            @Query("pre_address") String pre_address, @Query("res_address") String res_address, @Query("phone") String phone, @Query("email") String email,
                                            @Query("purpose") String purpose, @Query("ref_person") String ref_person, @Query("criminal_case") String criminal_case);

    @GET("view_complaint_station.php?")
    Call<ViewComplaintByStationModel> VIEW_COMPLAINT_BY_STATION_MODEL_CALL(@Query("district") String district, @Query("police_station") String police_station);

    @GET("view_pcc.php?")
    Call<ViewPccByPoliceModel> VIEW_PCC_BY_POLICE_MODEL_CALL(@Query("district") String district, @Query("police_station") String police_station);

    @GET("add_pcc.php?")
    Call<AddPCCModel> SEARCH_PCC_MODEL_CALL(@Query("user_id") String user_id, @Query("district") String district, @Query("station") String station
            , @Query("name") String name, @Query("father_name") String father_name, @Query("dob") String dob, @Query("passport") String passport, @Query("per_address") String per_address,
                                            @Query("pre_address") String pre_address, @Query("res_address") String res_address, @Query("phone") String phone, @Query("email") String email,
                                            @Query("purpose") String purpose, @Query("ref_person") String ref_person, @Query("criminal_case") String criminal_case);

    @GET("post_fir.php?")
    Call<GenerateFirModel> GENERATE_FIR_MODEL_CALL(@Query("station_id") String station_id, @Query("district") String district,
                                                   @Query("police_station") String police_station, @Query("fir_number") String fir_number, @Query("current_year") String current_year,
                                                   @Query("fir_date") String fir_date, @Query("fir_time") String fir_time, @Query("ipc_act") String ipc_act, @Query("ipc_section") String ipc_section,
                                                   @Query("occure_day") String occure_day, @Query("occure_datefrom") String occure_datefrom, @Query("occure_dateto") String occure_dateto,
                                                   @Query("occure_timefrom") String occure_timefrom, @Query("occure_timeto") String occure_timeto, @Query("diary_entry") String diary_entry,
                                                   @Query("time") String time, @Query("inform_type") String inform_type, @Query("distance") String distance, @Query("complaint_details") String complaint_details,
                                                   @Query("beat_number") String beat_number, @Query("address_citizen") String address_citizen, @Query("complaint_id") String complaint_id);

    @GET("view_pcc_userid.php?")
    Call<ViewPccByCitizenModel> VIEW_PCC_BY_CITIZEN_MODEL_CALL(@Query("user_id") String userId);

    @GET("verify_pcc.php?")
    Call<VerifyPccModel> VERIFY_PCC_MODEL_CALL(@Query("pcc_id") String pccId);

    @GET("verify_complaint.php?")
    Call<VerifyComplaintModel> VERIFY_COMPLAINT_MODEL_CALL(@Query("complaint_id") String complaintId);

    @GET("view_fir.php?")
    Call<ViewFirModel> VIEW_FIR_MODEL_CALL(@Query("fir_id") String firId);

    @GET("getallSection.php")
    Call<ViewIpcModel>VIEW_IPC_MODEL_CALL();
}


