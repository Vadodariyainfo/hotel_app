package com.example.hotelmanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingleRoomModel {

    @SerializedName("SINGLE_HOTEL_APP")
    @Expose
    private List<HotelSingleRoomModel> hotelSingleRoomModel = null;

    public List<HotelSingleRoomModel> getHotelSingleRoomModel() {
        return hotelSingleRoomModel;
    }

    public void setHotelSingleRoomModel(List<HotelSingleRoomModel> hotelSingleRoomModel) {
        this.hotelSingleRoomModel = hotelSingleRoomModel;
    }


}
