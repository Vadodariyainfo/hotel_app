package com.example.hotelmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.hotelmanagementsystem.Api.APIInterface;
import com.example.hotelmanagementsystem.Api.RetrofitApi;
import com.example.hotelmanagementsystem.Model.RatingModel;
import com.example.hotelmanagementsystem.Model.RegistrationModel;
import com.example.hotelmanagementsystem.R;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingActivity extends AppCompatActivity {

    ImageView back_arrow;
    RatingBar rb_rating_bar;
    EditText et_your_message;
    MaterialButton btn_submit;

    SharedPreferences sharedPrefrences;
    SharedPreferences.Editor editor;

    APIInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        back_arrow = findViewById(R.id.back_arrow);
        rb_rating_bar = findViewById(R.id.rb_rating_bar);
        et_your_message = findViewById(R.id.et_your_message);
        btn_submit = findViewById(R.id.btn_submit);

        sharedPrefrences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPrefrences.edit();


        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String rating_bar = String.valueOf(rb_rating_bar.getRating());
                String your_message = et_your_message.getText().toString();
                String room_id = getIntent().getStringExtra("room_id");
                String device_id = "123";
                String user_id = "37";


                if (rating_bar.equals("")) {

                    Toast.makeText(RatingActivity.this, "Please Select Rating", Toast.LENGTH_SHORT).show();

                } else if (your_message.equals("")) {

                    Toast.makeText(RatingActivity.this, "Please Enter Text Message", Toast.LENGTH_SHORT).show();

                }else{

                    rating_data(room_id,device_id,user_id,rating_bar,your_message);
                }

            }
        });
    }

    private void rating_data(String room_id,String device_id,String user_id,String rate,String message) {

        api = RetrofitApi.getClient(this).create(APIInterface.class);
        Call<RatingModel> ratingModel = api.GetRatingModel(""+room_id,""+device_id,""+user_id,""+rate,""+message);
        ratingModel.enqueue(new Callback<RatingModel>() {
            @Override
            public void onResponse(Call<RatingModel> call, Response<RatingModel> response) {

                String success=response.body().getHotelRatingModel().get(0).getMsg();
                Toast.makeText(RatingActivity.this, success, Toast.LENGTH_SHORT).show();

                finish();

            }
            @Override
            public void onFailure(Call<RatingModel> call, Throwable t) {

                Toast.makeText(RatingActivity.this, "Please Enter Valid Data", Toast.LENGTH_SHORT).show();
            }
        });

    }


}