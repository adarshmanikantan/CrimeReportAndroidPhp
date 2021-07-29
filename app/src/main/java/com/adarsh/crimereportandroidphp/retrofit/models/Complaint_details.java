package com.adarsh.crimereportandroidphp.retrofit.models;

public class Complaint_details
{
    private String complaint_id;
    private String fir_id;

    public String getFir_id() {
        return fir_id;
    }

    public void setFir_id(String fir_id) {
        this.fir_id = fir_id;
    }

    private String complaint_type;

    private String date_time;

    private String user_id;

    private String district;

    private String verify;

    private String police_station;

    private String details;

    private String place_of_occurance;

    public String getComplaint_id ()
    {
        return complaint_id;
    }

    public void setComplaint_id (String complaint_id)
    {
        this.complaint_id = complaint_id;
    }

    public String getComplaint_type ()
    {
        return complaint_type;
    }

    public void setComplaint_type (String complaint_type)
    {
        this.complaint_type = complaint_type;
    }

    public String getDate_time ()
    {
        return date_time;
    }

    public void setDate_time (String date_time)
    {
        this.date_time = date_time;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getDistrict ()
    {
        return district;
    }

    public void setDistrict (String district)
    {
        this.district = district;
    }

    public String getVerify ()
    {
        return verify;
    }

    public void setVerify (String verify)
    {
        this.verify = verify;
    }

    public String getPolice_station ()
    {
        return police_station;
    }

    public void setPolice_station (String police_station)
    {
        this.police_station = police_station;
    }

    public String getDetails ()
    {
        return details;
    }

    public void setDetails (String details)
    {
        this.details = details;
    }

    public String getPlace_of_occurance ()
    {
        return place_of_occurance;
    }

    public void setPlace_of_occurance (String place_of_occurance)
    {
        this.place_of_occurance = place_of_occurance;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [complaint_id = "+complaint_id+", complaint_type = "+complaint_type+", date_time = "+date_time+", user_id = "+user_id+", district = "+district+", verify = "+verify+", police_station = "+police_station+", details = "+details+", place_of_occurance = "+place_of_occurance+"]";
    }
}












