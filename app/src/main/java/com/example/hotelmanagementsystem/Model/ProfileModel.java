package com.example.hotelmanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileModel {

    @SerializedName("SINGLE_HOTEL_APP")
    @Expose
    private List<HotelProfileModel> hotelProfileModel = null;

    public List<HotelProfileModel> getHotelProfileModel() {
        return hotelProfileModel;
    }

    public void setHotelProfileModel(List<HotelProfileModel> hotelProfileModel) {
        this.hotelProfileModel = hotelProfileModel;
    }
}
