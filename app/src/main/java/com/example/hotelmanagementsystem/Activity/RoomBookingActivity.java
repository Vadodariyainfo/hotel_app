package com.example.hotelmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hotelmanagementsystem.Api.APIInterface;
import com.example.hotelmanagementsystem.Api.RetrofitApi;
import com.example.hotelmanagementsystem.Model.RoomBookingModel;
import com.example.hotelmanagementsystem.R;
import com.google.android.material.button.MaterialButton;
import com.razorpay.Checkout;
import com.razorpay.ExternalWalletListener;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.tuyenmonkey.mkloader.MKLoader;

import org.json.JSONObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomBookingActivity extends AppCompatActivity implements PaymentResultListener, ExternalWalletListener {

    private AlertDialog.Builder alertDialogBuilder;
    EditText et_name,et_phone_no,et_email_id,et_room_id,et_room_no,et_adults,et_children,et_check_in_date,et_check_out_date;
    ImageView iv_back_arrow,iv_check_in_date,iv_check_out_date;
    MKLoader twin_fishes_spinner;
    AppCompatButton btn_submit;
    int date,month,year;

    String name,phone_no,email_id;

    SharedPreferences sharedPrefrences;
    SharedPreferences.Editor editor;

    APIInterface api;
    private Double Sting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roombooking);

        et_name = findViewById(R.id.et_name);
        et_phone_no = findViewById(R.id.et_phone_no);
        et_email_id = findViewById(R.id.et_email_id);
        et_room_id = findViewById(R.id.et_room_id);
        et_room_no = findViewById(R.id.et_room_no);
        et_adults = findViewById(R.id.et_adults);
        et_children = findViewById(R.id.et_children);
        et_check_in_date = findViewById(R.id.et_check_in_date);
        et_check_out_date = findViewById(R.id.et_check_out_date);

        twin_fishes_spinner = findViewById(R.id.twin_fishes_spinner);

        iv_back_arrow = findViewById(R.id.iv_back_arrow);
        iv_check_in_date = findViewById(R.id.iv_check_in_date);
        iv_check_out_date = findViewById(R.id.iv_check_out_date);
        btn_submit = findViewById(R.id.btn_submit);

        sharedPrefrences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPrefrences.edit();

        name = sharedPrefrences.getString("name",name);
        phone_no = sharedPrefrences.getString("phone",phone_no);
        email_id = sharedPrefrences.getString("email",email_id);

        et_name.setText(name);
        et_phone_no.setText(phone_no);
        et_email_id.setText(email_id);

        String room_no,room_id,room_price;

        room_no = getIntent().getStringExtra("room_no");
        et_room_no.setText(room_no);
        room_id = getIntent().getStringExtra("id");
        et_room_id.setText(room_id);
        room_price = getIntent().getStringExtra("room_price");
//        Toast.makeText(RoomBookingActivity.this, room_price, Toast.LENGTH_SHORT).show();

        iv_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        iv_check_in_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();

                date = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(RoomBookingActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        et_check_in_date.setText(date+"-"+(month+1)+"-"+year);
                    }
                }, year, month, date);

                datePickerDialog.getDatePicker().setMinDate((System.currentTimeMillis()) - 1000);
                datePickerDialog.show();

            }
        });

        iv_check_out_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();

                date = calendar.get(Calendar.DATE);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(RoomBookingActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        et_check_out_date.setText(date+"-"+(month+1)+"-"+year);
                    }
                }, year, month, date);

                datePickerDialog.getDatePicker().setMinDate((System.currentTimeMillis()) - 1000);
                datePickerDialog.show();

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = et_name.getText().toString();
                String phone_no = et_phone_no.getText().toString();
                String email_id = et_email_id.getText().toString();

                String adults = et_adults.getText().toString();
                String children = et_children.getText().toString();
                String check_in_date = et_check_in_date.getText().toString();
                String check_out_date = et_check_out_date.getText().toString();

                if(adults.equals("")){

                    Toast.makeText(RoomBookingActivity.this, "Please Enter Adults", Toast.LENGTH_SHORT).show();

                }else if(children.equals("")){

                    Toast.makeText(RoomBookingActivity.this, "Please Enter Children", Toast.LENGTH_SHORT).show();

                }else if(check_in_date.equals("")){

                    Toast.makeText(RoomBookingActivity.this, "Please Enter Check In Date", Toast.LENGTH_SHORT).show();

                }else if(check_out_date.equals("")){

                    Toast.makeText(RoomBookingActivity.this, "Please Enter Check Out Date", Toast.LENGTH_SHORT).show();

                }else{

                    Checkout.preload(getApplicationContext());

                            startPayment();

//                    room_booking_data(name,phone_no,email_id,adults,children,check_in_date,check_out_date);
                }
            }
        });
    }

    private void room_booking_data(String name, String phone, String email, String adults, String children,String check_in_date,String check_out_date) {

        api = RetrofitApi.getClient(this).create(APIInterface.class);
        Call<RoomBookingModel> roomBookingModel = api.GetRoomBooking(""+name,""+phone,""+email,""+adults,""+children,""+check_in_date,""+check_out_date);
        roomBookingModel.enqueue(new Callback<RoomBookingModel>() {
            @Override
            public void onResponse(Call<RoomBookingModel> call, Response<RoomBookingModel> response) {

                if (response.body().getHotelRoomBookingModel().get(0).getSuccess().equals("1")) {

//                    twin_fishes_spinner.setVisibility(View.GONE);


                    Toast.makeText(RoomBookingActivity.this, "Room Booking SuccessFully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RoomBookingActivity.this, NavigationActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(RoomBookingActivity.this, "Not Valid Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RoomBookingModel> call, Throwable t) {

                Toast.makeText(RoomBookingActivity.this, "Please Enter Valid Data", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void startPayment() {

        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_fFeq8qJtm3bRKy");
        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            String name = et_name.getText().toString();
            String phone_no = et_phone_no.getText().toString();
            String email_id = et_email_id.getText().toString();
            double room_price = Float.parseFloat(getIntent().getStringExtra("room_price"));
            room_price = room_price*100;

            options.put("name", name);
//            options.put("description", "Reference No. #123456");
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
//            options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", room_price);//pass amount in currency subunits
            options.put("prefill.email", email_id);
            options.put("prefill.contact",phone_no);
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("Error", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        String name = et_name.getText().toString();
        String phone_no = et_phone_no.getText().toString();
        String email_id = et_email_id.getText().toString();

        String adults = et_adults.getText().toString();
        String children = et_children.getText().toString();
        String check_in_date = et_check_in_date.getText().toString();
        String check_out_date = et_check_out_date.getText().toString();

        room_booking_data(name,phone_no,email_id,adults,children,check_in_date,check_out_date);
        Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onExternalWalletSelected(String s, PaymentData paymentData) {
        try{
            alertDialogBuilder.setMessage("External Wallet Selected:\nPayment Data: "+paymentData.getData());
            alertDialogBuilder.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}