package com.adarsh.crimereportandroidphp.retrofit.models;

public class Sectiont_details
{
    private String section_id;

    private String description;

    private String sectiono;

    private String title;

    public String getSection_id ()
    {
        return section_id;
    }

    public void setSection_id (String section_id)
    {
        this.section_id = section_id;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getSectiono ()
    {
        return sectiono;
    }

    public void setSectiono (String sectiono)
    {
        this.sectiono = sectiono;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [section_id = "+section_id+", description = "+description+", sectiono = "+sectiono+", title = "+title+"]";
    }
}


