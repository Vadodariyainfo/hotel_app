package com.example.hotelmanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginModel {

    @SerializedName("SINGLE_HOTEL_APP")
    @Expose
    private List<HotelLoginModel> hotelLoginModel = null;

    public List<HotelLoginModel> getHotelLoginModel() {

        return hotelLoginModel;
    }

    public void setHotelLoginModel(List<HotelLoginModel> hotelLoginModel) {
        this.hotelLoginModel = hotelLoginModel;
    }
}
