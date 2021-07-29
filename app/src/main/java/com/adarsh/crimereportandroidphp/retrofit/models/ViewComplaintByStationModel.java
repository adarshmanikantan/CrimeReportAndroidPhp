package com.adarsh.crimereportandroidphp.retrofit.models;

public class ViewComplaintByStationModel {
    private String status;

    private Complaint_details[] complaint_details;

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public Complaint_details[] getComplaint_details ()
    {
        return complaint_details;
    }

    public void setComplaint_details (Complaint_details[] complaint_details)
    {
        this.complaint_details = complaint_details;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [status = "+status+", complaint_details = "+complaint_details+"]";
    }
}
