package com.example.hotelmanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesGalleryModel {

    @SerializedName("SINGLE_HOTEL_APP")
    @Expose
    private List<HotelCategoriesGalleryModel> hotelCategoriesGalleryModel = null;

    public List<HotelCategoriesGalleryModel> getHotelCategoriesGalleryModel() {
        return hotelCategoriesGalleryModel;
    }

    public void setHotelCategoriesGalleryModel(List<HotelCategoriesGalleryModel> hotelCategoriesGalleryModel) {
        this.hotelCategoriesGalleryModel = hotelCategoriesGalleryModel;
    }
}
