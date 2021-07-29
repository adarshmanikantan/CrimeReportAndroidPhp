package com.adarsh.crimereportandroidphp.retrofit.models;


public class Station_details
{
    private String phone;

    private String station_id;

    private String district;

    private String name;

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getStation_id ()
    {
        return station_id;
    }

    public void setStation_id (String station_id)
    {
        this.station_id = station_id;
    }

    public String getDistrict ()
    {
        return district;
    }

    public void setDistrict (String district)
    {
        this.district = district;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [phone = "+phone+", station_id = "+station_id+", district = "+district+", name = "+name+"]";
    }
}

