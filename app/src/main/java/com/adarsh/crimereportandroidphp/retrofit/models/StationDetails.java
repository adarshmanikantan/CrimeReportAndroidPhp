package com.adarsh.crimereportandroidphp.retrofit.models;

public class StationDetails
{
    private String station_id;

    private String name;

    private String district;

    private String phone;

    private String password;

    private String code;

    public void setStation_id(String station_id){
        this.station_id = station_id;
    }
    public String getStation_id(){
        return this.station_id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setDistrict(String district){
        this.district = district;
    }
    public String getDistrict(){
        return this.district;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
}


