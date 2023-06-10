package com.example.hotelmanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileUpdateModel {

    @SerializedName("SINGLE_HOTEL_APP")
    @Expose
    private List<HotelProfileUpdateModel> hotelProfileUpdateModel = null;

    public List<HotelProfileUpdateModel> getHotelProfileUpdateModel() {
        return hotelProfileUpdateModel;
    }

    public void setHotelProfileUpdateModel(List<HotelProfileUpdateModel> hotelProfileUpdateModel) {
        this.hotelProfileUpdateModel = hotelProfileUpdateModel;
    }
}

