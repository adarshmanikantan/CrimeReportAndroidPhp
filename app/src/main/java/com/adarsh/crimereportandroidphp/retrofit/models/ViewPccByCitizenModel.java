package com.adarsh.crimereportandroidphp.retrofit.models;

public class ViewPccByCitizenModel {

    private Pcc_details[] pcc_details;

    private String status;

    public Pcc_details[] getPcc_details() {
        return pcc_details;
    }

    public void setPcc_details(Pcc_details[] pcc_details) {
        this.pcc_details = pcc_details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassPojo [pcc_details = " + pcc_details + ", status = " + status + "]";
    }
}



