package com.example.hotelmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelmanagementsystem.Api.APIInterface;
import com.example.hotelmanagementsystem.Api.RetrofitApi;
import com.example.hotelmanagementsystem.Model.LoginModel;
import com.example.hotelmanagementsystem.Model.ProfileUpdateModel;
import com.example.hotelmanagementsystem.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity {

    ImageView iv_back_arrow,iv_save;

    EditText et_name,et_phone_no,et_email_id;
    String user_id,name,phone_no,email_id;

    APIInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        iv_back_arrow = findViewById(R.id.iv_back_arrow);
        iv_save = findViewById(R.id.iv_save);

        et_name = findViewById(R.id.et_name);
        et_phone_no = findViewById(R.id.et_phone_no);
        et_email_id = findViewById(R.id.et_email_id);

        user_id = getIntent().getStringExtra("user_id");
        name = getIntent().getStringExtra("name");
        et_name.setText(name);
        phone_no = getIntent().getStringExtra("phone_no");
        et_phone_no.setText(phone_no);
        email_id = getIntent().getStringExtra("email_id");
        et_email_id.setText(email_id);

        iv_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        iv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = et_name.getText().toString();
                String phone_no = et_phone_no.getText().toString();
                String email_id = et_email_id.getText().toString();

                if(name.equals("")){

                    Toast.makeText(UpdateProfileActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();

                }else if(phone_no.equals("")){

                    Toast.makeText(UpdateProfileActivity.this, "Please Enter Your Phone Number", Toast.LENGTH_SHORT).show();

                }else if(email_id.equals("")){

                    Toast.makeText(UpdateProfileActivity.this, "Please Enter Your Email Id", Toast.LENGTH_SHORT).show();

                }else if(!email_id.contains(".com")|| !email_id.contains("@")) {

                    Toast.makeText(UpdateProfileActivity.this, "Please Enter Valid Email Id", Toast.LENGTH_SHORT).show();

                } else {

                    profile_update_data(user_id,name,phone_no,email_id);
                }

            }
        });

    }

    private void profile_update_data(String user_id, String name,String phone,String email) {

        api = RetrofitApi.getClient(this).create(APIInterface.class);
        Call<ProfileUpdateModel> profileUpdateModel = api.GetProfileUpdateModel(""+user_id,""+name,""+phone,""+email," ");
        profileUpdateModel.enqueue(new Callback<ProfileUpdateModel>() {
            @Override
            public void onResponse(Call<ProfileUpdateModel> call, Response<ProfileUpdateModel> response) {

                if (response.body().getHotelProfileUpdateModel().get(0).getSuccess().equals("1")) {

                    Toast.makeText(UpdateProfileActivity.this, "Profile Updated SuccessFully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(UpdateProfileActivity.this, NavigationActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(UpdateProfileActivity.this, "Please Valid Your Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileUpdateModel> call, Throwable t) {

                Toast.makeText(UpdateProfileActivity.this, "Not Valid Email Id And Password", Toast.LENGTH_SHORT).show();
            }
        });

    }

}