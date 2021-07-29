package com.adarsh.crimereportandroidphp.retrofit.models;

public class ViewPoliceStationModel
{
    private Station_details[] station_details;

    private String status;

    public Station_details[] getStation_details ()
    {
        return station_details;
    }

    public void setStation_details (Station_details[] station_details)
    {
        this.station_details = station_details;
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
        return "ClassPojo [station_details = "+station_details+", status = "+status+"]";
    }
}

