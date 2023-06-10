 package com.example.hotelmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hotelmanagementsystem.Api.APIInterface;
import com.example.hotelmanagementsystem.Api.RetrofitApi;
import com.example.hotelmanagementsystem.Model.ForgotPasswordModel;
import com.example.hotelmanagementsystem.R;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class ForgotPasswordActivity extends AppCompatActivity {

     EditText et_email_id;
     AppCompatButton btn_forgot_password;

     APIInterface api;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_forgotpassword);

         et_email_id = findViewById(R.id.et_email_id);
         btn_forgot_password = findViewById(R.id.btn_forgot_password);


         btn_forgot_password.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 String email_id = et_email_id.getText().toString();

                 if (email_id.equals("")) {

                     Toast.makeText(ForgotPasswordActivity.this, "Please Enter Your Email Id", Toast.LENGTH_SHORT).show();

                 } else if (!email_id.contains("@") || !email_id.contains(".com")) {

                     Toast.makeText(ForgotPasswordActivity.this, "Please Enter Valid Email Id", Toast.LENGTH_SHORT).show();

                 } else {

                    forgot_password_data(email_id);
                 }
             }
         });
     }

     private void forgot_password_data(String email) {

         api = RetrofitApi.getClient(this).create(APIInterface.class);
         Call<ForgotPasswordModel> forgotPasswordModel = api.GetForgotPassword(""+email);
         forgotPasswordModel.enqueue(new Callback<ForgotPasswordModel>() {
             @Override
             public void onResponse(Call<ForgotPasswordModel> call, Response<ForgotPasswordModel> response) {

                 if (response.body().getHotelForgotPasswordModel().get(0).getSuccess().equals("1")) {

                     Toast.makeText(ForgotPasswordActivity.this, "Password Send To Email Id, Please Check Your Email Id", Toast.LENGTH_SHORT).show();

                     Intent intent = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                     startActivity(intent);
                     finish();

                 } else {

                     Toast.makeText(ForgotPasswordActivity.this, "Not Valid Email Id", Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onFailure(Call<ForgotPasswordModel> call, Throwable t) {

                 Toast.makeText(ForgotPasswordActivity.this, "Please Enter Valid Data", Toast.LENGTH_SHORT).show();
             }
         });

     }
 }