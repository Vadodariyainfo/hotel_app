package com.example.hotelmanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegistrationModel {

    @SerializedName("SINGLE_HOTEL_APP")
    @Expose
    private List<HotelRegistrationModel> hotelRegistrationModel = null;

    public List<HotelRegistrationModel> getHotelRegistrationModel() {

        return hotelRegistrationModel;
    }

    public void setHotelRegistrationModel(List<HotelRegistrationModel> hotelRegistrationModel) {
        this.hotelRegistrationModel = hotelRegistrationModel;
    }
}
