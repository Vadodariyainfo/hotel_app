package com.example.hotelmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelmanagementsystem.Adapter.SliderAdapter;
import com.example.hotelmanagementsystem.Api.APIInterface;
import com.example.hotelmanagementsystem.Api.RetrofitApi;
import com.example.hotelmanagementsystem.Model.HotelSingleRoomModel;
import com.example.hotelmanagementsystem.Model.SingleRoomGalleryImage;
import com.example.hotelmanagementsystem.Model.SingleRoomModel;
import com.example.hotelmanagementsystem.R;
import com.google.android.material.button.MaterialButton;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SingleRoomDetailsActivity extends AppCompatActivity {

    ImageView iv_back_arrow,iv_edit_rating;
    MaterialButton btn_book_now;
    TextView tv_room_no,tv_price,tv_room_details;
    WebView wv_room_description,wv_room_amenities,wv_room_rules;
    MKLoader twin_fishes_spinner;
    String room_id,room_no;
    ViewPager2 vp_vie_pager_2;
    int currentPage = 0;
    APIInterface api;
    List<SingleRoomGalleryImage> sliderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomdetails);

        iv_back_arrow = findViewById(R.id.iv_back_arrow);
        iv_edit_rating = findViewById(R.id.iv_edit_rating);

        tv_room_no = findViewById(R.id.tv_room_no);
        tv_price = findViewById(R.id.tv_price);
        tv_room_details = findViewById(R.id.tv_room_details);

        wv_room_description = findViewById(R.id.wv_room_description);
        wv_room_amenities = findViewById(R.id.wv_room_amenities);
        wv_room_rules = findViewById(R.id.wv_room_rules);

        twin_fishes_spinner = findViewById(R.id.twin_fishes_spinner);

        btn_book_now = findViewById(R.id.btn_book_now);

        vp_vie_pager_2 = findViewById(R.id.vp_view_pager_2);

        String string1 = "";
        wv_room_description.loadData(string1, "text/html; charset=UTF-8", null);
        wv_room_amenities.loadData(string1, "text/html; charset=UTF-8", null);
        wv_room_rules.loadData(string1, "text/html; charset=UTF-8", null);

        iv_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        iv_edit_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SingleRoomDetailsActivity.this,RatingActivity.class);
                intent.putExtra("room_id",room_id);
                startActivity(intent);

            }
        });

        room_id = getIntent().getStringExtra("id");

        btn_book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SingleRoomDetailsActivity.this, RoomBookingActivity.class);
                intent.putExtra("id",room_id);
                intent.putExtra("room_no",room_no);
                intent.putExtra("room_price",tv_price.getText().toString());
                startActivity(intent);
            }
        });

        single_room_details(room_id);
    }

    private void single_room_details(String room_id) {

        api = RetrofitApi.getClient(this).create(APIInterface.class);
        Call<SingleRoomModel> singleRoomModel = api.GetSingleRoomDetails(room_id);
        singleRoomModel.enqueue(new Callback<SingleRoomModel>() {
            @Override
            public void onResponse(Call<SingleRoomModel> call, Response<SingleRoomModel> response) {

                try{
                    List<HotelSingleRoomModel> hotelSingleRoomModel=response.body().getHotelSingleRoomModel();

                    if (hotelSingleRoomModel.size()!=0) {

                        twin_fishes_spinner.setVisibility(View.GONE);

                        tv_room_details.setText(""+hotelSingleRoomModel.get(0).getRoomName());
                        room_no = hotelSingleRoomModel.get(0).getRoomName();
                        tv_room_no.setText("Room No. = "+hotelSingleRoomModel.get(0).getRoomName());
                        tv_price.setText(""+hotelSingleRoomModel.get(0).getRoomPrice());
                        wv_room_description.loadData(""+hotelSingleRoomModel.get(0).getRoomDescription(),"text/html", "UTF-8");
                        wv_room_amenities.loadData(""+hotelSingleRoomModel.get(0).getRoomAmenities(),"text/html", "UTF-8");
                        wv_room_rules.loadData(""+hotelSingleRoomModel.get(0).getRoomRules(),"text/html", "UTF-8");

                         sliderList = hotelSingleRoomModel.get(0).getSingleRoomGalleyImage();

                        SliderAdapter sliderAdapter = new SliderAdapter(SingleRoomDetailsActivity.this,sliderList);
                        vp_vie_pager_2.setAdapter(sliderAdapter);

                        vp_vie_pager_2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                                if (position==sliderList.size()-2)
                                    sliderList.addAll(sliderList);
                            }

                            @Override
                            public void onPageSelected(int position) {
                                super.onPageSelected(position);
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {
                                super.onPageScrollStateChanged(state);
                            }
                        });
                        Handler handler = new Handler();
                        Runnable update = new Runnable(){
                            @Override
                            public void run() {
                                if (currentPage<sliderList.size()-1)
                                    vp_vie_pager_2.setCurrentItem(currentPage++, true);
                                else{
                                    sliderList.addAll(sliderList);
                                    vp_vie_pager_2.setCurrentItem(currentPage++, true);
                                }
                            }
                        };

                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler.post(update);
                            }
                        } , 3000, 3000);


                    }
                }catch (Exception e){

                    twin_fishes_spinner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<SingleRoomModel> call, Throwable t) {
                Log.d("message",t.getLocalizedMessage());
                Toast.makeText(SingleRoomDetailsActivity.this, "Please Enter Valid Data", Toast.LENGTH_SHORT).show();
            }
        });

    }

}