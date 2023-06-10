
package com.example.hotelmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelmanagementsystem.Api.APIInterface;
import com.example.hotelmanagementsystem.Api.RetrofitApi;
import com.example.hotelmanagementsystem.Model.HotelLoginModel;
import com.example.hotelmanagementsystem.Model.LoginModel;
import com.example.hotelmanagementsystem.Model.RegistrationModel;
import com.example.hotelmanagementsystem.R;
import com.google.android.material.button.MaterialButton;
import com.tuyenmonkey.mkloader.MKLoader;

import java.lang.reflect.Method;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText et_email_id,et_password;
    AppCompatButton btn_login,btn_cancel;
    TextView tv_new_registration,tv_forgot_password;
    CheckBox cb_rememberme;

    MKLoader classic_spinner;

    public static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    public static final String SHARED_PREF_NAME = "mypref";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "pass";

    boolean password_visible;
    APIInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        classic_spinner = findViewById(R.id.classic_spinner);

        et_email_id = findViewById(R.id.et_email_id);
        et_password = findViewById(R.id.et_password);
        cb_rememberme = findViewById(R.id.cb_rememberme);
        btn_login = findViewById(R.id.btn_login);
        btn_cancel = findViewById(R.id.btn_cancel);
        tv_new_registration = findViewById(R.id.tv_new_registration);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);


        et_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int Right = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {

                    if (event.getRawX() >= et_password.getRight() - et_password.getCompoundDrawables()[Right].getBounds().width()) {

                        int selection = et_password.getSelectionEnd();

                        if (password_visible) {
                            //set drawable image here
                            et_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eye_visibility_off, 0);
//                            for hide password
                            et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            password_visible = false;
                        } else {
                            //set drawable image here
                            et_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eye_visibility_on, 0);
//                            for show password
                            et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            password_visible = true;
                        }
                        et_password.setSelection(selection);
                        return true;
                    }

                }
                return false;
            }
        });

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        Boolean email = sharedPreferences.contains(KEY_EMAIL);

        if(email){

            Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
            startActivity(intent);
            finish();

        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email_id = et_email_id.getText().toString();
                String password = et_password.getText().toString();

                if (email_id.equals("")) {

                    Toast.makeText(LoginActivity.this, "Please Enter Your Email Id", Toast.LENGTH_SHORT).show();

                }
                else if(!email_id.contains(".com")|| !email_id.contains("@")) {

                    Toast.makeText(LoginActivity.this, "Please Enter Valid Email Id", Toast.LENGTH_SHORT).show();

                } else if (password.equals("")) {

                    Toast.makeText(LoginActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();

                } else {

                    classic_spinner.setVisibility(View.VISIBLE);

                    login_data(email_id, password);
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),LoginActivity.class);
                System.exit(0);
            }
        });

        tv_new_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });

        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login_data(String email, String password) {

        api = RetrofitApi.getClient(this).create(APIInterface.class);
        Call<LoginModel> loginModel = api.GetLogin(""+email,""+password);
        loginModel.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if (response.body().getHotelLoginModel().get(0).getSuccess().equals("1")) {

                    classic_spinner.setVisibility(View.GONE);

                    editor = sharedPreferences.edit();
                    /*editor.putString(KEY_EMAIL, email);
                    editor.putString(KEY_PASSWORD, password);
                    editor.apply();*/

                    HotelLoginModel hotelLoginModel=response.body().getHotelLoginModel().get(0);

                    Log.d("rahulData", String.valueOf(hotelLoginModel));

                    editor.putString("user_id",hotelLoginModel.getUserId());

                    editor.putString("name",hotelLoginModel.getName());

                    editor.putString("phone",hotelLoginModel.getPhone());

                    editor.putString("email",hotelLoginModel.getEmail());

                    editor.commit();

                    Toast.makeText(LoginActivity.this, "Login SuccessFully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    classic_spinner.setVisibility(View.GONE);

                    Toast.makeText(LoginActivity.this, "Please Valid Email Id And Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Not Valid Email Id And Password", Toast.LENGTH_SHORT).show();
            }
        });

    }
}