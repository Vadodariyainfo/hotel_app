package com.example.hotelmanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GalleryModel {

    @SerializedName("SINGLE_HOTEL_APP")
    @Expose
    private List<HotelGalleryModel> hotelGalleryModel = null;

    public List<HotelGalleryModel> getHotelGalleryModel() {
        return hotelGalleryModel;
    }

    public void setHotelGalleryModel(List<HotelGalleryModel> hotelGalleryModel) {
        this.hotelGalleryModel = hotelGalleryModel;
    }


}
