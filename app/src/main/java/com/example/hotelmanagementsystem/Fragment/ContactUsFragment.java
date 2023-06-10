package com.example.hotelmanagementsystem.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hotelmanagementsystem.Activity.NavigationActivity;
import com.example.hotelmanagementsystem.Api.APIInterface;
import com.example.hotelmanagementsystem.Api.RetrofitApi;
import com.example.hotelmanagementsystem.Model.ContactUsModel;
import com.example.hotelmanagementsystem.Model.LoginModel;
import com.example.hotelmanagementsystem.R;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsFragment extends Fragment {

    EditText et_name,et_phone_no,et_email_id,et_subject,et_description;
    AppCompatButton btn_submit;

    APIInterface api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_contact_us, container, false);

        et_name = view.findViewById(R.id.et_name);
        et_phone_no = view.findViewById(R.id.et_phone_no);
        et_email_id = view.findViewById(R.id.et_email_id);
        et_subject = view.findViewById(R.id.et_subject);
        et_description = view.findViewById(R.id.et_description);
        btn_submit = view.findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = et_name.getText().toString();
                String phone_no = et_phone_no.getText().toString();
                String email_id = et_email_id.getText().toString();
                String subject = et_subject.getText().toString();
                String description = et_description.getText().toString();

                if(name.equals("")){

                    Toast.makeText(getContext(), "Please Enter Your Name", Toast.LENGTH_SHORT).show();

                }else if(phone_no.equals("")){

                    Toast.makeText(getContext(), "Please Enter Your Phone Number", Toast.LENGTH_SHORT).show();

                }else if(email_id.equals("")){

                    Toast.makeText(getContext(), "Please Enter Your Email Id", Toast.LENGTH_SHORT).show();

                }else if(email_id.equals("@")||email_id.equals(".com")){

                    Toast.makeText(getContext(), "Please Enter Valid Email Id", Toast.LENGTH_SHORT).show();

                }else if(subject.equals("")){

                    Toast.makeText(getContext(), "Please Enter Subject", Toast.LENGTH_SHORT).show();

                }else if(description.equals("")){

                    Toast.makeText(getContext(), "Please Enter Description", Toast.LENGTH_SHORT).show();

                }else{

                    contact_us_data(name,phone_no,email_id,subject,description);

                }
            }
        });

        return view;
    }


    private void contact_us_data(String name,String phone,String email, String subject,String message) {

        api = RetrofitApi.getClient(getContext()).create(APIInterface.class);
        Call<ContactUsModel> contactUsModel = api.GetContactUs(name,phone,email,subject, message);
        contactUsModel.enqueue(new Callback<ContactUsModel>() {
            @Override
            public void onResponse(Call<ContactUsModel> call, Response<ContactUsModel> response) {

                if (response.body().getHotelContactUsModel().get(0).getSuccess().equals("1")) {

                    Toast.makeText(getContext(), "Submit SuccessFully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getContext(), NavigationActivity.class);
                    startActivity(intent);

                }

            }

            @Override
            public void onFailure(Call<ContactUsModel> call, Throwable t) {

                Toast.makeText(getContext(), "Not Valid Data", Toast.LENGTH_SHORT).show();
            }
        });

    }
}