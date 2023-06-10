package com.example.hotelmanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatingModel {

    @SerializedName("SINGLE_HOTEL_APP")
    @Expose
    private List<HotelRatingModel> hotelRatingModel = null;

    public List<HotelRatingModel> getHotelRatingModel() {
        return hotelRatingModel;
    }

    public void setHotelRatingModel(List<HotelRatingModel> hotelRatingModel) {
        this.hotelRatingModel = hotelRatingModel;
    }

}
