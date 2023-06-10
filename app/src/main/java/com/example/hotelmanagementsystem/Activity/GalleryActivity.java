package com.example.hotelmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hotelmanagementsystem.Adapter.SliderAdapter;
import com.example.hotelmanagementsystem.Adapter.SliderGalleryAdapter;
import com.example.hotelmanagementsystem.Api.APIInterface;
import com.example.hotelmanagementsystem.Api.RetrofitApi;
import com.example.hotelmanagementsystem.Model.GalleryModel;
import com.example.hotelmanagementsystem.Model.HotelGalleryModel;
import com.example.hotelmanagementsystem.Model.HotelSingleRoomModel;
import com.example.hotelmanagementsystem.Model.LoginModel;
import com.example.hotelmanagementsystem.R;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryActivity extends AppCompatActivity {

    ImageView iv_back_arrow;
    ViewPager2 vp_view_pager_2;
    MKLoader twin_fishes_spinner;

    String cat_id;

    APIInterface api;

    List<HotelGalleryModel> sliderGalleryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        iv_back_arrow = findViewById(R.id.iv_back_arrow);
        vp_view_pager_2 = findViewById(R.id.vp_view_pager_2);
        twin_fishes_spinner = findViewById(R.id.twin_fishes_spinner);

        iv_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        cat_id = getIntent().getStringExtra("cat_id");

        gallery_data(cat_id);

    }

    public void gallery_data(String cat_id){

        api = RetrofitApi.getClient(this).create(APIInterface.class);
        Call<GalleryModel> galleryModel = api.GetGalleryModel(""+cat_id);
        galleryModel.enqueue(new Callback<GalleryModel>() {
            @Override
            public void onResponse(Call<GalleryModel> call, Response<GalleryModel> response) {

                if (response.body().getHotelGalleryModel().size()!=0) {


                    sliderGalleryList = response.body().getHotelGalleryModel();

                    SliderGalleryAdapter sliderGalleryAdapter = new SliderGalleryAdapter(GalleryActivity.this,sliderGalleryList);
                    vp_view_pager_2.setAdapter(sliderGalleryAdapter);

                    vp_view_pager_2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            twin_fishes_spinner.setVisibility(View.GONE);

                            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
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
                }
            }

            @Override
            public void onFailure(Call<GalleryModel> call, Throwable t) {

                Toast.makeText(GalleryActivity.this, "Not Valid Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}