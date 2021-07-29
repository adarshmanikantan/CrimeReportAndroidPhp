package com.adarsh.crimereportandroidphp.retrofit.models;

public class AddPoliceModel {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassPojo [status = " + status + "]";
    }
}
