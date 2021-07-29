package com.adarsh.crimereportandroidphp.retrofit.models;

public class ViewIpcModel
{
    private Sectiont_details[] sectiont_details;

    private String status;

    public Sectiont_details[] getSectiont_details ()
    {
        return sectiont_details;
    }

    public void setSectiont_details (Sectiont_details[] sectiont_details)
    {
        this.sectiont_details = sectiont_details;
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
        return "ClassPojo [sectiont_details = "+sectiont_details+", status = "+status+"]";
    }
}


