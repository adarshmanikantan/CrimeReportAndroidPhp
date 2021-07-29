package com.adarsh.crimereportandroidphp.retrofit.models;


public class RootLoginModel {

        private String status;

        private User_data User_data;

        public void setStatus(String status){
                this.status = status;
        }
        public String getStatus(){
                return this.status;
        }
        public void setUser_data(User_data User_data){
                this.User_data = User_data;
        }
        public User_data getUser_data(){
                return this.User_data;
        }

}
