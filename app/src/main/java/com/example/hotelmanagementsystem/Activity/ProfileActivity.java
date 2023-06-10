package com.example.hotelmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelmanagementsystem.Api.APIInterface;
import com.example.hotelmanagementsystem.Api.RetrofitApi;
import com.example.hotelmanagementsystem.Model.ProfileModel;
import com.example.hotelmanagementsystem.Model.ProfileUpdateModel;
import com.example.hotelmanagementsystem.R;
import com.tuyenmonkey.mkloader.MKLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    ImageView iv_back_arrow,iv_edit;
    TextView tv_name,tv_phone_no,tv_email_id;

    MKLoader twin_fishes_spinner;

    String user_id,name,phone_no,email_id;

    SharedPreferences sharedPrefrences;
    SharedPreferences.Editor editor;

    APIInterface api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        twin_fishes_spinner = findViewById(R.id.twin_fishes_spinner);

        iv_back_arrow = findViewById(R.id.iv_back_arrow);
        iv_edit = findViewById(R.id.iv_edit);

        tv_name = findViewById(R.id.tv_name);
        tv_phone_no = findViewById(R.id.tv_phone_no);
        tv_email_id = findViewById(R.id.tv_email_id);

        sharedPrefrences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPrefrences.edit();

        user_id = sharedPrefrences.getString("user_id",user_id);

        iv_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileActivity.this, UpdateProfileActivity.class);
                intent.putExtra("user_id",user_id);
                intent.putExtra("name",name);
                intent.putExtra("phone_no",phone_no);
                intent.putExtra("email_id",email_id);
                startActivity(intent);

            }
        });
        profile_data(user_id);
    }

    private void profile_data(String id) {

        api = RetrofitApi.getClient(this).create(APIInterface.class);
        Call<ProfileModel> profileModel = api.GetProfileModel(""+id);
        profileModel.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {

                if (response.body().getHotelProfileModel().get(0).getSuccess().equals("1")) {

                    twin_fishes_spinner.setVisibility(View.GONE);

                  name = response.body().getHotelProfileModel().get(0).getName();
                  tv_name.setText(name);
                  phone_no = response.body().getHotelProfileModel().get(0).getPhone();
                  tv_phone_no.setText(phone_no);
                  email_id = response.body().getHotelProfileModel().get(0).getEmail();
                  tv_email_id.setText(email_id);
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {

                Toast.makeText(ProfileActivity.this, "Not Valid Email Id And Password", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
//ashishmarthak22@gmail.com