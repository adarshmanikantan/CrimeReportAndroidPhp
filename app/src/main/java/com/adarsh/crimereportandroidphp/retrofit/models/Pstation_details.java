package com.adarsh.crimereportandroidphp.retrofit.models;

public class Pstation_details {
    private String password;

    private String code;

    private String phone;

    private String district;

    private String name;

    private String police_id;

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
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

    public String getPolice_id ()
    {
        return police_id;
    }

    public void setPolice_id (String police_id)
    {
        this.police_id = police_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [password = "+password+", code = "+code+", phone = "+phone+", district = "+district+", name = "+name+", police_id = "+police_id+"]";
    }
}

