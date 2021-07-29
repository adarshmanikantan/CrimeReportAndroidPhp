package com.adarsh.crimereportandroidphp.retrofit.models;

public class Police_Login_Model {
    private Pstation_details Station_details;

    private String status;

    public Pstation_details getStation_details ()
    {
        return Station_details;
    }

    public void setStation_details (Pstation_details Station_details)
    {
        this.Station_details = Station_details;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Station_details = "+Station_details+", status = "+status+"]";
    }
}
