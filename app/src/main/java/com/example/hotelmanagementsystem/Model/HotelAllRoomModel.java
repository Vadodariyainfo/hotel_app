package com.example.hotelmanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelAllRoomModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("room_name")
    @Expose
    private String roomName;
    @SerializedName("room_image")
    @Expose
    private String roomImage;
    @SerializedName("room_image_thumb")
    @Expose
    private String roomImageThumb;
    @SerializedName("room_description")
    @Expose
    private String roomDescription;
    @SerializedName("room_rules")
    @Expose
    private String roomRules;
    @SerializedName("room_amenities")
    @Expose
    private String roomAmenities;
    @SerializedName("room_price")
    @Expose
    private String roomPrice;
    @SerializedName("total_rate")
    @Expose
    private String totalRate;
    @SerializedName("rate_avg")
    @Expose
    private String rateAvg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public String getRoomImageThumb() {
        return roomImageThumb;
    }

    public void setRoomImageThumb(String roomImageThumb) {
        this.roomImageThumb = roomImageThumb;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getRoomRules() {
        return roomRules;
    }

    public void setRoomRules(String roomRules) {
        this.roomRules = roomRules;
    }

    public String getRoomAmenities() {
        return roomAmenities;
    }

    public void setRoomAmenities(String roomAmenities) {
        this.roomAmenities = roomAmenities;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(String totalRate) {
        this.totalRate = totalRate;
    }

    public String getRateAvg() {
        return rateAvg;
    }

    public void setRateAvg(String rateAvg) {
        this.rateAvg = rateAvg;
    }

}
