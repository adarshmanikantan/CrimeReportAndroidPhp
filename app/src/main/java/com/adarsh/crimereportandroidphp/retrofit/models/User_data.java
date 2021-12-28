package com.adarsh.crimereportandroidphp.retrofit.models;

public class User_data
{
    private String villege;

    private String care_of;

    private String gender;

    private String year;

    private String password;

    private String adhar;

    private String user_id;

    private String phone;

    private String house_name;

    private String street;

    private String post_code;

    private String district;

    private String name;

    private String state;

    private String email;

    public String getVillege ()
    {
        return villege;
    }

    public void setVillege (String villege)
    {
        this.villege = villege;
    }

    public String getCare_of ()
    {
        return care_of;
    }

    public void setCare_of (String care_of)
    {
        this.care_of = care_of;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    public String getYear ()
    {
        return year;
    }

    public void setYear (String year)
    {
        this.year = year;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getAdhar ()
    {
        return adhar;
    }

    public void setAdhar (String adhar)
    {
        this.adhar = adhar;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getHouse_name ()
    {
        return house_name;
    }

    public void setHouse_name (String house_name)
    {
        this.house_name = house_name;
    }

    public String getStreet ()
    {
        return street;
    }

    public void setStreet (String street)
    {
        this.street = street;
    }

    public String getPost_code ()
    {
        return post_code;
    }

    public void setPost_code (String post_code)
    {
        this.post_code = post_code;
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

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [villege = "+villege+", care_of = "+care_of+", gender = "+gender+", year = "+year+", password = "+password+", adhar = "+adhar+", user_id = "+user_id+", phone = "+phone+", house_name = "+house_name+", street = "+street+", post_code = "+post_code+", district = "+district+", name = "+name+", state = "+state+", email = "+email+"]";
    }
}

