package com.example.hotelmanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllRoomModel {

    @SerializedName("SINGLE_HOTEL_APP")
    @Expose
    private List<HotelAllRoomModel> allRoomBookingData = null;

    public List<HotelAllRoomModel> getAllRoomBookingData() {

        return allRoomBookingData;
    }

    public void setAllRoomBookingData(List<HotelAllRoomModel> allRoomBookingData) {
        this.allRoomBookingData = allRoomBookingData;
    }
}
