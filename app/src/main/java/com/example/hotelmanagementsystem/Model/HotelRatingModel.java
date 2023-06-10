package com.example.hotelmanagementsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelRatingModel {

    @SerializedName("total_rate")
    @Expose
    private Integer totalRate;
    @SerializedName("rate_avg")
    @Expose
    private Integer rateAvg;
    @SerializedName("MSG")
    @Expose
    private String msg;

    public Integer getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(Integer totalRate) {
        this.totalRate = totalRate;
    }

    public Integer getRateAvg() {
        return rateAvg;
    }

    public void setRateAvg(Integer rateAvg) {
        this.rateAvg = rateAvg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
