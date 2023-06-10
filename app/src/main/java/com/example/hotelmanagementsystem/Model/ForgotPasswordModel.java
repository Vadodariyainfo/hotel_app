package com.example.hotelmanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForgotPasswordModel {

    @SerializedName("SINGLE_HOTEL_APP")
    @Expose
    private List<HotelForgotPasswordModel> hotelForgotPasswordModel = null;

    public List<HotelForgotPasswordModel> getHotelForgotPasswordModel() {
        return hotelForgotPasswordModel;
    }

    public void setHotelForgotPasswordModel(List<HotelForgotPasswordModel> hotelForgotPasswordModel) {
        this.hotelForgotPasswordModel = hotelForgotPasswordModel;
    }
}
