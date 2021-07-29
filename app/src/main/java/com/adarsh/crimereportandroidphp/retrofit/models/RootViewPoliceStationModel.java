package com.adarsh.crimereportandroidphp.retrofit.models;

import java.util.List;

public class RootViewPoliceStationModel {

        private String status;

        private List<StationDetails> station_details;

        public void setStatus(String status){
        this.status = status;
    }
        public String getStatus(){
        return this.status;
    }
        public void setStation_details(List<StationDetails> station_details){
        this.station_details = station_details;
    }
        public List<StationDetails> getStation_details(){
        return this.station_details;
    }
}
