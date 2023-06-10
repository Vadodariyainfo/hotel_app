package com.example.hotelmanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactUsModel {

    @SerializedName("SINGLE_HOTEL_APP")
    @Expose
    private List<HotelContactUsModel> hotelContactUsModel = null;

    public List<HotelContactUsModel> getHotelContactUsModel() {
        return hotelContactUsModel;
    }

    public void setHotelContactUsModel(List<HotelContactUsModel> hotelContactUsModel) {
        this.hotelContactUsModel = hotelContactUsModel;
    }

}
