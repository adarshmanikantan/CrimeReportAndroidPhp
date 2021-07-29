package com.adarsh.crimereportandroidphp.retrofit.models;

public class GenerateFirModel
{
    private String fir_id;

    private String status;

    public String getFir_id ()
    {
        return fir_id;
    }

    public void setFir_id (String fir_id)
    {
        this.fir_id = fir_id;
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
        return "ClassPojo [fir_id = "+fir_id+", status = "+status+"]";
    }
}

