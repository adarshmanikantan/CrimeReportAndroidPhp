package com.adarsh.crimereportandroidphp.retrofit.models;

public class Pcc_details
{
    private String present_address;

    private String purpose;

    private String pcc_id;

    private String police_station;

    private String residence_address;

    private String permanent_address;

    private String criminal_case;

    private String reference_person;

    private String passport;

    private String user_id;

    private String father_name;

    private String phone;

    private String dob;

    private String district;

    private String name;

    private String verify_status;

    private String email;

    public String getPresent_address ()
    {
        return present_address;
    }

    public void setPresent_address (String present_address)
    {
        this.present_address = present_address;
    }

    public String getPurpose ()
    {
        return purpose;
    }

    public void setPurpose (String purpose)
    {
        this.purpose = purpose;
    }

    public String getPcc_id ()
    {
        return pcc_id;
    }

    public void setPcc_id (String pcc_id)
    {
        this.pcc_id = pcc_id;
    }

    public String getPolice_station ()
    {
        return police_station;
    }

    public void setPolice_station (String police_station)
    {
        this.police_station = police_station;
    }

    public String getResidence_address ()
    {
        return residence_address;
    }

    public void setResidence_address (String residence_address)
    {
        this.residence_address = residence_address;
    }

    public String getPermanent_address ()
    {
        return permanent_address;
    }

    public void setPermanent_address (String permanent_address)
    {
        this.permanent_address = permanent_address;
    }

    public String getCriminal_case ()
    {
        return criminal_case;
    }

    public void setCriminal_case (String criminal_case)
    {
        this.criminal_case = criminal_case;
    }

    public String getReference_person ()
    {
        return reference_person;
    }

    public void setReference_person (String reference_person)
    {
        this.reference_person = reference_person;
    }

    public String getPassport ()
    {
        return passport;
    }

    public void setPassport (String passport)
    {
        this.passport = passport;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getFather_name ()
    {
        return father_name;
    }

    public void setFather_name (String father_name)
    {
        this.father_name = father_name;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getDob ()
    {
        return dob;
    }

    public void setDob (String dob)
    {
        this.dob = dob;
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

    public String getVerify_status ()
    {
        return verify_status;
    }

    public void setVerify_status (String verify_status)
    {
        this.verify_status = verify_status;
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
        return "ClassPojo [present_address = "+present_address+", purpose = "+purpose+", pcc_id = "+pcc_id+", police_station = "+police_station+", residence_address = "+residence_address+", permanent_address = "+permanent_address+", criminal_case = "+criminal_case+", reference_person = "+reference_person+", passport = "+passport+", user_id = "+user_id+", father_name = "+father_name+", phone = "+phone+", dob = "+dob+", district = "+district+", name = "+name+", verify_status = "+verify_status+", email = "+email+"]";
    }
}

