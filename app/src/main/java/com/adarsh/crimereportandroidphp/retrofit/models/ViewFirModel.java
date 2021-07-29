package com.adarsh.crimereportandroidphp.retrofit.models;

public class ViewFirModel
{
    private Fir_details fir_details;

    private String status;

    public Fir_details getFir_details ()
    {
        return fir_details;
    }

    public void setFir_details (Fir_details fir_details)
    {
        this.fir_details = fir_details;
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
        return "ClassPojo [fir_details = "+fir_details+", status = "+status+"]";
    }
}

